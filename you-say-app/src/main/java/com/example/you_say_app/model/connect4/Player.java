package com.example.you_say_app.model.connect4;

public class Player {
	private int pieceColor;
	private boolean isHuman;

	public Player(int pieceColor, boolean isHuman) {
		super();
		this.pieceColor = pieceColor;
		this.isHuman = isHuman;
	}

	public int getPieceColor() {
		return pieceColor;
	}

	public boolean isHuman() {
		return isHuman;
	}

	public int decideColumn(Board board, int column) {
		if (this.isHuman()) {
			return column;
		} else {
			return -1;
		}
	}
}
