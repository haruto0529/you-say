package com.example.you_say_app.model.connect4;

import java.util.ArrayList;
import java.util.Collections;

public class Connect4 {
	private String message;
	private Board board;
	private Player player1;
	private Player player2;
	private int turn = 1;
	private boolean GameOver = false;
	private static int winNumber = 4;

	public Connect4(int color1, boolean isHuman1, int color2, boolean isHuman2) {
		this.board = new Board();
		this.player1 = new Player(color1, isHuman1);
		this.player2 = new Player(color2, isHuman2);
		this.message = player1.isHuman() ? "プレイヤー1のターン" : "コンピュータのターン";

	}

	public void dropPiece(int column) {
		if (!board.checkFull()) {
			int[][] board = this.board.getBoard();
			// 案：playerを渡さず、turn から内部で決定する
			Player player = (turn == 1) ? player1 : player2;
			Player enemy = (turn == 1) ? player2 : player1;

			int col = player.decideColumn(this.board, column);

			if (col == -1) {
				if (enhance(player.getPieceColor()) != -1) {
					col = enhance(player.getPieceColor());
				} else if (enhance(enemy.getPieceColor()) != -1) {
					col = enhance(enemy.getPieceColor());
				} else {
					ArrayList<Integer> list = this.board.getCanChooseColumn();
					Collections.shuffle(list);
					col = list.get(0);
				}
			}

			int i = 0;
			for (i = 0; i <= 5; i++) {
				if (board[i][col] == 0) {
					board[i][col] = player.getPieceColor();
					break;
				}
			}
			if (checkWin(player.getPieceColor(), col, i)) {
				GameOver = true;

				// 1. まず現在のターンに応じたプレイヤー（勝者）と対戦相手を特定
				Player winner = (turn == 1) ? player1 : player2;
				Player loser = (turn == 1) ? player2 : player1;

				// 2. 対戦パターンの組み合わせでメッセージを決定
				if (winner.isHuman() && !loser.isHuman()) {
					// 人間がCPUに勝った場合
					this.message = "YOU WIN！チャッピーよりユッピー";
				} else if (!winner.isHuman() && winner == player2 && !player1.isHuman()) {
					// ターン2（player2）がCPUで、player1もCPUの場合（CPU同士の対戦でplayer2が勝った）
					this.message = "コンピューター2の勝ち";
				} else if (winner.isHuman()) {
					// 人間同士の対戦などで、人間が勝った場合
					this.message = (turn == 1) ? "プレイヤー1の勝ち" : "プレイヤー2の勝ち";
				} else {
					// それ以外のCPUの勝ち（1PモードでCPUが勝った場合など）
					this.message = "YOU LOSE！ユッピーよりチャッピー";
				}

			} else {
				if (this.board.checkFull(col)) {
					this.board.getCanChooseColumn().remove(this.board.getCanChooseColumn().indexOf(col));
				}
				changeTurn();
			}
		} else {
			this.message = "引き分け";
			GameOver = true;
		}
	}

