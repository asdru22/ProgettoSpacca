package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import gioco.progettospacca.classi.ValoriTorneo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class Torneo4giocatoriController implements Initializable {
    @FXML
    Label lbl_nomi_giocatori;
    @FXML
    Button btn_crea;
    @FXML
    Label lbl_codice;
    @FXML
    Button btn_home;
    @FXML
    TextField txt_gioc1,txt_gioc2,txt_gioc3,txt_gioc4,txt_codice;
    @FXML
    CheckBox chk_gioc1,chk_gioc2,chk_gioc3,chk_gioc4;
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
        Torneo.controlloLabel(tf,4,txt_codice);
    }

    public void cliccaCreaTorneo(MouseEvent mouseEvent) {
        creaTorneo();
    }
}
