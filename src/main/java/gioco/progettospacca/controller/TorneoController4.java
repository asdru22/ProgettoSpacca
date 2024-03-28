package gioco.progettospacca.controller;

import gioco.progettospacca.classi.BottoneTorneo;
import gioco.progettospacca.classi.Torneo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.*;


public class TorneoController4 implements Initializable {
    @FXML
    BottoneTorneo btn_semi1,btn_semi2,btn_finale;
    public void inizio() throws IOException {

        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(0).getId();
        OPZ.premiBottone();
        OPZ.playMusica("gioco.mp3");

        Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));
        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_finale.getScene().getWindow();
        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();
        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        btn_semi1.inizializza(t);
        btn_semi2.inizializza(t);
        btn_finale.inizializza(t);

    }

    public void giocaPartita(MouseEvent mouseEvent) throws IOException {
        BottoneTorneo b = (BottoneTorneo)mouseEvent.getSource();
        CODICE_GLOBALE_PARTITA = b.getPartita();
        OPZ.premiBottone();
        OPZ.playMusica("gioco.mp3");
        Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) b.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
    }
}
