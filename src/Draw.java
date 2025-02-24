
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.*;
import javafx.scene.*;
import javafx.application.Application;
import javafx.geometry.*;

import java.util.*;


public class Draw extends Application {


    private Stage window;
    private static final int screenWidth = 904;
    private static final int screenHeight = 648;
    private int diameter;
    private Label errorLabel;
    private TextField textField;
    private List<List<Cell>> cells = new ArrayList<>();
    private static final int MENU_WIDTH = 256;
    private BorderPane layout;
    private Pane drawPane;

    /**
     * The main entry point for this program
     *
     * @param primaryStage is the scene where the application is set. Provided by JavaFX
     */
    @Override
    public void start(Stage primaryStage){

        window = primaryStage;
        window.setTitle("Minecraft Circle Generator");
        BorderPane layout = createLayout();

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    /**
     * Creates the root container for the interface of the program
     *
     * @return {@link BorderPane} serves as the root container
     * @see #createMenuContainer() for the creation of the right side menu
     */
    private BorderPane createLayout(){
        layout = new BorderPane();
        HBox menuContainer = createMenuContainer();
        createDrawPane();

        layout.setRight(menuContainer);
        layout.setLeft(drawPane);
        return layout;
    }

    /**
     * Creating the container for the menu panel and the vertical separator.
     * This container is nested inside the root BorderPane
     *
     * @return {@link HBox} Contains both the menu panel and the separator
     * @see #createMenuPanel() for the creation of the menu content
     */
    private HBox createMenuContainer(){
        VBox menuPanel = createMenuPanel();

        Line separator = new Line(screenWidth - (screenWidth * 0.2), 0, screenWidth - (screenWidth * 0.2), screenHeight);
        separator.setStrokeWidth(3);

        return new HBox(separator, menuPanel);
    }

    /**
     * Builds the menuPanel with the input text field and button
     *
     * @return {@link VBox} Contains the input field, the action button, and the error field
     * @see #createInputLine() for text component creation
     * @see #handleSubmitAction(ActionEvent) for event handling logic
     */
    private VBox createMenuPanel(){

        // The VBox places the different components on different lines
        VBox menuPanel = new VBox(20);
        menuPanel.setStyle("-fx-background-color: #c5c5c5;");
        menuPanel.setPrefWidth(MENU_WIDTH);
        menuPanel.setPadding(new Insets(MENU_WIDTH * 0.05));
        menuPanel.setAlignment(Pos.CENTER);

        // Using an HBox for my label and text field ensures that they are on the same line
        HBox inputLine = createInputLine();

        // Constructing button
        Button submitButton = new Button("Generate");
        submitButton.setPrefWidth(100);
        submitButton.setOnAction(this::handleSubmitAction);

        // Error label is used to display an error message to the user
        errorLabel = new Label("");
        menuPanel.getChildren().addAll(inputLine, submitButton, errorLabel);

        return menuPanel;
    }

    /**
     * Creates the horizontal input line containing the label and the text field
     *
     * @return {@link HBox} the container
     */
    private HBox createInputLine(){

        HBox input = new HBox(10);

        Label label = new Label("Enter Diameter: ");

        textField = new TextField();
        textField.setPromptText("Width/Height");
        textField.setPrefColumnCount(10);

        input.getChildren().addAll(label, textField);
        return input;
    }

    /**
     * Handles the logic and processing of the user's input when the button is pressed.
     *
     *  - Checks for empty textfield
     *  - Validates that input is an int
     *  - Triggers the shape generation
     *  - Manages the errorfield display
     *
     * @param event The event triggered by a button press
     * @throws NumberFormatException if the input is not a valid int
     */
    private void handleSubmitAction(ActionEvent event){
        if (textField.getText().isEmpty()) return;

        // Checking that number entered is an int
        try {
            diameter = Integer.parseInt(textField.getText());
            generateGrid();
            errorLabel.setText("");
        } catch (NumberFormatException nfe) {
            errorLabel.setText("Please enter a round number");
        }
    }

    /**
     * Creates the zone of the window where the circle schematic will be drawn
     *
     * @return {@link Pane} The container of the drawPane
     */
    private void createDrawPane(){
        drawPane = new Pane();

        // Making the pane a square makes it a lot easier to figure out how large each cell should be when drawing it
        drawPane.setPrefWidth(screenHeight);
        drawPane.setPrefHeight(screenHeight);
        drawPane.setStyle("-fx-background-color: #e5e4e2");
    }

    /**
     * Generates the grid of {@link Cell}
     * Dimensions of the grid depend on the diameter of circle the user chooses
     */
    public void generateGrid(){
        //Setup
        double cellSize = screenHeight / diameter; // Resizing the cells based off the circle diameter so they fit the pane
        cells.clear();
        drawPane.getChildren().clear();

        // Creating and drawing cells
        for (int col = 0; col < diameter; col++){
            List<Cell> fullRow = new ArrayList<>();
            for (int row = 0; row < diameter; row++){
                Cell cell = new Cell(row * cellSize, col * cellSize, cellSize, false);
                fullRow.add(cell);
                drawPane.getChildren().add(cell.getRectangle());
            }
            cells.add(fullRow);
        }

        // Shading centre cell if diameter is odd
        if (diameter % 2 == 1) {
            Cell centre = getCellAt(screenHeight / 2, screenHeight / 2);
            centre.setShaded(true);
        }

        layout.setLeft(drawPane); // Re-displaying the drawPane

    }

    /**
     * Finds the {@link Cell} at the specified drawing pane coordinates.
     *
     * @param x the X-coordinate within the drawing pane
     * @param y the Y-coordinate within the drawing pane
     * @return the {@link Cell} at the specified coordinates, or {@code null} if coordinates
     *         are outside the grid or invalid
     */
    public Cell getCellAt(double x, double y) {
        if (diameter <= 0 || cells.isEmpty() || x < 0 || y < 0) {
            return null;
        }

        double cellSize = screenHeight / diameter;
        int col = (int) (x / cellSize);
        int row = (int) (y / cellSize);

        if (row < diameter && col < diameter) {
            return cells.get(row).get(col);
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
