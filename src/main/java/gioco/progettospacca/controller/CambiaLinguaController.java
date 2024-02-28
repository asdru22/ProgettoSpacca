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
import javafx.scene.input.KeyCode;
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
    Button btn_ger;
    @FXML
    Button btn_ing;
    @FXML
    Button btn_backToHome;

    public void linguaItaliana(MouseEvent mouseEvent) throws IOException {
        Utili.setLingua(Locale.ITALIAN);

    }

    public void linguaTedesca(MouseEvent mouseEvent) throws IOException {
        Utili.setLingua(Locale.GERMAN);
    }

    public void linguaInglese(MouseEvent mouseEvent) throws IOException {
        Utili.setLingua(Locale.ENGLISH);
    }

    public void BackToHome() throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

            // Ottieni la finestra corrente
            Stage currentStage = (Stage) btn_backToHome.getScene().getWindow();

            // Ottieni la scena corrente
            Scene currentScene = currentStage.getScene();

            // Imposta la nuova radice della scena
            currentScene.setRoot(root);

            // Imposta il titolo della finestra
            currentStage.setTitle(Utili.traduci("pokermon"));
    }

    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_backToHome.isFocused()) || keyEvent.getCode() == KeyCode.ESCAPE) {
            BackToHome();
        }
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_ita.isFocused())) {
            Utili.setLingua(Locale.ITALIAN);
        }
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_ing.isFocused())) {
            Utili.setLingua(Locale.ENGLISH);
        }
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_ger.isFocused())) {
            Utili.setLingua(Locale.GERMAN);
        }

        if (keyEvent.getCode() == KeyCode.UP) {
            if (btn_backToHome.isFocused()) {
                System.out.println("Sei già in alto");
            } else if (btn_ita.isFocused()) {
                btn_backToHome.requestFocus();
            } else if (btn_ger.isFocused()) {
                btn_ing.requestFocus();
            } else if (btn_ing.isFocused()) {
                btn_ita.requestFocus();
            }
        }

        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (btn_ger.isFocused()) {
                System.out.println("Sei già in basso");
            } else if (btn_backToHome.isFocused()) {
                btn_ita.requestFocus();
            } else if (btn_ita.isFocused()) {
                btn_ing.requestFocus();
            } else if (btn_ing.isFocused()) {
                btn_ger.requestFocus();
            }
        }
        /*
        System.out.println("Focused button: " + (btn_backToHome.isFocused() ? "btn_backToHome" :
                btn_ita.isFocused() ? "btn_ita" :
                        btn_ing.isFocused() ? "btn_ing" :
                                btn_ger.isFocused() ? "btn_ger" : "None"));
        */

        pulisci();
    }
    public void pulisci(){
        btn_ita.setFocusTraversable(false);
        btn_ger.setFocusTraversable(false);
        btn_backToHome.setFocusTraversable(false);
        btn_ing.setFocusTraversable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_lingua.setText(Utili.traduci("lingua"));
    }
}
