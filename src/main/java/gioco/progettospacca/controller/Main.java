package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Opzioni;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
public class Main extends Application {
    public static Opzioni OPZ = Opzioni.carica();
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle(OPZ.traduci("spacca"));

        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene=new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/gioco/progettospacca/Logo.png"));
        stage.show();
        stage.setResizable(false);

        OPZ.playMusica("lobby.wav");

    }

    public static void main(String[] args) {
        launch(args);
    }
}