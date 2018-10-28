import java.util.Scanner;

public class AdvancedFizzBuzz {

	public static void main(String[] args) {

		int n;
		int a;
		int  b;
		Scanner scan = new Scanner(System.in);

		do {
			System.out.println("Enter the value of n:");
			n = scan.nextInt();
			if (n > 1) {
			} else {
				System.out.println("Invalid value of n.");
			}
		} while (!(n > 1));

		do {
			System.out.println("Enter the value of a:");
			a = scan.nextInt();
			if (a>1 && a<n) {
			} else {
				System.out.println("Invalid value of a.");
			}
		} while (!(a>1 && a<n));
		
		do {
			System.out.println("Enter the value of b:");
			b = scan.nextInt();
			if (b>1 && b<n && a!=b) {
			} else {
				System.out.println("Invalid value of b.");
			}
		} while (!(b>1 && b<n && a!=b));

		

		for (int i = 1; i <= n; i++) {
			if (i % a == 0 && i % b == 0) {
				System.out.println("FizzBuzz");
			} else if (i % a == 0) {
				System.out.println("Fizz");
			} else if (i % b == 0) {
				System.out.println("Buzz");
			} else {
				System.out.println(i);
			}

		}

	}
}
