package gioco.progettospacca.classi;

import java.util.*;

public class test {
    public static void main(String[] args) {
        ArrayList<Giocatore> g = new ArrayList<>();
        g.add(new Giocatore("bot1"));
        g.add(new Giocatore("bot2"));
        g.add(new Giocatore("bot3"));
        g.add(new Giocatore("bot4"));
        System.out.println(g.size());

       Torneo t = new Torneo(g,5);
        System.out.println(t.getNumeroRound());


        System.out.println((int) (Math.log(4) / Math.log(2))); // caso con 4 giocatori, n_round = 2


    }
}
