package gioco.progettospacca.classi;

import com.google.gson.Gson;
import javafx.scene.control.TextField;

import java.util.Collections;
import java.util.ArrayList;

public class Torneo {
    private Giocatore vincitore;
    private int round_salvato = 0;
    private boolean finito = false;
    private ArrayList<Giocatore> giocatori;
    private ArrayList<String> listaGiocatoriIniziali;
    private ArrayList<Integer> partite = new ArrayList<>();
    private final int id;
    private final int giocatoriIniziali;
    private boolean salvabile = true;

    public Torneo(ArrayList<Giocatore> giocatori, int id) {
        // copia giocatori iniziali
        listaGiocatoriIniziali = new ArrayList<>();
        for (Giocatore g : giocatori) {
            listaGiocatoriIniziali.add(g.getNome());
        }
        this.giocatori = giocatori;
        Collections.shuffle(giocatori);
        this.id = id;
        this.giocatoriIniziali = giocatori.size();
    }

    public static Torneo carica(int id) {
        Gson gson = new Gson();
        String t = Utili.leggiFileJson("tornei", Integer.toString(id));
        if(t!="errore") return gson.fromJson(t,Torneo.class);
        else return null;
    }

    public void salva() {
        if (salvabile) {
            Utili.salva("tornei", Integer.toString(id), this);
        }
    }

    public ArrayList<Integer> getPartite() {
        return partite;
    }

    public void elimina() {
        for (int n : partite) {
            Partita.carica(n).elimina();
        }
        Utili.eliminaTorneo(id + "");
    }

    public void creaPartite() {
        if (giocatori.size() > 1) {
            Giocatore[] coppia = new Giocatore[2];
            int numPartite = giocatori.size() / 2;
            // crea una partita per coppia
            for (int i = 0; i < numPartite; i++) {
                coppia[0] = giocatori.get(0);
                giocatori.remove(0);
                coppia[1] = giocatori.get(0);
                giocatori.remove(0);
                partite.add(new Partita(coppia, id).getId());
            }
        }
        salva();
    }

    public void fineTorneo() {
        salvabile = false;
        elimina();
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

    public ArrayList<String> getListaGiocatoriIniziali() {
        return listaGiocatoriIniziali;
    }

    public Integer getId() {
        return id;
    }

    public static int controlloLabel(ArrayList<ValoriTorneo> vt, int max, TextField txt) {
        // itera su tutte le label per impostare giocatori/bot nel torneo
        Giocatore temp;
        int giocatori = 0;
        ArrayList<Giocatore> g = new ArrayList<>();
        int id = Utili.intCasuale(10000, 99999);
        int n = 0;
        txt.setText(String.valueOf(id));

        for (ValoriTorneo v : vt) {
            temp = Utili.controllaNomeTorneo(v.getText(), v.isSelected(), g);
            if (temp != null) {
                g.add(temp);
                giocatori += 1;
            }
        }
        // bot se ci sono giocatori mancanti
        if (giocatori < max) {
            n = aggiungiBot(max - giocatori, g);
        }
        Torneo t = new Torneo(g, id);
        t.salva();
        t.creaPartite();
        for (String s : t.getListaGiocatoriIniziali()) {
            Giocatore gioc = Giocatore.carica(s);
            if (!gioc.getEmail().isEmpty() && gioc.getEmail() != "") {
                MailThread thread = new MailThread(gioc.getEmail(), "Iscrizione torneo spacca", "Giocatore: " + gioc.getNome() + "\nCodice torneo: " + id);
                thread.start();
            }
        }
        return n;
    }

    private static int aggiungiBot(int giocatori_mancanti, ArrayList<Giocatore> giocatori) {
        int bot_esistenti = 0;
        for (Giocatore g : giocatori) {
            if (g.isBot()) bot_esistenti += 1;
        }
        for (int i = 0; i < giocatori_mancanti; i++) {
            bot_esistenti += 1;
            Giocatore b = new Giocatore("bot" + bot_esistenti, true);
            giocatori.add(b);
            b.salva();
        }
        return giocatori_mancanti;
    }

    public int getGiocatoriIniziali() {
        return giocatoriIniziali;
    }

    public int getRound() {
        return round_salvato;
    }

    public boolean tuttiVincitori() {
        // controlla se tutte le partite di un round hanno un vincitore
        Partita p;
        for (int n : partite) {
            p = Partita.carica(n);
            if (p.getVincitore() == null) return false;
        }
        return true;
    }

    public void aumentaRound() {
        round_salvato += 1;
    }

    public boolean isFinito() {
        return finito;
    }

    public void setFinito() {
        finito = true;
    }

    public void setVincitore(Giocatore g) {
        this.vincitore = g;
    }

    public Giocatore getVincitore() {
        return vincitore;
    }
}

