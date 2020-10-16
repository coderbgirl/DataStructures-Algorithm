
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanCoding {

	static HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	static ArrayList<HuffmanNode> nodeList = new ArrayList<HuffmanNode>();
	static ArrayList<HuffmanNode> freqList = new ArrayList<HuffmanNode>();
	static HashMap<Character, String> codeList = new HashMap<Character, String>();
	static int total = 0;

	public static void main(String[] args) {
		String input = null;
		String output = null;
		String infilepath = null;
		String outfilepath = null;
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println("Enter input directory path for infile.dat:");
			infilepath = scan.nextLine();

			input = readFile(infilepath);

			if (input == null) {
				System.out.println("Invalid directory path. Try again.");
			} else
				break;
		}

		while (true) {
			System.out.println("Enter output directory path for outfile.dat:");
			outfilepath = scan.nextLine();

			output = checkOutputPath(outfilepath);
			if (output == null) {
				System.out.println("Invalid directory path. Try again.");
			} else
				break;
		}

		if (input.equals("")) {
			System.out.println("File is empty.");
			return;
		}

		// Counting all distinct alphanumeric characters
		for (int i = 0; i < input.length(); i++) {
			Character c = input.charAt(i);
			if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
				Integer value = map.get(c);
				if (value == null) {
					map.put(c, 1);
				} else {
					map.put(c, value + 1);
				}
				total++;
			}
		}

		// Creating nodes for each character
		for (Character key : map.keySet()) {
			nodeList.add(new HuffmanNode(key, map.get(key), null, null));
		}

		// Creating the Huffman Tree
		while (nodeList.size() > 1) {
			HuffmanNode minNode1 = findMinNode(nodeList);
			HuffmanNode minNode2 = findMinNode(nodeList);

			if (minNode1.letter != null)
				freqList.add(minNode1);

			if (minNode2.letter != null)
				freqList.add(minNode2);

			nodeList.add(new HuffmanNode(null, minNode1.count + minNode2.count, minNode1, minNode2));
		}

		// Traversing the tree to generate Huffman code
		generateCodes(nodeList.get(0), "");

		// Writing codes to file
		writeAnswer(outfilepath);
	}

	private static String checkOutputPath(String outfilepath) {
		if(outfilepath == null)
			outfilepath = "";
		
		try {
			FileWriter fileWriter = null;
			if (outfilepath.equals(""))
				fileWriter = new FileWriter("outfile.dat");
			else
				fileWriter = new FileWriter(outfilepath + "\\" + "outfile.dat");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write("");
			bufferedWriter.newLine();

			return outfilepath;
		} catch (IOException ex) {
			return null;
		}
	}

	// Writing codes to file
	private static void writeAnswer(String outfilepath) {
		if(outfilepath == null)
			outfilepath = "";
		
		int totalBits = 0;
		for (HuffmanNode h : freqList) {
			for (Character c : codeList.keySet())
				if (h.letter.equals(c)) {
					h.HuffmanCode = codeList.get(c);
				}
		}
		try {
			FileWriter fileWriter = null;
			if (outfilepath.equals(""))
				fileWriter = new FileWriter("outfile.dat");
			else
				fileWriter = new FileWriter(outfilepath + "\\" + "outfile.dat");
			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write("Symbol Frequency");
			bufferedWriter.newLine();
			for (int i = freqList.size() - 1; i >= 0; i--) {
				Float freq = (float) freqList.get(i).count * 100 / total;
				String freqStr = freq.toString();
				int dec = freqStr.indexOf('.');
				if (freqStr.length() - dec > 2)
					freqStr = freqStr.substring(0, dec + 3);
				bufferedWriter.write("  " + freqList.get(i).letter + "       " + freqStr + "%");
				bufferedWriter.newLine();
			}
			bufferedWriter.write("\nSymbol Huffman Codes");
			bufferedWriter.newLine();
			for (int i = freqList.size() - 1; i >= 0; i--) {
				bufferedWriter.write("  " + freqList.get(i).letter + "       " + freqList.get(i).HuffmanCode);
				bufferedWriter.newLine();
			}

			for (int i = freqList.size() - 1; i >= 0; i--) {
				totalBits += freqList.get(i).count * freqList.get(i).HuffmanCode.length();
			}
			bufferedWriter.write("\nTotal Bits: " + totalBits);

			System.out.println("Answer written to outfile.dat");

			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file!");
		}

	}

	// Traversing the tree to generate Huffman code
	private static void generateCodes(HuffmanNode HuffmanNode, String HuffmanCode) {
		if (HuffmanNode.letter == null) {
			generateCodes(HuffmanNode.left, HuffmanCode + "0");
			generateCodes(HuffmanNode.right, HuffmanCode + "1");
		} else {
			codeList.put(HuffmanNode.letter, HuffmanCode);
		}
	}

	// Finds the minimum frequency node from nodelist and removes it
	private static HuffmanNode findMinNode(ArrayList<HuffmanNode> nodeList) {
		HuffmanNode minNode = null;
		for (int i = 0; i < nodeList.size(); i++) {
			if (minNode == null || minNode.count > nodeList.get(i).count) {
				minNode = nodeList.get(i);
			}
		}
		nodeList.remove(minNode);
		return minNode;
	}

	// Reads input data file
	private static String readFile(String filepath) {
		if (filepath == null)
			filepath = "";
		String ret = "";
		String line;
		try {
			FileReader fr = null;
			if (filepath.equals(""))
				fr = new FileReader("infile.dat");
			else
				fr = new FileReader(filepath + "\\" + "infile.dat");

			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null)
				ret += line;

			br.close();
			return ret;
		} catch (Exception e) {
			return null;
		}
	}
}

// Class to create node objects of Huffman tree
class HuffmanNode {
	Integer count;
	Character letter;
	HuffmanNode left = null;
	HuffmanNode right = null;
	String HuffmanCode = null;

	public HuffmanNode(Character letter, Integer count, HuffmanNode left, HuffmanNode right) {
		this.count = count;
		this.letter = letter;
		this.left = left;
		this.right = right;
	}
}
