package gioco.progettospacca.classi;

import java.util.ArrayList;
import java.util.Collections;

public class Mazzo {
    private ArrayList<Carta> mazzo;

    public Mazzo(ArrayList<Carta> mazzo){
        this.mazzo = mazzo;
    }
    public Mazzo(){
        this.mazzo = creaMazzoIniziale();
    }
    public void setMazzo(ArrayList<Carta> mazzo){this.mazzo = mazzo ;}

    public ArrayList<Carta> getMazzoArrayList(){
        return mazzo;
    }
    public static ArrayList<Carta> creaMazzoIniziale(){
        Seme semi[] = Seme.values();
        ArrayList<Carta> temp = new ArrayList<>();

        for(int i = 0 ; i<10; i++){
            temp.add(new Carta(semi[0],i));
            temp.add(new Carta(semi[1],i));
            temp.add(new Carta(semi[2],i));
            temp.add(new Carta(semi[3],i));
            temp.add(new Carta(semi[4],i));
        }
        Collections.shuffle(temp);
        return temp;

    }
    public Carta getCartaInCima(){
        return mazzo.get(0);
    }

}
