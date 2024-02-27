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
        //String css=this.getClass().getResource("style.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(500);
        stage.show();
    }

    public static void main(String[] args) {
        Utili.setLingua(Locale.ENGLISH); // lingua di default
        launch(args);
    }
}