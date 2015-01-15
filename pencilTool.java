import javafx.scene.shape.ArcType;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
* Pencil drawing tool
*
* @author Matthew Le
* @version 1.6
*/

public class pencilTool extends AbstractTools {

    private int thickness;
    private double xPos;
    private double yPos;

    /**
    * The constructor for a Pencil
    *
    * @param Color color color of pencil
    */    
    public pencilTool(int thickness, Color color) {
        this.thickness = thickness;
        this.color = color;
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
        xPos = e.getX();
        yPos = e.getY();

        g.setFill(color);

        g.fillArc(xPos - (thickness / 2), yPos - (thickness / 2), thickness,
            thickness, 0.0, 360.0, ArcType.ROUND); 
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
        xPos = e.getX();
        yPos = e.getY();

        g.setFill(color);
        g.setStroke(color);

        g.fillArc(e.getX() - (thickness / 2), e.getY() - (thickness / 2),
            thickness, thickness, 0.0, 360.0, ArcType.ROUND);
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

        g.fillArc(e.getX() - (thickness / 2), e.getY() - (thickness / 2),
            thickness, thickness, 0.0, 360.0, ArcType.ROUND);
    }

    /**
     * Method for calling the name of this tool
     *
     * @return String tool name
     */
    public String getName() {
        if (thickness == 6) {
            return "Pencil (thin)";
        } else if (thickness == 12) {
            return "Pencil (THICK)";
        } else if (thickness == 5) {
            return "Eraser (thin)";
        }
        else {
            return "Eraser (THICK)";
        }
    }
}