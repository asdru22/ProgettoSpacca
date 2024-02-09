package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Partita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
            stage.setTitle("Crea partita");
            stage.setScene(scene);
            stage.show();
        //}else
        //{
        //}
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

    @FXML
    private Label lbl_code;
    @FXML
    private Button btn_crea;
    public String EventoCreaCodicePartita(ActionEvent actionEvent)
    {
        try {
            String caratteriPermessi = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 6; i++) {
                int index = random.nextInt(caratteriPermessi.length());
                sb.append(caratteriPermessi.charAt(index));
            }
            lbl_code.setText(sb.toString());
            return sb.toString();

        }catch(Exception e)
        {
            System.out.println(e);
            return "";
        }

    }
}