package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.*;


public class TorneoController implements Initializable {
    @FXML
    Label lbl_prova;
    public void inizio(Torneo t) throws IOException {
        CODICE_GLOBALE_PARTITA = t.getPartite().get(0).getId();
        OPZ.premiBottone();
        OPZ.playMusica("gioco.mp3");

        Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));
        // Ottieni la finestra corrente
        Stage currentStage = (Stage) lbl_prova.getScene().getWindow();
        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();
        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);

        try {
            inizio(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
