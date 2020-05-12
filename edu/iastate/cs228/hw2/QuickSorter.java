package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 *  
 * @author
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the
 * lecture.
 *
 */

public class QuickSorter extends AbstractSorter {

	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public QuickSorter(Point[] pts) {
		super(pts);
		algorithm = "quick sort";
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class. helper
	 */
	@Override
	public void sort() {
		quickSortRec(0, points.length - 1);
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first starting index of the subarray
	 * @param last  ending index of the subarray
	 */
	private void quickSortRec(int first, int last) {
		// base case
		if (first < last) {
			// index and partition the while array
			int Index = partition(first, last);
			// call recursivly on first half and second half
			quickSortRec(first, Index - 1);
			quickSortRec(Index + 1, last);
		}
	}

	/**
	 * partitions the array
	 * 
	 * @param first
	 * @param last
	 * @return index + 1
	 */
	private int partition(int first, int last) {
		// set pivot
		Point pivot = points[last];
		int i = (first - 1);
		// from first to last
		for (int j = first; j < last; j++) {
			// if j < pivot swap the points
			if (pointComparator.compare(points[j], pivot) == -1) {
				i++;
				// swap points
				swap(i, j);

			}
		}

		swap(i + 1, last);

		return i + 1;
	}

	// Other private methods in case you need ...
}
