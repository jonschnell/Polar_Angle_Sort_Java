package edu.iastate.cs228.hw2;

/**
 * 
 * Fully implemented class to represent a line segment for drawing. Used by the
 * class Plot and the draw() method in the class AbstractSorter.
 *
 */
public class Segment {
	private Point p;
	private Point q;

	/**
	 * point p and q make up a segment
	 * 
	 * @param p0 point p
	 * @param q0 point q
	 */
	public Segment(Point p0, Point q0) {
		p = new Point(p0);
		q = new Point(q0);
	}

	/**
	 * 
	 * @return p
	 */
	public Point getP() {
		return p;
	}

	/**
	 * 
	 * @return q
	 */
	public Point getQ() {
		return q;
	}
}
