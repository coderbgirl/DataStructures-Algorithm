package Assignment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tic_Tac_Toe {

	static Integer players = 0;
	static Integer size = 0;
	static Integer winCriteria = 0;
	static Character grid[][] = new Character[999][999];
	static String s = "XOABCDEFGHIKLMNPQRSTUVWYZ";
	static Integer currPlayer = 0;

	public static void main(String[] args) {
		// This is the main function that takes input from users
		int r, c;
		String saved, input, file;
		boolean gameLoaded = false;
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println("Load saved game? (Y/N)");
			saved = scan.nextLine();
			saved = saved.toLowerCase();

			if (saved.equals("y") || saved.equals("yes")) {
				System.out.println("Enter filename:");
				file = scan.nextLine();
				if (loadGame(file)) {
					System.out.println("Game loaded successfully!");
					gameLoaded = true;
				} else {
					System.out.println("Error loading game. Try again.");
					continue;
				}
				break;
			} else if (saved.equals("n") || saved.equals("no"))
				break;
			else
				System.out.println("Invalid entry. Try again.");
		}

		if (!gameLoaded) {
			while (true) {
				System.out.println("Enter number of players (min 2, max 26): ");
				scan = new Scanner(System.in);
				if (scan.hasNextInt()) {
					players = scan.nextInt();
					if (players > 1 && players <= 26)
						break;
				}
				System.out.println("Invalid entry. Try again.");
			}
			while (true) {
				System.out.println("Enter number of game board rows (min 3, max 999):");
				scan = new Scanner(System.in);
				if (scan.hasNextInt()) {
					size = scan.nextInt();
					if (size >= 3 && size <= 999)
						break;
				}
				System.out.println("Invalid entry. Try again.");
			}

			while (true) {
				System.out.println("Enter win sequence criteria (min 3, max " + size + "):");
				scan = new Scanner(System.in);

				if (scan.hasNextInt()) {
					winCriteria = scan.nextInt();
					if (winCriteria >= 3 && winCriteria <= size)
						break;
				}
				System.out.println("Invalid entry. Try again.");

			}

			// Initializing grid to blank
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++)
					grid[i][j] = ' ';
		}

		while (!isCriteriaMet()) {

			printBoard();
			currPlayer = currPlayer % players;

			while (true) {
				System.out.println("Player " + s.charAt(currPlayer) + "'s turn. Enter row and column or Q to quit:");
				scan = new Scanner(System.in);
				if (!scan.hasNextInt()) {
					input = scan.nextLine();

					if (input.toLowerCase().equals("q") || input.toLowerCase().equals("quit")) {
						System.out.println("Enter filename to save game:");
						file = scan.nextLine();
						if (saveGame(file)) {
							System.out.println("Game saved! Goodbye!");
							scan.close();
							return;
						} else {
							System.out.println("Unable to save game. Continue to play? (Y/N)");
							input = scan.nextLine();
							input = input.toLowerCase();

							if (input.equals("y") || input.equals("yes")) {
								continue;
							} else if (input.equals("n") || input.equals("no")) {
								scan.close();
								return;
							}
						}
					} else
						System.out.println("Invalid entry. Try again.");
				} else {
					r = scan.nextInt();
					if (!scan.hasNextInt()) {
						System.out.println("Invalid entry. Try again.");
						continue;
					}
					c = scan.nextInt();
					r--;
					c--;
					if (r >= size || r < 0 || c >= size || c < 0) {
						System.out.println("Out of bounds entry!");
						continue;
					} else if (grid[r][c] != ' ') {
						System.out.println("Cell already used!");
						continue;
					} else
						break;
				}
			}

			grid[r][c] = s.charAt(currPlayer);
			currPlayer++;

		}
		scan.close();
	}

	public static void printBoard() {
		// Prints the game board
		int i = 0;
		int j = 0;
		int k = 0;

		System.out.print("    ");

		for (i = 0; i < size; i++) {
			if (i < 9)
				System.out.print(" " + (i + 1) + "  ");
			else if (i < 99)
				System.out.print(" " + (i + 1) + " ");
			else
				System.out.print((i + 1) + " ");
		}

		for (i = 0; i < size; i++) {
			System.out.printf("\n%3d ", i + 1);
			for (j = 0; j < size - 1; j++) {
				System.out.print(" " + grid[i][j] + " |");
			}
			System.out.print(" " + grid[i][j] + " \n    ");
			if (i < size - 1) {
				for (k = 0; k < size - 1; k++) {
					System.out.print("---+");
				}
				System.out.print("---");
			}
		}
		System.out.println();
	}

	public static boolean isCriteriaMet() {
		// Checks if someone has won or if there are no valid moves possible
		int i, j;
		int sumEmpty = 0;

		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				char letter = grid[i][j];

				if (letter == ' ')
					continue;

				if ((sumHorizontal(i, j + 1, letter) >= winCriteria) || (sumVertical(i + 1, j, letter) >= winCriteria)
						|| (sumLeftDiagonal(i + 1, j - 1, letter) >= winCriteria)
						|| (sumRightDiagonal(i + 1, j + 1, letter) >= winCriteria)) {
					printBoard();
					System.out.println("~~~Player " + letter + " wins!~~");
					return true;
				}
			}
		}

		for (i = 0; i < size; i++)
			for (j = 0; j < size; j++)
				if (grid[i][j] == ' ')
					sumEmpty++;

		if (sumEmpty == 0) {
			printBoard();
			System.out.println("Nobody wins. It's a Tie.");
			return true;
		}

		return false;
	}

	private static int sumRightDiagonal(int i, int j, char letter) {
		// Returns sum of same letter going diagonally right-down
		int sum = 1;

		while (i < size && j < size) {
			if (grid[i][j] == letter) {
				sum++;
				i++;
				j++;
			} else
				break;
		}
		return sum;
	}

	private static int sumLeftDiagonal(int i, int j, char letter) {
		// Returns sum of same letter going diagonally left-down
		int sum = 1;

		while (i < size && j >= 0) {
			if (grid[i][j] == letter) {
				sum++;
				i++;
				j--;
			} else
				break;
		}
		return sum;
	}

	private static int sumVertical(int i, int j, char letter) {
		// Returns sum of same letter going vertically down
		int sum = 1;

		while (i < size) {
			if (grid[i][j] == letter) {
				sum++;
				i++;
			} else
				break;
		}
		return sum;
	}

	private static int sumHorizontal(int i, int j, char letter) {
		// Returns sum of same letter going horizontally right
		int sum = 1;

		while (j < size) {
			if (grid[i][j] == letter) {
				sum++;
				j++;
			} else
				break;
		}
		return sum;
	}

	public static boolean saveGame(String file) {
		// Tries to save the game to a file
		try {
			FileWriter fileWriter = new FileWriter(file);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(players.toString());
			bufferedWriter.newLine();
			bufferedWriter.write(size.toString());
			bufferedWriter.newLine();
			bufferedWriter.write(winCriteria.toString());
			bufferedWriter.newLine();
			bufferedWriter.write(currPlayer.toString());
			bufferedWriter.newLine();

			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++)
					bufferedWriter.write(grid[i][j]);

			bufferedWriter.close();
			return true;
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + file + "'");
		}
		return false;
	}

	public static boolean loadGame(String file) {
		// Tries to load the game from saved file
		String line = null;

		try {
			FileReader fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			if ((line = bufferedReader.readLine()) != null)
				players = Integer.parseInt(line);

			if ((line = bufferedReader.readLine()) != null)
				size = Integer.parseInt(line);

			if ((line = bufferedReader.readLine()) != null)
				winCriteria = Integer.parseInt(line);

			if ((line = bufferedReader.readLine()) != null)
				currPlayer = Integer.parseInt(line);

			if ((line = bufferedReader.readLine()) != null) {
				int k = 0;
				for (int i = 0; i < size; i++)
					for (int j = 0; j < size; j++)
						grid[i][j] = line.charAt(k++);
			}

			bufferedReader.close();
			return true;
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + file + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + file + "'");
		}

		return false;
	}

}
