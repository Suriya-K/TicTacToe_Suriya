package com.suriya;

public class Board {
	private static int MAX_LENGHT = 3;
	private int X = 1, O = 2;
	private String player = "";
	private int[][] board = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

	public String checkWin() {
		if (checkRows() || checkColumns() || checkDiagonals()) {
			return player;
		}
		return "Draw";
	}

	public void setBoard(int[][] _board) {
		board = _board;
	}

	private boolean checkRows() {
		for (int i = 0; i < MAX_LENGHT; i++) {
			if (checkEachrowcol(board[i][0], board[i][1], board[i][2]))
				return true;
		}
		return false;
	}

	private boolean checkColumns() {
		for (int i = 0; i < MAX_LENGHT; i++) {
			if (checkEachrowcol(board[0][i], board[1][i], board[2][i]))
				return true;
		}
		return false;
	}

	private boolean checkDiagonals() {
		if (checkEachrowcol(board[0][0], board[1][1], board[2][2])
				|| checkEachrowcol(board[0][2], board[1][1], board[2][0])) {
			return true;
		}
		return false;
	}

	private boolean checkEachrowcol(int pos1, int pos2, int pos3) {
		if (checkEachrowcol(pos1, pos2, pos3) && (pos1 == X)) {
			player = "X Win";
		}
		if (checkEachrowcol(pos1, pos2, pos3) && (pos1 == O)) {
			player = "O Win";
		}
		return ((pos1 != 0) && (pos1 == pos2) && (pos2 == pos3));
	}
}
