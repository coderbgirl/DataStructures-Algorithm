package lab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class app {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		SortedSet sortedset = new SortedSet();

		try {
			String line;

			FileReader fr = null;
			fr = new FileReader("infile.dat");

			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				String[] array = line.split("\\D+");
				int[] integers = new int[array.length];
				for (int i = 0; i < integers.length; i++) {
					if (array[i].equals("") || array[i].equals(" "))
						continue;
					integers[i] = Integer.parseInt(array[i]);
					sortedset.add(integers[i]);
				}
			}
			if (sortedset.isEmpty()) {
				System.out.println("Set is empty.");
				return;
			}
			br.close();
		} catch (Exception ex) {
			System.out.println("Unable to open file. or Error reading file");
		}

		System.out.println("Enter the number:");
		int number = scan.nextInt();
		if (sortedset.contains(number) == true)
			System.out.println("Yes");

		else
			System.out.println("No");
	}
}
