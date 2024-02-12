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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    public void EventoCreaCodicePartita(ActionEvent actionEvent)
    {

        Giocatore temp;
        try {
            int id= Utili.intCasuale(10000,99999);

            lbl_code.setText(String.valueOf(id));

            List<Giocatore> g = new ArrayList<Giocatore>();

            temp = controllaNome(txt_gioc1.getText());
            if (temp!=null) {
                g.add(temp);
            }

            temp = controllaNome(txt_gioc2.getText());
            if (temp!=null) {
                g.add(temp);
            }

            temp = controllaNome(txt_gioc3.getText());
            if (temp!=null) {
                g.add(temp);
            }

            temp = controllaNome(txt_gioc4.getText());
            if (temp!=null) {
                g.add(temp);
            }

            temp = controllaNome(txt_gioc5.getText());
            if (temp!=null) {
                g.add(temp);
            }

            Giocatore[] giocatori = g.toArray(new Giocatore[g.size()]);

            Partita p=new Partita(id,giocatori,false);



        }catch(Exception e)
        {
            System.out.println(e);
        }

    }
    public Giocatore controllaNome(String nome){
        if(!Objects.equals(nome, "")) {
            return new Giocatore(nome);
        }
        else {
            return null;
        }
    }

}