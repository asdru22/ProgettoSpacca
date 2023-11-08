package gioco.progettospacca.classi;
import com.google.gson.Gson;
public class test {
        public static void main(String[] args) {
            /*
            store g = new Gestore();
            g.autenticazione();
            // comandi da eseguire per fare nuova partita
            Giocatore[] giocatori = g.aggiungiGiocatori(tipo);
             */
            Giocatore g = new Giocatore("paolo",-1);
            g.convertiAJson();
        }
}
