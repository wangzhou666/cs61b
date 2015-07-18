import java.util.AbstractList;

public class ArrayList61B<E> extends AbstractList<E> {

	private E[] elements;
	private int number;

	public ArrayList61B(int initialCapacity) {
		number = 0;
		elements = (E[]) new Object[initialCapacity];
	}

	public ArrayList61B() {
		number = 0;
		elements = (E[]) new Object[1];
	}

	public E get(int i) {
		if (i == number | i < 0) {
			throw new IllegalArgumentException();
		}
		return elements[i];
	}

	public boolean add(E item) {
		if (number < elements.length) {
			elements[number] = item;
		} else {
			E[] temp = elements;
			elements = (E[]) new Object[number * 2];
			for (int i = 0; i < number ; i++) {
				elements[i] = temp[i];
			}
			elements[number] = item;
		}
		number++;
		return true;
	}

	public int size() {
		return number;
	}
}