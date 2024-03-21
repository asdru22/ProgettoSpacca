package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Opzioni;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static Opzioni OPZ;

    static {
        try {
            OPZ = Opzioni.carica();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle(OPZ.traduci("spacca"));

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeView.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/gioco/progettospacca/Logo.png"))));
        stage.show();
        stage.setResizable(false);

        OPZ.playMusica("lobby.wav");

    }

    public static void main(String[] args) {
        launch(args);
    }
}