package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Partita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

        Partita p = Partita.carica(Integer.parseInt(txt_cod1.getText())); //al momento per comodità metto il codice del primo text, dopo dovremmo mettere i controlli che tutti i codici siano uguali e che corrispondano ad una partita (e ovviamente che i anche tutti i nomi corrispondano alla stessa partita)
        p.inizio();
    }
}
