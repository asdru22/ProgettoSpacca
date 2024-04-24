package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import gioco.progettospacca.classi.Utili;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class ModificaGiocatoreController implements Initializable {
    @FXML
    Label lbl_caNome, lbl_email;
    @FXML
    private TextField txt_vecchioNome;
    @FXML
    private TextField txt_nuovoNome, txt_email;
    @FXML
    private Button btn_confermaCambioNome;
    @FXML
    private Button btn_back;
    @FXML
    private Label lbl_veNome;
    @FXML
    private Label lbl_nuNome;
    @FXML
    private Label lbl_output;

    public void modificaNome(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        EventoCambioNome();
    }

    public void EventoCambioNome() {
        String out = "";
        String vecchio = String.valueOf((txt_vecchioNome.getText()));
        String nuovo = String.valueOf((txt_nuovoNome.getText()));
        String email = String.valueOf((txt_email.getText()));

        if (!Objects.equals(nuovo, "")) {
            out = Utili.cambiaNomeGiocatore(vecchio, nuovo);
        }
        if (!Objects.equals(email, "")) {
            out = Utili.cambiaEmail(vecchio, email);
        }
        lbl_output.setText(out);
    }

    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("PrivilegiAdminView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_back.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_veNome.setText(OPZ.traduci("vecchio_nome"));
        lbl_nuNome.setText(OPZ.traduci("nuovo_nome"));
        lbl_caNome.setText(OPZ.traduci("cambia_nome"));
        lbl_email.setText(OPZ.traduci("cambia_email"));

        btn_confermaCambioNome.setText(OPZ.traduci("conferma"));

    }
}
