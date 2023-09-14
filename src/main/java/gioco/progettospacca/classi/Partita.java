package gioco.progettospacca.classi;

import java.util.ArrayList;
import  java.util.Arrays;

public class Partita {
    private int id;
    private Giocatore[] giocatori;
    private Giocatore vincitore;
    private ArrayList<Carta> mazzo;

    public Partita(int id,Giocatore[] giocatori){
        this.id = id;
        this.giocatori = giocatori;
        this.vincitore = null;
        this.mazzo = creaMazzo();
    }
    public int getId(){ return  id;}
    public Giocatore[] getGiocatori(){return giocatori;}
    public Giocatore getVincitore(){return vincitore;}
    public Carta getCartaInCima(){
        return mazzo.get(0);
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
    public void impostaIdGiocatori(){
        for (Giocatore giocatore : this.getGiocatori()) giocatore.setCodicePartita(id);
    }
    @Override
    public String toString(){
        if(vincitore == null) vincitore = new Giocatore("NESSUNO",-1);
        return "{Id Partita: " +id+", Giocatori: "+Arrays.toString(giocatori)+", Vincitore: "+vincitore.toString()+"} ";
    }

}
