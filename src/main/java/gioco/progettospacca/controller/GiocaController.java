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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GiocaController {

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

    @FXML
    private TextField txt_cod1;
    @FXML
    private TextField txt_cod2;
    @FXML
    private TextField txt_cod3;
    @FXML
    private TextField txt_cod4;
    @FXML
    private TextField txt_cod5;

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
        int codice = Utili.leggiInt(txt_cod1);
        if(Utili.esistePartita(codice)){
          partitaEsiste(codice);
        } else throw new Error("Partita con id "+codice+" non esiste");
    }
    private void partitaEsiste(int codice){
        boolean inizia_partita = true;
        Partita p = Partita.carica(codice);
        int n = p.getGiocatori().length;
        inizia_partita = controlloGiocatore(txt_gioc1.getText(),Utili.leggiInt(txt_cod1));
        if(inizia_partita){
            inizia_partita = controlloGiocatore(txt_gioc2.getText(),Utili.leggiInt(txt_cod2));
        }
        if(inizia_partita&& n>=3){
            inizia_partita = controlloGiocatore(txt_gioc3.getText(),Utili.leggiInt(txt_cod3));
        }
        if(inizia_partita&& n>=4){
            inizia_partita = controlloGiocatore(txt_gioc4.getText(),Utili.leggiInt(txt_cod4));
        }
        if(inizia_partita&& n==5){
            inizia_partita = controlloGiocatore(txt_gioc5.getText(),Utili.leggiInt(txt_cod5));
        }

        if(inizia_partita && n>=2) {
            //al momento per comodità metto il codice del primo text, dopo dovremmo mettere i controlli che tutti i codici siano uguali e che corrispondano ad una partita (e ovviamente che i anche tutti i nomi corrispondano alla stessa partita)
            p.inizio();
        }
        else{
            throw new Error("Valori invalidi per iniziare partita");
        }
    }
    private boolean controlloGiocatore(String nome,int id) {
        if(Utili.esisteGiocatore(nome)) {
            Giocatore g = Giocatore.carica(nome);
            ArrayList<Integer> partite = g.getPartite();
            if (!partite.contains(id)) throw new Error("Giocatore " + nome + " non ha questo id");
            return partite.contains(id);
        }
        else throw new Error("Giocatore "+nome+" non esiste");
    }
}

