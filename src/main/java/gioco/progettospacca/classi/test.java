package gioco.progettospacca.classi;


import java.nio.file.FileSystems;

public class test {
        public static void main(String[] args) {

            Giocatore temp[] = new Giocatore[2];
            temp[0] = new Giocatore("paolo");
            temp[1] = new Giocatore("ciao");

            Partita p = new Partita(temp,false);
            p.inizia();
            p.salva();

            //Utili.riprendiPartita(5007);
        }
}
