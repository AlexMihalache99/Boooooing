
/**
 * Arc.java
 * @version 1.0.0
 * @author Alexandru Mihalache
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.canvas.GraphicsContext;

/**
 * Arc is a shape that can be drawn to the screen, either filled with colour or
 * opaque. Its position is determined by the upper left corner of the circle's
 * bounding rectangle.
 */
public class Arc extends ClosedShape {
	/**
	 * The specifics components on arc. 
	 * -the width 
	 * -the height 
	 * -the starting angle 
	 * -the length 
	 * -the type of the arc
	 */
	private int width;
	private int height;
	private int startangle;
	private int extendingangle;
	private ArcType arc;

	/**
	 * Creates an arc.
	 * 
	 * @param shape
	 *            The type of the shape.
	 * @param x
	 *            The display component's x position.
	 * @param y
	 *            The display component's y position.
	 * @param vx
	 *            The display component's x velocity.
	 * @param vy
	 *            The display component's y velocity.
	 * @param width
	 *            The width of the arc(in pixels).
	 * @param height
	 *            The height of the arc(in pixels).
	 * @param startangle
	 *            The starting angle of the arc(in degrees).
	 * @param extendingangle
	 *            The extending angle of the arc(in degrees).
	 * @param arc
	 *            The type of the arc.
	 * @param colour
	 *            The line colour or fill colour.
	 * @param isFilled
	 *            True if the arc is filled with colour, false if opaque.
	 * @param isPulsing
	 *            True if the arc is pulsing, false if not.
	 */

	/**
	 * 
	 * Public constructor for the Arc class
	 */
	public Arc(String shape, int insertionTime, int x, int y, int vx, int vy, int width, int height, int startangle,
			int extendingangle, ArcType arc, Color colour, boolean isFilled, boolean isPulsing) {

		super(shape, insertionTime, x, y, vx, vy, colour, isFilled, isPulsing);
		this.width = width;
		this.height = height;
		this.startangle = startangle;
		this.extendingangle = extendingangle;
		this.arc = arc;

	}

	/**
	 * Method to convert an arc to a string.
	 */
	public String toString() {
		String result = "This is an arc\n";
		result += super.toString();
		result += "Its width is " + this.width + " , its height is " + this.height + " ,its startangle "
				+ this.startangle + " ,its extending angle " + extendingangle + "\n";
		return result;
	}

	/**
	 * @param width
	 *            Resets the width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @param height
	 *            Resets the height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @param startangle
	 *            Resets the starting angle.
	 */
	public void setStartAngle(int startangle) {
		this.startangle = startangle;
	}

	/**
	 * @param extendingangle
	 *            Resets the extending angle.
	 */
	public void setExtendingangle(int extendingangle) {
		this.extendingangle = extendingangle;
	}

	/**
	 * @param arc
	 *            Resets the type of the arc.
	 */
	public void setArcType(ArcType arc) {
		this.arc = arc;
	}

	/**
	 * @return The width of the arc.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return The height of the arc.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return The starting angle of the arc.
	 */
	public int getStartAngle() {
		return startangle;
	}

	/**
	 * @return The length of the arc.
	 */
	public double getExtendingangle() {
		return extendingangle;
	}
	
	/**
	 * @return The type of the arc.
	 */
	public ArcType getArcType() {
		return arc;
	}

	/**
	 * 
	 * @return the length of the arc using a mathematical formula
	 * 
	 */
	public double arcLength() {

		return (Math.sqrt(Math.pow(this.getWidth(), 2) * Math.pow(Math.sin(startangle), 2) + Math.pow(this.getHeight(), 2) * Math.pow(Math.sin(startangle), 2))) / 2;
	}

	@Override
	/**
	 * Puts the shape back in bounds in X
	 */
	public void putInBoundsX(double winX) {
		if (x < 0)
			x = 0;
		
		///This is just a normal case and we do not need any special formula
		if (this.extendingangle > 180) {

			if (x + this.getWidth() > winX) {

				x = (int) (winX - Math.ceil(this.getWidth()));
				
			}
		} else if (x + arcLength() > winX) {
			///this is a case where  we need the radius in order to keep the shape in bounds
			x = (int) (winX - arcLength());

		} else if (x + this.getWidth() / 2 > winX) {
			///this is a special case where we need just half of the diameter
			x = (int) (winX - Math.ceil(this.getWidth() / 2));
			
		}
	}

	@Override
	/**
	 * Puts the shape back in bounds in Y
	 */
	public void putInBoundsY(double winY) {
		if (y < 0)
			y = 0;
		
		///This is just a normal case and we do not need any special formula
		if (this.extendingangle > 180) {
			
			if(y + this.getHeight() > winY){
				
				y = (int) (winY - Math.ceil(this.getHeight()));
				
			}
			
		} else if (y + arcLength() > winY) {
			///this is a case where  we need the radius in order to keep the shape in bounds
			y = (int) (winY - arcLength());

		} else if (y + this.getHeight() / 2 > winY) {
			///this is a special case where we need just half of the diameter
			y = (int) (winY - Math.ceil(this.getHeight() / 2));
			
		} 
		
	}

	@Override
	/**
	 * Returns true if the shapes have gone out of bounds in X
	 */
	public boolean outOfBoundsX(double winX) {
         
		///This is just a normal case and we do not need any special formula
		if (this.extendingangle > 180){
	
			return (x + this.getWidth() > winX || (x < 0));
		
		}
		
		///if the arc is one of the above we have a special case where we need just half of the diameter
		if (arc == ArcType.ROUND || arc == ArcType.CHORD){
			
			return (x + this.getWidth() / 2 > winX || (x < 0));
			
		}
		
        ///if the arc is an open one we need the radius in order to keep the shape in bounds
		return (x + arcLength() > winX) || (x < 0);

	}

	@Override
	/**
	 * Returns true if the shapes have gone out of bounds in Y
	 */
	public boolean outOfBoundsY(double winY) {
        
		///This is just a normal case and we do not need any special formula
		if (this.extendingangle > 180){
			
			return (y + this.getHeight() > winY || (y < 0));
			
		}
		
		///if the arc is one of the above we have a special case where we need just half of the diameter
		if (arc == ArcType.ROUND || arc == ArcType.CHORD){
					
			return (y + this.getHeight() / 2 > winY || (y < 0));
					
	     }
		
		///if the arc is an open one we need the radius in order to keep the shape in bounds
		return (y + arcLength() > winY) || (y < 0);
		
    }

	/**
	 * Draw the arc on the screen.
	 * 
	 * @param g
	 *            The graphics object of the scene component.
	 */
	public void draw(GraphicsContext g) {
		g.setFill(colour);
		g.setStroke(colour);
		if (isFilled) {
			g.fillArc(x, y, width, height, startangle, extendingangle, arc);
		} else {
			g.strokeArc(x, y, width, height, startangle, extendingangle, arc);

		}
	}

}
