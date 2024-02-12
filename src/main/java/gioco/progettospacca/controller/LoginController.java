package gioco.progettospacca.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

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
    private TextField txt_user;
    @FXML
    private TextField txt_psw;
    @FXML
    private Button btn_login;
    public void LoginAdmin(ActionEvent actionEvent) throws IOException
    {
        try {
            String user= String.valueOf((txt_user.getText()));
            String psw= String.valueOf((txt_psw.getText()));
            //aggiungere usrrname e password
            if ("admin".equals(user) && "spacca".equals(psw)) {
                Parent root = FXMLLoader.load(getClass().getResource("CreaPartita.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Crea partita");
                stage.setScene(scene);
                stage.show();
            } else
            {
                System.out.println(user);
                System.out.println(psw);
                System.out.println("Email o password errata");
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
