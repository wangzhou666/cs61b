import java.util.Comparator;
/* Import anything else you need here. */

/**
 * MaxPlanet.java
 */

public class MaxPlanet {

    /** Returns the Planet with the maximum value according to Comparator c. */
    public static Planet maxPlanet(Planet[] planets, Comparator<Planet> c) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        Planet mp = planets[0];
        for (Planet p : planets) {
        	if (c.compare(p, mp) > 0) {
        		mp = p;
        	}
        }
        return mp;
    }
}