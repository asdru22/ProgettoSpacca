package gioco.progettospacca.classi;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create an ArrayList of integers
        ArrayList<Integer> integerList = Utili.elencaPartite(true,true);

        // Convert the ArrayList to an ObservableList
        ObservableList<Integer> items = FXCollections.observableArrayList(integerList);

        // Create the ComboBox and set its items
        ComboBox<Integer> comboBox = new ComboBox<>(items);

        // Create a layout and add the ComboBox to it
        VBox root = new VBox(comboBox);

        // Create a scene and set the layout as its root
        Scene scene = new Scene(root, 200, 100);

        // Set the scene to the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dropdown Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

