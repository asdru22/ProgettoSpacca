package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Suoni;
import gioco.progettospacca.classi.Utili;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle(Utili.traduci("spacca"));

        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene=new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/gioco/progettospacca/Logo.png"));
        stage.show();
        stage.setResizable(false);

        Utili.suono("lobby.wav", Suoni.Musica);

    }

    public static void main(String[] args) {
        Utili.getLingua(); // lingua di default
        launch(args);
    }
}