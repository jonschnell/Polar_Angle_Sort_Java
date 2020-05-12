package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author Jonathon Schnell
 * @version 1.0
 * @since 3-7-2020
 * COM S 228
 * homework2
 *
 */

/**
 * 
 * This class implements selection sort.
 *
 */

public class SelectionSorter extends AbstractSorter {
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public SelectionSorter(Point[] pts) {
		super(pts);
		algorithm = "selection sort";
	}

	/**
	 * Apply selection sort on the array points[] of the parent class
	 * AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		// lower point
		for (int i = 0; i < points.length - 1; i++) {
			// next point or selected point
			for (int j = i + 1; j < points.length - 1; j++) {
				// if point at j < i swap their positions else move to next j
				if (pointComparator.compare(points[j], points[i]) == -1) {
					swap(j, i);
				}
			}
		}
	}

}
