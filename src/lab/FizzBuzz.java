package lab;
import java.util.Scanner;

public class FizzBuzz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * for (int i = 1; i <= 100; i++) { if (i % 3 == 0 && i % 5 == 0) {
		 * System.out.println("Fizzbuzz"); } else if (i % 3 == 0) {
		 * System.out.println("Fizz"); } else if (i % 5 == 0) {
		 * System.out.println("Buzz"); } else { System.out.println(i); } }
		 */

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number");
		int a = scan.nextInt();

		if (a % 3 != 0 && a % 5 != 0) {
			System.out.println(a);
		} else if (a % 3 == 0) {
			System.out.println("Fizz");
		} else if (a % 5 == 0) {
			System.out.println("Buzz");

		}

	}
}
