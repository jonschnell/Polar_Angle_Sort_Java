package edu.iastate.cs228.hw2;

/**
 * 
 * @author Jonathon Schnell
 * @version 1.0
 * @since 3-7-2020 COM S 228 homework2
 *
 */

public class Point implements Comparable<Point> {
	private int x;
	private int y;

	public static boolean xORy; // compare x coordinates if xORy == true and y coordinates otherwise
								// To set its value, use Point.xORy = true or false.

	/**
	 * constructs a point @ 0,0
	 */
	public Point() // default constructor
	{
		// x and y get default value 0
		this.x = 0;
		this.y = 0;
	}

	/**
	 * constructs a point at x,y
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * copy constructor
	 * 
	 * @param p point to be copied
	 */
	public Point(Point p) {
		x = p.getX();
		y = p.getY();
	}

	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the value of the static instance variable xORy.
	 * 
	 * @param xORy
	 */
	public static void setXorY(boolean xORy) {
		Point.xORy = xORy;
	}

	/**
	 * @param obj being compared
	 * @return Boolean true if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	/**
	 * Compare this point with a second point q depending on the value of the static
	 * variable xORy
	 * 
	 * @param q
	 * @return -1 if (xORy == true && (this.x < q.x || (this.x == q.x && this.y <
	 *         q.y))) || (xORy == false && (this.y < q.y || (this.y == q.y && this.x
	 *         < q.x))) 0 if this.x == q.x && this.y == q.y) 1 otherwise
	 */
	public int compareTo(Point q) {
		if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y))) {
			return -1;
		}
		if (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x))) {
			return -1;
		}
		if (this.x == q.x && this.y == q.y) {
			return 1;
		}
		return 0;
	}

	/**
	 * Output a point in the standard form (x, y).
	 * 
	 * @return point string or format (x,y)
	 */
	@Override
	public String toString() {
		String point = "(" + x + ", " + y + ")";
		return point;
	}
}
