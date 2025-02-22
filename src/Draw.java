
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.*;
import javafx.scene.*;
import javafx.application.Application;
import javafx.geometry.*;


public class Draw extends Application {



    private Stage window;
    private static final int screenWidth = 1152;
    private static final int screenHeight = 648;
    private int diameter;

    @Override
    public void start(Stage primaryStage){

        //Generating Window
        window = primaryStage;
        window.setTitle("Minecraft Circle Generator");


        Insets padding = new Insets(screenWidth * 0.005);

        VBox menuPanel = new VBox(20);
        menuPanel.setStyle("-fx-background-color: #c5c5c5;");
        menuPanel.setPadding(padding);
        menuPanel.setAlignment(Pos.CENTER);


        BorderPane layout = new BorderPane();

        // Using an HBox for my label and text field ensures that they are on the same line
        HBox input = new HBox(10);
        Label label = new Label("Enter Diameter: ");
        TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        textField.setPromptText("Width/Height");
        input.getChildren().addAll(label, textField);


        // Code for the button, on a new line due to using VBox
        Button submitButton = new Button("Generate");
        submitButton.setPrefWidth(100);
        menuPanel.getChildren().addAll(input, submitButton);

        Label errorLabel = new Label("");
        menuPanel.getChildren().add(errorLabel);

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!textField.getText().isEmpty()){

                    // Checking that number entered is an int
                    try {
                        diameter = Integer.parseInt(textField.getText());

                        generateShape();
                    } catch (NumberFormatException nfe) {
                        errorLabel.setText("Please enter a round number");
                    }
                }
            }
        });


        Line separator = new Line(screenWidth - (screenWidth * 0.2), 0, screenWidth - (screenWidth * 0.2), screenHeight);
        separator.setStrokeWidth(3);


        HBox menuContainer = new HBox(separator, menuPanel);

        layout.setRight(menuContainer);

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    public void generateShape(){
        System.out.println(diameter);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
