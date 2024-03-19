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

    public void settaCarteNulle(int i) {
        List<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
        manoList.add(i, null);
        this.mano = manoList.toArray(new Carta[0]);
    }

    public void togliCarteNulle() {
        List<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));
        Iterator<Carta> iterator = manoList.iterator();
        while (iterator.hasNext()) {
            Carta elemento = iterator.next();
            if (elemento == null) {
                // Rimuove l'elemento null usando il metodo remove dell'iteratore
                iterator.remove();
            }
        }
        this.mano = manoList.toArray(new Carta[0]);
    }

    public void scarta(int pos) {
        //System.out.println("Posizione prima della rimozione: " + pos);

        List<Carta> manoList = new ArrayList<>(Arrays.asList(this.mano));

        //System.out.println("Mano prima della rimozione: " + manoList);

        // Rimuovi l'elemento dalla posizione specificata
        manoList.remove(pos);

        //System.out.println("Mano dopo la rimozione: " + manoList);

        // Aggiorna l'array mano con la nuova lista
        this.mano = manoList.toArray(new Carta[0]);

        //System.out.println("Nuova mano dopo l'aggiornamento: " + Arrays.toString(this.mano));
    }


    // classe->.json
    public void salva() {
        Utili.salva("giocatori", nome, this);
    }

    // .json->classe
    public static Giocatore carica(String nome) {
        return new Gson().fromJson(Utili.leggiFileJson("giocatori", nome), Giocatore.class);
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
        return nome + " ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Giocatore giocatore = (Giocatore) obj;
        return Objects.equals(nome, giocatore.nome);
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

    public void setMano(Carta[] mano) {
        this.mano = mano;
    }

    public void setPunti(int punti) {
        this.punti = this.punti + punti;
    }
}
