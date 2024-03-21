package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static gioco.progettospacca.controller.Main.OPZ;

public class ModificaGiocatoreController {
    @FXML
    private TextField txt_vecchioNome;
    @FXML
    private TextField txt_nuovoNome;
    @FXML
    private Button btn_confermaCambioNome;
    @FXML
    private Button btn_back;

    public void giocaPartita(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        EventoCambioNome();
    }
    public void EventoCambioNome() throws IOException {
        String vecchio = String.valueOf((txt_vecchioNome.getText()));
        String nuovo = String.valueOf((txt_nuovoNome.getText()));

        //dall'array di giocatori esistenti andare a prendere quello col nome vecchio scritto e cambiarlo col nome nuovo

    }
    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("PrivilegiAdminView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_back.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
    }

}
