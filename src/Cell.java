import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Cell {
    private double xPos;
    private double yPos;
    private double size;
    private boolean isShaded;
    private Rectangle rectangle;
    private static final double PADDING_PERCENTAGE = 0.09375; // Based on some testing, a total padding of 18.75% looked best so 9.375% on each side


    public Cell(double xPos, double yPos, double size, boolean isShaded) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.isShaded = isShaded;
        createRectangle();
    }

    /**
     * Calculates the parameters of and then contructs the rectangle objects which make up the cells
     *
     * @see {@link Rectangle} the object used
     */
    private void createRectangle(){
        double padding = size * PADDING_PERCENTAGE;
        double paddedSize = size - (2 * padding); // Total padding is 18.75%

        rectangle = new Rectangle(paddedSize, paddedSize);
        rectangle.setX(xPos + padding);
        rectangle.setY(yPos + padding);
        rectangle.setFill(isShaded ? Paint.valueOf("#097969") : Paint.valueOf("#E4D00A"));
    }

    /**
     * Returns the rectangle object so it can be drawn on the drawPane
     *
     * @return the Rectangle object
     */
    public Rectangle getRectangle(){
        return this.rectangle;
    }
}
