package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import gioco.progettospacca.classi.ValoriTorneo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class Torneo16giocatoriController implements Initializable {
    @FXML
    Label lbl_nomi_giocatori;
    @FXML
    Button btn_crea;
    @FXML
    Label lbl_codice;
    @FXML
    Button btn_home;
    @FXML
    TextField txt_gioc1,txt_gioc2,txt_gioc3,txt_gioc4,txt_gioc5,txt_gioc6,txt_gioc7,txt_gioc8,
            txt_gioc9,txt_gioc10,txt_gioc11,txt_gioc12,txt_gioc13,txt_gioc14,txt_gioc15,txt_gioc16,txt_codice;
    @FXML
    CheckBox chk_gioc1,chk_gioc2,chk_gioc3,chk_gioc4,chk_gioc5,chk_gioc6,chk_gioc7,chk_gioc8,
            chk_gioc9,chk_gioc10,chk_gioc11,chk_gioc12,chk_gioc13,chk_gioc14,chk_gioc15,chk_gioc16;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_nomi_giocatori.setText(OPZ.traduci("nomi_giocatori"));
        btn_crea.setText(OPZ.traduci("crea_torneo"));
        lbl_codice.setText(OPZ.traduci("codice_torneo"));
        btn_home.setText(OPZ.traduci("torna_alla_home"));
    }
    public void creaTorneo() {
        ArrayList<ValoriTorneo> tf = new ArrayList<>();
        tf.add(new ValoriTorneo(txt_gioc1,chk_gioc1));
        tf.add(new ValoriTorneo(txt_gioc2,chk_gioc2));
        tf.add(new ValoriTorneo(txt_gioc3,chk_gioc3));
        tf.add(new ValoriTorneo(txt_gioc4,chk_gioc4));
        tf.add(new ValoriTorneo(txt_gioc5,chk_gioc5));
        tf.add(new ValoriTorneo(txt_gioc6,chk_gioc6));
        tf.add(new ValoriTorneo(txt_gioc7,chk_gioc7));
        tf.add(new ValoriTorneo(txt_gioc8,chk_gioc8));
        tf.add(new ValoriTorneo(txt_gioc9,chk_gioc9));
        tf.add(new ValoriTorneo(txt_gioc10,chk_gioc10));
        tf.add(new ValoriTorneo(txt_gioc11,chk_gioc11));
        tf.add(new ValoriTorneo(txt_gioc12,chk_gioc12));
        tf.add(new ValoriTorneo(txt_gioc13,chk_gioc13));
        tf.add(new ValoriTorneo(txt_gioc14,chk_gioc14));
        tf.add(new ValoriTorneo(txt_gioc15,chk_gioc15));
        tf.add(new ValoriTorneo(txt_gioc16,chk_gioc16));
        Torneo.controlloLabel(tf,16,txt_codice);
    }

    public void cliccaCreaTorneo(MouseEvent mouseEvent) {
        creaTorneo();
    }
    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("CreaTorneo.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_home.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
    }

}
