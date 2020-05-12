package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;
import java.util.Arrays;

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
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {
	// Other private instance variables if you need ...

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		algorithm = "merge sort";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		mergeSortRec(points);
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * HELPER FUNCTION
	 * 
	 * @param pts point array
	 */

	private void mergeSortRec(Point[] pts) {
		sort(pts, 0, pts.length - 1);
	}

	/**
	 * THIS IS THE RECURSIVE FUNCTION to do mergesort
	 * 
	 * @param pts   point array
	 * @param left  lowest element
	 * @param right upper element
	 */
	void sort(Point[] pts, int left, int right) {
		if (left < right) {
			// middle m
			int m = (left + right) / 2;

			// Sort left half
			sort(pts, left, m);
			// sort right half
			sort(pts, m + 1, right);
			// Merge the sorted halves
			merge(pts, left, m, right);
		}
	}

	/**
	 * Merge the arrays
	 * 
	 * @param pts   points array
	 * @param left  lower element
	 * @param mid   midpoint
	 * @param right upper element
	 */
	void merge(Point[] pts, int left, int mid, int right) {
		// size of the arrays to be merged
		int sizeL = mid - left + 1;
		int sizeR = right - mid;

		// instantiate temp arrays
		Point arrL[] = new Point[sizeL];
		Point arrR[] = new Point[sizeR];

		// populate temp arrays
		for (int i = 0; i < sizeL; ++i) {
			arrL[i] = pts[left + i];
		}
		for (int j = 0; j < sizeR; ++j) {
			arrR[j] = pts[mid + 1 + j];
		}

		int i = 0;
		int j = 0;

		// initial index of merged array
		int k = left;

		while (i < sizeL && j < sizeR) {
			if (this.pointComparator.compare(arrL[i], arrR[j]) <= 0) {
				pts[k] = arrL[i];
				i++;
			} else {
				pts[k] = arrR[j];
				j++;
			}
			k++;
		}

		// copy left over elements of arrL[]
		while (i < sizeL) {
			pts[k] = arrL[i];
			i++;
			k++;
		}
		// copy left over elements of arrR[]
		while (j < sizeR) {
			pts[k] = arrR[j];
			j++;
			k++;
		}
	}
}
