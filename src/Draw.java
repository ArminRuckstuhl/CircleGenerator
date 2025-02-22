
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.*;
import javafx.scene.*;
import javafx.application.Application;
import javafx.geometry.*;
import org.w3c.dom.Text;


public class Draw extends Application {


    private Stage window;
    private static final int screenWidth = 1152;
    private static final int screenHeight = 648;
    private int diameter;
    private Label errorLabel;
    private TextField textField;

    /**
     * The main entry point for this program
     *
     * @param primaryStage is the scene where the application is set. Provided by JavaFX
     */
    @Override
    public void start(Stage primaryStage){

        window = primaryStage;
        window.setTitle("Minecraft Circle Generator");
        BorderPane layout = createMenuLayout();

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    /**
     * Creates the root container for the interface of the program
     *
     * @return BorderPane which serves as the root container
     * @see #createMenuContainer() for the creation of the right side menu
     */
    private BorderPane createMenuLayout(){
        BorderPane layout = new BorderPane();
        HBox menuContainer = createMenuContainer();
        layout.setRight(menuContainer);
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
        menuPanel.setPadding(new Insets(screenWidth * 0.005));
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
        textField.setPrefColumnCount(10);
        textField.setPromptText("Width/Height");

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
            generateShape();
            errorLabel.setText("");
        } catch (NumberFormatException nfe) {
            errorLabel.setText("Please enter a round number");
        }
    }

    public void generateShape(){
        System.out.println(diameter);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
