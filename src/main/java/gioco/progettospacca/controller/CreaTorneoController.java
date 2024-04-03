package gioco.progettospacca.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class CreaTorneoController implements Initializable {
    @FXML
    Label lbl_npart;
    @FXML
    private Button btn_4players;
    @FXML
    private Button btn_8players;
    @FXML
    private Button btn_16players;

    public void BackToHome(ActionEvent actionEvent) throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_4players.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
        currentScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public void creaTorneo4giocatori(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        EventoCreaTorneo4Giocatori();
    }

    public void creaTorneo8giocatori(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        EventoCreaTorneo8Giocatori();
    }

    public void creaTorneo16giocatori(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        EventoCreaTorneo16Giocatori();
    }

    public void EventoCreaTorneo4Giocatori() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Torneo4giocatoriView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_4players.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));

        // Imposta il titolo della finestra
        //currentStage.setTitle(OPZ.traduci("Crea torneo (4 giocatori)"));
    }

    public void EventoCreaTorneo8Giocatori() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Torneo8giocatoriView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_8players.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));

        // Imposta il titolo della finestra
        //currentStage.setTitle(OPZ.traduci("Crea torneo (8 giocatori)"));
    }

    public void EventoCreaTorneo16Giocatori() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Torneo16giocatoriView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_16players.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));

        // Imposta il titolo della finestra
        //currentStage.setTitle(OPZ.traduci("Crea torneo (16 giocatori)"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_4players.setOnMouseEntered(e -> {
            if (btn_4players.getScene() != null) {
                btn_4players.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_4players.setOnMouseExited(e -> {
            if (btn_4players.getScene() != null) {
                btn_4players.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_8players.setOnMouseEntered(e -> {
            if (btn_8players.getScene() != null) {
                btn_8players.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_8players.setOnMouseExited(e -> {
            if (btn_8players.getScene() != null) {
                btn_8players.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_16players.setOnMouseEntered(e -> {
            if (btn_16players.getScene() != null) {
                btn_16players.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_16players.setOnMouseExited(e -> {
            if (btn_16players.getScene() != null) {
                btn_16players.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        lbl_npart.setText(OPZ.traduci("inserisci_partecipanti"));
    }
}
