package gioco.progettospacca.controller;

import gioco.progettospacca.classi.*;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static gioco.progettospacca.controller.Main.OPZ;

public class PartitaController implements Initializable {

    
    public static final int CODICE_TEMP = 95442;

  @FXML
    public Label lbl_pausa;

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
    @FXML
    private Label lbl_attenzione;
    @FXML
    private AnchorPane anchPane_score;
    @FXML
    private Pane pane_pausa;
    @FXML
    private Button btn_esci;
    @FXML
    private ToggleButton tglbtn_suono;
    @FXML
    private Button btn_regole;
    @FXML
    private ToggleButton tglbtn_musica;


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
    String percorsoMazzo = "src/main/resources/gioco/progettospacca/Retro.png";
    boolean pausa = false;

    private void inizializzaTraduzioni(){

        lbl_pausa.setText(OPZ.traduci("pausa"));
        lbl_attenzione.setText(OPZ.traduci("attenzione"));
        lbl_classifica.setText(OPZ.traduci("classifica"));
        lbl_scegliCarteDaScartare.setText(OPZ.traduci("scegli_carte_da_scartare"));
        btn_esci.setText(OPZ.traduci("esci"));
        tglbtn_suono.setSelected(OPZ.getSuono());
        tglbtn_musica.setSelected(OPZ.getMusica());
        Utili.gestisciSuoni(tglbtn_suono);
        Utili.gestisciMusica(tglbtn_musica);
    }
    public void giocaTurno() throws FileNotFoundException {
        anch_mazzo.getScene().setOnKeyPressed(this::keyEventPausa); //recupero la scena corrente e imposto il gestore degli eventi da tastiera

        mazzo = p.getMazzo();
        System.out.println("Tocca a "+p.getToccaA());

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
        System.out.println("Hai deciso di scartare");
        pulisciSchermata();
        aggiungiEventiCarte();
        lbl_scegliCarteDaScartare.setVisible(true);
        btn_conferma.setVisible(true);
    }

