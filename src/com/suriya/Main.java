package com.suriya;

import java.util.Scanner;

public class Main {
	private static int MAX_LENGHT = 3;

	static int[][] inputBoard = new int[MAX_LENGHT][MAX_LENGHT];

	public static void main(String[] args) {
		Board board = new Board();
		fillBoard();
		checkResult(board);
	}

	private static void fillBoard() {
		try (Scanner sc = new Scanner(System.in)) {

			for (int i = 0; i < MAX_LENGHT; i++) {
				for (int j = 0; j < MAX_LENGHT; j++) {
					inputBoard[i][j] = sc.nextInt();
				}
			}
		} catch (Exception e) {
			System.exit(0);
		}
	}

	private static void checkResult(Board board) {
		board.setBoard(inputBoard);
		System.out.printf("\nOutput : %s", board.checkWin());
	}

}
