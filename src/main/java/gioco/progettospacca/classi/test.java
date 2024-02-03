package gioco.progettospacca.classi;


import java.nio.file.FileSystems;

public class test {
        public static void main(String[] args) {

            Giocatore temp[] = new Giocatore[2];
            temp[0] = new Giocatore("Paolo");
            temp[1] = new Giocatore("Dani");

            Partita p = new Partita(temp,false);
            p.inizia();

            //System.out.println("mano giocatore 1"+temp[1].getMano());

            //Utili.riprendiPartita(5007);
        }
}