    public void stai(MouseEvent event) throws IOException {
        System.out.println("Hai deciso di stare");
        int punti = p.valutaCarte(mano);
        lbl_punteggio.setVisible(true);
        lbl_punteggio.setText(String.valueOf(punti));
        pulisciSchermata();
        p.setCont(cont+1);
        toccaA.setPunti(punti);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.play();
        pause.setOnFinished(event2 ->{
            fineMano();
        });

    }
    //tasto conferma carte da scartare
    public void cambiaCarteSelezionate(MouseEvent mouseEvent) throws FileNotFoundException {
        ArrayList<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
        boolean almenoUnaTrue = false;
        for(Carta carta : manoList){
            if(carta.getCliccata()){
                almenoUnaTrue = true;
                break;
            }
        }
        if(almenoUnaTrue) {
            scartataAnimazione(); //lo metto subito perchè appena inizia una transizione il codice va avanti e non aspetta
            // la fine dell'animazione, quindi cambia le carte animatamente corrette poi eseguo lo scarto effettivo in questo metodo poi una volta terminata
            // l'animazione dello scarto, parte l'animazione della pescata con le carte già modificate in quanto il codice di questo metodo ha
            // eseguito subito dopo l'animazione dello scarto

            //debug
            //System.out.println("ciao");
            int numCarteDaPescare = 0;
            int pos = 1;
            for (Carta carta : manoList) {
                if (carta.getCliccata()) {
                    toccaA.settaCarteNulle(pos - 1);
                    toccaA.scarta(pos);
                    numCarteDaPescare++;
                }
                pos++;
            }

            manoList = new ArrayList<>(Arrays.asList(toccaA.getMano()));

            for (int i = 0; i < manoList.size(); i++) {
                Carta carta = manoList.get(i);
                if (carta == null) {
                    manoList.remove(i);  // Rimuovi l'elemento null
                    manoList.add(i, p.getMazzo().getMazzoArrayList().remove(0));// Inserisci la nuova carta dal mazzo
                    manoList.get(i).setCliccata(true);
                }
            }

            toccaA.setMano(manoList.toArray(toccaA.getMano()));

            lbl_scegliCarteDaScartare.setVisible(false);
            btn_conferma.setVisible(false);

            int punti = p.valutaCarte(toccaA.getMano());
            lbl_punteggio.setText(String.valueOf(punti));
            p.setCont(cont + 1);
            toccaA.setPunti(punti);
            rimuoviEventiCarte();
        }
        else{
            lbl_attenzione.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event ->{
                lbl_attenzione.setVisible(false);
            });

        }
    }

    public void comparsaSchermata() {
        btn_scarta.setVisible(true);
        btn_stai.setVisible(true);
    }

    private void pulisciSchermata() {
        btn_scarta.setVisible(false);
        btn_stai.setVisible(false);
    }
    public void rimuoviEventiCarte(){
        imageView1.setOnMouseClicked(null);
        imageView2.setOnMouseClicked(null);
        imageView3.setOnMouseClicked(null);
        imageView4.setOnMouseClicked(null);
        imageView5.setOnMouseClicked(null);
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
        } else {
            // Se la carta non è cliccata, riportala alla posizione originale (traslazione +20)
            translateTransition.setByY(+20);
        }

        // Avvio dell'animazione di traslazione
        translateTransition.play();
    }


    private void carta1Click(MouseEvent event) {
        if(mano[0].getCliccata()){
            mano[0].setCliccata(false);
        }else{
            mano[0].setCliccata(true);
        }
        spostaCarta(carta1,mano[0]);
    }

    private void carta2Click(MouseEvent event) {
        if(mano[1].getCliccata()){
            mano[1].setCliccata(false);
        }else{
            mano[1].setCliccata(true);
        }
        spostaCarta(carta2,mano[1]);
    }

    private void carta3Click(MouseEvent event) {
        if(mano[2].getCliccata()){
            mano[2].setCliccata(false);
        }else{
            mano[2].setCliccata(true);
        }
        spostaCarta(carta3,mano[2]);
    }

    private void carta4Click(MouseEvent event) {
        if(mano[3].getCliccata()){
            mano[3].setCliccata(false);
        }else{
            mano[3].setCliccata(true);
        }
        spostaCarta(carta4,mano[3]);
    }

    private void carta5Click(MouseEvent event) {
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
    public void nuoveCartePescateAnimazione() throws FileNotFoundException {
        //debug
        //System.out.println("sto per pescare");
        // Imposta la posizione iniziale della cartaMazzo
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        for (int i = 0; i < 5; i++) {
            if (toccaA.getMano()[i].getCliccata()==true) {
                String cartaName = "carta" + i;

                AnchorPane currentCarta = cartaPaneMap.get(cartaName);

                ImageView retroCarta = createImageView(percorsoMazzo);
                currentCarta.getChildren().add(retroCarta);

                //posizioni delle carte sul terreno che sono fisse
                double endToX = (140*(i+1)-(10*(i)));
                double endToY = 365;


                currentCarta.setLayoutX(startFromX);
                currentCarta.setLayoutY(startFromY);

                // Crea la transizione
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), retroCarta);
                transition.setToX(-(startFromX - endToX));
                transition.setToY(-(startFromY - endToY));

                // Esegui l'animazione
                transition.play();
                transition.setOnFinished(event -> {
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.play();
                    pause.setOnFinished(event2 ->{
                            RotateTransition rotate = new RotateTransition(Duration.seconds(0.25), retroCarta);

                            rotate.setByAngle(180); // Specifica l'angolo di rotazione desiderato
                            rotate.setAxis(Rotate.Y_AXIS);

                            rotate.play();
                            rotate.setOnFinished(event3 -> {
                                try {
                                    currentCarta.getChildren().remove(retroCarta);
                                    giroCarte();
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                    });
                });
            }
        }
    }
    //animazione quando le nuove carte pescate arrivano in mano
    public void giroCarte() throws FileNotFoundException {

        for (int i = 0; i < 5; i++) {
            if (toccaA.getMano()[i].getCliccata()==true) {
                String cartaName = "carta" + i;

                AnchorPane currentCarta = cartaPaneMap.get(cartaName);
                currentCarta.setLayoutX(140*(i+1)-(10*(i)));
                currentCarta.setLayoutY(365);

                ImageView imageView = createImageView(toccaA.getMano()[i].getImage());
                currentCarta.getChildren().add(imageView);

                toccaA.getMano()[i].setCliccata(false);
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.play();
                pause.setOnFinished(event2 ->{
                    fineMano();
                });

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
                //debug
                //System.out.println("sto per inzizare la transazione");

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
        currentStage.setTitle(OPZ.traduci("spacca"));
    }
    public void fineMano(){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), anchPane_manoSuccesiva);
        translateTransition.setByX(-300);
        translateTransition.play();

    }
    //collegato al bottone del anchor pane manoSuccesiva
    public void prossimaMano(MouseEvent mouseEvent) throws IOException {
        newScene();
    }
    public void schermataToccaA(){
        pulisciSchermata();
        anchPane_toccaA.setVisible(true);
        lbl_toccaA.setText("Turno: "+p.getToccaA());
        lbl_turno.setText("Turno: "+(p.getTurnoSalvato()));
    }
    //collegato al bottone del anchor pane toccaA
    public void procedi(MouseEvent mouseEvent) throws FileNotFoundException {
        anchPane_toccaA.setVisible(false);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), anchPane_score);
        translateTransition.setByX(220);
        translateTransition.play();
        try {
            giocaTurno();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void newScene() throws IOException {
        if(cont == p.getNumeroTurni()*p.getGiocatori().length-1) {
            p.finePartita();
            salvaEdEsci();
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
    public void salvaEdEsci() throws IOException {
        BackToHome();
        OPZ.playMusica("lobby.wav");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        inizializzaTraduzioni();

        System.out.println(">>> initializa partita con codice "+CODICE_TEMP);
        int codice = CODICE_TEMP;
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

        p.newMazzo();

    }

    private void mostraClassifica() {
        String s = "";
        for(int i = 0; i<p.getGiocatori().length;i++){
            s = s+p.getGiocatori()[i].getNome()+":  "+p.getGiocatori()[i].getPunti()+"\n";
        }
        lbl_classifica.setText(s);
    }

    public void keyEventPausa(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ESCAPE){
            if(!pausa) {
                pane_pausa.setVisible(true);
                anchorPane.setDisable(true);
                anchPane_manoSuccesiva.setDisable(true);
                anchPane_score.setDisable(true);
                btn_regole.requestFocus();
                pausa = true;
            }
            else{
                pane_pausa.setVisible(false);
                anchorPane.setDisable(false);
                anchPane_manoSuccesiva.setDisable(false);
                anchPane_score.setDisable(false);
                pausa = false;
            }
        }
    }
    public void keyEventTastini(KeyEvent keyEvent) throws IOException {
        if(tglbtn_suono.isFocused() || btn_esci.isFocused() || btn_regole.isFocused() || tglbtn_musica.isFocused()) {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                if (btn_regole.isFocused()) {
                    tglbtn_suono.requestFocus();
                } else if (tglbtn_suono.isFocused()) {
                    tglbtn_musica.requestFocus();
                } else if (tglbtn_musica.isFocused()) {
                    btn_esci.requestFocus();
                } else {
                    System.out.println("sei in basso");
                }
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                if (btn_esci.isFocused()) {
                    tglbtn_musica.requestFocus();
                } else if (tglbtn_musica.isFocused()) {
                    tglbtn_suono.requestFocus();
                } else if (tglbtn_suono.isFocused()) {
                    btn_regole.requestFocus();
                } else {
                    System.out.println("sei in alto");
                }
            }
            pulisci();

        }
        if(keyEvent.getCode() == KeyCode.ENTER && btn_esci.isFocused()){
            salvaEdEsci();
        }
        if(keyEvent.getCode() == KeyCode.ENTER && tglbtn_musica.isFocused()){
            Utili.gestisciMusica(tglbtn_musica);
        }
        if(keyEvent.getCode() == KeyCode.ENTER && tglbtn_suono.isFocused()){
            Utili.gestisciSuoni(tglbtn_suono);
        }

    }
    //sistemare ogni volta i focus dei bottoni
    public void pulisci(){
        btn_esci.setFocusTraversable(false);
        tglbtn_suono.setFocusTraversable(false);
        btn_regole.setFocusTraversable(false);
        tglbtn_musica.setFocusTraversable(false);
    }

    public void salvaEdEsci(MouseEvent mouseEvent) throws IOException {
        salvaEdEsci();
    }

    public void setSuono(MouseEvent mouseEvent) {
        Utili.gestisciSuoni(tglbtn_suono);
    }

    public void setMusica(MouseEvent mouseEvent) {
        Utili.gestisciMusica(tglbtn_musica);
    }
}
