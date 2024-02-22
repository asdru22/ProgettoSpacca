package gioco.progettospacca.classi;
import java.util.*;

import java.nio.file.FileSystems;

public class test{
        public static void main(String[] args) {

                ArrayList<Giocatore> g = new ArrayList<>();
                g.add(new Giocatore("bot1",true));
                g.add(new Giocatore("bot2",true));
                g.add(new Giocatore("bot3",true));

                Torneo t = new Torneo(g,4);

                t.iniziaMain();

                //Partita p = t.getPartite().get(0);
                //t.salva();
                //p.inizio();

        }
}