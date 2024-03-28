package gioco.progettospacca.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

import static gioco.progettospacca.controller.Main.OPZ;

public class GiocaTorneoController {
    @FXML
    private Button btn_entraTorneo;

    public void BackToHome(ActionEvent actionEvent) throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle(OPZ.traduci("spacca"));
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }
    public void giocaTorneo(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        EventoGiocaTorneo();
    }

    public void EventoGiocaTorneo() throws IOException
    {

        Parent root = FXMLLoader.load(getClass().getResource("TorneoView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_entraTorneo.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("gioca_torneo"));


    }

}
