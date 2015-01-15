import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
* Rectangle drawing tool
*
* @author Matthew Le
* @version 1.6
*/
public class rectangleTool extends AbstractTools {

    private double z1;
    private double z2;
    private double xPos1;
    private double xPos2;
    private double yPos1;
    private double yPos2;
    private Color color1;
    private Color color2;

    /**
    * constructor for Rectangle tool
    * @param color color of rectangle
    * 
    */
    public rectangleTool(Color color, Color other) {
        this.color = color;
        this.other = other;
    }

    /**
     * Method for action when mouse is clicked,
     * which creates a point with specified color at
     * the mouseclick location.
     *
     * @param e the mouseevent that calls method
     * @param g the current graph context
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        z1 = 0;
        z2 = 0;

        xPos1 = e.getX();
        yPos1 = e.getY();

        xPos2 = e.getX();
        yPos2 = e.getY();
    }

    /**
     * Method for action when mouse is dragged,
     * which creates points continuously along
     * the drag's path
     *
     * @param e the mouseevent that calls method
     * @param g the current graph context
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.setFill(other);
        g.fillRect(xPos2, yPos2, z1, z2);
        g.setFill(color);

        if (xPos1 > e.getX() && yPos1 < e.getY()) {
            g.fillRect(e.getX(), yPos1, xPos1 - e.getX(), e.getY() - yPos1);
            z1 = xPos1 - e.getX();
            z2 = e.getY() - yPos1;
            xPos2 = e.getX();
            yPos2 = yPos1;
        } else if (xPos1 > e.getX() && yPos1 > e.getY()) {
            g.fillRect(e.getX(), e.getY(), xPos1 - e.getX(), yPos1 - e.getY());
            z1 = xPos1 - e.getX();
            z2 = yPos1 - e.getY();
            xPos2 = e.getX();
            yPos2 = e.getY();
        } else if (xPos1 < e.getX() && yPos1 < e.getY()) {
            g.fillRect(xPos1, yPos1, e.getX() - xPos1, e.getY() - yPos1);
            z1 = e.getX() - xPos1;
            z2 = e.getY() - yPos2;
            xPos2 = xPos1;
            yPos2 = yPos1;
        } else {
            g.fillRect(xPos1, e.getY(), e.getX() - xPos1, yPos1 - e.getY());
            z1 = e.getX() - xPos1;
            z2 = yPos1 - e.getY();
            xPos2 = xPos1;
            yPos2 = e.getY();
        }
    }

    /**
     * Method for action when mouse is released,
     * which creates point on the last position
     *
     * @param e the mouseevent that calls method
     * @param g the current graph context
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        g.setFill(color);
        g.setStroke(color);

        if (xPos1 > e.getX() && yPos1 < e.getY()) {
            g.strokeRect(e.getX(), yPos1, xPos1 - e.getX(), e.getY() - yPos1);
            g.fillRect(e.getX(), yPos1, xPos1 - e.getX(), e.getY() - yPos1);
        } else if (xPos1 > e.getX() && yPos1 > e.getY()) {
            g.strokeRect(e.getX(), e.getY(), xPos1 - e.getX(),
                yPos1 - e.getY());
            g.fillRect(e.getX(), e.getY(), xPos1 - e.getX(),
                yPos1 - e.getY());
        } else if (xPos1 < e.getX() && yPos1 < e.getY()) {
            g.strokeRect(xPos1, yPos1, e.getX() - xPos1, e.getY() - yPos1);
            g.fillRect(xPos1, yPos1, e.getX() - xPos1, e.getY() - yPos1);
        } else {
            g.strokeRect(xPos1, e.getY(), e.getX() - xPos1, yPos1 - e.getY());
            g.fillRect(xPos1, e.getY(), e.getX() - xPos1, yPos1 - e.getY());
        }    
    }

    /**
     * Method for calling the name of this tool
     *
     * @return String tool name
     */
    public String getName() {
        return "Rectangle";
    }
}