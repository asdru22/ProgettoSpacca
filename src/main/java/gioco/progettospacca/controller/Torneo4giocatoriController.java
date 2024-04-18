package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import gioco.progettospacca.classi.ValoriTorneo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
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

import static gioco.progettospacca.classi.Utili.checkBox;
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

    private int checkBoxSelezionati = 0;


    public void checkBox1(MouseEvent mouseEvent) {
        checkBox(txt_gioc1,chk_gioc1,checkBoxSelezionati);
        txt_gioc2.requestFocus();
    }

    public void checkBox2(MouseEvent mouseEvent) {
        checkBox(txt_gioc2,chk_gioc2,checkBoxSelezionati);
        txt_gioc3.requestFocus();
    }

    public void checkBox3(MouseEvent mouseEvent) {
        checkBox(txt_gioc3,chk_gioc3,checkBoxSelezionati);
        txt_gioc4.requestFocus();
    }

    public void checkBox4(MouseEvent mouseEvent) {
        checkBox(txt_gioc4,chk_gioc4,checkBoxSelezionati);
        btn_crea.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_crea.setOnMouseEntered(e -> {
            if (btn_crea.getScene() != null) {
                btn_crea.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_crea.setOnMouseExited(e -> {
            if (btn_crea.getScene() != null) {
                btn_crea.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_home.setOnMouseEntered(e -> {
            if (btn_home.getScene() != null) {
                btn_home.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_home.setOnMouseExited(e -> {
            if (btn_home.getScene() != null) {
                btn_home.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
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

    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_home.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
    }
}
