package gioco.progettospacca.controller;

import gioco.progettospacca.classi.*;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;


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
    private Label lbl_classifica;
    @FXML
    private Button btn_scarta;
    @FXML
    private Button btn_stai;
    @FXML
    private Label lbl_punteggio;
    @FXML
    private AnchorPane anchPane_manoSuccesiva;
    @FXML
    private Label lbl_toccaA;
    @FXML
    private Label lbl_turno;
    @FXML
    private Label lbl_scegliCarteDaScartare;
    @FXML
    private Button btn_conferma;
    @FXML
    private AnchorPane anchPane_toccaA;
    @FXML
    private AnchorPane anch_mazzo;
    @FXML
    private AnchorPane anch_seme;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageViewMazzo;
    private ImageView imageViewSeme;


    private Map<String, AnchorPane> cartaPaneMap = new HashMap<>();
    private Map<String, ImageView> cartaMap = new HashMap<>();

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
        System.out.println("tocca a "+p.getToccaA());

        toccaA.pesca(5, mazzo);

        mano = toccaA.getMano();
        toccaA.setMano(mano);

        mostraCarte(mano);
        pescataAnimazione();
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
        aggiungiEventiCarte();
        lbl_scegliCarteDaScartare.setVisible(true);
        btn_conferma.setVisible(true);
    }

    public void stai(MouseEvent event) throws IOException {
        System.out.println("hai deciso di stare");
        int punti = p.valutaCarte(mano);
        lbl_punteggio.setVisible(true);
        lbl_punteggio.setText(String.valueOf(punti));
        pulisciSchermata();
        p.setCont(cont+1);
        toccaA.setPunti(punti);
        fineMano();

    }
    //tasto conferma carte da scartare
    public void cambiaCarteSelezionate(MouseEvent mouseEvent) throws FileNotFoundException {
        ArrayList<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
        scartataAnimazione();
        int numCarteDaPescare= 0;
        int pos = 1;
        for(Carta carta : manoList){
            if(carta.getCliccata()){
                toccaA.settaCarteNulle(pos-1);
                toccaA.scarta(pos);
                numCarteDaPescare++;
            }
            pos++;
        }
        /* //per debug
        for(int i = 0; i<toccaA.getMano().length; i++){
            System.out.println(toccaA.getMano()[i]+" ");
        }

         */

        manoList = new ArrayList<>(Arrays.asList(toccaA.getMano()));

        for (int i = 0; i < manoList.size(); i++) {
            Carta carta = manoList.get(i);
            if (carta == null) {
                manoList.remove(i);  // Rimuovi l'elemento null
                manoList.add(i, p.getMazzo().getMazzoArrayList().remove(0));// Inserisci la nuova carta dal mazzo
                manoList.get(i).setCliccata(true);
                String pane = "carta"+i;
                //spostaCarta(cartaMap.get(pane),manoList.get(i));
            }
        }


        toccaA.setMano(manoList.toArray(toccaA.getMano()));

        /* //per debug
        System.out.println("nuova mano");
        for(int i = 0; i<toccaA.getMano().length; i++){
            System.out.println(toccaA.getMano()[i]+" ");
        }

         */

        lbl_scegliCarteDaScartare.setVisible(false);
        btn_conferma.setVisible(false);

        int punti = p.valutaCarte(mano);
        lbl_punteggio.setVisible(true);
        lbl_punteggio.setText(String.valueOf(punti));
        p.setCont(cont+1);
        toccaA.setPunti(punti);
        fineMano();
    }

    public void comparsaSchermata() {
        btn_scarta.setVisible(true);
        btn_stai.setVisible(true);
    }

    private void pulisciSchermata() {
        btn_scarta.setVisible(false);
        btn_stai.setVisible(false);
    }

    public void aggiungiEventiCarte(){
        imageView1.setOnMouseClicked(event -> carta1Click(event));
        imageView2.setOnMouseClicked(event -> carta2Click(event));
        imageView3.setOnMouseClicked(event -> carta3Click(event));
        imageView4.setOnMouseClicked(event -> carta4Click(event));
        imageView5.setOnMouseClicked(event -> carta5Click(event));
    }
    //per selezionare la carta da scartare
    private void spostaCarta(AnchorPane carta, Carta cartaSelezionata) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.2), carta);

        if (cartaSelezionata.getCliccata()) {
            // Se la carta è cliccata, spostala verso l'alto di 20 unità
            translateTransition.setByY(-20);
            System.out.println(cartaSelezionata.getCliccata());
        } else {
            // Se la carta non è cliccata, riportala alla posizione originale (traslazione +20)
            translateTransition.setByY(+20);
            System.out.println(cartaSelezionata.getCliccata());
        }

        // Avvio dell'animazione di traslazione
        translateTransition.play();
    }


    private void carta1Click(MouseEvent event) {
        System.out.println("carta1 cliccata");
        if(mano[0].getCliccata()){
            mano[0].setCliccata(false);
        }else{
            mano[0].setCliccata(true);
        }
        spostaCarta(carta1,mano[0]);
    }

    private void carta2Click(MouseEvent event) {
        System.out.println("carta2 cliccata");
        if(mano[1].getCliccata()){
            mano[1].setCliccata(false);
        }else{
            mano[1].setCliccata(true);
        }
        spostaCarta(carta2,mano[1]);
    }

    private void carta3Click(MouseEvent event) {
        System.out.println("carta3 cliccata");
        if(mano[2].getCliccata()){
            mano[2].setCliccata(false);
        }else{
            mano[2].setCliccata(true);
        }
        spostaCarta(carta3,mano[2]);
    }

    private void carta4Click(MouseEvent event) {
        System.out.println("carta4 cliccata");
        if(mano[3].getCliccata()){
            mano[3].setCliccata(false);
        }else{
            mano[3].setCliccata(true);
        }
        spostaCarta(carta4,mano[3]);
    }

    private void carta5Click(MouseEvent event) {
        System.out.println("carta5 cliccata");
        if(mano[4].getCliccata()){
            mano[4].setCliccata(false);
        }else{
            mano[4].setCliccata(true);
        }
        spostaCarta(carta5,mano[4]);
    }


    private void mostraCarte(Carta[] mano) throws FileNotFoundException {
        mano[0].setCliccata(false);
        mano[1].setCliccata(false);
        mano[2].setCliccata(false);
        mano[3].setCliccata(false);
        mano[4].setCliccata(false);

        String percorsoCarta1 = mano[0].getImage();
        String percorsoCarta2 = mano[1].getImage();
        String percorsoCarta3 = mano[2].getImage();
        String percorsoCarta4 = mano[3].getImage();
        String percorsoCarta5 = mano[4].getImage();
        String percorsoMazzo = "src/main/resources/gioco/progettospacca/Retro.png";
        String percorsoSeme = p.getSeme().getImage();

        imageView1 = createImageView(percorsoCarta1);
        imageView2 = createImageView(percorsoCarta2);
        imageView3 = createImageView(percorsoCarta3);
        imageView4 = createImageView(percorsoCarta4);
        imageView5 = createImageView(percorsoCarta5);
        imageViewMazzo = createImageView(percorsoMazzo);
        imageViewSeme = createImageView(percorsoSeme);

        carta1.getChildren().add(imageView1);
        carta2.getChildren().add(imageView2);
        carta3.getChildren().add(imageView3);
        carta4.getChildren().add(imageView4);
        carta5.getChildren().add(imageView5);
        anch_mazzo.getChildren().add(imageViewMazzo);
        anch_seme.getChildren().add(imageViewSeme);

        cartaPaneMap.put("carta0",carta1);
        cartaPaneMap.put("carta1",carta2);
        cartaPaneMap.put("carta2",carta3);
        cartaPaneMap.put("carta3",carta4);
        cartaPaneMap.put("carta4",carta5);

        cartaMap.put("carta0",imageView1);
        cartaMap.put("carta1",imageView2);
        cartaMap.put("carta2",imageView3);
        cartaMap.put("carta3",imageView4);
        cartaMap.put("carta4",imageView5);


    }
    //animazione quando le nuove carte pescate arrivano in mano
    public void nuoveCartePescateAnimazione() throws FileNotFoundException {
        // Imposta la posizione iniziale della cartaMazzo
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        for (int i = 0; i < 5; i++) {
            if (toccaA.getMano()[i].getCliccata()==true) {
                String cartaName = "carta" + i;

                AnchorPane currentCarta = cartaPaneMap.get(cartaName); // recupera l'ImageView corrente in base al nome dinamico, potrebbe essere necessario un array o una mappa */;

                ImageView imageView = createImageView(toccaA.getMano()[i].getImage());

                currentCarta.getChildren().add(imageView);


                double endToX = (130*(i+1));
                double endToY = 365;


                currentCarta.setLayoutX(startFromX);
                currentCarta.setLayoutY(startFromY);

                // Crea la transizione
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), currentCarta);
                transition.setToX(-(startFromX - endToX));
                transition.setToY(-(startFromY - endToY));

                // Esegui l'animazione
                transition.play();
                toccaA.getMano()[i].setCliccata(false);
            }
        }
    }
    //animazione quando le carte scartate tornano nel mazzo
    public void scartataAnimazione(){
        // Imposta la posizione iniziale della cartaMazzo
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        for (int i = 0; i < 5; i++) {
            if (toccaA.getMano()[i].getCliccata()==true) {
                String cartaName = "carta" + i;
                AnchorPane currentCarta = cartaPaneMap.get(cartaName)/* recupera l'ImageView corrente in base al nome dinamico, potrebbe essere necessario un array o una mappa */;

                double endToX = currentCarta.getLayoutX();
                double endToY = currentCarta.getLayoutY();

                currentCarta.setLayoutX(startFromX);
                currentCarta.setLayoutY(startFromY);

                // Crea la transizione
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), currentCarta);
                transition.setToX(-(startFromX - endToX));
                transition.setToY(-(startFromY - endToY));

                // Esegui l'animazione
                transition.play();
                transition.setOnFinished(event -> {
                    //creo una pausa tra l'animazione delle carte scartate e quelle pescate
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.play();
                    pause.setOnFinished(event2 ->{
                        try {
                            nuoveCartePescateAnimazione();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
            }
        }
    }
    public void pescataAnimazione(){
        // Imposta la posizione iniziale della cartaMazzo
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        for (int i = 0; i < 5; i++) {
            String cartaName = "carta" + i;
            AnchorPane currentCarta = cartaPaneMap.get(cartaName)/* recupera l'ImageView corrente in base al nome dinamico, potrebbe essere necessario un array o una mappa */;

            double endToX = currentCarta.getLayoutX();
            double endToY = currentCarta.getLayoutY();

            currentCarta.setLayoutX(startFromX);
            currentCarta.setLayoutY(startFromY);

            // Crea la transizione
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), currentCarta);
            transition.setToX(-(startFromX - endToX));
            transition.setToY(-(startFromY - endToY));

            // Esegui l'animazione
            transition.play();
        }
    }

    private ImageView createImageView(String percorsoImmagine) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(percorsoImmagine));
        return new ImageView(image);
    }

    public void BackToHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_scarta.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(Utili.traduci("spacca"));
    }
    public void fineMano(){
        anchPane_manoSuccesiva.setVisible(true);
    }
    //collegato al bottone del anchor pane manoSuccesiva
    public void prossimaMano(MouseEvent mouseEvent) throws IOException {
        newScene();
    }
    public void schermataToccaA(){
        anchPane_toccaA.setVisible(true);
        lbl_toccaA.setText("turno: "+p.getToccaA());
        lbl_turno.setText("turno: "+(p.getTurnoSalvato()));
    }
    //collegato al bottone del anchor pane toccaA
    public void procedi(MouseEvent mouseEvent) throws FileNotFoundException {
        anchPane_toccaA.setVisible(false);

        try {
            giocaTurno();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void newScene() throws IOException {
        if(cont == p.getNumeroTurni()*p.getGiocatori().length-1) {
            p.finePartita();
            BackToHome();
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
        System.out.println(">>> initializa");
        int codice = 43619;
        p = Partita.carica(codice);
        mostraClassifica();
        cont = p.getCont();
        giocatori = p.getGiocatori();
        turno_salvato = p.getTurnoSalvato();
        giocatore_salvato = p.getGiocatoreSalvato();
        p.setSeme(p.getMazzo().getMazzoArrayList().remove(0));

        p.setTurnoSalvato(p.getCont()/p.getGiocatori().length);
        turno_salvato = p.getTurnoSalvato();

        if(giocatore_salvato < p.getGiocatori().length){
            p.setGiocatoreSalvato(giocatore_salvato+1);
            toccaA = giocatori[p.getGiocatoreSalvato()-1];

        }
        else{
            p.setGiocatoreSalvato(1);
            toccaA = giocatori[0];
        }

        p.setToccaA(toccaA);

        schermataToccaA();

        /*
        System.out.println(p.getGiocatoreSalvato()+"       "+p.getTurnoSalvato());
        System.out.println(">>> turno: "+p.getTurnoSalvato()+"/"+p.getNumeroTurni()+ ", giocatore: "+(p.getGiocatoreSalvato())+"/"+p.getGiocatori().length);
         */


        p.newMazzo();

    }

    private void mostraClassifica() {
        String s = "";
        for(int i = 0; i<p.getGiocatori().length;i++){
            s = s+" giocatore "+p.getGiocatori()[i].getNome()+" punti"+p.getGiocatori()[i].getPunti()+"\n";
        }
        lbl_classifica.setText(s);
    }


}
