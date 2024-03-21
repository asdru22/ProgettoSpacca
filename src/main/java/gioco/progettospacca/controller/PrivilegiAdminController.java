package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static gioco.progettospacca.controller.Main.OPZ;

public class PrivilegiAdminController {

    @FXML
    private Button btn_back;
    @FXML
    private Button btn_partiteSalvate;
    @FXML
    private Button btn_modificaGiocatore;


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

    private void EventoPartiteSalvate() throws IOException{

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
    private void EventoModificaGiocatore()throws IOException {

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
    public void keyEvent(KeyEvent keyEvent) throws IOException {
        OPZ.premiFreccia();
        pulisci();
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
            if (btn_back.isFocused()) {
                System.out.println("sei già in alto");
            } else if (btn_partiteSalvate.isFocused()) {
                OPZ.premiFreccia();
                btn_back.requestFocus();
            } else if (btn_modificaGiocatore.isFocused()) {
                OPZ.premiFreccia();
                btn_partiteSalvate.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (btn_modificaGiocatore.isFocused()) {
                System.out.println("sei già in basso");
            } else if (btn_back.isFocused()) {
                OPZ.premiFreccia();
                btn_partiteSalvate.requestFocus();
            } else if (btn_partiteSalvate.isFocused()) {
                OPZ.premiFreccia();
                btn_modificaGiocatore.requestFocus();
            }
        }
    }
    private void pulisci() {
        btn_partiteSalvate.setFocusTraversable(false);
        btn_modificaGiocatore.setFocusTraversable(false);
        btn_back.setFocusTraversable(false);
    }


}
