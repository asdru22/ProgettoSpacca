package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Partita;
import gioco.progettospacca.classi.Utili;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HomeController {

    public void EventoGiocaPartita(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GiocaPartita.fxml"));
        Scene scene=new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Gioca partita");
        stage.setScene(scene);
        stage.show();
    }

    public void EventoCreaPartita(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdminView.fxml"));
        Scene scene=new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login amministratore");
        stage.setScene(scene);
        stage.show();

    }

    public void BackToHome(ActionEvent actionEvent) throws IOException
    {
       Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
       Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Home");
       stage.setScene(scene);
        stage.show();
    }

}