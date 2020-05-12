package edu.iastate.cs228.hw2;

import java.io.File;

/**
 *  
 * @author Jonathon Schnell
 * @version 1.0
 * @since 3-7-2020
 * COM S 228
 * homework2
 *
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array by polar angle with respect to a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class RotationalPointScanner {
	private Point[] points;

	private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
											// the x coordinates and y coordinates of those points in the array
											// points[].
	private Algorithm sortingAlgorithm;

	protected String outputFileName; // "select.txt", "insert.txt", "merge.txt", or "quick.txt"

	protected long scanTime; // execution time in nanoseconds.

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[]. Set
	 * outputFileName.
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public RotationalPointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		// catch exception
		if (pts == null | pts.length == 0) {
			throw new IllegalArgumentException();
		} else {
			// construct and copy points to this
			this.points = new Point[pts.length];
			for (int i = 0; i < pts.length; i++) {
				this.points[i] = pts[i];
			}
			// set algo
			sortingAlgorithm = algo;
		}

	}

	/**
	 * This constructor reads points from a file. Set outputFileName.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected RotationalPointScanner(String inputFileName, Algorithm algo)
			throws FileNotFoundException, InputMismatchException {
		// set algo
		sortingAlgorithm = algo;
		// try opening the file
		try {
			File f = new File(inputFileName);
		} catch (Exception e) {
			throw new FileNotFoundException();
		} finally {

			File f = new File(inputFileName);
			// scanner to determine num of points
			Scanner sc = new Scanner(f);
			// scanner that does the scanning
			Scanner sc2 = new Scanner(f);
			int x = 0;
			int y = 0;
			int numPoints = 0;
			int numNums = 0;

			while (sc.hasNext()) {
				x = sc.nextInt();
				y = sc.nextInt();
				numPoints++;
				numNums += 2;
			}
			// only even input is valid
			if (numNums % 2 == 1) {
				throw new InputMismatchException();
			}
			// constructor for points array with proper length
			points = new Point[numPoints];

			Point temp = null;
			int i = 0;
			// constructs point and saves to correct index
			while (sc2.hasNext()) {
				x = sc2.nextInt();
				y = sc2.nextInt();
				temp = new Point(x, y);
				this.points[i] = temp;
				i++;
			}
		}
	}

	/**
	 * Carry out three rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates. d) Sort points[] again by the polar angle with respect to
	 * medianCoordinatePoint.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting. Copy the
	 * sorting result back onto the array points[] by calling the method getPoints()
	 * in AbstractSorter.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		// start time
		long timeStart = System.nanoTime();

		AbstractSorter aSorter = null;

		// create an object to be referenced by aSorter according to sortingAlgorithm.
		// for each of the three
		// rounds of sorting, have aSorter do the following:
		//
		// a) call setComparator() with an argument 0, 1, or 2. in case it is 2, must
		// have made
		// the call setReferencePoint(medianCoordinatePoint) already.
		//
		// b) call sort().
		//
		// sum up the times spent on the three sorting rounds and set the instance
		// variable scanTime.
		if (sortingAlgorithm.equals(Algorithm.QuickSort)) {
			aSorter = new QuickSorter(points);
		}
		if (sortingAlgorithm.equals(Algorithm.InsertionSort)) {
			aSorter = new InsertionSorter(points);
		}
		if (sortingAlgorithm.equals(Algorithm.SelectionSort)) {
			aSorter = new SelectionSorter(points);
		}
		if (sortingAlgorithm.equals(Algorithm.MergeSort)) {
			aSorter = new MergeSorter(points);
		}

		// sort by x
		aSorter.setComparator(0);
		aSorter.sort();
		aSorter.getPoints(points);
		int medX = aSorter.points[points.length / 2].getX();
		// sort by y
		aSorter.setComparator(1);
		aSorter.sort();
		aSorter.getPoints(points);
		int medY = aSorter.points[points.length / 2].getY();

		// construct median
		medianCoordinatePoint = new Point(medX, medY);
		aSorter.setReferencePoint(medianCoordinatePoint);

		// test median coord
		// System.out.println(medianCoordinatePoint);

		// sort polar
		aSorter.setComparator(2);
		aSorter.sort();
		aSorter.getPoints(points);

		// end time
		long timeEnd = System.nanoTime();
		// calculate time
		scanTime = timeEnd - timeStart;

	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		return sortingAlgorithm + " " + (points.length) + " " + scanTime;
	}

	/**
	 * Write points[] after a call to scan(). When printed, the points will appear
	 * in order of polar angle with respect to medianCoordinatePoint with every
	 * point occupying a separate line. The x and y coordinates of the point are
	 * displayed on the same line with exactly one blank space in between.
	 */
	@Override
	public String toString() {
		String list = "";
		for (int i = 0; i < points.length; i++) {
			// list = list + "\n" + points[i].toString();
			list = list + "\n" + points[i].getX() + " " + points[i].getY();
		}
		return list;
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException {
		// 1. Open the file.
		File f = new File(outputFileName);
		// 2. Write to the file.
		String toPrint = this.toString();
		FileOutputStream outputStream = new FileOutputStream(f);
		// FileWriter fileWriter = new FileWriter(f);
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.print(toPrint);
		// 3. Close the file.
		printWriter.close();
	}

	/**
	 * This method is called after each scan for visually check whether the result
	 * is correct. You just need to generate a list of points and a list of
	 * segments, depending on the value of sortByAngle, as detailed in Section 4.1.
	 * Then create a Plot object to call the method myFrame().
	 */
	public void draw() {
		int numSegs = 0; // number of segments to draw

		// Based on Section 4.1, generate the line segments to draw for display of the
		// sorting result.
		// Assign their number to numSegs, and store them in segments[] in the order.

		// remove duplicates from points
		Point[] noDup = removeDup(points);
		// calculate numsegs
		numSegs = noDup.length * 2;
		// construct segment array
		Segment[] segments = new Segment[numSegs];

		// ring of polar line segments
		for (int i = 0; i < noDup.length; i++) {
			if (i == noDup.length - 1) {
				segments[i] = new Segment(points[i], points[0]);
			} else {
				segments[i] = new Segment(points[i], points[i + 1]);
			}
		}
		// second half of segments are lines to reference point
		int k = 0;
		for (int j = noDup.length; j < numSegs; j++) {
			segments[j] = new Segment(points[k], medianCoordinatePoint);
			k++;
		}

		String sort = null;

		switch (sortingAlgorithm) {
		case SelectionSort:
			sort = "Selection Sort";
			break;
		case InsertionSort:
			sort = "Insertion Sort";
			break;
		case MergeSort:
			sort = "Mergesort";
			break;
		case QuickSort:
			sort = "Quicksort";
			break;
		default:
			break;
		}

		// The following statement creates a window to display the sorting result.
		Plot.myFrame(points, segments, sort);

	}

	/**
	 * 
	 * @param arr of points
	 * @return array of points without duplicates
	 */
	public Point[] removeDup(Point[] arr) {

		int arrL = arr.length;

		for (int i = 0; i < arrL; i++) {
			for (int j = i + 1; j < arrL; j++) {
				// if two elements are duplicates
				if (arr[i].equals(arr[j])) {
					// set index
					int index = j;
					// copy all items to the right of index left 1 index
					// needed to maintain sort
					while (index < arrL - 1) {
						arr[index] = arr[index + 1];
						index++;

					}
					j--;
					arrL--;
				}

			}
		}
		// copy to new array
		Point[] arr2 = Arrays.copyOf(arr, arrL);
		return arr2;
	}

	/**
	 * sets outputFileName
	 * 
	 * @param outputFileName
	 */
	public void setoutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

}
