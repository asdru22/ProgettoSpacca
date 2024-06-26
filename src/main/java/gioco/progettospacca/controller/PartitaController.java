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

import static gioco.progettospacca.controller.Main.*;

public class PartitaController implements Initializable {
    @FXML
    Label lbl_titoloImprevisto;
    @FXML
    Label lbl_vincitorePartitaTorneo;
    @FXML
    Label lbl_vincitoreFisso1;
    @FXML
    Label lbl_classificaFisso;
    @FXML
    Label lbl_vincitoreFisso;
    @FXML
    Label lbl_punteggio_fatto;
    @FXML
    Button btn_home;
    @FXML
    Label lbl_pausa;
    @FXML
    Label lbl_turnodi;
    @FXML
    Label lbl_turnoNome;
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
    private ImageView imageFocus;
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
    private Label lbl_PerFocus;
    @FXML
    private AnchorPane anchPane_toccaA;
    @FXML
    private AnchorPane anch_mazzo;
    @FXML
    Button btn_tabellone;
    @FXML
    private AnchorPane anch_seme;
    @FXML
    private AnchorPane anch_imprevisto;
    @FXML
    private Label lbl_attenzione;
    @FXML
    private Label lbl_imprevisto;
    @FXML
    private AnchorPane anchPane_score;
    @FXML
    private Pane pane_pausa;
    @FXML
    private Pane pane_finePartita;
    @FXML
    private Pane pane_finePartitaTorneo;
    @FXML
    private Pane pane_fineTorneo;
    @FXML
    private Pane pane_imprevisto;
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
    private Button btn_classifica;
    @FXML
    private Button btn_prossimaMano;
    @FXML
    private Button btn_scarta;
    @FXML
    private Button btn_stai;
    @FXML
    private Button btn_conferma;
    @FXML
    private Button btn_backhome;
    @FXML
    Label lbl_vincitoreFisso11;
    @FXML
    Label lbl_vincitoreTorneo;
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
    private Carta[] mano;
    private Mazzo mazzo;
    private Giocatore[] giocatori = null;
    private int turno_salvato = 0;
    private int giocatore_salvato = 0;
    private Giocatore toccaA;
    private int cont;
    String percorsoMazzo = "/gioco/progettospacca/Retro.png";
    boolean pausa = false;
    Torneo t = null;
    //inizialmente 6 cioè nessuna carta imprevisto, se compare prende la posizione della carta in mano
    private int imprevistoNum = 6;

