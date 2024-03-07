package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Utili;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle(Utili.traduci("pokermon"));

        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene=new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        //Utili.suono("lobby.wav");

    }

    public static void main(String[] args) {
        Utili.getLingua(); // lingua di default
        launch(args);
    }
}