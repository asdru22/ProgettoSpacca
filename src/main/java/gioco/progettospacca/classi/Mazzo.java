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
    public void setMazzo(ArrayList<Carta> mazzo){
        this.mazzo = mazzo ;
    }

    public ArrayList<Carta> getMazzoArrayList(){
        return this.mazzo;
    }
    public static ArrayList<Carta> creaMazzoIniziale(){
        Seme semi[] = Seme.values();
        ArrayList<Carta> temp = new ArrayList<>();

        for(int i = 0 ; i<10; i++){
            temp.add(new Carta(semi[0],i,"src/main/resources/gioco/progettospacca/"+i+"Fuoco.png"));
            temp.add(new Carta(semi[1],i,"src/main/resources/gioco/progettospacca/"+i+"Fuoco.png"));
            temp.add(new Carta(semi[2],i,"src/main/resources/gioco/progettospacca/"+i+"Erba.png"));
            temp.add(new Carta(semi[3],i,"src/main/resources/gioco/progettospacca/"+i+"Elettro.png"));
            temp.add(new Carta(semi[4],i,"src/main/resources/gioco/progettospacca/"+i+"Acqua.png"));
        }
        Collections.shuffle(temp);
        return temp;

    }
    public Carta getCartaInCima(){
        return mazzo.get(0);
    }

}
