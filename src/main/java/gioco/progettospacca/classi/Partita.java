package gioco.progettospacca.classi;

import com.google.gson.Gson;
import java.util.Arrays;
public class Partita{
    private int id;
    private Giocatore[] giocatori;
    private Giocatore vincitore;
    private Giocatore toccaA;
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
    public Giocatore getToccaA() {
        return toccaA;
    }
    public void setToccaA(Giocatore toccaA) {
        this.toccaA = toccaA;
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
    private void prossimoTurno(int i){
        toccaA= giocatori[i];
    }
    public void inizia(){
        mazzo = new Mazzo();
        System.out.println(giocatori[0]);
        toccaA = giocatori[0];

        for(int i = 0;i<10;i++){ // per ogni turno
            for(int j = 0; j< giocatori.length;j++){ // per ogni mano
                toccaA.pesca(5,this.mazzo); // sempre all'inizio
                // giocatore fa roba
                System.out.println("Giocatore:"+toccaA.getNome()+" turno: "+ i+"\nmano: "+Arrays.toString(toccaA.getMano()));
                prossimoTurno(j); // sempre in fondo
            }
            mazzo = new Mazzo(Mazzo.creaMazzoIniziale());
        }
    }

}
