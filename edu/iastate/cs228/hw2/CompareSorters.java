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
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {

		// setup system scanner
		Scanner scanner = new Scanner(System.in);

		System.out.println("Performances of Four Sorting Algorithms in Point S");
		System.out.println("key: 1 (random integers) 2 (file input) 3 (exit)");
		// master for loop allows for 99 trials
		for (int trial = 1; trial < 100; trial++) {
			// keeps track of number of trials
			System.out.print("Trail " + trial + ":");
			// scans user selection int
			int selection = scanner.nextInt();

			// selection 1 random
			if (selection == 1) {
				System.out.print("Enter number of random points:");
				int numPoints = scanner.nextInt();
				System.out.println("algorithm size time (ns)");
				System.out.println("------------------------------");

				// construct random number generator
				Random r = new Random();
				Point[] arr = generateRandomPoints(numPoints, r);

				// construct RotationalPointScanner arry for each element
				RotationalPointScanner[] scanners = new RotationalPointScanner[4];
				// each element is a different sort method
				scanners[0] = new RotationalPointScanner(arr, Algorithm.SelectionSort);
				scanners[1] = new RotationalPointScanner(arr, Algorithm.InsertionSort);
				scanners[2] = new RotationalPointScanner(arr, Algorithm.MergeSort);
				scanners[3] = new RotationalPointScanner(arr, Algorithm.QuickSort);

				// scan draw and print stats for each sorting algorithm
				for (int i = 0; i < 4; i++) {
					scanners[i].scan();
					scanners[i].draw();
					System.out.println(scanners[i].stats());
				}

				System.out.println("------------------------------");
			}

			// selection 2 file input else
			if (selection == 2) {
				System.out.println("Points from a file");
				System.out.print("File name: ");
				String fileName = scanner.next();
				System.out.println("algorithm size time (ns)");
				System.out.println("------------------------------");

				// construct RotationalPointScanner arry for each element
				RotationalPointScanner[] scanners = new RotationalPointScanner[4];
				// each element is a different sort method
				scanners[0] = new RotationalPointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new RotationalPointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new RotationalPointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new RotationalPointScanner(fileName, Algorithm.QuickSort);
				// scan draw and print stats for each sorting algorithm
				for (int i = 0; i < 4; i++) {
					scanners[i].scan();
					scanners[i].draw();
					System.out.println(scanners[i].stats());
				}
				System.out.println("------------------------------");

			}

			// selection 3 exit program else
			if (selection == 3) { // close the scanner
			scanner.close();
			System.exit(0);
			}

			// invalid user input catch } else {
			System.out.println("Error enter 1 for random, 2 for file, or 3 to exit.");
		}

		// TEST CODE

		/*
		 * Random r = new Random(); Point[] arr = generateRandomPoints(10, r);
		 * 
		 * Point pa = new Point(0, 0); Point pb = new Point(1, 1); Point pc = new
		 * Point(2, 2); Point pd = new Point(3, 3); Point pe = new Point(4, 4); Point pf
		 * = new Point(9, 1); Point pg = new Point(-5, 2); Point ph = new Point(13, 3);
		 * 
		 * Point[] arr2 = { pd, pb, pc, pa, pe, pf, pg, ph };
		 */
		/*
		Point a = new Point(0, 0);
		Point b = new Point(-3, -9);
		Point c = new Point(0, -10);
		Point d = new Point(8, 4);
		Point e = new Point(3, 3);
		Point f = new Point(-6, 3);
		Point g = new Point(-2, 1);
		Point h = new Point(10, 5);
		Point i = new Point(-7, -10);
		Point j = new Point(5, -2);
		Point k = new Point(7, 3);
		Point l = new Point(10, 5);
		Point m = new Point(-7, -10);
		Point n = new Point(0, 8);
		Point o = new Point(-1, -6);
		Point p = new Point(-10, 0);
		Point q = new Point(5, 5);

		Point[] arr3 = { a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q };

		// AbstractSorter a = new MergeSorter(arr2);
		// AbstractSorter a = new InsertionSorter(arr2);
		// AbstractSorter abs = new SelectionSorter(arr3);
		AbstractSorter abs = new QuickSorter(arr3);

		// sort by x
		abs.setComparator(0);
		// sort by y
		// a.setComparator(2);

		abs.sort();

		for (int ave = 0; ave < 8; ave++) {
			System.out.println(abs.points[ave].toString());
		}
		*/

		// TODO
		//
		// Conducts multiple rounds of comparison of four sorting algorithms. Within
		// each round,
		// set up scanning as follows:
		//
		// a) If asked to scan random points, calls generateRandomPoints() to initialize
		// an array
		// of random points.
		//
		// b) Reassigns to the array scanners[] (declared below) the references to four
		// new
		// RotationalPointScanner objects, which are created using four different values
		// of the Algorithm type: SelectionSort, InsertionSort, MergeSort and QuickSort.
		//
		//

		/*
		 * RotationalPointScanner[] scanners = new RotationalPointScanner[4];
		 * scanners[0] = new RotationalPointScanner(arr3, Algorithm.SelectionSort);
		 * scanners[1] = new RotationalPointScanner(arr3, Algorithm.InsertionSort);
		 * scanners[2] = new RotationalPointScanner(arr3, Algorithm.MergeSort);
		 * scanners[3] = new RotationalPointScanner(arr3, Algorithm.QuickSort);
		 * 
		 * scanners[0].scan(); scanners[1].scan(); scanners[2].scan();
		 * scanners[3].scan();
		 * 
		 * scanners[0].draw();
		 * 
		 * System.out.println(scanners[0].stats());
		 * System.out.println(scanners[1].stats());
		 * System.out.println(scanners[2].stats());
		 * System.out.println(scanners[3].stats());
		 * 
		 * System.out.println("------------------------------");
		 * 
		 * System.out.print(scanners[0].toString());
		 * 
		 * scanners[3].setoutputFileName("test.txt"); scanners[3].writePointsToFile();
		 */

		// Plot.myFrame(arr3, segments, "selection sort");

		// For each input of points, do the following.
		//
		// a) Initialize the array scanners[].
		//
		// b) Iterate through the array scanners[], and have every scanner call the
		// scan() and draw()
		// methods in the RotationalPointScanner class. You can visualize the result of
		// each scan.
		// (Windows have to be closed manually before rerun.)
		//
		// c) After all four scans are done for the input, print out the statistics
		// table (cf. Section 2).
		//
		// A sample scenario is given in Section 2 of the project description.

	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] � [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		// arrays should be larger than 1
		if (numPts < 1) {
			throw new IllegalArgumentException();
		}
		// temp point
		Point[] p = new Point[numPts];
		// set up random
		Random r = new Random();

		// generate an array of random points between 0 and 50
		for (int i = 0; i < numPts; i++) {
			int x = r.nextInt(101) - 50;
			int y = r.nextInt(101) - 50;
			Point point = new Point(x, y);
			p[i] = point;

		}
		return p;

	}

}
