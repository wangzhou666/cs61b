public class PartOneEx {
	public static void main(String[] args) {
		// System.out.println("Q1:");
		// Animal a0 = new Animal();   // Line 1
		// Fox f0 = new Fox();         // Line 2
		// a0.speak();                 // Line 3
		// f0.speak();                 // Line 4
		// ((Animal) f0).speak();      // Line 5
		// ((Fox) a0).speak();         // Line 6

		// System.out.println("Q2:");
		// Animal a1 = new Fox();
		// a1.speak();
		// ((Animal) a1).speak();

		System.out.println("Q3:");
		Animal a2 = new Animal();
		System.out.println(a2.name);
		Animal a3 = new Fox("SuperFox");
		System.out.println(a3.name);
		System.out.println(((Fox) a3).name);
	}
}