	private int enhance(int color) {
		int[][] board = getBoard();
		int[] frame = new int[4];

		// ==========================================
		// 1. 横の判定
		// ==========================================
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j <= board[i].length - 4; j++) {
				int number = 0;
				int space = 0;
				int spaceColumn = -1;
				for (int n = 0; n < frame.length; n++) {
					frame[n] = board[i][j + n];
					if (frame[n] == color) {
						number++;
					} else if (frame[n] == 0) {
						space++;
						spaceColumn = j + n;
					} else {
						break;
					}
				}
				if (number == winNumber - 1 && space == 1) {
					if (i == 0) {
						return spaceColumn;
					} else {
						if (board[i - 1][spaceColumn] != 0) {
							return spaceColumn;
						}
					}
				}
			}
		}

		// ==========================================
		// 2. 縦の判定
		// ==========================================
		for (int i = 0; i <= board.length - 4; i++) {
			for (int j = 0; j < board[i].length; j++) {
				int number = 0;
				int space = 0;
				for (int n = 0; n < frame.length; n++) {
					frame[n] = board[i + n][j];
					if (frame[n] == color) {
						number++;
					} else if (frame[n] == 0) {
						space++;
					}
				}
				if (space == 1 && number == winNumber - 1) {
					return j;
				}
			}
		}

		// ==========================================
		// 3. 右斜めの判定（前半：右側・上側のライン）
		// ==========================================
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j <= 2; j++) {
				// 枠の終端（n=3のとき）が盤面（縦6行、横7列）を飛び出さないかチェック
				if (3 + j >= 6 || i + 3 + j >= 7) {
					continue;
				}
				int number = 0;
				int space = 0;
				int spaceColumn = -1;
				int spaceRow = -1;
				for (int n = 0; n < frame.length; n++) {
					frame[n] = board[n + j][i + n + j];
					if (frame[n] == color) {
						number++;
					} else if (frame[n] == 0) {
						space++;
						spaceColumn = i + n + j;
						spaceRow = n + j;
					} else {
						break;
					}
				}
				if (space == 1 && number == winNumber - 1) {
					if (spaceRow == 0) {
						return spaceColumn;
					} else {
						if (board[spaceRow - 1][spaceColumn] != 0) {
							return spaceColumn;
						}
					}
				}
			}
		}

		// ==========================================
		// 4. 右斜めの判定（後半：左側・下側のライン）
		// ==========================================
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 3; j++) {
				// 枠の終端（n=3のとき）が盤面（縦6行、横7列）を飛び出さないかチェック
				if (i + 3 + j >= 6 || 3 + j >= 7) {
					continue;
				}
				int number = 0;
				int space = 0;
				int spaceColumn = -1;
				int spaceRow = -1;
				for (int n = 0; n < frame.length; n++) {
					frame[n] = board[i + n + j][n + j];
					if (frame[n] == color) {
						number++;
					} else if (frame[n] == 0) {
						space++;
						spaceColumn = n + j;
						spaceRow = i + n + j;
					} else {
						break;
					}
				}
				if (space == 1 && number == winNumber - 1) {
					if (spaceRow == 0) {
						return spaceColumn;
					} else {
						if (board[spaceRow - 1][spaceColumn] != 0) {
							return spaceColumn;
						}
					}
				}
			}
		}

		// ==========================================
		// 5. 左斜めの判定（前半：左側・上側のライン）
		// ==========================================
		for (int i = 5; i >= 3; i--) {
			for (int j = 0; j <= 2; j++) {
				// 枠の終端（n=3のとき）が盤面からはみ出さないかチェック
				if (3 + j >= 6 || i - 3 - j < 0) {
					continue;
				}
				int number = 0;
				int space = 0;
				int spaceColumn = -1;
				int spaceRow = -1;
				for (int n = 0; n < frame.length; n++) {
					frame[n] = board[j + n][i - n - j];
					if (frame[n] == color) {
						number++;
					} else if (frame[n] == 0) {
						space++;
						spaceColumn = i - n - j;
						spaceRow = j + n;
					} else {
						break;
					}
				}
				if (space == 1 && number == winNumber - 1) {
					if (spaceRow == 0) {
						return spaceColumn;
					} else {
						if (board[spaceRow - 1][spaceColumn] != 0) {
							return spaceColumn;
						}
					}
				}
			}
		}

		// ==========================================
		// 6. 左斜めの判定（後半：右側・下側のライン）
		// ==========================================
		for (int i = 6; i >= 4; i--) {
			for (int j = 0; j <= 2; j++) {
				// 枠の終端（n=3のとき）が盤面からはみ出さないかチェック
				if ((6 - i) + 3 + j >= 6 || 6 - 3 - j < 0) {
					continue;
				}
				int number = 0;
				int space = 0;
				int spaceColumn = -1;
				int spaceRow = -1;
				for (int n = 0; n < frame.length; n++) {
					frame[n] = board[(6 - i) + j + n][6 - j - n];
					if (frame[n] == color) {
						number++;
					} else if (frame[n] == 0) {
						space++;
						spaceColumn = 6 - j - n;
						spaceRow = (6 - i) + j + n;
					} else {
						break;
					}
				}
				if (space == 1 && number == winNumber - 1) {
					if (spaceRow == 0) {
						return spaceColumn;
					} else {
						if (board[spaceRow - 1][spaceColumn] != 0) {
							return spaceColumn;
						}
					}
				}
			}
		}

		// どこにも置くべき場所がない（リーチがない）場合
		return -1;
	}

	private boolean checkWin(int color, int column, int row) {
		//		横のチェック
		int checkNumber = 1;
		int[][] board = this.board.getBoard();

		//			右側
		for (int i = column + 1; i <= 6; i++) {
			if (board[row][i] == color) {
				checkNumber++;
			} else {
				break;
			}
		}
		//左側
		for (int i = column - 1; i >= 0; i--) {
			if (board[row][i] == color) {
				checkNumber++;
			} else {
				break;
			}
		}
		if (checkNumber >= 4) {
			return true;
		} else {
			checkNumber = 1;
		}
		//		縦のチェック
		//		上
		for (int i = row + 1; i <= 5; i++) {
			if (board[i][column] == color) {
				checkNumber++;
			} else {
				break;
			}
		}
		//		下
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][column] == color) {
				checkNumber++;
			} else {
				break;
			}
		}
		if (checkNumber >= 4) {
			return true;
		} else {
			checkNumber = 1;
		}

		//		斜め
		//		左上
		int n = 1;
		while (0 <= column - n && row + n <= 5) {
			if (board[row + n][column - n] == color) {
				checkNumber++;
			} else {
				break;
			}
			n++;
		}
		n = 1;
		//		右下
		while (column + n <= 6 && row - n >= 0) {
			if (board[row - n][column + n] == color) {
				checkNumber++;
			} else {
				break;
			}
			n++;
		}
		if (checkNumber >= 4) {
			return true;
		} else {
			checkNumber = 1;
			n = 1;
		}
		//		右上
		while (column + n <= 6 && row + n <= 5) {
			if (board[row + n][column + n] == color) {
				checkNumber++;
			} else {
				break;
			}
			n++;
		}
		n = 1;
		//		左下
		while (0 <= column - n && row - n >= 0) {
			if (board[row - n][column - n] == color) {
				checkNumber++;
			} else {
				break;
			}
			n++;
		}
		if (checkNumber >= 4) {
			return true;
		} else {
			return false;
		}
	}

	private void changeTurn() {
		turn = (turn == 1) ? 2 : 1;
		if (turn == 1) {
			this.message = player1.isHuman() ? "プレイヤー1のターン" : "コンピュータのターン";
		} else {
			this.message = player2.isHuman() ? "プレイヤー2のターン" : "コンピュータのターン";
		}
	}

	public String getMessage() {
		return message;
	}

	public boolean isGameOver() {
		return GameOver;
	}

	public int[][] getBoard() {
		return board.getBoard();
	}

	public boolean isComputerTurn() {
		if (GameOver)
			return false;
		return (turn == 1) ? !player1.isHuman() : !player2.isHuman();
	}

	public boolean isVsComputer() {
		return !player1.isHuman() || !player2.isHuman();
	}

}
