package lab;
import java.util.LinkedList;
import java.util.Scanner;

public class CircularQueue {

	static LinkedList<String> cQueue = new LinkedList();
	static int start = -1;
	static int end = -1;
	static boolean isFull = false;
	static final int MAX_QUEUE_SIZE = 12;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input;

		System.out.println("Enter queue strings:. or Enter Quit" );

		while (true) {
			input = scan.nextLine();

			if (input.toLowerCase().equals("quit")) {
				dequeue();
				return;
			}

			enqueue(input);
		}
	}

	private static void enqueue(String input) {
		if (start == -1) {
			start = 0;
			end = 1;
			cQueue.add(input);
			return;
		}
		if (!isFull) {
			cQueue.add(input);
			end++;
			if (end == MAX_QUEUE_SIZE) {
				isFull = true;
			}
		} else {
			end++;
			if (end >= MAX_QUEUE_SIZE)
				end = 0;
			start = end + 1;
			if (start == MAX_QUEUE_SIZE)
				start = 0;
			cQueue.set(end, input);
		}

	}

	private static void dequeue() {
		if (start < 0) {
			System.out.println("Queue empty. Nothing to print");
			return;
		}
		
		System.out.println("Dequeuing:");
		while (cQueue.size() > 0) {
			System.out.println(cQueue.remove(start));
			if (start == cQueue.size())
				start = 0;
		}
	}
}


