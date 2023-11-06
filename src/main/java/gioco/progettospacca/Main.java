package gioco.progettospacca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();
        System.out.println("alle gatto");
    }

    public static void main(String[] args) {
        launch(args);
    }
}