    private int ceGia1Imprevisto = 0;

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
        btn_scarta.setText(OPZ.traduci("scarta"));
        btn_stai.setText(OPZ.traduci("stai"));
        btn_gioca.setText(OPZ.traduci("gioca"));
        btn_conferma.setText(OPZ.traduci("conferma"));
        lbl_punteggio.setText(OPZ.traduci("punteggio_fatto"));
        btn_prossimaMano.setText(OPZ.traduci("prossimo_turno"));
        lbl_vincitore.setText(OPZ.traduci("vincitore"));
        lbl_classificaFinale.setText(OPZ.traduci("classifica_finale"));
        btn_home.setText(OPZ.traduci("torna_alla_home"));
        lbl_punteggio_fatto.setText(OPZ.traduci("punteggio_fatto"));
        lbl_classificaFisso.setText(OPZ.traduci("classifica_finale"));
        lbl_vincitoreFisso.setText(OPZ.traduci("vincitore"));
        btn_regole.setText(OPZ.traduci("regole"));
        btn_backhome.setText(OPZ.traduci("torna_alla_home"));
        lbl_vincitoreFisso11.setText(OPZ.traduci("vincitore_torneo"));
        lbl_vincitoreFisso1.setText(OPZ.traduci("vincitore"));
        lbl_titoloImprevisto.setText(OPZ.traduci("pescato_imprevisto"));
        btn_tabellone.setText(OPZ.traduci("torna_al_tabellone"));
        lbl_turnodi.setText(OPZ.traduci("turno_di"));
        btn_classifica.setText(OPZ.traduci("classifica"));

    }


    public void giocaTurno() throws IOException {
        anch_mazzo.getScene().setOnKeyPressed(this::keyEventPausa); //recupero la scena corrente e imposto il gestore degli eventi da tastiera

        mazzo = p.getMazzo();

        toccaA.pesca(5, mazzo);

        mano = toccaA.getMano();
        toccaA.setMano(mano);

        for (int i = 0; i < 5; i++) {     //controllo per non far comparire 2 carte imprevisti nella stessa mano
            if (mano[i].getSeme() == Seme.Neutro) {
                ceGia1Imprevisto++;
            }
            if (ceGia1Imprevisto > 1) {
                toccaA.pesca(5, mazzo);
                ceGia1Imprevisto = 0;
                i = -1;
            }
        }


        mostraCarte(mano);
        semeAnimazione();
        pescataAnimazione();
        p.setImprevisti();  //per tenere traccia dell'effetto dell'imprevisto anche dopo che l'ho tolto dalla mano

        for (int i = 0; i < 5; i++) {
            if (mano[i].getImage() == "/gioco/progettospacca/carte/imprevisti/1.png") {
                imprevistoNum = i + 1;
                PauseTransition pauseImprevisto = new PauseTransition(Duration.seconds(2));
                pauseImprevisto.play();
                int finalI = i;
                pauseImprevisto.setOnFinished(event -> {
                    pane_imprevisto.setVisible(true);
                    lbl_imprevisto.setText(OPZ.traduci("punti_raddoppiati"));
                    cambiaSingolaCarta(finalI + 1);
                    btn_stai.setDisable(true);
                    btn_scarta.setDisable(true);

                    PauseTransition pauseImprevisto2 = new PauseTransition(Duration.seconds(3));
                    pauseImprevisto2.play();
                    pauseImprevisto2.setOnFinished(event2 -> {
                        pane_imprevisto.setVisible(false);
                        animazioneImprevisto(finalI);
                    });
                });
                break;
            } else if (mano[i].getImage() == "/gioco/progettospacca/carte/imprevisti/2.png") {
                imprevistoNum = i + 1;
                PauseTransition pauseImprevisto = new PauseTransition(Duration.seconds(2));
                pauseImprevisto.play();
                int finalI = i;
                pauseImprevisto.setOnFinished(event -> {
                    String s = "";
                    for (int j = 0; j < p.getGiocatori().length; j++) {
                        s = s + p.getGiocatori()[j].getNome() + ":  " + p.getGiocatori()[j].getPunti() + "\n";
                    }
                    lbl_classifica.setText(s);
                    pane_imprevisto.setVisible(true);
                    lbl_imprevisto.setText(OPZ.traduci("ruba_punti") + (p.getSommaRubata()));
                    cambiaSingolaCarta(finalI + 1);
                    btn_stai.setDisable(true);
                    btn_scarta.setDisable(true);

                    PauseTransition pauseImprevisto2 = new PauseTransition(Duration.seconds(3));
                    pauseImprevisto2.play();
                    pauseImprevisto2.setOnFinished(event2 -> {
                        pane_imprevisto.setVisible(false);
                        animazioneImprevisto(finalI);
                    });
                });
                break;
            }
        }
        PauseTransition pausa = new PauseTransition(Duration.seconds(2));
        pausa.play();
        pausa.setOnFinished(event2 -> {
            comparsaSchermata();
        });

        if (!toccaA.isBot()) {
            //giocatore reale
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
        } else {
            //bot
            double tempo = 1.5;
            if (imprevistoNum <= 5) {
                tempo = 8;
            }
            PauseTransition pause = new PauseTransition(Duration.seconds(tempo));
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
                            mano = p.getToccaA().getMano();
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
                                    String s = "";
                                    for (int j = 0; j < p.getGiocatori().length; j++) {
                                        s = s + p.getGiocatori()[j].getNome() + ":  " + p.getGiocatori()[j].getPunti() + "\n";
                                    }
                                    lbl_classifica.setText(s);
                                    if(btn_scarta.getScene()==null){        //controllo per quando clicco salva ed esci durante il turno del bot, non gli faccio fare il newScene (avendo le pause, completa il codice lo stesso anche se sono su una nuova scena)

                                    }else {
                                        newScene();
                                    }
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
                        Iterator<Carta> iterator = mazzo.getMazzoArrayList().iterator();        //faccio questo per evitare che quando si pescano le carte al posto di quelle scartate si possano pescare degli imprevisti (scelto a livello di regole, gli imprevisti si possono pescare solo ad inizio partita per non renderli troppo comuni da trovare in quanto danno un aiuto non da poco )
                        while (iterator.hasNext()) {
                            Carta carta = iterator.next();
                            if (carta.getSeme() == Seme.Neutro) {
                                iterator.remove();
                            }
                        }
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
                                System.err.println("qualcosa è andato storto");
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
                            mano = p.getToccaA().getMano();
                            int punti = p.valutaCarte(toccaA.getMano());
                            lbl_punteggio.setText(String.valueOf(punti));
                            p.setCont(cont + 1);
                            toccaA.setPunti(punti);
                            btn_prossimaMano.setDisable(true);
                            PauseTransition pause3 = new PauseTransition(Duration.seconds(8));
                            pause3.play();
                            pause3.setOnFinished(event3 -> {
                                try {
                                    String s = "";
                                    for (int j = 0; j < p.getGiocatori().length; j++) {
                                        s = s + p.getGiocatori()[j].getNome() + ":  " + p.getGiocatori()[j].getPunti() + "\n";
                                    }
                                    lbl_classifica.setText(s);
                                    if(btn_scarta.getScene()==null){        //controllo per quando clicco salva ed esci durante il turno del bot, non gli faccio fare il newScene (avendo le pause, completa il codice lo stesso anche se sono su una nuova scena)

                                    }else {
                                        newScene();
                                    }

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
        OPZ.premiBottone();
        pulisciSchermata();
        aggiungiEventiCarte();
        lbl_scegliCarteDaScartare.setVisible(true);
        btn_conferma.setVisible(true);
    }

    public void scarta() {
        OPZ.premiBottone();
        pulisciSchermata();
        aggiungiEventiCarte();
        lbl_scegliCarteDaScartare.setVisible(true);
        btn_conferma.setVisible(true);
    }

    public void stai(MouseEvent event) throws IOException {
        OPZ.premiBottone();
        mano = p.getToccaA().getMano();
        int punti = p.valutaCarte(mano);
        lbl_punteggio.setVisible(true);
        lbl_punteggio.setText(String.valueOf(punti));
        pulisciSchermata();
        p.setCont(cont + 1);
        toccaA.setPunti(punti);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.play();
        pause.setOnFinished(event2 -> {
            String s = "";
            for (int j = 0; j < p.getGiocatori().length; j++) {
                s = s + p.getGiocatori()[j].getNome() + ":  " + p.getGiocatori()[j].getPunti() + "\n";
            }
            lbl_classifica.setText(s);
            fineMano();
        });

    }

    public void stai() throws IOException {
        OPZ.premiBottone();
        mano = p.getToccaA().getMano();
        int punti = p.valutaCarte(mano);
        lbl_punteggio.setVisible(true);
        lbl_punteggio.setText(String.valueOf(punti));
        pulisciSchermata();
        p.setCont(cont + 1);
        toccaA.setPunti(punti);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.play();
        pause.setOnFinished(event2 -> {
            String s = "";
            for (int j = 0; j < p.getGiocatori().length; j++) {
                s = s + p.getGiocatori()[j].getNome() + ":  " + p.getGiocatori()[j].getPunti() + "\n";
            }
            lbl_classifica.setText(s);
            fineMano();
        });

    }

    //cambia carta imprevisto con una carta da gioco
    public void cambiaSingolaCarta(int pos) {
        Iterator<Carta> iterator = mazzo.getMazzoArrayList().iterator();        //faccio questo per evitare che quando si pescano le carte al posto di quelle scartate si possano pescare degli imprevisti (scelto a livello di regole, gli imprevisti si possono pescare solo ad inizio partita per non renderli troppo comuni da trovare in quanto danno un aiuto non da poco )
        while (iterator.hasNext()) {
            Carta carta = iterator.next();
            if (carta.getSeme() == Seme.Neutro) {
                iterator.remove();
            }
        }
        toccaA.settaCarteNulle(pos - 1);
        toccaA.scarta(pos);

        ArrayList<Carta> manoList = new ArrayList<>(Arrays.asList(toccaA.getMano()));
        manoList.remove(pos - 1);  // Rimuovi l'elemento null
        manoList.add(pos - 1, p.getMazzo().getMazzoArrayList().remove(0));// Inserisci la nuova carta dal mazzo

        toccaA.setMano(manoList.toArray(toccaA.getMano()));


    }

    //tasto conferma carte da scartare
    public void cambiaCarteSelezionate() {
        OPZ.premiBottone();
        Iterator<Carta> iterator = mazzo.getMazzoArrayList().iterator();        //faccio questo per evitare che quando si pescano le carte al posto di quelle scartate si possano pescare degli imprevisti (scelto a livello di regole, gli imprevisti si possono pescare solo ad inizio partita per non renderli troppo comuni da trovare in quanto danno un aiuto non da poco )
        while (iterator.hasNext()) {
            Carta carta = iterator.next();
            if (carta.getSeme() == Seme.Neutro) {
                iterator.remove();
            }
        }
        ArrayList<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
        boolean almenoUnaTrue = false;
        for (Carta carta : manoList) {
            if (carta.getCliccata()) {
                almenoUnaTrue = true;
                break;
            }
        }
        if (almenoUnaTrue) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.25));
            pause.play();
            pause.setOnFinished(event2 -> {
                scartataAnimazione();
            }); //lo metto subito perchè appena inizia una transizione il codice va avanti e non aspetta
            // la fine dell'animazione, quindi cambia le carte animatamente corrette poi eseguo lo scarto effettivo in questo metodo poi una volta terminata
            // l'animazione dello scarto, parte l'animazione della pescata con le carte già modificate in quanto il codice di questo metodo ha
            // eseguito subito dopo l'animazione dello scarto

            //debug
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

            mano = p.getToccaA().getMano();
            int punti = p.valutaCarte(toccaA.getMano());
            lbl_punteggio.setText(String.valueOf(punti));
            p.setCont(cont + 1);
            toccaA.setPunti(punti);
            rimuoviEventiCarte();
            lbl_PerFocus.setFocusTraversable(true);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("scegli_almeno_una_carta"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
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

    private int numCarteSelezionate = 0;

    private void carta1Click(MouseEvent event) {
        if (mano[0].getCliccata()) {
            mano[0].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta1, mano[0]);
        } else if (numCarteSelezionate < 3) {
            mano[0].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta1, mano[0]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
    }

    private void carta1Click() {
        if (mano[0].getCliccata()) {
            mano[0].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta1, mano[0]);
        } else if (numCarteSelezionate < 3) {
            mano[0].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta1, mano[0]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }

    }

    private void carta2Click(MouseEvent event) {
        if (mano[1].getCliccata()) {
            mano[1].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta2, mano[1]);
        } else if (numCarteSelezionate < 3) {
            mano[1].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta2, mano[1]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
    }

    private void carta2Click() {
        if (mano[1].getCliccata()) {
            mano[1].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta2, mano[1]);
        } else if (numCarteSelezionate < 3) {
            mano[1].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta2, mano[1]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
    }

    private void carta3Click(MouseEvent event) {
        if (mano[2].getCliccata()) {
            mano[2].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta3, mano[2]);
        } else if (numCarteSelezionate < 3) {
            mano[2].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta3, mano[2]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
    }

    private void carta3Click() {
        if (mano[2].getCliccata()) {
            mano[2].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta3, mano[2]);
        } else if (numCarteSelezionate < 3) {
            mano[2].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta3, mano[2]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
    }

    private void carta4Click(MouseEvent event) {
        if (mano[3].getCliccata()) {
            mano[3].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta4, mano[3]);
        } else if (numCarteSelezionate < 3) {
            mano[3].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta4, mano[3]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
    }

    private void carta4Click() {
        if (mano[3].getCliccata()) {
            mano[3].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta4, mano[3]);
        } else if (numCarteSelezionate < 3) {
            mano[3].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta4, mano[3]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
    }


    private void carta5Click(MouseEvent event) {
        if (mano[4].getCliccata()) {
            mano[4].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta5, mano[4]);
        } else if (numCarteSelezionate < 3) {
            mano[4].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta5, mano[4]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
    }

    private void carta5Click() {
        if (mano[4].getCliccata()) {
            mano[4].setCliccata(false);
            numCarteSelezionate--;
            spostaCarta(carta5, mano[4]);
        } else if (numCarteSelezionate < 3) {
            mano[4].setCliccata(true);
            numCarteSelezionate++;
            spostaCarta(carta5, mano[4]);
        } else {
            lbl_attenzione.setVisible(true);
            lbl_attenzione.setText(OPZ.traduci("max_3_carte"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl_attenzione);
            fadeTransition.setFromValue(1.0); // Opacità iniziale
            fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
            fadeTransition.play();
            fadeTransition.setOnFinished(event1 -> {
                lbl_attenzione.setVisible(false);
                lbl_attenzione.setText("");
            });
        }
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

    //animazione dello spostamento della carta imprevisto nella apposita casella
    public void animazioneImprevisto(int i) {
        OPZ.suonoScarta();
        double startFromX = anch_imprevisto.getLayoutX();
        double startFromY = anch_imprevisto.getLayoutY();

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
            currentCarta.getChildren().clear();
            anch_imprevisto.getChildren().add(makeImageView(mano[i].getImage()));
            currentCarta.setLayoutX(-(startFromX - endToX) + endToX);
            currentCarta.setLayoutY(-(startFromY - endToY) + endToY);

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.play();
            pause.setOnFinished(event2 -> {
                pescataSingola(i);
            });
        });

    }

    //animazione per una singola pescata
    public void pescataSingola(int i) {
        OPZ.suonoScarta();
        // Imposta la posizione iniziale della cartaMazzo
        double startFromX = 0;
        if (i == 0) {
            startFromX = anch_mazzo.getLayoutX() - (130 * 1);
        } else {
            startFromX = anch_mazzo.getLayoutX() + (130 * (i - 1));
        }
        double startFromY = anch_mazzo.getLayoutY() + 300;

        String cartaName = "carta" + i;

        AnchorPane currentCarta = cartaPaneMap.get(cartaName);
        currentCarta.setLayoutX(startFromX);
        currentCarta.setLayoutY(startFromY);

        ImageView retroCarta = makeImageView(percorsoMazzo);
        currentCarta.getChildren().add(retroCarta);

        //posizioni delle carte sul terreno che sono fisse
        double endToX = (140 * (i + 1) - (10 * (i)));
        double endToY = 365;


        // Crea la transizione
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), currentCarta);
        transition.setToX(-(startFromX - endToX));
        transition.setToY(-(startFromY - endToY));
        // Esegui l'animazione
        transition.play();
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
                        giroCarte3(i);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        });
    }

    //animazione delle carte pescate dopo la scartata
    public void nuoveCartePescateAnimazione() throws FileNotFoundException {
        OPZ.suonoScarta();
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
            String s = "";
            for (int j = 0; j < p.getGiocatori().length; j++) {
                s = s + p.getGiocatori()[j].getNome() + ":  " + p.getGiocatori()[j].getPunti() + "\n";
            }
            lbl_classifica.setText(s);
            fineMano();
        });
    }

    //quando si girano le carte dopo aver pescato la mano iniziale
    public void giroCarte2(int i) throws FileNotFoundException {
        String cartaName = "carta" + i;
        AnchorPane currentCarta = cartaPaneMap.get(cartaName);
        currentCarta.setLayoutX(140 * (i + 1) - (10 * (i)));
        currentCarta.setLayoutY(365);

        Carta carta = toccaA.getMano()[i];
        if (carta != null) {
            ImageView cartaImageView = makeImageView(carta.getImage());
            currentCarta.getChildren().add(cartaImageView);
        }
    }

    //l'animazione del giro carta pescata dopo lo spostamento della carta imprevisto
    public void giroCarte3(int i) throws FileNotFoundException {
        String cartaName = "carta" + i;
        AnchorPane currentCarta = cartaPaneMap.get(cartaName);
        currentCarta.setLayoutX((140 * (i + 1) - (10 * (i))) + 260);
        currentCarta.setLayoutY(360);

        Carta carta = toccaA.getMano()[i];
        if (carta != null) {
            ImageView cartaImageView = makeImageView(carta.getImage());
            currentCarta.getChildren().add(cartaImageView);
        }
        if (!toccaA.isBot()) {
            btn_stai.setDisable(false);
            btn_scarta.setDisable(false);
        }
    }


    //animazione quando le carte scartate tornano nel mazzo
    public void scartataAnimazione() {
        OPZ.suonoScarta();
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

    //animazione de seme che comanda all'inizio del turno
    public void semeAnimazione() {
        double startFromX = anch_mazzo.getLayoutX();
        double startFromY = anch_mazzo.getLayoutY();

        double endToX = 400;
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

    //la pescata all'inizio del turno della mano
    public void pescataAnimazione() {
        OPZ.suonoScarta();
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
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_scarta.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
        OPZ.playMusica("lobby.wav");

    }

    //metodo chiamato dal bottone del pane fineTorneo
    public void BackToHome(MouseEvent event) throws IOException {
        BackToHome();
        Torneo.carica(CODICE_GLOBALE_TORNEO).fineTorneo();
    }


    public void fineMano() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), anchPane_manoSuccesiva);
        translateTransition.setByX(-255);
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
            lbl_toccaA.setText(OPZ.traduci("turno") + ": " + p.getToccaA());
            lbl_turno.setText(OPZ.traduci("round") + ": " + (p.getTurnoSalvato() + 1));
        } else {          //bot
            pulisciSchermata();
            anchPane_toccaA.setVisible(true);
            btn_gioca.setDisable(true);
            lbl_toccaA.setText(OPZ.traduci("turno") + ": " + p.getToccaA());
            lbl_turno.setText(OPZ.traduci("round") + ": " + (p.getTurnoSalvato() + 1));

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
    public void procedi() throws FileNotFoundException {
        OPZ.premiBottone();
        anchPane_toccaA.setVisible(false);
        btn_gioca.setVisible(false);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), anchPane_score);
        translateTransition.setByX(220);
        translateTransition.play();
        try {
            lbl_turnodi.setVisible(true);
            lbl_turnoNome.setText(toccaA.getNome());
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
        OPZ.premiBottone();
        if (cont >= p.getNumeroTurni() * p.getGiocatori().length - 1) {
            String s = "";
            for (int i = 0; i < p.getGiocatori().length; i++) {
                s = s + p.getGiocatori()[i].getNome() + ":  " + p.getGiocatori()[i].getPunti() + "\n";
            }
            p.finePartita();
            lbl_classificaFinale.setText(s);
            lbl_vincitore.setText("" + p.getVincitore());
            if (p.getIdTorneo() == 0) {
                pane_finePartita.setVisible(true);
            } else {
                pane_finePartitaTorneo.setVisible(true);
                lbl_vincitorePartitaTorneo.setText(p.getVincitore().getNome());
            }
            anchPane_score.setDisable(true);
            anchPane_manoSuccesiva.setDisable(true);
            anchorPane.setDisable(true);
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
        OPZ.premiBottone();
        BackToHome();
    }

    public void tornaAlTabellone() throws IOException {
        OPZ.premiBottone();
        Parent root = null;
        t = Torneo.carica(p.getIdTorneo());
        p.setPartitaTorneoNumGiocatori(t.getGiocatoriIniziali());
        if (t.isFinito()) {
            pane_fineTorneo.setVisible(true);
            pane_finePartitaTorneo.setDisable(true);
            lbl_vincitoreTorneo.setText(t.getVincitore().getNome());
        } else {
            if (p.getPartitaTorneoNumGiocatori() == 4) {
                root = FXMLLoader.load(getClass().getResource("TorneoView4.fxml"));
            } else if (p.getPartitaTorneoNumGiocatori() == 8) {
                root = FXMLLoader.load(getClass().getResource("TorneoView8.fxml"));
            } else if (p.getPartitaTorneoNumGiocatori() == 16) {
                root = FXMLLoader.load(getClass().getResource("TorneoView16.fxml"));
            }
            // Ottieni la finestra corrente
            Stage currentStage = (Stage) btn_scarta.getScene().getWindow();

            // Ottieni la scena corrente
            Scene currentScene = currentStage.getScene();

            // Imposta la nuova radice della scena
            currentScene.setRoot(root);

            // Imposta il titolo della finestra
            currentStage.setTitle(OPZ.traduci("spacca"));
            OPZ.playMusica("lobby.wav");
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_esci.setOnMouseEntered(e -> {
            if (btn_esci.getScene() != null) {
                btn_esci.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_esci.setOnMouseExited(e -> {
            if (btn_esci.getScene() != null) {
                btn_esci.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        tglbtn_suono.setOnMouseEntered(e -> {
            if (tglbtn_suono.getScene() != null) {
                tglbtn_suono.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        tglbtn_suono.setOnMouseExited(e -> {
            if (tglbtn_suono.getScene() != null) {
                tglbtn_suono.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        tglbtn_musica.setOnMouseEntered(e -> {
            if (tglbtn_musica.getScene() != null) {
                tglbtn_musica.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        tglbtn_musica.setOnMouseExited(e -> {
            if (tglbtn_musica.getScene() != null) {
                tglbtn_musica.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_regole.setOnMouseEntered(e -> {
            if (btn_regole.getScene() != null) {
                btn_regole.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_regole.setOnMouseExited(e -> {
            if (btn_regole.getScene() != null) {
                btn_regole.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_gioca.setOnMouseEntered(e -> {
            if (btn_gioca.getScene() != null) {
                btn_gioca.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });
        btn_classifica.setOnMouseEntered(e -> {
            if (btn_classifica.getScene() != null) {
                btn_classifica.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_classifica.setOnMouseExited(e -> {
            if (btn_classifica.getScene() != null) {
                btn_classifica.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_gioca.setOnMouseExited(e -> {
            if (btn_gioca.getScene() != null) {
                btn_gioca.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_tabellone.setOnMouseEntered(e -> {
            if (btn_tabellone.getScene() != null) {
                btn_tabellone.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_tabellone.setOnMouseExited(e -> {
            if (btn_tabellone.getScene() != null) {
                btn_tabellone.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_tabellone.setOnMouseEntered(e -> {
            if (btn_tabellone.getScene() != null) {
                btn_tabellone.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_backhome.setOnMouseExited(e -> {
            if (btn_backhome.getScene() != null) {
                btn_backhome.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_backhome.setOnMouseEntered(e -> {
            if (btn_backhome.getScene() != null) {
                btn_backhome.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_prossimaMano.setOnMouseExited(e -> {
            if (btn_prossimaMano.getScene() != null) {
                btn_prossimaMano.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_scarta.setOnMouseEntered(e -> {
            if (btn_scarta.getScene() != null) {
                btn_scarta.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_scarta.setOnMouseExited(e -> {
            if (btn_scarta.getScene() != null) {
                btn_scarta.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_stai.setOnMouseEntered(e -> {
            if (btn_stai.getScene() != null) {
                btn_stai.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_stai.setOnMouseExited(e -> {
            if (btn_stai.getScene() != null) {
                btn_stai.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_conferma.setOnMouseEntered(e -> {
            if (btn_conferma.getScene() != null) {
                btn_conferma.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_conferma.setOnMouseExited(e -> {
            if (btn_conferma.getScene() != null) {
                btn_conferma.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        carta1.setOnMouseEntered(e -> {
            if (carta1.getScene() != null) {
                carta1.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        carta1.setOnMouseExited(e -> {
            if (carta1.getScene() != null) {
                carta1.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        carta2.setOnMouseEntered(e -> {
            if (carta2.getScene() != null) {
                carta2.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        carta2.setOnMouseExited(e -> {
            if (carta2.getScene() != null) {
                carta2.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        carta3.setOnMouseEntered(e -> {
            if (carta3.getScene() != null) {
                carta3.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        carta3.setOnMouseExited(e -> {
            if (carta3.getScene() != null) {
                carta3.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        carta4.setOnMouseEntered(e -> {
            if (carta4.getScene() != null) {
                carta4.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        carta4.setOnMouseExited(e -> {
            if (carta4.getScene() != null) {
                carta4.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        carta5.setOnMouseEntered(e -> {
            if (carta5.getScene() != null) {
                carta5.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        carta5.setOnMouseExited(e -> {
            if (carta5.getScene() != null) {
                carta5.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        inizializzaTraduzioni();
        p = Partita.carica(CODICE_GLOBALE_PARTITA);
        mostraClassifica();
        cont = p.getCont();
        giocatori = p.getGiocatori();
        turno_salvato = p.getTurnoSalvato();
        giocatore_salvato = p.getGiocatoreSalvato();

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
        //controllo per far si che il seme che comanda non può essere un imprevisto
        int i = 0;
        while (p.getMazzo().getMazzoArrayList().get(i).getSeme() == Seme.Neutro) {
            i++;
        }
        p.setSeme(p.getMazzo().getMazzoArrayList().remove(i));
        p.setSemeComandante();


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
            btn_regole.setFocusTraversable(false);
        if(imageFocus.isFocusTraversable()){
            imageFocus.setFocusTraversable(false);
            btn_regole.requestFocus();
            return;
        }

            if (keyEvent.getCode() == KeyCode.DOWN) {
                OPZ.premiFreccia();
                if (btn_regole.isFocused()) {
                    btn_classifica.requestFocus();
                } else if (tglbtn_suono.isFocused()) {
                    tglbtn_musica.requestFocus();
                } else if (tglbtn_musica.isFocused()) {
                    btn_esci.requestFocus();
                } else if (btn_classifica.isFocused()) {
                    tglbtn_suono.requestFocus();
                }
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                OPZ.premiFreccia();
                if (btn_esci.isFocused()) {
                    tglbtn_musica.requestFocus();
                } else if (tglbtn_musica.isFocused()) {
                    tglbtn_suono.requestFocus();
                } else if (tglbtn_suono.isFocused()) {
                    btn_classifica.requestFocus();
                } else if (btn_classifica.isFocused()) {
                    btn_regole.requestFocus();
                }
            }

        if (keyEvent.getCode() == KeyCode.ENTER && btn_esci.isFocused()) {
            OPZ.premiBottone();
            salvaEdEsci();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_musica.isFocused()) {
            OPZ.premiBottone();
            Utili.gestisciMusica(tglbtn_musica);
        }
        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_suono.isFocused()) {
            OPZ.premiBottone();
            Utili.gestisciSuoni(tglbtn_suono);
        }
        if(keyEvent.getCode() == KeyCode.ENTER && btn_regole.isFocused()){
            OPZ.premiBottone();
            showRegole();
        }
        if(keyEvent.getCode() == KeyCode.ENTER && btn_classifica.isFocused()){
            OPZ.premiBottone();
            showLeaderboard();
        }

    }

    boolean prima = false; //per verificare se è la prima volta che clicco la freccia a destra per impostare correttamente il focus

    public void keyPartita(KeyEvent keyEvent) throws FileNotFoundException {
        if (keyEvent.getCode() == KeyCode.RIGHT && btn_conferma.isVisible()) {
            OPZ.premiFreccia();
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
            OPZ.premiFreccia();
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
            OPZ.premiFreccia();
            btn_conferma.requestFocus();
        }
        if ((keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.LEFT) && btn_conferma.isFocused() && btn_conferma.isVisible()) {
            OPZ.premiFreccia();
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

    }

    public void keyScelta(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT && btn_scarta.isVisible()) {
            OPZ.premiFreccia();
            btn_scarta.requestFocus();
            if (btn_scarta.isFocused()) {
                btn_stai.requestFocus();
            }

        }
        if (keyEvent.getCode() == KeyCode.LEFT && btn_scarta.isVisible()) {
            OPZ.premiFreccia();
            btn_scarta.requestFocus();
            if (btn_stai.isFocused()) {
                btn_scarta.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_scarta.isVisible() && btn_scarta.isFocused()) {
            scarta();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_stai.isVisible() && btn_stai.isFocused()) {
            try {
                stai();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void keyEvent(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && pane_fineTorneo.isVisible()) {
            try {
                BackToHome();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && pane_finePartitaTorneo.isVisible()) {
            try {
                tornaAlTabellone();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void showRegole() throws IOException {

        // Carica la nuova finestra
        Parent root = FXMLLoader.load(getClass().getResource("RegoleView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/gioco/progettospacca/Logo.png"))));

        stage.setTitle(OPZ.traduci("regole"));
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
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

    public void showLeaderboard() throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("LeaderboardViewPartita.fxml"));

            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            scene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));

            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/gioco/progettospacca/Logo.png"))));

            newStage.setTitle(OPZ.traduci("classifica"));
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
