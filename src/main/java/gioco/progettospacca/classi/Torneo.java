package gioco.progettospacca.classi;

import com.google.gson.Gson;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;

public class Torneo {

    private int round_salvato = 0;
    private int numero_partite = 0;
    private ArrayList<Giocatore> giocatori;
    private ArrayList<Integer> partite = new ArrayList<>();
    private final int id;
    private final int giocatoriIniziali;

    public Torneo(ArrayList<Giocatore> giocatori, int id) {
        this.giocatori = giocatori;
        this.id = id;
        this.giocatoriIniziali = giocatori.size();
        creaPartite();
    }

    public static Torneo carica(int id) {
        Gson gson = new Gson();
        return gson.fromJson(Utili.leggiFileJson("tornei", Integer.toString(id)), Torneo.class);
    }
    public void skip(){
        // PER TESTARE TORNO
        System.out.println("SETTANDO VINCITORE TEMP PARTITE");
        for(int i = 0;i<partite.size()-1;i++){
            Partita p = Partita.carica(partite.get(i));
            p.setVincitoreTemp();
            giocatori.add(p.getVincitore());
            p.salva();
        }
        salva();
    }

    public void salva() {
        Utili.salva("tornei", Integer.toString(id), this);
    }

    public ArrayList<Integer> getPartite() {
        return partite;
    }

    public void elimina() {
        Utili.eliminaTorneo(id);
    }

    public void creaPartite() {
        // numero partite dati i giocatori
        System.out.println("Numoer giocatori"+giocatori.size());
        numero_partite = giocatori.size() / 2;
        if(giocatori.size() == 1){
            fineTorneo(giocatori.get(0));
            return;
        }
        Giocatore[] coppia = new Giocatore[2];
        Collections.shuffle(giocatori);
        for (int i = 0; i < numero_partite; i++) {

            coppia[0] = giocatori.get(0);
            giocatori.remove(0);
            coppia[1] = giocatori.get(0);
            giocatori.remove(0);
            partite.add(new Partita(coppia, id).getId());
            System.out.println("Creata nuova partita con:" + coppia[0].getNome() + " e " + coppia[1].getNome());
        }
        salva();
    }

    private void fineTorneo(Giocatore vincitore) {
        for(int n: partite){
            Partita.carica(n).elimina();
        }
        elimina();
        //vincitore.setPartiteVinte(vincitore.getPartiteVinte()+numero_round);
    }

    public ArrayList<String> getNomiGiocatori() {
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

    public static void controlloLabel(ArrayList<ValoriTorneo> vt, int max, TextField txt) {
        Giocatore temp;
        int giocatori = 0;
        ArrayList<Giocatore> g = new ArrayList<>();
        int id = Utili.intCasuale(10000, 99999);

        txt.setText(String.valueOf(id));

        for (ValoriTorneo v : vt) {
            temp = Utili.controllaNomeTorneo(v.getText(), v.isSelected());
            if (temp != null) {
                g.add(temp);
                giocatori += 1;
            }
        }
        // bot se ci sono giocatori mancanti
        if (giocatori < max) {
            aggiungiBot(max - giocatori, g);
        }
        Torneo t = new Torneo(g, id);
        t.creaPartite();
        // scommentare per andare direttamente all'ultima partita del primo round
        // t.skip();

    }

    private static void aggiungiBot(int giocatori_mancanti, ArrayList<Giocatore> giocatori) {
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

    public int getGiocatoriIniziali() {
        return giocatoriIniziali;
    }

    public int getRound() {
        return round_salvato;
    }

    public boolean tuttiVincitori(){
        Partita p;
        for(int n: partite){
            p = Partita.carica(n);
            if(p.getVincitore()==null) return false;
        }
        return true;
    }

    public void aumentaRound() {
        round_salvato+=1;
    }
}

