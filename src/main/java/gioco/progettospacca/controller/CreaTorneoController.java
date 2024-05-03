package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class CreaTorneoController implements Initializable {
    @FXML
    private Label lbl_npart;
    @FXML
    private Button btn_4players;
    @FXML
    private Button btn_8players;
    @FXML
    private Button btn_16players;
    @FXML
    private Button btn_back;
    @FXML
    private ImageView imageFocus;

    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_4players.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public void EventoCreaTorneo4Giocatori() throws IOException {
        OPZ.premiBottone();

        Parent root = FXMLLoader.load(getClass().getResource("Torneo4giocatoriView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_4players.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));

    }

    public void EventoCreaTorneo8Giocatori() throws IOException {
        OPZ.premiBottone();

        Parent root = FXMLLoader.load(getClass().getResource("Torneo8giocatoriView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_8players.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));

        // Imposta il titolo della finestra
    }

    public void EventoCreaTorneo16Giocatori() throws IOException {

        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("Torneo16giocatoriView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_16players.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));


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

    public void keyEvent(KeyEvent keyEvent) {
        btn_4players.setFocusTraversable(false);

        if(imageFocus.isFocusTraversable()){
            imageFocus.setFocusTraversable(false);
            btn_4players.requestFocus();
            return;
        }

        if (keyEvent.getCode() == KeyCode.UP && (btn_4players.isFocused() || btn_8players.isFocused() || btn_16players.isFocused())) {
            btn_back.requestFocus();
        } else if (keyEvent.getCode() == KeyCode.UP && btn_back.isFocused()) {
        }

        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (btn_back.isFocused()) {
                btn_4players.requestFocus();
            } else if (btn_4players.isFocused() || btn_8players.isFocused() || btn_16players.isFocused()) {
            }
        }
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            if (btn_4players.isFocused()) {
                btn_8players.requestFocus();
            } else if (btn_8players.isFocused()) {
                btn_16players.requestFocus();
            } else if (btn_16players.isFocused()) {
            }
        }
        if (keyEvent.getCode() == KeyCode.LEFT) {
            if (btn_16players.isFocused()) {
                btn_8players.requestFocus();
            } else if (btn_8players.isFocused()) {
                btn_4players.requestFocus();
            } else if (btn_4players.isFocused()) {
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_back.isFocused()) {
            try {
                BackToHome();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_4players.isFocused()) {
            try {
                EventoCreaTorneo4Giocatori();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_8players.isFocused()) {
            try {
                EventoCreaTorneo8Giocatori();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_16players.isFocused()) {
            try {
                EventoCreaTorneo16Giocatori();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            try {
                BackToHome();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
