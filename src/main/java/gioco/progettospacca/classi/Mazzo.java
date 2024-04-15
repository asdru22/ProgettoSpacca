package gioco.progettospacca.classi;

import java.util.ArrayList;
import java.util.Collections;

public class Mazzo {
    private ArrayList<Carta> mazzo;

    public Mazzo(ArrayList<Carta> mazzo) {
        this.mazzo = mazzo;
    }

    public Mazzo() {
        this.mazzo = creaMazzoIniziale();
    }

    public ArrayList<Carta> getMazzoArrayList() {
        return this.mazzo;
    }

    public static ArrayList<Carta> creaMazzoIniziale() {
        Seme[] semi = Seme.values();
        ArrayList<Carta> temp = new ArrayList<>();
        temp.add(new Carta(semi[5],15, "/gioco/progettospacca/carte/imprevisti/1.png"));
        temp.add(new Carta(semi[5],15, "/gioco/progettospacca/carte/imprevisti/2.png"));
        for (int i = 0; i < 10; i++) {
            temp.add(new Carta(semi[0], i, "/gioco/progettospacca/carte/fuoco/" + (i + 1) + ".png"));
            temp.add(new Carta(semi[1], i, "/gioco/progettospacca/carte/terra/" + (i + 1) + ".png"));
            temp.add(new Carta(semi[2], i, "/gioco/progettospacca/carte/erba/" + (i + 1) + ".png"));
            temp.add(new Carta(semi[3], i, "/gioco/progettospacca/carte/elettro/" + (i + 1) + ".png"));
            temp.add(new Carta(semi[4], i, "/gioco/progettospacca/carte/acqua/" + (i + 1) + ".png"));
        }

        Collections.shuffle(temp);
        return temp;
    }

}
