public class NBody {

	public static void main(String[] args) {
		// read args
		double finalTime = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		
		// read data into planets array
		In data = new In(filename);
		int planetNumber = data.readInt();
		double universeRadius = data.readDouble();
		Planet[] planets = new Planet[planetNumber];
		for (int i = 0; i < planetNumber; i++) {
			planets[i] = getPlanet(data);
		}
		
		// drawing
		StdDraw.clear();
		StdDraw.picture(0.5, 0.5, "images/starfield.jpg");
		for (Planet p : planets) {
			StdDraw.picture(p.x / universeRadius + 0.5, p.y / universeRadius + 0.5, "images/" + p.img);
		}
		for (double initTime = 0; initTime < finalTime; initTime += dt) {
			for (int i = 0; i < planetNumber; i++) {
				Planet[] otherPlanets = new Planet[planetNumber-1];
				System.arraycopy(planets, 0, otherPlanets, 0, i);
				System.arraycopy(planets, i+1, otherPlanets, i, planetNumber-1-i);
				planets[i].setNetForce(otherPlanets);
				planets[i].update(dt);
			}
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, "images/starfield.jpg");
			for (Planet p : planets) {
			StdDraw.picture(p.x / universeRadius + 0.5, p.y / universeRadius + 0.5, "images/" + p.img);
			}
			StdDraw.show(2);
		}

		// print
		StdOut.printf("%d\n", planetNumber);
		StdOut.printf("%.2e\n", universeRadius);
		for (int i = 0; i < planetNumber; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                   planets[i].x, planets[i].y, planets[i].xVelocity, planets[i].yVelocity, planets[i].mass, planets[i].img);
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