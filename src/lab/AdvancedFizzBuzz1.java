package lab;

public class AdvancedFizzBuzz1 {

	public static void main(String[] args) {
		int low = 10;
		int high = 250;
		//int i = 0;
		int[] array = new int[high - low + 1];
		for (int i = low; i <= high; i++) {
			array[i++] = i;
			//i++;
		}

		fizzbuzzer(array);

	}

	public static void fizzbuzzer(int[] array) {

		int i = 0;
		while (i < array.length) {
			
			if (array[i] % 3 == 0 && array[i] % 5 == 0) {
				System.out.println("FizzBuzz");
			} else if (array[i] % 5 == 0) {
				System.out.println("Fizz");
			} else if (array[i] % 3 == 0) {
				System.out.println("Buzz");
			} else {
				System.out.println(array[i]);
			}
			i++;
		}
	}
}
