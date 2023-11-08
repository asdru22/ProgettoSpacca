package gioco.progettospacca.classi;
import com.google.gson.Gson;

import java.util.Scanner;

public class test {
        public static void main(String[] args) {
            /*
            store g = new Gestore();
            g.autenticazione();
            // comandi da eseguire per fare nuova partita
            Giocatore[] giocatori = g.aggiungiGiocatori(tipo);
             */
            Giocatore gioc[] = new Giocatore[1];
            gioc[0] = new Giocatore("paolo");
            Partita p = new Partita(1,gioc);
            p.salva();
            System.out.println(p.carica());
        }
}
