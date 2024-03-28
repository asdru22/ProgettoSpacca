package gioco.progettospacca.classi;

import java.util.*;

public class test {
    public static void main(String[] args) {
        Torneo t = Torneo.carica(72517);
        for(int i = 0;i<t.getPartite().size()-1;i++){
            Partita p = Partita.carica(t.getPartite().get(i));
            p.setVincitoreTemp();
            p.salva();
        }



    }
}
