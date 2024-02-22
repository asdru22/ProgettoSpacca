package gioco.progettospacca.classi;

import com.google.gson.Gson;

import java.io.File;
import java.util.*;
import java.util.*;

public class Giocatore {
    private String nome;
    private Carta[] mano = new Carta[5];
    private boolean bot;
    private int partite_vinte = 0;
    private ArrayList<Integer> partite = new ArrayList<>();
    private int punti = 0;

    public Giocatore(String nome, boolean bot) {
        this.nome = nome;
        this.bot = bot;
    }

    public Giocatore(String nome) {
        this.nome = nome;
        this.bot = false;
    }

    public String getNome() {
        return nome;
    }

    public Carta[] getMano() {
        return mano;
    }

    public int getPartiteVinte() {
        return partite_vinte;
    }

    public void setPartiteVinte(int partiteVinte) {
        this.partite_vinte = partiteVinte;
    }

    public int getPunti() {
        return punti;
    }

    public boolean isBot() {
        return bot;
    }

    public ArrayList<Integer> getPartite() {
        return partite;
    }

    public void aggiungiPartita(int id) {
        if (!this.partite.contains(id)) {
            this.partite.add(id);
        }
    }

    public void rimuoviPartita(int id) {
        this.partite.remove((Object) id);
    }

    public void aggiungiPunti(int punti) {
        this.punti = this.punti + punti;
    }

    public void pesca(int n, Mazzo m) { //n sono le carte da pescare
        if (this.mano.length == 5) {
            for (int i = 0; i < n; i++) {
                this.mano[i] = m.getMazzoArrayList().remove(0); //rimuove dal mazzo e le mette in mano
            }
        } else {
            List<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
            for (int i = 0; i < n; i++) {
                manoList.add(m.getMazzoArrayList().remove(0));
            }
            this.mano = manoList.toArray(new Carta[0]);
        }
    }

    public void scarta(int pos) {
        List<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
        // !!!! fix temporanea per scarto carta
        pos = 0;
        // !!!!
        manoList.remove(pos);
        this.mano = manoList.toArray(new Carta[0]);
    }

    // classe->.json
    public void salva() {
        Utili.salva("giocatori", nome, this);
    }

    // .json->classe
    public static Giocatore carica(String nome) {
        Gson gson = new Gson();
        return gson.fromJson(Utili.leggiFileJson("giocatori", nome), Giocatore.class);
    }

    public void aggiungiSalvataggio() {
        File folder = new File("src/main/java/gioco/progettospacca/salvataggi/giocatori");
        File[] file_giocatori = folder.listFiles();
        boolean trovato = false;
        for (File file : file_giocatori) {
            if (file.isFile()) {
                String s = file.getName().substring(0, file.getName().length() - 5);
                if (s.equals(nome)) trovato = true;
            }
        }
        if (!trovato) this.salva();
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", mano: " + Arrays.toString(mano);
    }

    public void resetPunti() {
        this.punti = 0;
    }

    public void resetMazzo() {
        Arrays.fill(this.mano, null);
    }

    public void elimina() {
        Utili.eliminaGiocatore(this.getNome());
    }
}
