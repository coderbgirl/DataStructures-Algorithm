package lab;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxHeap {

	private static int size = 0;
	private static int arrayLength = 16;
	private static int userInputSize = 10;
	private static int[] heapList = new int[arrayLength];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter " + userInputSize + " numbers in separate lines without spaces:");
		for (int i = 0; i < userInputSize; i++) {
			String input = scan.nextLine();
			try {
				Integer number = Integer.parseInt(input);
				addToHeap(number);
			} catch (Exception e) {
				System.out.println("Invalid entry. Try again");
				i--;
			}

		}

		System.out.println("\nPrinting output:");
		for (int i = 0; i < userInputSize; i++) {
			System.out.println(removeFromHeap());
		}
	}

	private static void addToHeap(int nextInt) {
		heapList[size] = nextInt;
		int index = size;
		size++;
		while (index > 0 && heapList[index] > heapList[(index - 1) / 2]) {
			swapListItems(index, (index - 1) / 2);
			index = (index - 1) / 2;
		}

	}

	private static Integer removeFromHeap() {
		if (size == 0)
			return null;

		int ret = heapList[0];
		size--;
		heapList[0] = heapList[size];

		int index = 0;

		while (index < size && (index * 2 + 2) < heapList.length
				&& (heapList[index] < heapList[index * 2 + 1] || heapList[index] < heapList[index * 2 + 2])) {
			if (heapList[index * 2 + 2] < heapList[index * 2 + 1]) {
				swapListItems(index, index * 2 + 1);
				index = index * 2 + 1;
			} else {
				swapListItems(index, index * 2 + 2);
				index = index * 2 + 2;
			}
		}

		return ret;

	}

	private static void swapListItems(int index1, int index2) {
		if (index1 >= size || index2 >= size)
			return;
		int temp = heapList[index1];
		heapList[index1] = heapList[index2];
		heapList[index2] = temp;
	}

}
