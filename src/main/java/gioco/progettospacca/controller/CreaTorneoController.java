package gioco.progettospacca.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle(OPZ.traduci("spacca"));
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
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

        // Imposta il titolo della finestra
        //currentStage.setTitle(OPZ.traduci("Crea torneo (16 giocatori)"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_npart.setText(OPZ.traduci("inserisci_partecipanti"));
    }
}
