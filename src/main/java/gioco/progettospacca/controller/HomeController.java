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
    @FXML
    public void giocaPartita(ActionEvent actionEvent) {

    }

    public void EventoCreaPartita(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdminView.fxml"));
        Scene scene=new Scene(root);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login amministratore");
        stage.setScene(scene);
        stage.show();

    }


    public void giocaTorneo(ActionEvent actionEvent) {
    }

    public void creaTorneo(ActionEvent actionEvent) {
    }


    public void LoginAdmin(ActionEvent actionEvent) throws IOException
    {
        //aggiungere usrrname e password
        //if(user=="admin" && psw=="spacca") {
            Parent root = FXMLLoader.load(getClass().getResource("CreaPartita.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Login amministratore");
            stage.setScene(scene);
            stage.show();
        //}else
        //{

        //}
    }

    //public void BackToHome(ActionEvent actionEvent) throws IOException
    //{
    //   Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
    //   Scene scene = new Scene(root);
    //  Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    //    stage.setTitle("Home");
    //   stage.setScene(scene);
    //    stage.show();
    //}
}