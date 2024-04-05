package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class PrivilegiAdminController implements Initializable {
    @FXML
    Label lbl_titlePrivilegi;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_partiteSalvate;
    @FXML
    private Button btn_modificaGiocatore;
    @FXML
    private Button btn_cambiaTurni;
    @FXML
    private TextField txt_nturni;


    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_back.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
    }

    public void backToHome(MouseEvent mouseEvent) throws IOException {
        BackToHome();
    }

    public void partiteSalvate(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        EventoPartiteSalvate();
    }

    private void EventoPartiteSalvate() throws IOException {

        // Carica la nuova radice della scena
        Parent root = FXMLLoader.load(getClass().getResource("PartiteSalvateView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_partiteSalvate.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        currentScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        // Imposta il titolo della finestra
        //currentStage.setTitle(OPZ.traduci("Partite salvate"));
    }

    public void modificaGiocatore(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        EventoModificaGiocatore();
    }

    private void EventoModificaGiocatore() throws IOException {

        // Carica la nuova radice della scena
        Parent root = FXMLLoader.load(getClass().getResource("ModificaGiocatoreView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_modificaGiocatore.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        currentScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        // Imposta il titolo della finestra
        //currentStage.setTitle(OPZ.traduci("Modifica giocatore"));
    }

    private void EventoCambiaTurni()throws IOException
    {

    }
    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            BackToHome();
        }
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (btn_partiteSalvate.isFocused()) {
                EventoPartiteSalvate();
            } else if (btn_back.isFocused()) {
                BackToHome();
            } else if (btn_modificaGiocatore.isFocused()) {
                EventoModificaGiocatore();
            }
        }

        if (keyEvent.getCode() == KeyCode.UP) {
            OPZ.premiFreccia();
            if (btn_back.isFocused()) {
                System.out.println("sei già in alto");
            } else if (btn_partiteSalvate.isFocused()) {
                btn_back.requestFocus();
            } else if (btn_modificaGiocatore.isFocused()) {
                btn_partiteSalvate.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.DOWN) {
            OPZ.premiFreccia();
            if (btn_modificaGiocatore.isFocused()) {
                System.out.println("sei già in basso");
            } else if (btn_back.isFocused()) {
                btn_partiteSalvate.requestFocus();
            } else if (btn_partiteSalvate.isFocused()) {
                btn_modificaGiocatore.requestFocus();
            }
        }
        pulisci();
    }

    private void pulisci() {
        btn_partiteSalvate.setFocusTraversable(false);
        btn_modificaGiocatore.setFocusTraversable(false);
        btn_back.setFocusTraversable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_modificaGiocatore.setOnMouseEntered(e -> {
            if (btn_modificaGiocatore.getScene() != null) {
                btn_modificaGiocatore.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_modificaGiocatore.setOnMouseExited(e -> {
            if (btn_modificaGiocatore.getScene() != null) {
                btn_modificaGiocatore.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_back.setOnMouseEntered(e -> {
            if (btn_back.getScene() != null) {
                btn_back.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_back.setOnMouseExited(e -> {
            if (btn_back.getScene() != null) {
                btn_back.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_partiteSalvate.setOnMouseEntered(e -> {
            if (btn_partiteSalvate.getScene() != null) {
                btn_partiteSalvate.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_partiteSalvate.setOnMouseExited(e -> {
            if (btn_partiteSalvate.getScene() != null) {
                btn_partiteSalvate.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_cambiaTurni.setOnMouseEntered(e -> {
            if (btn_cambiaTurni.getScene() != null) {
                btn_cambiaTurni.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_cambiaTurni.setOnMouseExited(e -> {
            if (btn_cambiaTurni.getScene() != null) {
                btn_cambiaTurni.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_partiteSalvate.setText(OPZ.traduci("partite_salvate"));
        btn_modificaGiocatore.setText(OPZ.traduci("modifica_giocatore"));
        lbl_titlePrivilegi.setText(OPZ.traduci("privilegi_amministratore"));

    }

}
