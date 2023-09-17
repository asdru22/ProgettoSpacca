package gioco.progettospacca.classi;

import java.util.ArrayList;
import java.util.Arrays;

public class Partita {
    private int id;
    private Giocatore[] giocatori;
    private Giocatore vincitore;
    private Mazzo mazzo;
    public Partita(int id,Giocatore[] giocatori){
        this.id = id;
        this.giocatori = giocatori;
        this.vincitore = null;
        this.mazzo = new Mazzo();
    }
    public int getId(){ return  id;}
    public Giocatore[] getGiocatori(){return giocatori;}
    public Giocatore getVincitore(){return vincitore;}
    public Mazzo getMazzo(){
        return mazzo;
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
