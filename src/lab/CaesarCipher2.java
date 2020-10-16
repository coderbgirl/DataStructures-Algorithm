package lab;

public class CaesarCipher2 {

	public static void main(String[] args) {
		

		String s = "Htsnyhcdjwlevbah! Pfl zxo afsb dwusb srnsyz!";

		int key = 3;
		
		for (int i = 0; i < s.length(); i++) {
			if (i % 3 == 0)
				key += 2;

			key %= 26;

			int c = (int) s.charAt(i);

			if (c >= 65 && c <= 90) {
				if (c - key < 65)
					c += 26;
				c -= key;

			}

			if (c >= 97 && c <= 122) {
				if (c - key < 97)
					c += 26;
				c -= key;

			}
			System.out.print((char)c);
			
		}

	}
}
