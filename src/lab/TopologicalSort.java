package lab;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class TopologicalSort {

	static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	static HashMap<Integer, Integer> nodeTopoNums = new HashMap<Integer, Integer>();
	static Boolean isFirstAttempt = true;

	public static void main(String[] args) {
		if (!readGraph()) {
			System.out.println("Error reading infile.dat or invalid data in file");
			return;
		}

		if (!generateTopoNum()) {
			return;
		}
		
		System.out.println("Topological Ordering Solution 1:");
		printTopoNum();

		flush();

		readGraph();
		generateTopoNum();
		System.out.println("\nTopological Ordering Solution 2:");
		printTopoNum();
	}

	private static void flush() {
		graph = new HashMap<Integer, ArrayList<Integer>>();
		nodeTopoNums = new HashMap<Integer, Integer>();
	}

	private static void printTopoNum() {
		System.out.println("Node   Topological Number");
		for (Integer i : nodeTopoNums.keySet()) {
			System.out.println(i + "      " + nodeTopoNums.get(i));
		}
	}

	private static boolean readGraph() {
		try {
			FileReader fr = new FileReader("infile.dat");
			BufferedReader br = new BufferedReader(fr);
			String[] inputs = br.readLine().split(" ");
			Integer nodeCount = Integer.parseInt(inputs[0]);
			Integer edgeCount = Integer.parseInt(inputs[1]);

			for (int i = 0; i < edgeCount; i++) {
				String edge = br.readLine();
				Integer src = Integer.parseInt(edge.split(" ")[0]);
				Integer dest = Integer.parseInt(edge.split(" ")[1]);

				if (graph.get(dest) == null) {
					graph.put(dest, new ArrayList<Integer>());
				}
				if (graph.get(src) == null) {
					graph.put(src, new ArrayList<Integer>());
				}
				ArrayList<Integer> sources = graph.get(dest);
				if (!sources.contains(src))
					sources.add(src);
			}

			br.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private static boolean generateTopoNum() {
		int topo = 0;
		while (!isGraphEmpty()) {
			Integer node = findZeroIndegreeNode();
			if (node == null) {
				System.out.println("Graph is not acyclic or only 1 solution possible! \nTerminating Program!");
				return false;
			}
			topo++;
			deleteNode(node);
			setTopoNum(node, topo);
		}
		return true;
	}

	private static void setTopoNum(Integer node, int topo) {
		nodeTopoNums.put(node, topo);
	}

	private static void deleteNode(Integer node) {
		graph.remove(node);
		for (Integer i : graph.keySet()) {
			ArrayList<Integer> array = graph.get(i);
			array.remove(node);
		}
	}

	private static Integer findZeroIndegreeNode() {
		if (graph.size() == 0)
			return null;

		for (Integer i : graph.keySet()) {
			ArrayList<Integer> array = graph.get(i);
			if (array == null || array.size() == 0) {
				if (isFirstAttempt) {
					isFirstAttempt = false;
					continue;
				}
				return i;
			}
		}

		return null;
	}

	private static boolean isGraphEmpty() {
		if (graph.size() == 0)
			return true;
		return false;
	}
}
