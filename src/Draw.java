
import javafx.scene.control.Separator;
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
    private int width;
    private int height;

    @Override
    public void start(Stage primaryStage){

        width = 1152;
        height = 648;

        //Generating Window
        window = primaryStage;
        window.setTitle("Minecraft Circle Generator");

        Insets padding = new Insets(width * 0.1);

        VBox menuPanel = new VBox(10);
        menuPanel.setStyle("-fx-background-color: #c5c5c5;");
        menuPanel.setPadding(padding);
        BorderPane layout = new BorderPane();

//        Separator separator = new Separator(Orientation.VERTICAL);
//        separator.setStyle("-fx-background-color: #000; " +
//                "-fx-border-color: null; " +
//                "-fx-border-width: 0px;"
//        );
        Line separator = new Line(width - (width * 0.2), 0, width - (width * 0.2), height);
        separator.setStrokeWidth(3);


        HBox menuContainer = new HBox(separator, menuPanel);

        layout.setRight(menuContainer);

        Scene scene = new Scene(layout, width, height);
        window.setScene(scene);
        window.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
