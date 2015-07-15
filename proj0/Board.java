public class Board {
	private class Slot {
		public boolean isEmpty = true;
		public Piece pieceInSlot = null;
	}
	private Slot[][] slotsOfBoard;
	private boolean fireTurn;
	private boolean selectedPiece;
	private int[] currentSelectedCoordinate; 
	private boolean currentTurnMoved;
	private boolean currentTurnCaptured;

	public Board(boolean shouldBeEmpty) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                StdDrawPlus.filledSquare((i + 0.5)/8, (j + 0.5)/8, 0.5/8);
            }
        }
        slotsOfBoard = new Slot[8][8];
        for (int i = 0; i < 8; i++){
        	for (int j = 0; j < 8; j++) {
        		slotsOfBoard[i][j] = new Slot();
        	}
        }        
        if (!shouldBeEmpty) {
        	for(int i = 0; i < 4; i++){
        		StdDrawPlus.picture((2 * i + 0 + 0.5)/8, (0 + 0.5)/8, "img/pawn-fire.png", 0.125, 0.125);		
        		StdDrawPlus.picture((2 * i + 1 + 0.5)/8, (1 + 0.5)/8, "img/shield-fire.png", 0.125, 0.125);
          		StdDrawPlus.picture((2 * i + 0 + 0.5)/8, (2 + 0.5)/8, "img/bomb-fire.png", 0.125, 0.125);
        		StdDrawPlus.picture((2 * i + 1 + 0.5)/8, (5 + 0.5)/8, "img/bomb-water.png", 0.125, 0.125);   		
        		StdDrawPlus.picture((2 * i + 0 + 0.5)/8, (6 + 0.5)/8, "img/shield-water.png", 0.125, 0.125);
            	StdDrawPlus.picture((2 * i + 1 + 0.5)/8, (7 + 0.5)/8, "img/pawn-water.png", 0.125, 0.125);
           	}
           	fireTurn = true;
           	selectedPiece = false;
           	currentTurnMoved = false;
           	currentSelectedCoordinate = new int[2];
           	currentTurnCaptured = false;
        }
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 | x < 0 | y > 7 | y < 0) {
			return null;
		}
		return this.slotsOfBoard[x][y].pieceInSlot;
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null){  // if (x,y) is a piece
			if ((!selectedPiece) | (!currentTurnMoved)) {
				return true;
			} else {
				return false;
			}
		} else { // if (x,y) is a empty slot
			if (selectedPiece & !currentTurnMoved) {
				return validMove(currentSelectedCoordinate[0], currentSelectedCoordinate[1], x, y);
			} else if (selectedPiece & currentTurnCaptured) {
				return validMove(currentSelectedCoordinate[0], currentSelectedCoordinate[1], x, y);
			} else {
				return false;
			}
			
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) != null) { // chosen slot is not empty
			return false;
		} else if (xf > 7 | xf < 0 | yf > 7 | yf < 0) { // out of board
			return false;
		} else { 
			if (pieceAt(xi, yi).isKing()) {
				// diagonally move
				if (xf - xi == yf - yi)	{
					if (!hasPieceInBetween(xi, yi, xf, yf)){
						return true;
					} else {
						if (xf - xi == 2) {
							return (pieceAt(xi, yi).isFire() ^ pieceAt(xi + 1, yi + 1).isFire());
						} else if (xi - xf == 2) {
							return (pieceAt(xi, yi).isFire() ^ pieceAt(xi - 1, yi - 1).isFire());
						} else {
							return false;
						}
					}
				} else if (xf - xi == yi - yf) {
					if (!hasPieceInBetween(xi, yi, xf, yf)){
						return true;
					} else {
						if (xf - xi == 2) {
							return (pieceAt(xi, yi).isFire() ^ pieceAt(xi + 1, yi - 1).isFire());
						} else if (xi - xf == 2) {
							return (pieceAt(xi, yi).isFire() ^ pieceAt(xi - 1, yi + 1).isFire());
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else if (pieceAt(xi, yi).isFire()) {
				if (xf > xi) { // if backwards
					return false;
				}
				// diagonally move
				if (xf - xi == yf - yi)	{
					if (!hasPieceInBetween(xi, yi, xf, yf)) {
						return true;
					} else {
						if (xi - xf == 2) {
							return !pieceAt(xi - 1, yi - 1).isFire();
						} else {
							return false;
						}
					}
				} else if (xf - xi == yi - yf) {
					if (!hasPieceInBetween(xi, yi,xf, yf)) {
						return true;
					} else {
						if (xi - xf == 2) {
							return !pieceAt(xi - 1, yi + 1).isFire();
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else {
				if (xf < xi) { // if backwards
					return false;
				}
				// diagonally move
				if (xf - xi == yf - yi)	{
					if (!hasPieceInBetween(xi, yi, xf, yf)) {
						return true;
					} else {
						if (xf - xi == 2) {
							return !pieceAt(xi + 1, yi + 1).isFire();
						} else {
							return false;
						}
					}
				} else if (xf - xi == yi - yf) {
					if (!hasPieceInBetween(xi, yi, xf, yf)) {
						return true;
					} else {
						if (xf - xi == 2) {
							return !pieceAt(xi + 1, yi - 1).isFire();
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			}
		}		
	}

	private boolean hasPieceInBetween(int xi, int yi, int xf, int yf) {
		int diag = (xf - xi)/(yf - yi);
		if (diag == 1) {
			if (xi < xf) {
				for (int i = 1; i < xf - xi; i++) {
					if (pieceAt(xi + i, yi + i) != null) {
						return true;
					}
				}
				return false;
			} else {
				for (int i = 1; i < xi - xf; i++) {
					if (pieceAt(xi - i, yi - i) != null) {
						return true;
					}
				}
				return false;
			}
		} else if (diag == -1) {
			if (xi < xf) {
				for (int i = 1; i < xf - xi; i++) {
					if (pieceAt(xi + i, yi - i) != null) {
						return true;
					}
				}
				return false;
			} else {
				for (int i = 1; i < xi - xf; i++) {
					if (pieceAt(xi - i, yi + i) != null) {
						return true;
					}
				}
				return false;
			}
		} else {
			return false;
		}
	}

	public void select(int x, int y) {
		if (!canSelect(x, y)) {
			return;
		}
		currentSelectedCoordinate[0] = x;
		currentSelectedCoordinate[1] = y;
		StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
		StdDrawPlus.filledSquare((7 - x + 0.5)/8, (y + 0.5)/8, 0.5/8);
		if (pieceAt(x, y) != null) {
			StdDrawPlus.picture((7 - x + 0.5)/8, (y + 0.5)/8, getIcon(pieceAt(x, y)), 0.125, 0.125);
		}
	}

	private String getIcon(Piece p) {
		if (p.isFire()) {
			if (p.isKing()) {
				if (p.isBomb()) {
					return "img/bomb-fire-crowned.png";
				} else if (p.isShield()) {
					return "img/shield-fire-crowned.png";
				} else {
					return "img/pawn-fire-crowned.png";
				}
			} else {
				if (p.isBomb()) {
					return "img/bomb-fire.png";
				} else if (p.isShield()) {
					return "img/shield-fire.png";
				} else {
					return "img/pawn-fire.png";
				}
			}
		} else {
			if (p.isKing()) {
				if (p.isBomb()) {
					return "img/bomb-water-crowned.png";
				} else if (p.isShield()) {
					return "img/shield-water-crowned.png";
				} else {
					return "img/pawn-water-crowned.png";
				}
			} else {
				if (p.isBomb()) {
					return "img/bomb-water.png";
				} else if (p.isShield()) {
					return "img/shield-water.png";
				} else {
					return "img/pawn-water.png";
				}
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (x > 7 | x < 0 | y > 7 | y < 0 | p == null) {
			return;
		}
		int xp = x;
		int yp = y;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; i < 7; j++) {
				if (slotsOfBoard[i][j].pieceInSlot == p) {
					xp = i;
					yp = j;
				}
			}
		}
		slotsOfBoard[x][y].isEmpty = false;
		slotsOfBoard[x][y].pieceInSlot = p;
		slotsOfBoard[xp][yp].isEmpty = true;
		slotsOfBoard[xp][yp].pieceInSlot = null;
		// display changes
		if ((xp + yp) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
        StdDrawPlus.filledSquare((7 - xp + 0.5)/8, (yp + 0.5)/8, 0.5/8);
        if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
        StdDrawPlus.filledSquare((7 - x + 0.5)/8, (y + 0.5)/8, 0.5/8);
        StdDrawPlus.picture((7 - x + 0.5)/8, (y + 0.5)/8, getIcon(p), 0.125, 0.125);
	}

	public Piece remove(int x, int y) {
		if (x > 7 | x < 0 | y > 7 | y < 0) {
			System.out.println("Remove Error: out of bounds.");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("Remove Error: no piece at location (" + x + ", " + y +").");
			return null;
		}
		Piece pieceToReturn = slotsOfBoard[x][y].pieceInSlot;
		slotsOfBoard[x][y].isEmpty = true;
		slotsOfBoard[x][y].pieceInSlot = null;
		// display changes
		if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
        StdDrawPlus.filledSquare((7 - x + 0.5)/8, (y + 0.5)/8, 0.5/8);
		return pieceToReturn;
	}

	public boolean canEndTurn() {
		if (currentTurnMoved | currentTurnCaptured) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		fireTurn = !fireTurn;
		selectedPiece = false;
		currentSelectedCoordinate = new int[2];
		currentTurnMoved = false;
		currentTurnCaptured = false;
		if (fireTurn) {
			System.out.println("Fire's turn.");
		} else {
			System.out.println("Water's turn.");
		}
	}

	public String winner() {
		int countFire = 0;
		int countWater = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; i < 7; j++) {
				if (slotsOfBoard[i][j] == null) {
					continue;
				} else {
					if (slotsOfBoard[i][j].pieceInSlot.isFire()) {
						countFire++;
					} else {
						countWater++;
					}
				}
			}
		}
		if (countFire == 0 & countWater == 0) {
			return "No one";
		} else if (countWater == 0){
			return "Fire";
		} else if (countFire == 0) {
			return "Water";
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		// initiate board
		Board b = new Board(false);
		for(int i = 0; i < 4; i++) {
			b.slotsOfBoard[0][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[0][2 * i + 1].pieceInSlot = new Piece(false, b, 0, 2 * i + 1, "pawn");
	        b.slotsOfBoard[1][2 * i + 0].isEmpty = false;
	        b.slotsOfBoard[1][2 * i + 0].pieceInSlot = new Piece(false, b, 1, 2 * i + 1, "shield");
	        b.slotsOfBoard[2][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[2][2 * i + 1].pieceInSlot = new Piece(false, b, 2, 2 * i + 1, "bomb");
	        b.slotsOfBoard[5][2 * i + 0].isEmpty = false;
	        b.slotsOfBoard[5][2 * i + 0].pieceInSlot = new Piece(true, b, 5, 2 * i + 1, "bomb");
	        b.slotsOfBoard[6][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[6][2 * i + 1].pieceInSlot = new Piece(true, b, 6, 2 * i + 1, "shield");
	        b.slotsOfBoard[7][2 * i + 0].isEmpty = false;
	        b.slotsOfBoard[7][2 * i + 0].pieceInSlot = new Piece(true, b, 7, 2 * i + 1, "pawn");
    	}

    	// test
    	System.out.println(b.pieceAt(4,4));
    	System.out.println(b.validMove(7,0,4,3));
    	b.select(7,0);
    	while (true) {
    		if (StdDrawPlus.mousePressed()) {
    			System.out.println((int)(StdDrawPlus.mouseX()*8));
    			System.out.println((int)(StdDrawPlus.mouseY()*8));
    			
    		}
    		StdDrawPlus.show(5);
    	}
	}
}








