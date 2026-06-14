package com.example.you_say_app.model.connect4;

import java.util.ArrayList;

public class Board {
	private int[][] board = new int[6][7];
	private ArrayList<Integer> canChooseColumn = new ArrayList<>();

	public Board() {
		for (int i = 0; i <= 6; i++) {
			canChooseColumn.add(i);
		}
	}

	
	public boolean checkFull(int column) {
	    return board[5][column] != 0;
	}

	public boolean checkFull() {
	    return getCanChooseColumn().isEmpty();
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public ArrayList<Integer> getCanChooseColumn() {
		return canChooseColumn;
	}

	

}
