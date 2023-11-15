package gioco.progettospacca.classi;

import com.google.gson.Gson;
import java.util.Arrays;
public class Partita{
    private int id;
    private Giocatore[] giocatori;
    private Giocatore vincitore;
    private Mazzo mazzo;
    public Partita(Giocatore[] giocatori){
        this.id = Utili.intCasuale(1,10000);
        this.giocatori = giocatori;
        this.vincitore = null;
        this.mazzo = new Mazzo();
        this.impostaIdGiocatori();
    }
    public Partita(int id,Giocatore[] giocatori){
        this.id = id;
        this.giocatori = giocatori;
        this.vincitore = null;
        this.mazzo = new Mazzo();
        this.impostaIdGiocatori();
    }
    public int getId(){return id;}
    public Giocatore[] getGiocatori(){return giocatori;}
    public Giocatore getVincitore(){return vincitore;}
    public Mazzo getMazzo(){
        return mazzo;
    }
    // classe->.json
    public void salva(){
        Utili.salva("partite",Integer.toString(id),this);
    }
    // .json->classe
    public static Partita carica(int id){
        Gson gson = new Gson();
        return gson.fromJson(Utili.leggiFileJson("partite",Integer.toString(id)), Partita.class);
    }
    private void impostaIdGiocatori(){
        for (Giocatore giocatore : giocatori) giocatore.setCodicePartita(id);
    }
    @Override
    public String toString(){
        if (vincitore == null) vincitore = new Giocatore("nessuno");
        return "> Id Partita: " +id+", Giocatori: "+Arrays.toString(giocatori)+", Vincitore: "+vincitore.toString();
    }

}
