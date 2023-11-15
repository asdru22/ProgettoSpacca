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
            Giocatore gioc[] = new Giocatore[1];
            gioc[0] = new Giocatore("paolo");
            Partita p = new Partita(gioc);
            p.getMazzo().setMazzo(Mazzo.creaMazzoIniziale());
            p.salva();
            //System.out.println(Partita.carica(6249));

        }
}
