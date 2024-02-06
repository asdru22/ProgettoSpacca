package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Partita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void giocaPartita(ActionEvent actionEvent) {

    }

    public void EventoCreaPartita(ActionEvent actionEvent)throws IOException{
        try {
            // Il tuo codice esistente qui
            root = FXMLLoader.load(getClass().getResource("LoginAdminView.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void giocaTorneo(ActionEvent actionEvent) {
    }

    public void creaTorneo(ActionEvent actionEvent) {
    }
}