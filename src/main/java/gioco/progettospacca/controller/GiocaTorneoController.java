package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Utili;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import gioco.progettospacca.classi.Torneo;

import static gioco.progettospacca.controller.Main.CODICE_GLOBALE_TORNEO;
import static gioco.progettospacca.controller.Main.OPZ;

public class GiocaTorneoController implements Initializable {
    @FXML
    private Button btn_entraTorneo, btn_home;
    @FXML
    TextField txt_codTorneo;
    @FXML
    Label lbl_titleCodTorneo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        btn_entraTorneo.setOnMouseEntered(e -> {
            if (btn_entraTorneo.getScene() != null) {
                btn_entraTorneo.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_entraTorneo.setOnMouseExited(e -> {
            if (btn_entraTorneo.getScene() != null) {
                btn_entraTorneo.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_entraTorneo.setText(OPZ.traduci("entra"));
        lbl_titleCodTorneo.setText(OPZ.traduci("inserisci_codice_torneo"));
    }

    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_entraTorneo.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
        currentScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public void giocaTorneo(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        CODICE_GLOBALE_TORNEO = Utili.leggiInt(txt_codTorneo);

        EventoGiocaTorneo();

    }

    public void EventoGiocaTorneo() throws IOException {
        int giocatori = Torneo.carica(CODICE_GLOBALE_TORNEO).getGiocatoriIniziali();
        Parent root = null;
        if(giocatori==4) root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TorneoView4.fxml")));
        if(giocatori==8) root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TorneoView8.fxml")));
        if(giocatori==16) root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TorneoView16.fxml")));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_entraTorneo.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("gioca_torneo"));
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));


    }

    public void keyEvent(KeyEvent keyEvent){
        txt_codTorneo.setFocusTraversable(false);
        if (keyEvent.getCode() == KeyCode.UP) {
            if(txt_codTorneo.isFocused()){
                btn_home.requestFocus();
            } else if (btn_home.isFocused()) {
                System.out.println("sei già in alto");
            } else if (btn_entraTorneo.isFocused()) {
                txt_codTorneo.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.DOWN) {
            if(txt_codTorneo.isFocused()){
                btn_entraTorneo.requestFocus();
            } else if (btn_home.isFocused()) {
                txt_codTorneo.requestFocus();
            } else if (btn_entraTorneo.isFocused()) {
                System.out.println("sei già in basso");
            }
        }
        if(keyEvent.getCode() == KeyCode.ENTER && btn_home.isFocused()){
            try {
                BackToHome();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(keyEvent.getCode() == KeyCode.ENTER && btn_entraTorneo.isFocused()){
            try {
                EventoGiocaTorneo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
