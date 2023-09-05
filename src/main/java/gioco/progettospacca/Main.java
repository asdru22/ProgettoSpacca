package gioco.progettospacca;
// import classi locali
import gioco.progettospacca.classi.Gestore;
import  gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Partita;



public class Main {
    public static void main(String[] args) {
        Gestore g = new Gestore();
        g.autenticazione();
        // comandi da eseguire per fare nuova partita
        Giocatore[] giocatori = g.aggiungiGiocatori();
        int id_temp = g.scriviPartita(giocatori);
        //
        //
        Partita partita =g.leggiPartita(id_temp);


    }

}

