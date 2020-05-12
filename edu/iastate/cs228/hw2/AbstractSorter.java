package edu.iastate.cs228.hw2;

/**
 *  
 * @author Jonathon Schnell
 * @version 1.0
 * @since 3-7-2020
 * COM S 228
 * homework2
 *
 */

import java.util.Comparator;
import java.lang.IllegalArgumentException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort,
 * and QuickSort. It stores the input (later the sorted) sequence.
 *
 */
public abstract class AbstractSorter {

	protected Point[] points; // array of points operated on by a sorting algorithm.
								// stores ordered points after a call to sort().

	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
										// "quicksort". Initialized by a subclass constructor.

	protected Comparator<Point> pointComparator = null;

	private Point referencePoint = null; // common reference point for computing the polar angle

	// Add other protected or private instance variables you may need.

	/**
	 * This constructor accepts an array of points as input. Copy the points into
	 * the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException {
		points = new Point[pts.length];
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < pts.length; i++) {
				points[i] = pts[i];
			}
		}
	}

	/**
	 * 
	 * @param p
	 * @throws IllegalArgumentException if p == null
	 */
	public void setReferencePoint(Point p) throws IllegalArgumentException {
		if (p == null) {
			throw new IllegalArgumentException();
		} else {
			referencePoint = p;
		}
	}

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order ==
	 * 0, by y-coordinate if order == 1, and by polar angle with respect to
	 * referencePoint if order == 2. Assign the comparator to the variable
	 * pointComparator.
	 * 
	 * If order == 2, the method cannot be called when referencePoint == null. Call
	 * setRereferencePoint() first to set referencePoint.
	 * 
	 * Need to create an object of the PolarAngleComparator class and call the
	 * compareTo() method in the Point class.
	 * 
	 * @param order 0 by x-coordinate 1 by y-coordinate 2 by polar angle w.r.t
	 *              referencePoint
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 2
	 *                                  IllegalStateException if order == 2 and
	 *                                  referencePoint == null;
	 */
	public void setComparator(int order) throws IllegalArgumentException, IllegalStateException {
		if (order < 0 || order > 2) {
			throw new IllegalArgumentException();
		}
		if (order == 2 && referencePoint == null) {
			throw new IllegalArgumentException();
		}
		// constructs x comparator
		class xCompare implements Comparator<Point> {
			@Override
			public int compare(Point a, Point b) {
				if (a.getX() == b.getX()) {
					return 0;
				}
				if (a.getX() < b.getX()) {
					return -1;
				} else {
					return 1;
				}
			}
		}
		// constructs y comparator
		class yCompare implements Comparator<Point> {
			@Override
			public int compare(Point a, Point b) {
				if (a.getY() == b.getY()) {
					return 0;
				}
				if (a.getY() < b.getY()) {
					return -1;
				} else {
					return 1;
				}
			}
		}

		if (order == 0) {
			pointComparator = new xCompare();
		}
		if (order == 1) {
			pointComparator = new yCompare();
		}
		if (order == 2) {
			if (referencePoint == null) {
				throw new IllegalStateException();
			}
			pointComparator = new PolarAngleComparator(referencePoint);
		}
	}

	/**
	 * Use the created pointComparator to conduct sorting.
	 * 
	 * Ought to be protected. Made public for testing.
	 */
	public abstract void sort();

	/**
	 * Obtain the point in the array points[] that has median index
	 * 
	 * @return median point
	 */
	public Point getMedian() {
		return points[points.length / 2];
	}

	/**
	 * Copys the array points[] onto the array pts[].
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts) {
		for (int i = 0; i < points.length; i++) {
			pts[i] = points[i];
		}
	}

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[].
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j) {
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}
}
