package com.suriya;

public class Board {
	private static int MAX_LENGHT = 3;
	private int X = 1, O = 2;
	private String nullResult = "Null", drawResult = "Draw";
	private int[][] board = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

	public String getWinner() {
		boolean xWin = checkWin(X);
		boolean oWin = checkWin(O);

		if (isFull() && (!xWin) && !oWin) {
			return drawResult;
		}
		if (xWin && oWin) {
			return nullResult;
		}
		if (xWin) {
			return "X Win";
		}
		if (oWin) {
			return "O Win";
		}
		return nullResult;
	}

	public void setBoard(int[][] _board) {
		board = _board;
	}

	public boolean isFull() {
		for (int i = 0; i < MAX_LENGHT; i++) {
			for (int j = 0; j < MAX_LENGHT; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkWin(int player) {
		if (checkRows(player) || checkColumns(player) || checkDiagonals(player)) {
			return true;
		}
		return false;
	}

	private boolean checkRows(int player) {
		for (int i = 0; i < MAX_LENGHT; i++) {
			if (checkEachrowcol(board[i][0], board[i][1], board[i][2], player)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumns(int player) {
		for (int i = 0; i < MAX_LENGHT; i++) {
			if (checkEachrowcol(board[0][i], board[1][i], board[2][i], player)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonals(int player) {
		if (checkEachrowcol(board[0][0], board[1][1], board[2][2], player)
				|| checkEachrowcol(board[0][2], board[1][1], board[2][0], player)) {
			return true;
		}
		return false;
	}

	private boolean checkEachrowcol(int pos1, int pos2, int pos3, int player) {
		if ((pos1 == pos2) && (pos2 == pos3) && (pos1 == player)) {
			return true;
		}
		return false;
	}
}
