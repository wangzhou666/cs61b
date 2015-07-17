public class Planet {
	
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double mass;
	public String img;
	public double xNetForce = 0;
	public double yNetForce = 0;
	public double xAccel = 0;
	public double yAccel = 0;
	private double radius;

	public Planet() {
		x = 0;
		y = 0;
		xVelocity = 0;
		yVelocity = 0;
		mass = 0;
		img = "";
	}
	
	public Planet(double _x, double _y, double _xVelocity, double _yVelocity, double _mass, String _img, double _radius) {
		x = _x;
		y = _y;
		xVelocity = _xVelocity;
		yVelocity = _yVelocity;
		mass = _mass;
		img = _img;
		radius = _radius;
	}

	public double getMass() {
		return mass;
	}

	public double getRadius() {
		return radius;
	}
	
	public double calcDistance(Planet anotherPlanet) {
		double dis;
		dis = Math.sqrt(Math.pow(x - anotherPlanet.x, 2) + Math.pow(y - anotherPlanet.y, 2));
		return dis;
	}
	
	public double calcPairwiseForce(Planet anotherPlanet) {
		double force;
		double paraG = 6.67e-11;
		force = paraG * mass * anotherPlanet.mass / Math.pow(calcDistance(anotherPlanet), 2);
		return force;
	}
	
	public double calcPairwiseForceX(Planet anotherPlanet) {
		double forceX;
		forceX = - calcPairwiseForce(anotherPlanet) * (x - anotherPlanet.x) / calcDistance(anotherPlanet);
		return forceX;
	}
	
	public double calcPairwiseForceY(Planet anotherPlanet) {
		double forceY;
		forceY = - calcPairwiseForce(anotherPlanet) * (y - anotherPlanet.y) / calcDistance(anotherPlanet);
		return forceY;
	}
	
	public void setNetForce(Planet[] planets) {
		xNetForce = 0;
		yNetForce = 0;
		for (Planet p : planets) {
			xNetForce += calcPairwiseForceX(p);
			yNetForce += calcPairwiseForceY(p);
		}
	}

	public void draw() {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point((int)x, (int)y);
	}

	public void update(double dt) {
		xAccel = xNetForce / mass;
		yAccel = yNetForce / mass;
		xVelocity += xAccel * dt;
		yVelocity += yAccel * dt;
		x += dt * xVelocity;
		y += dt * yVelocity;
	}
}