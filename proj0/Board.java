public class Board {
	private class Slot {
		public boolean isEmpty = true;
		public Piece pieceInSlot = null;
	}
	private Slot[][] slotsOfBoard;
	private boolean fireTurn;
	private boolean selectedPiece;
	private boolean currentTurnMoved;

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
        }
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 | x < 0 | y > 7 | y < 0) {
			return null;
		}
		return this.slotsOfBoard[x][y].pieceInSlot;
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null){
			if ((!selectedPiece) | (!currentTurnMoved)) {
				return true;
			} else {
				return false;
			}
		} else {
			
		}
	}

	public static void main(String[] args) {
		// initiate board
		Board b = new Board(false);
		for(int i = 0; i < 4; i++) {
			b.slotsOfBoard[0][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[0][2 * i + 1].pieceInSlot = new Piece(false, b, 0, 2 * i + 1, "pawn");
	        b.slotsOfBoard[1][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[1][2 * i + 1].pieceInSlot = new Piece(false, b, 1, 2 * i + 1, "shield");
	        b.slotsOfBoard[2][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[2][2 * i + 1].pieceInSlot = new Piece(false, b, 2, 2 * i + 1, "bomb");
	        b.slotsOfBoard[5][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[5][2 * i + 1].pieceInSlot = new Piece(true, b, 5, 2 * i + 1, "bomb");
	        b.slotsOfBoard[6][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[6][2 * i + 1].pieceInSlot = new Piece(true, b, 6, 2 * i + 1, "shield");
	        b.slotsOfBoard[7][2 * i + 1].isEmpty = false;
	        b.slotsOfBoard[7][2 * i + 1].pieceInSlot = new Piece(true, b, 7, 2 * i + 1, "pawn");
    	}
    	System.out.println(b.pieceAt(5,3).isFire());
	}
}








