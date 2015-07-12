public class CalculatorUI {
	public static void main(String[] args) {
		Calculator c = new Calculator();
		while (true) {
			System.out.print("> ");
			String cmd = StdIn.readString();
			switch (cmd) {
				case "quit": return;
				default:
					int x = Integer.parseInt(cmd);
					String opertion = StdIn.readString();
					int y = StdIn.readInt();
					switch (opertion) {
						case "+": System.out.println(c.add(x, y)); break;
						case "*": System.out.println(c.multiply(x, y));
					}
			}
		}
	}
}