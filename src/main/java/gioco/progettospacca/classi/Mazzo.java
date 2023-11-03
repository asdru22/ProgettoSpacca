package gioco.progettospacca.classi;

import java.util.ArrayList;

public class Mazzo {
    private ArrayList<Carta> mazzo;

    public Mazzo(){
        this.mazzo = creaMazzo();
    }
    public ArrayList<Carta> getMazzo(){
        return mazzo;
    }
    private ArrayList<Carta> creaMazzo(){
        ArrayList<Carta> temp = new ArrayList<>();
        for(int i = 0 ; i<40; i++){
            temp.add(new Carta("carta trappola"));
        }
        return temp;
    }
    public Carta getCartaInCima(){
        return mazzo.get(0);
    }

}
