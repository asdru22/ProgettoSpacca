package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Partita;
import gioco.progettospacca.classi.Utili;
import gioco.progettospacca.classi.ValoriGioca;
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
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GiocaController implements Initializable {

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
    @FXML
    private Label lbl_nomi;
    @FXML
    private Label lbl_codice;
    @FXML
    private Button btn_entra;

    public void BackToHome(ActionEvent actionEvent) throws IOException {
        Utili.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle(Utili.traduci("spacca"));
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }


    public void entraInPartita(ActionEvent actionEvent) {
        Utili.premiBottone();
        System.out.println(txt_cod1.getText());
        if (!Objects.equals(txt_cod1.getText(), "")) {
            int codice = Utili.leggiInt(txt_cod1);
            if (Utili.esistePartita(codice)) {
                // vai al controllo giocatori / codice
                partitaEsiste(codice);
            } else {
                System.out.println("Partita con id " + codice + " non esiste");
            }

        }
    }

    private void partitaEsiste(int codice) {
        Partita p = Partita.carica(codice);
        int n = p.getGiocatori().length;
        int nbot = p.getBot();
        // n-nbot è il numero di giocatori non bot in partita

        System.out.println(n);
        ArrayList<ValoriGioca> temp = new ArrayList<>();
        if(!Objects.equals(txt_gioc1.getText(), "")) temp.add(new ValoriGioca(txt_gioc1,txt_cod1));
        if(!Objects.equals(txt_gioc2.getText(), "")) temp.add(new ValoriGioca(txt_gioc2,txt_cod1));
        if(!Objects.equals(txt_gioc3.getText(), "")) temp.add(new ValoriGioca(txt_gioc3,txt_cod1));
        if(!Objects.equals(txt_gioc4.getText(), "")) temp.add(new ValoriGioca(txt_gioc4,txt_cod1));
        if(!Objects.equals(txt_gioc5.getText(), "")) temp.add(new ValoriGioca(txt_gioc5,txt_cod1));

        boolean inizia_partita = (n==temp.size());
        if(inizia_partita){
            for(ValoriGioca v: temp){
                inizia_partita = v.controlloGiocatore();
                if(!inizia_partita) break;
            }
        }

        if (inizia_partita && n >= 2) {
            //p.inizio();
        } else {
            System.out.println("Valori invalidi per iniziare partita");
        }

    }

    public void provaMomentanea() {
        //mettiamo il codice giusto
        int codice = Integer.parseInt(txt_cod1.getText());
        Partita p = Partita.carica(codice);
        //p.inizio();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_nomi.setText(Utili.traduci("nomi"));
        lbl_codice.setText(Utili.traduci("inserisci_codice"));
        btn_entra.setText(Utili.traduci("entra"));
    }
}

