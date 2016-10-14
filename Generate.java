/* Author Vidhya */

import java.util.*;

/**
 * The Generator class creates a random sudoku board of 4*4 Blocks
 *
 */

public class Generate {
	private int[][] board;
	private static final int BOARD_WIDTH = 4;
	private static final int BOARD_HEIGHT = 4;
	private static final int MAX_LIMITTER = 5;
	private static final int BOX = 2;
	private int Flag;
	private int FlagSet;

	/* Constructor ,Resets board to zeros */

	private Generate() {
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
	}

	/*
	 * generateBoard method Generates a standard board with Number starting from
	 * 1 to 4 in all the columns
	 */
	private void generateBoard() {
		int value = 1, checker = 1;
		for (int row = 0; row < BOARD_WIDTH; row++) {
			value = checker;
			for (int col = 0; col < BOARD_HEIGHT; col++) {
				if (value <= BOARD_WIDTH) {
					board[row][col] = value;
					value++;
				} else {
					value = 1;
					board[row][col] = value;
					value++;
				}
			}
			checker = value + BOX;
			if (value == MAX_LIMITTER) // only during first row generation
				checker = BOX + 1;
			if (checker > BOARD_WIDTH) // to start the next value (i.e) 1
				checker = (checker % BOARD_WIDTH) + 1;
		}
	}

	/*
	 * inputExchange method has the logic to random generate the Sudoku from the
	 * standard table
	 */

	private void inputExchange(int gen) {

		Random rand = new Random();
		int rowCol = rand.nextInt();
		int sets = rand.nextInt();

		// To pick the Row or Column (even(2) is row and odd (1) is column)
		if (rowCol % 2 == 0) {
			Flag = 2;
			// To pick First Set or Second (Odd(1) is First and Even(2) is
			// Second)
			if (sets % 2 == 0) {
				FlagSet = 2;
			} else {
				FlagSet = 1;
			}

		} else {
			Flag = 1;
			// To pick First Set or Second (Odd(1) is First and Even(2) is
			// Second)
			if (sets % 2 == 0) {
				FlagSet = 2;
			} else {
				FlagSet = 1;
			}
		}

		// System.out.println("Flag " + Flag +" Flagset "+ FlagSet);

	}

	/*
	 * Does the interchanging of Rows/columns according to the logic derived
	 * from the previous method
	 */

	private void interChange() {
		int temp;

		if (Flag == 1) {

			if (FlagSet == 1) {
				for (int row = 0; row < BOARD_WIDTH; row++) {
					temp = board[row][0];
					board[row][0] = board[row][1];
					board[row][1] = temp;
				}

			} else {

				for (int row = 0; row < BOARD_WIDTH; row++) {
					temp = board[row][2];
					board[row][2] = board[row][3];
					board[row][3] = temp;
				}

			}

		} else {

			if (FlagSet == 1) {
				for (int col = 0; col < BOARD_HEIGHT; col++) {
					temp = board[0][col];
					board[0][col] = board[1][col];
					board[1][col] = temp;
				}
			} else {
				for (int col = 0; col < BOARD_HEIGHT; col++) {
					temp = board[2][col];
					board[2][col] = board[3][col];
					board[3][col] = temp;
				}
			}
		}
	}

	/*
	 * makeHoles does the removal of blocks from the Generated sudoku board
	 */
	private void makeHoles() {
		double remainingHoles = BOARD_WIDTH * BOARD_HEIGHT;
		Random rand = new Random();
		for (int row = 0; row < BOARD_WIDTH; row++)
			for (int col = 0; col < BOARD_HEIGHT; col++) {
				if (rand.nextInt() <= remainingHoles) {
					board[row][col] = 0;
					remainingHoles--;
				}
			}

	}

	/*
	 * print method is used to print the sudoku board
	 */

	private void print() {
		System.out.print(" -------- \n");
		for (int row = 0; row < BOARD_WIDTH; row++) {
			System.out.print("|");

			for (int col = 0; col < BOARD_HEIGHT; col++) {
				if (board[row][col] == 0) {
					System.out.print(" " + "|");
				} else {
					System.out.print(board[row][col] + "|");
				}
			}
			System.out.print("\n");
			System.out.print(" -------- \n");

		}

		System.out.println();
	}

	/*
	 * Main Method
	 */

	public static void main(String[] args) {

		Generate obj = new Generate();
		obj.generateBoard();
		// System.out.println("Our Sudoku");
		// obj.print();
		obj.inputExchange(BOARD_WIDTH);
		obj.interChange();
		// System.out.println("Solved Sudoku");
		// g.print();
		obj.makeHoles();
		System.out.println("Solve It!!!");
		obj.print();
	}

}