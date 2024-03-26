package gioco.progettospacca.classi;

import com.google.gson.Gson;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;

public class Torneo {
    private boolean iniziato = false;
    private int round_salvato = 0;
    private int numero_round = 0;
    private int partita_salvata = 0;
    private int numero_partite = 0;
    private ArrayList<Giocatore> giocatori;
    private ArrayList<Partita> partite = new ArrayList<>();
    private final int id;

    public Torneo(ArrayList<Giocatore> giocatori, int id) {
        this.giocatori = giocatori;
        this.id = id;
    }

    public boolean isIniziato() {
        return iniziato;
    }

    public static Torneo carica(int id) {
        Gson gson = new Gson();
        return gson.fromJson(Utili.leggiFileJson("tornei", Integer.toString(id)), Torneo.class);
    }

    public void salva() {
        Utili.salva("tornei", Integer.toString(id), this);
    }

    public ArrayList<Partita> getPartite() {
        return partite;
    }

    public void iniziaMain(int giocatori_scelti) throws FileNotFoundException {
        // aggiunge bot se il numero di giocatori partecipanti non è 4,8 o 16
        int bot_esistenti = 0;
        for (Giocatore g : giocatori) {
            if (g.isBot()) bot_esistenti += 1;
        }
        int n_bot = giocatori_scelti-giocatori.size();
        for (int i = 0; i < n_bot; i++) {
            bot_esistenti += 1;
            giocatori.add(new Giocatore("bot" + bot_esistenti, true));
        }
        System.out.println("Aggiunti " + n_bot + " bot per raggiungere il numero di giocatori richiesti (" + giocatori.size() + ")");
        inizia();
    }


    public void elimina() {
        Utili.eliminaTorneo(id);
    }

    private void riprendi() throws FileNotFoundException {
        riprendiRound();
    }

    private void inizia() throws FileNotFoundException {
        iniziato = true;
        numero_round = (int) (Math.log(giocatori.size()) / Math.log(2)); // caso con 4 giocatori, n_round = 2
        System.out.println("Numero round: " + numero_round + ", numero giocatori:" + giocatori.size());
        salva();
        riprendiRound();
    }

    private void riprendiRound() throws FileNotFoundException {
        for (int j = round_salvato; j < numero_round; j++) {
            round_salvato = j;
            creaPartite(); // crea
            riprendiPartita(); // esegui
            // fine round
        }
        Giocatore vincitore = giocatori.get(0);
        // fine torneo
        System.out.println(vincitore.getNome() + " ha vinto il torneo!");
        vincitore.setPartiteVinte(vincitore.getPartiteVinte() + numero_round);
        elimina();
    }

    private void riprendiPartita() throws FileNotFoundException {
        Partita partita_corrente;
        numero_partite = partite.size();
        for (int i = partita_salvata; i < numero_partite; i++) {
            partita_salvata = i;
            System.out.println(">>> Partita: " + (partita_salvata + 1) + "/" + numero_partite + ", Round: " + (round_salvato + 1) + "/" + numero_round);

            partita_corrente = partite.get(partita_salvata);
            // controllo per riprendi partita
            if (partita_corrente.isIniziata()) {
                //partita_corrente.riprendi();
            } else {
                partita_corrente.inizio();
            }

            giocatori.add(partita_corrente.getVincitore());
        }
        partita_salvata = 0;
        partite.clear(); // elimina partite
    }

    private void creaPartite() {
        // numero partite dati i giocatori
        numero_partite = giocatori.size() / 2;
        Giocatore[] coppia = new Giocatore[2];
        Collections.shuffle(giocatori);
        for (int i = 0; i < numero_partite; i++) {

            coppia[0] = giocatori.get(0);
            giocatori.remove(0);
            coppia[1] = giocatori.get(0);
            giocatori.remove(0);
            partite.add(new Partita(coppia, id));
            System.out.println("Creata nuova partita con:" + coppia[0].getNome() + " e " + coppia[1].getNome());
        }
    }

    public ArrayList<String> getNomiGiocatori(){
        ArrayList<String> r = new ArrayList<>();
        for (Giocatore giocatore : giocatori) {
            r.add(giocatore.getNome());
        }
        return r;
    }
    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }

    public Integer getId() {
        return id;
    }

    public static void controlloLabel(ArrayList<ValoriTorneo> vt, int max, TextField txt){
        Giocatore temp;
        int giocatori = 0;
        ArrayList<Giocatore> g = new ArrayList<>();
        int id = Utili.intCasuale(10000, 99999);

        txt.setText(String.valueOf(id));

        for(ValoriTorneo v: vt){
            temp = Utili.controllaNomeTorneo(v.getText(), v.isSelected());
            if (temp != null) {
                g.add(temp);
                giocatori +=1;
            }
        }
        // bot se ci sono giocatori mancanti
        if (giocatori<max){
         aggiungiBot(max-giocatori,g);
        }
        Torneo t = new Torneo(g,id);
        t.salva();

    }
    private static void aggiungiBot(int giocatori_mancanti,ArrayList<Giocatore> giocatori){
        int bot_esistenti = 0;
        for (Giocatore g : giocatori) {
            if (g.isBot()) bot_esistenti += 1;
        }
        for (int i = 0; i < giocatori_mancanti; i++) {
            bot_esistenti += 1;
            giocatori.add(new Giocatore("bot" + bot_esistenti, true));
        }
        System.out.println("Aggiunti " + giocatori_mancanti + " bot per raggiungere il numero di giocatori richiesti");
    }
}

