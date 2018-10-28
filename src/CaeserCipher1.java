import java.util.Scanner;

public class CaeserCipher1 {

	public static void main(String[] args) {

		// String s = "BHUMIaKZ";

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the string:");
		String s = scan.nextLine();

		System.out.println("Enter cipher code:");
		int cipherCode = scan.nextInt();
		
		char[] b = s.toCharArray();

		for (int i = 0; i < s.length(); i++) {

			if (b[i] >= 'A' && b[i] <= 'Z') {
				b[i] = (char) ('A' + (b[i] - 'A' + cipherCode) % 26);

			} else if (b[i] >= 'a' && b[i] <= 'z') {
				b[i] = (char) ('a' + (b[i] - 'a' + cipherCode) % 26);
			}
			System.out.print(b[i]);

		}

	}
}
