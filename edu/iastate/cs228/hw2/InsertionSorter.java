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

/**
 * 
 * This class implements insertion sort.
 *
 */

public class InsertionSorter extends AbstractSorter {
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public InsertionSorter(Point[] pts) {
		super(pts);
		algorithm = "insertion sort";
	}

	/**
	 * Perform insertion sort on the array points[] of the parent class
	 * AbstractSorter.
	 */
	@Override
	public void sort() {

		for (int j = 1; j < points.length; j++) {
			// set index
			Point index = points[j];
			// second point i to the left of j
			int i = j - 1;
			// while index < i move that point to the left
			while ((i >= 0) && (pointComparator.compare(index, points[i])) == -1) {
				points[i + 1] = points[i];
				i--;
			}
			// iterate index
			points[i + 1] = index;
		}
	}
}
