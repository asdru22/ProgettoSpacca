package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

import static gioco.progettospacca.controller.Main.OPZ;

public class LeaderboardPartitaController {
    @FXML
    public Label lbl_classifica;
    @FXML
    private Label lbl_pos1;
    @FXML
    private Label lbl_pos2;
    @FXML
    private Label lbl_pos3;
    @FXML
    private Label lbl_pos4;
    @FXML
    private Label lbl_pos5;
    @FXML
    private Label lbl_pos6;
    @FXML
    private Label lbl_pos7;
    @FXML
    private Label lbl_pos8;
    @FXML
    private Label lbl_pos9;
    @FXML
    private Label lbl_pos10;

    @FXML
    public void initialize() throws FileNotFoundException {
        String[] lead = Utili.getLeaderboard();
        lbl_pos1.setText(lead[0]);
        lbl_pos2.setText(lead[1]);
        lbl_pos3.setText(lead[2]);
        lbl_pos4.setText(lead[3]);
        lbl_pos5.setText(lead[4]);
        lbl_pos6.setText(lead[5]);
        lbl_pos7.setText(lead[6]);
        lbl_pos8.setText(lead[7]);
        lbl_pos9.setText(lead[8]);
        lbl_pos10.setText(lead[9]);

        lbl_classifica.setText(OPZ.traduci("classifica"));
    }

    public void back() {
        OPZ.premiBottone();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) lbl_classifica.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
    }

    public void keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
    }
}
