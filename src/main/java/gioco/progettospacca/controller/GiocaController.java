package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Partita;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static gioco.progettospacca.controller.CreaController.id;

public class GiocaController {
    public void BackToHome(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }


    public void entraInPartita(ActionEvent actionEvent) {
        Partita p = Partita.carica(id);
        p.riprendi();
    }
}
