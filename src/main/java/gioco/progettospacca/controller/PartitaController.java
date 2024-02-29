package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Carta;
import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Mazzo;
import gioco.progettospacca.classi.Partita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    static Partita p;

    public void giocaTurno() throws FileNotFoundException {
        Giocatore toccaA = p.getToccaA();
        Mazzo mazzo = p.getMazzo();
        Giocatore[] giocatori = p.getGiocatori();
        int giocatore_salvato = 0;

        p.setToccaA(giocatori[giocatore_salvato]);

        // il giocatore pesca le carte
        toccaA.pesca(5, mazzo);
        Carta[] mano = toccaA.getMano();

        // carica la mano a schermo
        mostraCarte(mano);

        // visualizza i pulsanti
        comparsaSchermata();

        // attendi che uno dei pulsanti venga cliccato
        btn_scarta.setOnMouseClicked(event -> scarta(event));
        btn_stai.setOnMouseClicked(event -> stai(event));

    }

    public void scarta(MouseEvent event) {
        System.out.println("hai deciso di scartare");
        pulisciSchermata();
    }

    public void stai(MouseEvent event) {
        System.out.println("hai deciso di stare");
        pulisciSchermata();
    }

    public void comparsaSchermata() {
        btn_scarta.setVisible(true);
        btn_stai.setVisible(true);
    }

    private void pulisciSchermata() {
        // Puoi nascondere i bottoni o eseguire altre operazioni di pulizia
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

        // Aggiungi l'ImageView a ciascuna AnchorPane
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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        int codice = 78414;
        p = Partita.carica(codice);
        try {
            giocaTurno();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
