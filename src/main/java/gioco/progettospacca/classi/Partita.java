package gioco.progettospacca.classi;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Scanner;

public class Partita{
    private static int NUMERO_TURNI = 2;
    private int id;
    private int turno_salvato =0;
    private int giocatore_salvato=0;
    private boolean in_torneo;
    private Giocatore[] giocatori;
    private Giocatore vincitore = null;
    private Giocatore toccaA;
    private Mazzo mazzo;
    private Seme seme_che_comanda = null;
    public Partita(Giocatore[] giocatori, boolean in_torneo){
        this(Utili.intCasuale(1,10000),giocatori,in_torneo);
    }
    public Partita(int id,Giocatore[] giocatori,boolean in_torneo){
        this.id = id;
        this.giocatori = giocatori;
        this.mazzo = new Mazzo();
        this.in_torneo = in_torneo;
    }
    public Giocatore getToccaA() {
        return toccaA;
    }
    public void setToccaA(Giocatore toccaA) {
        this.toccaA = toccaA;
    }
    public int getId(){
        return id;
    }

    public Giocatore[] getGiocatori(){
        return giocatori;
    }
    public Giocatore getVincitore(){
        return vincitore;
    }
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
        if (vincitore == null){
            vincitore = new Giocatore("nessuno");
        }
        return "> Id Partita: " +id+", Giocatori: "+Arrays.toString(giocatori)+", Vincitore: "+vincitore.toString();
    }
    public void inizia(){
        // forse questo va messo quando si aggiungono i giocatori
        for(Giocatore g: giocatori){
            g.aggiungiSalvataggio();
        } // aggiunge il file del giocatore se non esiste
        mazzo = new Mazzo();
        toccaA = giocatori[0];
        cicloPrincipale();
    }
    public void cicloPrincipale(){
        for(int i = turno_salvato;i<NUMERO_TURNI;i++){ // per ogni turno
            turno_salvato = i;
            seme_che_comanda = Utili.semeCasuale();
            for(int j = 0; j< giocatori.length;j++){ // per ogni mano
                giocatore_salvato = j;
                azioniGiocatore();
            }
            mazzo = new Mazzo(Mazzo.creaMazzoIniziale());
        }
        //finePartita();
    }
    private void azioniGiocatore(){

        toccaA= giocatori[giocatore_salvato]; // sempre in fondo
        System.out.print(giocatore_salvato+" ");
        System.out.println("> Giocatore: "+toccaA.getNome()+" turno: "+ turno_salvato);

        toccaA.pesca(5,this.mazzo); // sempre all'inizio
        // giocatore fa roba
        int azione = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("premi 1 per scartare, premi 2 per stare");
        azione = scan.nextInt();
        switch (azione){
            case 1: System.out.println("quante carte vuoi scartare?");
                int num = scan.nextInt();
                switch (num){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default: System.out.println("il numero non è corretto");
                }
                break;

            case 2: System.out.println("mantiene le stesse carte");
                valutaCarte(toccaA.getMano());
                break;

        }
        System.out.println("carte in mano:" + Arrays.toString(toccaA.getMano())+"\n");
    }

    public void valutaCarte(Carta [] mano){

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
    private void finePartita(){
        int max = 0;
        Giocatore vincitore = null;
        int temp;
        for(Giocatore g: giocatori){
            temp = g.getPunti();
            if(temp>max){
                max = temp;
                vincitore = g;
            }
        }
        vincitore.setPartiteVinte(vincitore.getPartiteVinte()+1);
        vincitore.salva();
        Utili.getLeaderboard();
        System.out.println(vincitore.getNome()+" ha vinto!");
    }
}


