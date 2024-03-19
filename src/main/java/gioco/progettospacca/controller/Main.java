package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Opzioni;
import gioco.progettospacca.classi.Utili;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static Opzioni OPZ;

    static {
        try {
            OPZ = Opzioni.carica();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String PATH = Utili.getPath()+ File.separator;

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle(OPZ.traduci("spacca"));

        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/gioco/progettospacca/Logo.png")));
        stage.show();
        stage.setResizable(false);

        OPZ.playMusica("lobby.wav");

    }

    public static void main(String[] args) {
        launch(args);
    }
}