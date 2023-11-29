package gioco.progettospacca.classi;

import com.google.gson.Gson;
import java.util.Arrays;
public class Partita{
    private static int NUMERO_TURNI = 10;
    private int id;
    private int turno_salvato;
    private int giocatore_salvato;
    private Giocatore[] giocatori;
    private Giocatore vincitore;
    private Giocatore toccaA;
    private Mazzo mazzo;
    public Partita(Giocatore[] giocatori){
        this.id = Utili.intCasuale(1,10000);
        this.giocatori = giocatori;
        this.vincitore = null;
        this.mazzo = new Mazzo();
        this.turno_salvato = 0;
        this.giocatore_salvato= 0;

    }
    public Partita(int id,Giocatore[] giocatori){
        this.id = id;
        this.giocatori = giocatori;
        this.vincitore = null;
        this.mazzo = new Mazzo();
        this.turno_salvato = 0;
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
    @Override
    public String toString(){
        if (vincitore == null) vincitore = new Giocatore("nessuno");
        return "> Id Partita: " +id+", Giocatori: "+Arrays.toString(giocatori)+", Vincitore: "+vincitore.toString();
    }
    public void inizia(){
        for(Giocatore g: giocatori) g.aggiungiSalvataggio();
        mazzo = new Mazzo();
        toccaA = giocatori[0];
        cicloPrincipale();
    }
    public void cicloPrincipale(){
        for(int i = turno_salvato;i<NUMERO_TURNI;i++){ // per ogni turno
            turno_salvato = i;
            for(int j = 0; j< giocatori.length;j++){ // per ogni mano
                giocatore_salvato = j;
                azioniGiocatore();
            }
            mazzo = new Mazzo(Mazzo.creaMazzoIniziale());
        }
    }
    public void azioniGiocatore(){
        toccaA.pesca(5,this.mazzo); // sempre all'inizio
        // giocatore fa roba
        toccaA= giocatori[giocatore_salvato]; // sempre in fondo
        System.out.print(giocatore_salvato+" ");
        System.out.println("> Giocatore: "+toccaA.getNome()+" turno: "+ turno_salvato);
    }
    public void riprendi(){ //eseguito solo fino alla fine del turno corrente
        toccaA=giocatori[giocatore_salvato];
        for(int j = giocatore_salvato; j< giocatori.length;j++){ // per ogni mano
            giocatore_salvato = j;
            azioniGiocatore();
        }
        mazzo = new Mazzo(Mazzo.creaMazzoIniziale());
        turno_salvato+=1;
        cicloPrincipale();
    }
}


