package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class CambiaLinguaController implements Initializable {
    @FXML
    public Label lbl_lingua;
    @FXML
    Button btn_ita;
    @FXML
    Button btn_esp;
    @FXML
    Button btn_ing;
    public void keyEvent(KeyEvent keyEvent) {
    }

    public void linguaItaliana(MouseEvent mouseEvent) throws IOException {
        Utili.setLingua(Locale.ITALIAN);
        BackToHome();

    }

    public void linguaSpagnola(MouseEvent mouseEvent) throws IOException {
        Utili.setLingua(Locale.KOREA);
        BackToHome();
    }

    public void linguaInglese(MouseEvent mouseEvent) throws IOException {
        Utili.setLingua(Locale.ENGLISH);
        BackToHome();

    }

    public void BackToHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_ing.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(Utili.traduci("pokermon"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_lingua.setText(Utili.traduci("lingua"));
    }
}
