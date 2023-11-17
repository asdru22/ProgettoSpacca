package gioco.progettospacca.classi;


import java.nio.file.FileSystems;

public class test {
        public static void main(String[] args) {
            /*
            store g = new Gestore();
            g.autenticazione();
            // comandi da eseguire per fare nuova partita
            Giocatore[] giocatori = g.aggiungiGiocatori(tipo);
             */
            Giocatore gioc[] = new Giocatore[2];
            gioc[0] = new Giocatore("paolo");
            gioc[1] = new Giocatore("dio merda");

            Partita p = new Partita(gioc);
            p.inizia();
            //p.salva();
        }
}
