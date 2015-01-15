import javafx.scene.paint.Color;

/**
* Abstract class that implements tools
* 
* @author Matthew Le
* @version 1.0
*/
public abstract class AbstractTools implements Tool {

    protected Color color;
    protected Color other;

    /**
    * method allows user to choose color
    *
    * @param color color of tool wanted
    */
    public void colorSet(Color c) {
        color = c;
    }

    /**
    * 
    *
    *
    * @param color the color for tool
    */
    public void colorSetBack(Color c) {
        other = c;
    }
}