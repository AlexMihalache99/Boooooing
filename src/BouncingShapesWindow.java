import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Dr D. Archambault, Modified for JAVAFX by Dr J. L. Jones
 * 
 */
public class BouncingShapesWindow {

	private static final int ANIMATION_DELAY = 10;
	private static final String FRAME_TITLE = "Shape Booooiiinggg Frame";
	private static final int CONSTANT = 2;///the value with which the figure will grow or shrink

	private GraphicsContext gc;
	private Queue<ClosedShape> shapesToAdd;
	private ArrayList<ClosedShape> activeShapes;
	private int currentTime = 0;
	private boolean flag = true;

	private String filename;

	public BouncingShapesWindow(GraphicsContext gc, String filename) {
		this.gc = gc;

		activeShapes = new ArrayList<ClosedShape>();
		this.initShapes(filename);
		this.insertShapes();
		drawClosedShapes();
		actionPerformed();
	}

	private void drawClosedShapes(){
		for (ClosedShape s : activeShapes) {
			s.draw(gc);
			
		}
	}

	private void initShapes(String filename) {
		shapesToAdd = ReadShapeFile.readDataFile(filename);

	}

	private void insertShapes() {
		// no more shapes to add, we are done
		if (shapesToAdd.isEmpty()) {
			return;
		}

		// add shapes if needed
		ClosedShape current = shapesToAdd.peek();
		while (!shapesToAdd.isEmpty() && (current.getInsertionTime() <= currentTime * ANIMATION_DELAY)) {
			activeShapes.add(current);
			shapesToAdd.dequeue();
			if (!shapesToAdd.isEmpty()) {
				current = shapesToAdd.peek();
			}
		}
	}

	public void actionPerformed() {
        
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5), ae ->onTime()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
	}

	private void onTime() {
		currentTime++;

		double h = gc.getCanvas().getHeight();
		double w = gc.getCanvas().getWidth();

		gc.clearRect(0, 0, w, h);

		moveShapes();
		insertShapes();
		drawClosedShapes();

	}

	public void moveShapes() {
		
		double dimsY = gc.getCanvas().getHeight();
		double dimsX = gc.getCanvas().getWidth();
		for (ClosedShape s : activeShapes) {
			s.move();
            
			///at every 75 milliseconds the shape will grow or shrink
			if(currentTime%75==0){
			
				flag=s.isPulsing();///if the shape is pulsing or not
				
			if (flag==true) {
       
				s.setTurn(s.getTurn());/// to know if is shrinking or growing
				
				
				/**
				 * 
				 * Now in order to access the methods of every type of shape we need to cast the variable s
				 * 
				 * After casting , we check what operation we should do,grow or shrink
				 * 
				 * if is grow we multiply the length components of the shape(e.g. diameter for circle,width and height for square)
				 * if is shrink we divide the length components of the shape
				 * 
				 * */
				
				
				if (s.shape.equals("circle")) {

					Circle circle = (Circle) s;///Casting to a circle variable
					
					if (s.getTurn() == 1) {///if is growing we multiply
						
						circle.setDiameter(circle.getDiameter() * CONSTANT);
						
					} else {///if is shrinking we divide
						
						circle.setDiameter(circle.getDiameter() / CONSTANT);
						
					}
				} else if (s.shape.equals("oval")) {

					Oval oval = (Oval) s;///Casting to an oval variable
					
					if (s.getTurn() == 1) {///if is growing we multiply
						
						oval.setWidth(oval.getWidth() * CONSTANT);
						oval.setHeight(oval.getHeight() * CONSTANT);
						
					} else {///if is shrinking we divide
						
						oval.setWidth(oval.getWidth() / CONSTANT);
						oval.setHeight(oval.getHeight() / CONSTANT);
						
					}
				} else if (s.shape.equals("rect")) {

					Rect rect = (Rect) s;///Casting to a rectangle variable
					
					if (s.getTurn() == 1) {///if is growing we multiply
						
						rect.setWidth(rect.getWidth() * CONSTANT);
						rect.setHeight(rect.getHeight() * CONSTANT);
						
					} else {///if is shrinking we divide
						
						rect.setWidth(rect.getWidth() / CONSTANT);
						rect.setHeight(rect.getHeight() / CONSTANT);
						
					}
				} else if (s.shape.equals("square")) {

					Square square = (Square) s;///Casting to a square variable
					
					if (s.getTurn() == 1) {///if is growing we multiply
						
						square.setSide(square.getSide() * CONSTANT);
						
					} else {///if is shrinking we divide
						
						square.setSide(square.getSide() / CONSTANT);
						
					}
				} else if (s.shape.equals("arc")) {

					Arc arc = (Arc) s;///Casting to an arc variable
					
					if (s.getTurn() == 1) {///if is growing we multiply
						
						arc.setWidth(arc.getWidth() * CONSTANT);
						arc.setHeight(arc.getHeight() * CONSTANT);
						
					} else {///if is shrinking we divide
						
						arc.setWidth(arc.getWidth() / CONSTANT);
						arc.setHeight(arc.getHeight() / CONSTANT);
						
					}
				}
				
			}/// end if for flag
			
		}///end if for currentTime

			
			// Move us back in and bounce if we went outside the drawing area.
			if (s.outOfBoundsX(dimsX)) {
				s.putInBoundsX(dimsX);
				s.bounceX();
			}

			if (s.outOfBoundsY(dimsY)) {
				s.putInBoundsY(dimsY);
				s.bounceY();
			}

		}
	}

}
