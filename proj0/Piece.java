public class Piece {
	private int x, y;
	private boolean isFire;
	private String type;
	private Board b;
	private boolean capturedAnother;

	public Piece(boolean _isFire, Board _b, int _x, int _y, String _type) {
		x = _x;
		y = _y;
		b = _b;
		isFire = _isFire;
		type = _type;
		capturedAnother = false;
	}

	public boolean isFire() {
		return isFire;
	}

	public int side() {
		return isFire ? 0 : 1;
	}

	public boolean isKing() {
		if (type.equals("king")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBomb() {
		if (type.equals("bomb")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isShield() {
		if (type.equals("shield")) {
			return true;
		} else {
			return false;
		}
	}

	public void move(int _x, int _y) {
		x = _x;
		y = _y;
		b.place(this, x, y);
	}

	public boolean hasCaptured() {
		return capturedAnother;
	}

	public void doneCapturing() {
		capturedAnother = false;
	}
}