package Assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = null;
		String postfixform = null;
		Double value;
		String[] infixElements = null;
		String[] postfixElements = null;

		while (true) {
			System.out.println("Enter valid mathematical expression in infix form:");
			input = scan.nextLine();
			input = input.toUpperCase();

			if (input.equals("QUIT")) {
				System.out.println("Goodbye!");
				return;
			}

			infixElements = expressionSplitter(input);
			if (infixElements == null) {
				System.out.println("Invalid input! Try again.");
				continue;
			}

			postfixform = infixToPostfix(infixElements);
			if (postfixform == null) {
				System.out.println("Invalid input! Try again.");
				continue;
			}

			postfixElements = expressionSplitter(postfixform);

			value = calculateValue(postfixElements);
			if (value == null) {
				continue;
			} else {
				postfixform = postfixform.replaceAll("\\^", "POW");
				System.out.println("Postfix form: " + postfixform);
				System.out.println("Value = " + value);
			}
		}
	}

	private static String[] expressionSplitter(String input) {
		ArrayList<String> elements = new ArrayList<String>();
		String currentNumber = null;

		for (int i = 0; i < input.length(); i++) {
			Character c = input.charAt(i);
			switch (c) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
				if (currentNumber != null)
					currentNumber += c.toString();
				else
					currentNumber = c.toString();
				break;
			case '+':
			case '-':
			case '/':
			case '*':
			case '%':
			case '^':
			case '(':
			case ')':
				if (currentNumber != null)
					elements.add(currentNumber);
				currentNumber = null;

				elements.add(c.toString());
				break;
			case 'P':
				if (currentNumber != null)
					elements.add(currentNumber);
				currentNumber = null;

				if (i + 2 < input.length() && input.charAt(i + 1) == 'O' && input.charAt(i + 2) == 'W') {
					elements.add("^");
					i += 2;
				} else {
					return null;
				}
				break;
			case ' ':
				if (currentNumber != null)
					elements.add(currentNumber);
				currentNumber = null;
				continue;
			default:
				return null;
			}
		}

		if (currentNumber != null)
			elements.add(currentNumber);

		if (elements.size() <= 0)
			return null;

		String[] elementArray = new String[elements.size()];

		for (int i = 0; i < elements.size(); i++)
			elementArray[i] = elements.get(i);
		return elementArray;
	}

	private static String infixToPostfix(String[] infixElements) {

		String postfix = "";
		Stack<Character> operatorStack = new Stack<Character>();

		for (int i = 0; i < infixElements.length; i++) {

			if (isNumber(infixElements[i])) {
				postfix += infixElements[i];
				postfix += " ";
			} else if (isOperator(infixElements[i])) {
				postfix += handleOperator(infixElements[i], operatorStack);
			} else if (isBrackets(infixElements[i])) {
				postfix += handleBrackets(infixElements[i], operatorStack);
			}
		}

		while (operatorStack.size() != 0) {
			postfix += operatorStack.pop();
			postfix += " ";
		}

		return postfix;
	}

	private static String handleBrackets(String element, Stack<Character> operatorStack) {
		String ret = "";

		if (element.equals("(")) {
			operatorStack.add('(');
			return "";
		} else if (element.equals(")")) {
			while (operatorStack.size() > 0) {
				Character c = operatorStack.pop();
				if (c == '(')
					break;
				ret += c.toString();
				ret += " ";
			}
		}
		return ret;
	}

	private static String handleOperator(String element, Stack<Character> operatorStack) {
		String ret = "";

		while (operatorStack.size() > 0 && operatorStack.peek() != '('
				&& operatorRank(element.charAt(0)) >= operatorRank(operatorStack.peek())) {
			ret += operatorStack.pop();
			ret += " ";
		}
		operatorStack.push(element.charAt(0));

		return ret;
	}

	private static Integer operatorRank(char operator) {

		switch (operator) {
		case '^':
			return 1;
		case '%':
			return 2;
		case '*':
			return 2;
		case '/':
			return 2;
		case '-':
			return 3;
		case '+':
			return 3;
		}
		return null;
	}

	private static boolean isNumber(String element) {
		if (element.charAt(0) >= '0' && element.charAt(0) <= '9')
			return true;
		else
			return false;
	}

	private static boolean isOperator(String element) {
		if (element.compareTo("+") == 0 || element.compareTo("-") == 0 || element.compareTo("*") == 0
				|| element.compareTo("/") == 0 || element.compareTo("%") == 0 || element.compareTo("^") == 0)
			return true;
		else
			return false;
	}

	private static boolean isBrackets(String element) {
		if (element.compareTo("(") == 0 || element.compareTo(")") == 0)
			return true;
		else
			return false;
	}

	public static Double calculateValue(String[] postfixElements) {

		Stack<Double> numStack = new Stack<>();

		for (int i = 0; i < postfixElements.length; i++) {
			if (isNumber(postfixElements[i])) {
				numStack.push(Double.parseDouble((postfixElements[i])));
			} else if (isOperator(postfixElements[i])) {
				if (numStack.size() < 2) {
					System.out.println("Invalid input. Please try again.");
					return null;
				}
				Double A = numStack.pop();
				Double B = numStack.pop();
				switch (postfixElements[i].charAt(0)) {
				case '+':
					numStack.push(A + B);
					break;
				case '-':
					numStack.push(B - A);
					break;
				case '*':
					numStack.push(A * B);
					break;
				case '/':
					if (A.equals(0.0)) {
						System.out.println("Divide by zero exception. Try valid input.");
						return null;
					}
					numStack.push(B / A);
					break;
				case '%':
					if (A.equals(0.0)) {
						System.out.println("Modulo of zero exception. Try valid input.");
						return null;
					}
					numStack.push(B % A);
					break;
				case '^':
					numStack.push(Math.pow(B, A));
					break;
				}
			}
		}
		if (numStack.size() == 1) {
			return numStack.pop();
		} else {
			System.out.println("Invalid input. Try valid expression.");
			return null;
		}
	}
}