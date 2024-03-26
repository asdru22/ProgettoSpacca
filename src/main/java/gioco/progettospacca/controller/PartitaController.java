package gioco.progettospacca.controller;

import gioco.progettospacca.classi.*;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;


import static gioco.progettospacca.controller.Main.OPZ;

public class PartitaController implements Initializable {
    public static final int CODICE_TEMP = 74564;
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
    private Label lbl_classificaFinale;
    @FXML
    private Label lbl_vincitore;
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
    private Pane pane_finePartita;
    @FXML
    private Button btn_esci;
    @FXML
    private ToggleButton tglbtn_suono;
    @FXML
    private Button btn_regole;
    @FXML
    private ToggleButton tglbtn_musica;
    @FXML
    private Button btn_gioca;
    @FXML
    private Button btn_prossimaMano;

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
    String percorsoMazzo = "/gioco/progettospacca/Retro.png";
    boolean pausa = false;


    private void inizializzaTraduzioni() {

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


    public void giocaTurno() throws IOException {
        anch_mazzo.getScene().setOnKeyPressed(this::keyEventPausa); //recupero la scena corrente e imposto il gestore degli eventi da tastiera

        mazzo = p.getMazzo();
        System.out.println("Tocca a " + p.getToccaA());

        toccaA.pesca(5, mazzo);

        mano = toccaA.getMano();
        toccaA.setMano(mano);

        mostraCarte(mano);
        semeAnimazione();
        pescataAnimazione();
        comparsaSchermata();
        btn_stai.setDisable(true);
        btn_scarta.setDisable(true);

        if (!toccaA.isBot()) {       //giocatore reale
            btn_stai.setDisable(false);
            btn_scarta.setDisable(false);
            btn_scarta.setOnMouseClicked(event -> scarta(event));
            btn_stai.setOnMouseClicked(event -> {
                try {
                    stai(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {//bot
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.play();
            pause.setOnFinished(event -> {
                btn_stai.setDisable(true);
                btn_scarta.setDisable(true);
                int azione = Utili.intCasuale(1, 2);

                switch (azione) {
                    case 1:
                        PauseTransition pause1 = new PauseTransition(Duration.seconds(2));
                        pause1.play();
                        pause1.setOnFinished(event2 -> {
                            pulisciSchermata();
                            System.out.println("Hai deciso di stare");
                            int punti = p.valutaCarte(mano);
                            lbl_punteggio.setVisible(true);
                            lbl_punteggio.setText(String.valueOf(punti));
                            pulisciSchermata();
                            p.setCont(cont + 1);
                            toccaA.setPunti(punti);
                            fineMano();
                            btn_prossimaMano.setDisable(true);
                            PauseTransition pause3 = new PauseTransition(Duration.seconds(3));
                            pause3.play();
                            pause3.setOnFinished(event3 -> {
                                try {
                                    newScene();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        });

                        break;
                    case 2:
                        int num = 0;
                        int pos = 0;
                        num = Utili.intCasuale(1, 3);
                        switch (num) {
                            case 1:
                                ArrayList<Integer> numeri = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

                                Collections.shuffle(numeri);
                                pos = 1;

                                toccaA.settaCarteNulle(pos - 1);
                                toccaA.scarta(pos);

                                break;
                            case 2:
                                numeri = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
                                int i = 1;
                                do {

                                    Collections.shuffle(numeri);
                                    pos = numeri.remove(0);

                                    toccaA.settaCarteNulle(pos - 1);
                                    toccaA.scarta(pos);
                                    i++;

                                } while (i < 3);
                                break;
                            case 3:
                                numeri = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
                                int j = 1;
                                do {
                                    Collections.shuffle(numeri);
                                    pos = numeri.remove(0);


                                    toccaA.settaCarteNulle(pos - 1);
                                    toccaA.scarta(pos);
                                    j++;


                                } while (j < 4);
                                break;
                            default:
                                System.out.println("qualcosa è andato storto");
                        }


                        ArrayList<Carta> manoList = new ArrayList<>(Arrays.asList(toccaA.getMano()));

                        for (int i = 0; i < manoList.size(); i++) {
                            Carta carta = manoList.get(i);
                            if (carta == null) {
                                manoList.remove(i);  // Rimuovi l'elemento null
                                manoList.add(i, p.getMazzo().getMazzoArrayList().remove(0));// Inserisci la nuova carta dal mazzo
                                manoList.get(i).setCliccata(true);
                            }
                        }


                        PauseTransition pause2 = new PauseTransition(Duration.seconds(2.5));
                        pause2.play();
                        pause2.setOnFinished(event2 -> {
                            pulisciSchermata();
                            toccaA.setMano(manoList.toArray(new Carta[0]));
                            scartataAnimazione();
                            int punti = p.valutaCarte(toccaA.getMano());
                            lbl_punteggio.setText(String.valueOf(punti));
                            p.setCont(cont + 1);
                            toccaA.setPunti(punti);
                            btn_prossimaMano.setDisable(true);
                            PauseTransition pause3 = new PauseTransition(Duration.seconds(8));
                            pause3.play();
                            pause3.setOnFinished(event3 -> {
                                try {
                                    newScene();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        });


                }
            });
        }
    }

    public void scarta(MouseEvent event) {
        System.out.println("Hai deciso di scartare");
        pulisciSchermata();
        aggiungiEventiCarte();
        lbl_scegliCarteDaScartare.setVisible(true);
        btn_conferma.setVisible(true);
    }
    public void scarta() {
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
        p.setCont(cont + 1);
        toccaA.setPunti(punti);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.play();
        pause.setOnFinished(event2 -> {
            fineMano();
        });

    }
    public void stai() throws IOException {
        System.out.println("Hai deciso di stare");
        int punti = p.valutaCarte(mano);
        lbl_punteggio.setVisible(true);
        lbl_punteggio.setText(String.valueOf(punti));
        pulisciSchermata();
        p.setCont(cont + 1);
        toccaA.setPunti(punti);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.play();
        pause.setOnFinished(event2 -> {
            fineMano();
        });

    }

    //tasto conferma carte da scartare
    public void cambiaCarteSelezionate(MouseEvent mouseEvent) throws FileNotFoundException {
        ArrayList<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
        boolean almenoUnaTrue = false;
        for (Carta carta : manoList) {
            if (carta.getCliccata()) {
                almenoUnaTrue = true;
                break;
            }
        }
        if (almenoUnaTrue) {
            scartataAnimazione(); //lo metto subito perchè appena inizia una transizione il codice va avanti e non aspetta
            // la fine dell'animazione, quindi cambia le carte animatamente corrette poi eseguo lo scarto effettivo in questo metodo poi una volta terminata
            // l'animazione dello scarto, parte l'animazione della pescata con le carte già modificate in quanto il codice di questo metodo ha
            // eseguito subito dopo l'animazione dello scarto

            //debug
            //System.out.println("ciao");
            int pos = 1;
            for (Carta carta : manoList) {
                if (carta.getCliccata()) {
                    toccaA.settaCarteNulle(pos - 1);
                    toccaA.scarta(pos);
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
        } else {
            lbl_attenzione.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event -> {
                lbl_attenzione.setVisible(false);
            });

        }
    }

    public void cambiaCarteSelezionate() throws FileNotFoundException {

        ArrayList<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
        boolean almenoUnaTrue = false;
        for (Carta carta : manoList) {
            if (carta.getCliccata()) {
                almenoUnaTrue = true;
                break;
            }
        }
        if (almenoUnaTrue) {
            scartataAnimazione(); //lo metto subito perchè appena inizia una transizione il codice va avanti e non aspetta
            // la fine dell'animazione, quindi cambia le carte animatamente corrette poi eseguo lo scarto effettivo in questo metodo poi una volta terminata
            // l'animazione dello scarto, parte l'animazione della pescata con le carte già modificate in quanto il codice di questo metodo ha
            // eseguito subito dopo l'animazione dello scarto

            //debug
            //System.out.println("ciao");
            int pos = 1;
            for (Carta carta : manoList) {
                if (carta.getCliccata()) {
                    toccaA.settaCarteNulle(pos - 1);
                    toccaA.scarta(pos);
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
        } else {
            lbl_attenzione.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event -> {
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

    public void rimuoviEventiCarte() {
        carta1.setOnMouseClicked(null);
        carta2.setOnMouseClicked(null);
        carta3.setOnMouseClicked(null);
        carta4.setOnMouseClicked(null);
        carta5.setOnMouseClicked(null);
    }

    public void aggiungiEventiCarte() {
        carta1.setOnMouseClicked(event -> carta1Click(event));
        carta2.setOnMouseClicked(event -> carta2Click(event));
        carta3.setOnMouseClicked(event -> carta3Click(event));
        carta4.setOnMouseClicked(event -> carta4Click(event));
        carta5.setOnMouseClicked(event -> carta5Click(event));

    }

    //per selezionare la carta da scartare per giocatori reali
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
        if (mano[0].getCliccata()) {
            mano[0].setCliccata(false);
        } else {
            mano[0].setCliccata(true);
        }
        spostaCarta(carta1, mano[0]);
    }

    private void carta1Click() {
        if (mano[0].getCliccata()) {
            mano[0].setCliccata(false);
        } else {
            mano[0].setCliccata(true);
        }
        spostaCarta(carta1, mano[0]);
    }

    private void carta2Click(MouseEvent event) {
        if (mano[1].getCliccata()) {
            mano[1].setCliccata(false);
        } else {
            mano[1].setCliccata(true);
        }
        spostaCarta(carta2, mano[1]);
    }

    private void carta2Click() {
        if (mano[1].getCliccata()) {
            mano[1].setCliccata(false);
        } else {
            mano[1].setCliccata(true);
        }
        spostaCarta(carta2, mano[1]);
    }

    private void carta3Click(MouseEvent event) {
        if (mano[2].getCliccata()) {
            mano[2].setCliccata(false);
        } else {
            mano[2].setCliccata(true);
        }
        spostaCarta(carta3, mano[2]);
    }

    private void carta3Click() {
        if (mano[2].getCliccata()) {
            mano[2].setCliccata(false);
        } else {
            mano[2].setCliccata(true);
        }
        spostaCarta(carta3, mano[2]);
    }

    private void carta4Click(MouseEvent event) {
        if (mano[3].getCliccata()) {
            mano[3].setCliccata(false);
        } else {
            mano[3].setCliccata(true);
        }
        spostaCarta(carta4, mano[3]);
    }

    private void carta4Click() {
        if (mano[3].getCliccata()) {
            mano[3].setCliccata(false);
        } else {
            mano[3].setCliccata(true);
        }
        spostaCarta(carta4, mano[3]);
    }


    private void carta5Click(MouseEvent event) {
        if (mano[4].getCliccata()) {
            mano[4].setCliccata(false);
        } else {
            mano[4].setCliccata(true);
        }
        spostaCarta(carta5, mano[4]);
    }

    private void carta5Click() {
        if (mano[4].getCliccata()) {
            mano[4].setCliccata(false);
        } else {
            mano[4].setCliccata(true);
        }
        spostaCarta(carta5, mano[4]);
    }


    private void mostraCarte(Carta[] mano) throws FileNotFoundException {
        mano[0].setCliccata(false);
        mano[1].setCliccata(false);
        mano[2].setCliccata(false);
        mano[3].setCliccata(false);
        mano[4].setCliccata(false);

        imageView1 = makeImageView(mano[0].getImage());
        imageView2 = makeImageView(mano[1].getImage());
        imageView3 = makeImageView(mano[2].getImage());
        imageView4 = makeImageView(mano[3].getImage());
        imageView5 = makeImageView(mano[4].getImage());
        imageViewSeme = makeImageView(p.getSeme().getImage());
        imageViewMazzo = makeImageView(percorsoMazzo);
        imageViewSeme.setRotate(270);

        anch_mazzo.getChildren().add(imageViewMazzo);
        anch_seme.getChildren().add(imageViewSeme);


        cartaPaneMap.put("carta0", carta1);
        cartaPaneMap.put("carta1", carta2);
        cartaPaneMap.put("carta2", carta3);
        cartaPaneMap.put("carta3", carta4);
        cartaPaneMap.put("carta4", carta5);

        cartaMap.put("carta0", imageView1);
        cartaMap.put("carta1", imageView2);
        cartaMap.put("carta2", imageView3);
        cartaMap.put("carta3", imageView4);
        cartaMap.put("carta4", imageView5);


    }

    public void nuoveCartePescateAnimazione() throws FileNotFoundException {
        // Imposta la posizione iniziale della cartaMazzo
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        for (int i = 0; i < 5; i++) {
            if (toccaA.getMano()[i].getCliccata() == true) {
                String cartaName = "carta" + i;

                AnchorPane currentCarta = cartaPaneMap.get(cartaName);

                ImageView retroCarta = makeImageView(percorsoMazzo);
                currentCarta.getChildren().add(retroCarta);

                //posizioni delle carte sul terreno che sono fisse
                double endToX = (140 * (i + 1) - (10 * (i)));
                double endToY = 365;


                // Crea la transizione
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), retroCarta);
                transition.setToX(-(startFromX - endToX));
                transition.setToY(-(startFromY - endToY));

                // Esegui l'animazione
                transition.play();
                int finalI = i;
                transition.setOnFinished(event -> {
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.play();
                    pause.setOnFinished(event2 -> {
                        RotateTransition rotate = new RotateTransition(Duration.seconds(0.25), retroCarta);

                        rotate.setByAngle(180); // Specifica l'angolo di rotazione desiderato
                        rotate.setAxis(Rotate.Y_AXIS);

                        rotate.play();
                        rotate.setOnFinished(event3 -> {
                            currentCarta.getChildren().remove(retroCarta);
                            try {
                                giroCarte(finalI);
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
    public void giroCarte(int i) throws FileNotFoundException {
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        double endToX = (140 * (i + 1) - (10 * (i)));
        double endToY = 365;

        String cartaName = "carta" + i;

        AnchorPane currentCarta = cartaPaneMap.get(cartaName);
        ImageView imageView = makeImageView(toccaA.getMano()[i].getImage());

        imageView.setLayoutX(-(startFromX - endToX));
        imageView.setLayoutY(-(startFromY - endToY));


        currentCarta.getChildren().add(imageView);

        toccaA.getMano()[i].setCliccata(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.play();
        pause.setOnFinished(event2 -> {
            fineMano();
        });


    }

    public void giroCarte2(int i) throws FileNotFoundException {
        String cartaName = "carta" + i;
        AnchorPane currentCarta = cartaPaneMap.get(cartaName);
        currentCarta.setLayoutX(140 * (i + 1) - (10 * (i)));
        currentCarta.setLayoutY(365);

        Carta carta = toccaA.getMano()[i];
        if (carta != null) {
            ImageView cartaImageView = makeImageView(carta.getImage());
            currentCarta.getChildren().add(cartaImageView);
        } else {
            // Gestisci il caso in cui la carta è nulla
            // Ad esempio, puoi aggiungere un'immagine di default o fare altre operazioni necessarie
        }
    }


    //animazione quando le carte scartate tornano nel mazzo
    public void scartataAnimazione() {
        // Imposta la posizione iniziale della cartaMazzo
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        for (int i = 0; i < 5; i++) {
            if (toccaA.getMano()[i].getCliccata() == true) {
                String cartaName = "carta" + i;
                AnchorPane currentCarta = cartaPaneMap.get(cartaName);

                double endToX = currentCarta.getLayoutX();
                double endToY = currentCarta.getLayoutY();


                // Crea la transizione
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), currentCarta);
                transition.setToX((startFromX - endToX));
                transition.setToY((startFromY - endToY));

                // Esegui l'animazione
                transition.play();
                transition.setOnFinished(event -> {
                    //creo una pausa tra l'animazione delle carte scartate e quelle pescate
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.play();
                    pause.setOnFinished(event2 -> {
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

    public void semeAnimazione() {
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        double endToX = 335;
        double endToY = 60;

        imageViewSeme.setLayoutX(startFromX - endToX);
        imageViewSeme.setLayoutY(startFromY - endToY);

        // Crea la transizione
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), imageViewSeme);
        transition.setToX(-(startFromX - endToX));
        transition.setToY(-(startFromY - endToY));

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), imageViewSeme);
        rotateTransition.setByAngle(90); // Specifica l'angolo di rotazione finale (360 gradi)
        rotateTransition.setAxis(Rotate.Y_AXIS);

        // Avvia l'animazione
        rotateTransition.play();
        transition.play();

    }

    public void pescataAnimazione() {
        // Imposta la posizione iniziale della cartaMazzo
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        for (int i = 0; i < 5; i++) {
            String cartaName = "carta" + i;
            AnchorPane currentCarta = cartaPaneMap.get(cartaName);
            ImageView carta = cartaMap.get(cartaName);
            ImageView retroCarta = makeImageView(percorsoMazzo);
            currentCarta.getChildren().add(retroCarta);

            double endToX = currentCarta.getLayoutX();
            double endToY = currentCarta.getLayoutY();

            currentCarta.setLayoutX(startFromX);
            currentCarta.setLayoutY(startFromY);

            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), retroCarta);
            transition.setToX(-(startFromX - endToX));
            transition.setToY(-(startFromY - endToY));

            // Esegui l'animazione
            transition.play();
            int finalI = i;
            transition.setOnFinished(event -> {
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.play();
                pause.setOnFinished(event2 -> {
                    RotateTransition rotate = new RotateTransition(Duration.seconds(0.25), retroCarta);

                    rotate.setByAngle(180); // Specifica l'angolo di rotazione desiderato
                    rotate.setAxis(Rotate.Y_AXIS);

                    rotate.play();
                    rotate.setOnFinished(event3 -> {
                        currentCarta.getChildren().remove(retroCarta);
                        try {
                            giroCarte2(finalI);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
            });
        }
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

    public void fineMano() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), anchPane_manoSuccesiva);
        translateTransition.setByX(-300);
        translateTransition.play();
        btn_prossimaMano.setVisible(true);

    }

    //collegato al bottone del anchor pane manoSuccesiva
    public void prossimaMano(MouseEvent mouseEvent) throws IOException {
        newScene();
    }

    public void schermataToccaA() {
        if (!toccaA.isBot()) {       //persona reale
            pulisciSchermata();
            anchPane_toccaA.setVisible(true);
            btn_gioca.setVisible(true);
            lbl_toccaA.setText("Turno: " + p.getToccaA());
            lbl_turno.setText("Turno: " + (p.getTurnoSalvato()));
        } else {          //bot
            pulisciSchermata();
            anchPane_toccaA.setVisible(true);
            btn_gioca.setDisable(true);
            lbl_toccaA.setText("Turno: " + p.getToccaA());
            lbl_turno.setText("Turno: " + (p.getTurnoSalvato()));

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.play();
            pause.setOnFinished(event -> {
                anchPane_toccaA.setVisible(false);
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), anchPane_score);
                translateTransition.setByX(220);
                translateTransition.play();
                try {
                    giocaTurno();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }

    //collegato al bottone del anchor pane toccaA
    public void procedi(MouseEvent mouseEvent) throws FileNotFoundException {
        anchPane_toccaA.setVisible(false);
        btn_gioca.setVisible(false);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), anchPane_score);
        translateTransition.setByX(220);
        translateTransition.play();
        try {
            giocaTurno();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void procedi() throws FileNotFoundException {
        anchPane_toccaA.setVisible(false);
        btn_gioca.setVisible(false);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), anchPane_score);
        translateTransition.setByX(220);
        translateTransition.play();
        try {
            giocaTurno();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ImageView makeImageView(String path) {
        try {
            // solleva errore se il path è null
            return new ImageView(new Image(Objects.requireNonNull(Carta.class.getResourceAsStream(path))));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Immagine non trovata: " + path, e);
        }
    }

    private void newScene() throws IOException {
        if (cont == p.getNumeroTurni() * p.getGiocatori().length - 1) {
            String s = "";
            for (int i = 0; i < p.getGiocatori().length; i++) {
                s = s + p.getGiocatori()[i].getNome() + ":  " + p.getGiocatori()[i].getPunti() + "\n";
            }
            p.finePartita();
            lbl_classificaFinale.setText(s);
            lbl_vincitore.setText(""+p.getVincitore());
            pane_finePartita.setVisible(true);
            anchPane_score.setDisable(true);
            anchPane_manoSuccesiva.setDisable(true);
        } else {

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
    //bottone torna all home una volta finita la partita, bottone del pane_finePartita
    public void finePartita(MouseEvent event) throws IOException {
        salvaEdEsci();
    }

    public void salvaEdEsci() throws IOException {
        BackToHome();
        OPZ.playMusica("lobby.wav");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        inizializzaTraduzioni();

        p = Partita.carica(CODICE_TEMP);
        mostraClassifica();
        cont = p.getCont();
        giocatori = p.getGiocatori();
        turno_salvato = p.getTurnoSalvato();
        giocatore_salvato = p.getGiocatoreSalvato();
        p.setSeme(p.getMazzo().getMazzoArrayList().remove(0));

        p.setTurnoSalvato(p.getCont() / p.getGiocatori().length);
        turno_salvato = p.getTurnoSalvato();

        if (giocatore_salvato < p.getGiocatori().length) {
            p.setGiocatoreSalvato(giocatore_salvato + 1);
            toccaA = giocatori[p.getGiocatoreSalvato() - 1];

        } else {
            p.setGiocatoreSalvato(1);
            toccaA = giocatori[0];
        }

        p.setToccaA(toccaA);

        schermataToccaA();

        p.newMazzo();

    }

    private void mostraClassifica() {
        String s = "";
        for (int i = 0; i < p.getGiocatori().length; i++) {
            s = s + p.getGiocatori()[i].getNome() + ":  " + p.getGiocatori()[i].getPunti() + "\n";
        }
        lbl_classifica.setText(s);
    }

    public void keyEventPausa(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            if (!pausa) {
                pane_pausa.setVisible(true);
                anchorPane.setDisable(true);
                anchPane_manoSuccesiva.setDisable(true);
                anchPane_score.setDisable(true);
                btn_regole.requestFocus();
                pausa = true;
            } else {
                pane_pausa.setVisible(false);
                anchorPane.setDisable(false);
                anchPane_manoSuccesiva.setDisable(false);
                anchPane_score.setDisable(false);
                pausa = false;
            }
        }
    }

    public void keyEventTastiniPausa(KeyEvent keyEvent) throws IOException {
        if (tglbtn_suono.isFocused() || btn_esci.isFocused() || btn_regole.isFocused() || tglbtn_musica.isFocused()) {
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
        if (keyEvent.getCode() == KeyCode.ENTER && btn_esci.isFocused()) {
            salvaEdEsci();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_musica.isFocused()) {
            Utili.gestisciMusica(tglbtn_musica);
        }
        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_suono.isFocused()) {
            Utili.gestisciSuoni(tglbtn_suono);
        }

    }

    boolean prima = false; //per verificare se è la prima volta che clicco la freccia a destra per impostare correttamente il focus

    public void keyPartita(KeyEvent keyEvent) throws FileNotFoundException {
        if (keyEvent.getCode() == KeyCode.RIGHT && btn_conferma.isVisible()) {
            carta1.setFocusTraversable(false);
            carta1.getStyleClass().add("carta");
            carta2.getStyleClass().add("carta");
            carta3.getStyleClass().add("carta");
            carta4.getStyleClass().add("carta");
            carta5.getStyleClass().add("carta");
            if (carta1.isFocused()) {
                if (prima == false) {
                    carta1.requestFocus();
                    prima = true;
                } else {
                    carta2.requestFocus();
                }
            } else if (carta2.isFocused()) {
                carta3.requestFocus();
            } else if (carta3.isFocused()) {
                carta4.requestFocus();
            } else if (carta4.isFocused()) {
                carta5.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.LEFT && btn_conferma.isVisible()) {
            carta1.setFocusTraversable(false);
            carta1.getStyleClass().add("carta");
            carta2.getStyleClass().add("carta");
            carta3.getStyleClass().add("carta");
            carta4.getStyleClass().add("carta");
            carta5.getStyleClass().add("carta");
            if (carta1.isFocused()) {
                if (prima == false) {
                    carta1.requestFocus();
                    prima = true;
                }
            } else if (carta2.isFocused()) {
                carta1.requestFocus();
            } else if (carta3.isFocused()) {
                carta2.requestFocus();
            } else if (carta4.isFocused()) {
                carta3.requestFocus();
            } else if (carta5.isFocused()) {
                carta4.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_conferma.isVisible()) {
            if (carta1.isFocused()) {
                carta1Click();
            } else if (carta2.isFocused()) {
                carta2Click();
            } else if (carta3.isFocused()) {
                carta3Click();
            } else if (carta4.isFocused()) {
                carta4Click();
            } else if (carta5.isFocused()) {
                carta5Click();
            } else if (btn_conferma.isFocused()) {
                cambiaCarteSelezionate();
            }
        }
        if (keyEvent.getCode() == KeyCode.UP && btn_conferma.isVisible()) {
            btn_conferma.requestFocus();
        }
        if ((keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.LEFT) && btn_conferma.isFocused() && btn_conferma.isVisible()) {
            carta1.requestFocus();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_prossimaMano.isVisible()) {
            try {
                newScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_gioca.isVisible()) {
            try {
                procedi();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        /*
        if (keyEvent.getCode() == KeyCode.ENTER && pane_finePartita.isVisible()) {
            try {
                salvaEdEsci();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        */

    }

    public void keyScelta(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT && btn_scarta.isVisible()) {
            btn_scarta.requestFocus();
            if (btn_scarta.isFocused()) {
                btn_stai.requestFocus();
            }

        }
        if (keyEvent.getCode() == KeyCode.LEFT && btn_scarta.isVisible()) {
            btn_scarta.requestFocus();
            if (btn_stai.isFocused()) {
                btn_scarta.requestFocus();
            }
        }
        if(keyEvent.getCode() == KeyCode.ENTER && btn_scarta.isVisible() && btn_scarta.isFocused()){
            scarta();
        }
        if(keyEvent.getCode() == KeyCode.ENTER && btn_stai.isVisible() && btn_stai.isFocused()){
            try {
                stai();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    //sistemare ogni volta i focus dei bottoni
    public void pulisci() {
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
