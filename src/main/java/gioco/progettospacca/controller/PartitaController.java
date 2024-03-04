package gioco.progettospacca.controller;

import gioco.progettospacca.classi.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartitaController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane carta1;
    @FXML
    private AnchorPane carta2;
    @FXML
    private AnchorPane carta3;
    @FXML
    private AnchorPane carta4;
    @FXML
    private AnchorPane carta5;
    @FXML
    private TextField txt_cod1;
    @FXML
    private Button btn_scarta;
    @FXML
    private Button btn_stai;
    @FXML
    private Label lbl_punteggio;

    private Partita p;
    private static int NUMERO_TURNI = 2;
    private Carta[] mano;
    private Mazzo mazzo;
    private Giocatore[] giocatori = null;
    private int turno_salvato = 0;
    private int giocatore_salvato = 0;
    private Seme seme_che_comanda = null;
    private Giocatore toccaA;
    private int cont;

    public void giocaTurno() throws FileNotFoundException {
        mazzo = p.getMazzo();
        p.setToccaA(toccaA);
        System.out.println("tocca a "+p.getToccaA());

        toccaA.pesca(5, mazzo);
        mano = toccaA.getMano();
        toccaA.setMano(mano);

        mostraCarte(mano);

        comparsaSchermata();

        btn_scarta.setOnMouseClicked(event -> scarta(event));
        btn_stai.setOnMouseClicked(event -> {
            try {
                stai(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void scarta(MouseEvent event) {
        System.out.println("hai deciso di scartare");
        pulisciSchermata();
    }

    public void stai(MouseEvent event) throws IOException {
        System.out.println("hai deciso di stare");
        int punti = p.valutaCarte(mano);
        lbl_punteggio.setVisible(true);
        lbl_punteggio.setText(String.valueOf(punti));
        pulisciSchermata();
        p.setCont(cont+1);

        newScene();

    }

    public void comparsaSchermata() {
        btn_scarta.setVisible(true);
        btn_stai.setVisible(true);
    }

    private void pulisciSchermata() {
        btn_scarta.setVisible(false);
        btn_stai.setVisible(false);
    }

    private void mostraCarte(Carta[] mano) throws FileNotFoundException {
        String percorsoCarta1 = mano[0].getImage();
        String percorsoCarta2 = mano[1].getImage();
        String percorsoCarta3 = mano[2].getImage();
        String percorsoCarta4 = mano[3].getImage();
        String percorsoCarta5 = mano[4].getImage();

        ImageView imageView1 = createImageView(percorsoCarta1);
        ImageView imageView2 = createImageView(percorsoCarta2);
        ImageView imageView3 = createImageView(percorsoCarta3);
        ImageView imageView4 = createImageView(percorsoCarta4);
        ImageView imageView5 = createImageView(percorsoCarta5);

        carta1.getChildren().add(imageView1);
        carta2.getChildren().add(imageView2);
        carta3.getChildren().add(imageView3);
        carta4.getChildren().add(imageView4);
        carta5.getChildren().add(imageView5);
    }

    private ImageView createImageView(String percorsoImmagine) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(percorsoImmagine));
        return new ImageView(image);
    }

    private void newScene() throws IOException {
        if(cont == p.getNumeroTurni()*p.getGiocatori().length-1) {
            p.finePartita();
            System.exit(0);
        }
        else{
            p.salva();
            Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));

            // Ottieni la finestra corrente
            Stage currentStage = (Stage) btn_scarta.getScene().getWindow();

            // Ottieni la scena corrente
            Scene currentScene = currentStage.getScene();

            // Imposta la nuova radice della scena
            currentScene.setRoot(root);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        int codice = 59480;
        p = Partita.carica(codice);
        cont = p.getCont();
        giocatori = p.getGiocatori();
        turno_salvato = p.getTurnoSalvato();
        giocatore_salvato = p.getGiocatoreSalvato();

        toccaA = giocatori[giocatore_salvato];

        p.setTurnoSalvato(p.getCont()/p.getGiocatori().length);
        turno_salvato = p.getTurnoSalvato();

        System.out.println(p.getGiocatoreSalvato()+"       "+p.getTurnoSalvato());
        System.out.println(">>> turno: "+p.getTurnoSalvato()+"/"+p.getNumeroTurni()+ ", giocatore: "+(p.getGiocatoreSalvato())+"/"+p.getGiocatori().length);

        try {
            giocaTurno();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        p.newMazzo();

    }
}
