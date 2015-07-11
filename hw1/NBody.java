public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double t = Double.parseDouble(args[1]);
		String filename = args[2];
		In data = new In(filename);
		int planetNumber = data.readInt();
		double universeRadius = data.readDouble();
		Planet[] planets = new Planet[planetNumber];
		for (int i = 0; i < planetNumber; i++) {
			planets[i] = getPlanet(data);
		}
	}

	public static Planet getPlanet(In data) {
		Planet p = new Planet();
		p.x = data.readDouble();
		p.y = data.readDouble();
		p.xVelocity = data.readDouble();
		p.yVelocity = data.readDouble();
		p.mass = data.readDouble();
		p.img = data.readString();
		return p;
	}
}