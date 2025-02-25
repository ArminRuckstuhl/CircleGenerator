import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

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
     * Returns the X Position of the top left corner of the cell
     * @return the current X Position of the cell
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Returns the Y Position of the top left corner of the cell
     * @return the current Y Position of the cell
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Returns the size of the cell
     * @return the current total size of the cell, including the 18.5% padding
     */
    public double getSize() {
        return size;
    }

    /**
     * Returns the rectangle object so it can be drawn on the drawPane
     * @return the Rectangle object
     */
    public Rectangle getRectangle(){
        return this.rectangle;
    }

    /**
     * Calculates the parameters of and then contructs the rectangle objects which make up the cells
     *
     * @see {@link Rectangle} the object used
     */
    public void createRectangle(){
        double padding = size * PADDING_PERCENTAGE;
        double paddedSize = size - (2 * padding); // Total padding is 18.75%

        rectangle = new Rectangle(paddedSize, paddedSize);
        rectangle.setX(xPos + padding);
        rectangle.setY(yPos + padding);
        rectangle.setFill(isShaded ? Paint.valueOf("#097969") : Paint.valueOf("#E4D00A"));
    }


    /**
     * Toggles whether a cell is shaded or not
     * @param isShaded value to set
     */
    public void setShaded (boolean isShaded){
        this.isShaded = isShaded;
        getRectangle().setFill(isShaded ? Paint.valueOf("#097969") : Paint.valueOf("#E4D00A"));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.xPos + " " + this.yPos + " " + this.size + " " + this.isShaded);
        return str.toString();
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (!(other instanceof Cell)) return false;
        Cell otherCell = (Cell)other;
        return this.xPos == otherCell.getxPos() && this.yPos == otherCell.getyPos();
    }

    @Override
    public int hashCode(){
        return Objects.hash(xPos, yPos);
    }
}
