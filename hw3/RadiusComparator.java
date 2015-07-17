import java.util.Comparator;

public class RadiusComparator implements Comparator<Planet> {

	public RadiusComparator() {

	}

	public int compare(Planet p1, Planet p2) {
		double difference = p1.getRadius() - p2.getRadius();
		return (int) Math.round(difference);
	}
}