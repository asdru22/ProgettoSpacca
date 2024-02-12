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

public class CreaController {
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
    @FXML
    private TextField txt_gioc1;
    @FXML
    private TextField txt_gioc2;
    @FXML
    private TextField txt_gioc3;
    @FXML
    private TextField txt_gioc4;
    @FXML
    private TextField txt_gioc5;
    public int EventoCreaCodicePartita(ActionEvent actionEvent)
    {
        int id;
        Giocatore giocatori[]=new Giocatore[5];
        try {
            id= Utili.intCasuale(10000,99999);

            lbl_code.setText(String.valueOf(id));

            Giocatore g1=new Giocatore(txt_gioc1.getText());
            Giocatore g2=new Giocatore(txt_gioc2.getText());
            Giocatore g3=new Giocatore(txt_gioc3.getText());
            Giocatore g4=new Giocatore(txt_gioc4.getText());
            Giocatore g5=new Giocatore(txt_gioc5.getText());

            giocatori[0]=g1;
            giocatori[1]=g2;
            giocatori[2]=g3;
            giocatori[3]=g4;
            giocatori[4]=g5;

            Partita p=new Partita(id,giocatori,false);

            System.out.println("Giocatori:");
            for(int i=0;i<giocatori.length;i++)
            {
                System.out.println(giocatori[i].getNome());
            }
            return id;

        }catch(Exception e)
        {
            System.out.println(e);
            return 0;
        }

    }

}
