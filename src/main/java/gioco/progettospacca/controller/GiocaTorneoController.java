package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Partita;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gioco.progettospacca.classi.Torneo;

import static gioco.progettospacca.controller.Main.CODICE_GLOBALE_PARTITA;
import static gioco.progettospacca.controller.Main.CODICE_GLOBALE_TORNEO;
import static gioco.progettospacca.controller.Main.OPZ;

public class GiocaTorneoController implements Initializable {
    @FXML
    private Button btn_entraTorneo;
    @FXML
    TextField txt_codTorneo;
    @FXML
    Label lbl_titleCodTorneo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void BackToHome(ActionEvent actionEvent) throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle(OPZ.traduci("spacca"));
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }

    public void giocaTorneo(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        CODICE_GLOBALE_TORNEO = Utili.leggiInt(txt_codTorneo);
        // debug
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        for(Partita p: t.getPartite()){
            System.out.println("Partita: "+p.getId());
        }
        //
        EventoGiocaTorneo();

    }

    public void EventoGiocaTorneo() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("TorneoView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_entraTorneo.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("gioca_torneo"));


    }

}
