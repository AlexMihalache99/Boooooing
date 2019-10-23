
/**
 *
 * @author Alexandru Mihalache
 *
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.*;
import java.util.Scanner;

public class ReadShapeFile {
	// TODO: You will likely need to write four methods here. One method to
	// construct each shape
	// given the Scanner passed as a parameter. I would suggest static
	// methods in this case.

	/**
	 * Creating an object of circle type by reading the line of the file
	 * 
	 * @param in the scanner of the file
	 * @return circle the object created
	 */
	public static Circle Circle(Scanner in) {

		Circle circle = null;

		int px = in.nextInt();
		int py = in.nextInt();
		int vx = in.nextInt();
		int vy = in.nextInt();
		boolean isFilled = in.nextBoolean();
		int diameter = in.nextInt();
		int r = in.nextInt();
		int g = in.nextInt();
		int b = in.nextInt();
		int insertiontime = in.nextInt();
		boolean isPulsing = in.nextBoolean();

		circle = new Circle("circle", insertiontime, px, py, vx, vy, diameter, Color.rgb(r, g, b), isFilled, isPulsing);

		return circle;
	}

	/**
	 * Creating an object of oval type by reading the line of the file
	 * 
	 * @param in the scanner of the file
	 * @return oval the object created
	 */
	public static Oval Oval(Scanner in) {

		Oval oval = null;

		int px = in.nextInt();
		int py = in.nextInt();
		int vx = in.nextInt();
		int vy = in.nextInt();
		boolean isFilled = in.nextBoolean();
		int width = in.nextInt();
		int height = in.nextInt();
		int r = in.nextInt();
		int g = in.nextInt();
		int b = in.nextInt();
		int insertiontime = in.nextInt();
		boolean isPulsing = in.nextBoolean();

		oval = new Oval("oval", insertiontime, px, py, vx, vy, width, height, Color.rgb(r, g, b), isFilled, isPulsing);

		return oval;
	}

	/**
	 * Creating an object of rectangle type by reading the line of the file
	 * 
	 * @param in the scanner of the file
	 * @return rect the object created
	 */
	public static Rect Rectangle(Scanner in) {

		Rect rect = null;

		int px = in.nextInt();
		int py = in.nextInt();
		int vx = in.nextInt();
		int vy = in.nextInt();
		boolean isFilled = in.nextBoolean();
		int width = in.nextInt();
		int height = in.nextInt();
		int r = in.nextInt();
		int g = in.nextInt();
		int b = in.nextInt();
		int insertiontime = in.nextInt();
		boolean isPulsing = in.nextBoolean();

		rect = new Rect("rect", insertiontime, px, py, vx, vy, width, height, Color.rgb(r, g, b), isFilled, isPulsing);

		return rect;

	}

	/**
	 * Creating an object of square type by reading the line of the file
	 * 
	 * @param in the scanner of the file
	 * @return square the object created
	 */
	public static Square Square(Scanner in) {

		Square square = null;

		int px = in.nextInt();
		int py = in.nextInt();
		int vx = in.nextInt();
		int vy = in.nextInt();
		boolean isFilled = in.nextBoolean();
		int side = in.nextInt();
		int r = in.nextInt();
		int g = in.nextInt();
		int b = in.nextInt();
		int insertiontime = in.nextInt();
		boolean isPulsing = in.nextBoolean();

		square = new Square("square", insertiontime, px, py, vx, vy, side, Color.rgb(r, g, b), isFilled, isPulsing);

		return square;
	}

	/**
	 * Creating an object of arc type by reading the line of the file
	 * 
	 * @param in  the scanner of the file
	 * @return arc the object created
	 */
	public static Arc Arc(Scanner in) {

		Arc arc = null;

		int px = in.nextInt();
		int py = in.nextInt();
		int vx = in.nextInt();
		int vy = in.nextInt();
		boolean isFilled = in.nextBoolean();
		int width = in.nextInt();
		int height = in.nextInt();
		int startangle = in.nextInt();
		int length = in.nextInt();
		String arctype = in.next();
		int r = in.nextInt();
		int g = in.nextInt();
		int b = in.nextInt();
		int insertiontime = in.nextInt();
		boolean isPulsing = in.nextBoolean();

		/**
		 * An arc has three types:
		 * 
		 * -Round 
		 * -Chord 
		 * -Open
		 */
		if (arctype.equals("r")) {

			arc = new Arc("arc", insertiontime, px, py, vx, vy, width, height, startangle, length, ArcType.ROUND,
					Color.rgb(r, g, b), isFilled, isPulsing);

		} else if (arctype.equals("c")) {

			arc = new Arc("arc", insertiontime, px, py, vx, vy, width, height, startangle, length, ArcType.CHORD,
					Color.rgb(r, g, b), isFilled, isPulsing);

		} else {

			arc = new Arc("arc", insertiontime, px, py, vx, vy, width, height, startangle, length, ArcType.OPEN,
					Color.rgb(r, g, b), isFilled, isPulsing);

		}

		return arc;
	}
	
	/**
	 * Reads the data file used by the program and returns the constructed queue
	 * 
	 * @param in the scanner of the file
	 * @return the queue represented by the data file
	 */
	private static Queue<ClosedShape> readDataFile(Scanner in) {
		Queue<ClosedShape> shapeQueue = new Queue<ClosedShape>();

		while (in.hasNextLine() == true) {
			String newline = in.nextLine();
			Scanner line = new Scanner(newline);///A scanner for the lines

			String shape = line.next();

			if (shape.equals("oval")) {

				/// introducing the Oval object in the queue
				shapeQueue.enqueue(Oval(line));

			} else if (shape.equals("circle")) {

				/// introducing the Circle object in the queue
				shapeQueue.enqueue(Circle(line));

			} else if (shape.equals("square")) {

				/// introducing the Square object in the queue
				shapeQueue.enqueue(Square(line));

			} else if (shape.equals("rect")) {

				/// introducing the Rect object in the queue
				shapeQueue.enqueue(Rectangle(line));

			} else if (shape.equals("arc")) {

				/// introducing the Arc object in the queue
				shapeQueue.enqueue(Arc(line));
			} 

		}

		// read in the shape files and place them on the Queue

		// Right now, returning an empty Queue. You need to change this.
		return shapeQueue;

	}

	/**
	 * Method to read the file and return a queue of shapes from this file. The
	 * program should handle the file not found exception here and shut down the
	 * program gracefully.
	 * 
	 * @param filename the name of the file
	 * @return the queue of shapes from the file
	 */
	public static Queue<ClosedShape> readDataFile(String filename) {
		// HINT: You might want to open a file here.

		Scanner in = null;

		try {
            
			in = new Scanner(new File(filename));
			
		} catch (FileNotFoundException e) {
			System.out.println("oooops I can not open the file:" + filename);
			System.exit(0);
		}
		return ReadShapeFile.readDataFile(in);


	}
}
