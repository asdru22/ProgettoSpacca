package gioco.progettospacca.classi;

import com.google.gson.Gson;
import java.util.*;


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
    public Partita(int id,Giocatore[] giocatori,boolean in_torneo){
        this.id = id;
        this.giocatori = giocatori;
        this.mazzo = new Mazzo();
        this.in_torneo = in_torneo;

        for (Giocatore g : giocatori) {
            g.aggiungiSalvataggio();
            System.out.println("Giocatore:"+g.getNome()+ ", Vittorie: "+g.getPartiteVinte());
        }
        toccaA = giocatori[0];
        this.salva();
    }
    public Partita(Giocatore[] giocatori, boolean in_torneo){
        this(Utili.intCasuale(10000,99999),giocatori,in_torneo);
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
    /*// per test
    public void inizia(){
        // forse questo va messo quando si aggiungono i giocatori
        for(Giocatore g: giocatori){
            g.aggiungiSalvataggio();
        } // aggiunge il file del giocatore se non esiste
        mazzo = new Mazzo();
        toccaA = giocatori[0];
        cicloPrincipale();
    }

     */

    public void cicloPrincipale(){
        Scanner scan = new Scanner(System.in);
        int scelta =0;
        for(int i = turno_salvato;i<NUMERO_TURNI;i++){ // per ogni turno
            turno_salvato = i;
            seme_che_comanda = Utili.semeCasuale();
            for(int j = 0; j< giocatori.length;j++){ // per ogni mano
                giocatore_salvato = j;
                azioniGiocatore();

                mazzo = new Mazzo(Mazzo.creaMazzoIniziale());
                System.out.println("vuoi interrompere la partita?, clicca 1 per uscire");
                scelta = scan.nextInt();

                if(scelta==1){
                    this.salva();
                    break;
                }
                //System.out.println("numero rimasto di carte nel mazzo "+ mazzo.getMazzoArrayList().size()); //controllo se le carte nel mazzo vengono rimosse e poi rimescolate bene
            }
            if(scelta == 1){
                break;
            }
        }
        finePartita();
    }
    public void inizio(){
        cicloPrincipale();
    }

    //test
    public void riprendi(){ //eseguito solo fino alla fine del turno corrente
        giocatore_salvato = (giocatore_salvato+1)%giocatori.length;
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
        Giocatore vincitore = giocatori[0];
        int temp;
        for(Giocatore g: giocatori){
            g.rimuoviPartita(this.id);
            temp = g.getPunti();
            if(temp>max){
                max = temp;
                vincitore = g;
            }
        }
        vincitore.setPartiteVinte(vincitore.getPartiteVinte()+1);
        System.out.println("Vittorie: "+vincitore.getPartiteVinte());
        vincitore.salva();
        Utili.getLeaderboard();
        System.out.println(vincitore.getNome()+" ha vinto!");
        Utili.eliminaSalvataggio(this.id);  //viene eliminato il salvataggio solo se viene conclusa la partita
        System.exit(0); //termina l'applicazione (per il momento teniamolo per comodità più avanti metteremo un bottone torna alla home una vota terminata la partita )
    }
    private void azioniGiocatore(){

        toccaA= giocatori[giocatore_salvato]; // sempre in fondo
        System.out.print(giocatore_salvato+" ");
        System.out.println("> Giocatore: "+toccaA.getNome()+" turno: "+ turno_salvato);

        toccaA.pesca(5,this.mazzo); // sempre all'inizio
        // giocatore fa roba
        Scanner scan = new Scanner(System.in);

        System.out.println("carte in mano:" + Arrays.toString(toccaA.getMano()));
        System.out.println("premi 1 per scartare, premi 2 per stare");
        int azione = scan.nextInt();
        int num = 0;
        int pos = 0;
        switch (azione){
            case 1: System.out.println("quante carte vuoi scartare?");
                do {
                    num = scan.nextInt();
                    switch (num) {
                        case 1:
                            System.out.println("scegli la posizione della carta che vuoi scartare, da 1 a 5");
                            do {
                                pos = scan.nextInt();
                                switch (pos) {
                                    case 1:
                                        toccaA.scarta(pos - 1);
                                        break;
                                    case 2:
                                        toccaA.scarta(pos - 1);
                                        break;
                                    case 3:
                                        toccaA.scarta(pos - 1);
                                        break;
                                    case 4:
                                        toccaA.scarta(pos - 1);
                                        break;
                                    case 5:
                                        toccaA.scarta(pos - 1);
                                        break;
                                    default:
                                        System.out.println("devi scegliere un numero tra 0 e 4");
                                }
                            }while(pos<1 || pos>5);
                            break;
                        case 2:
                            System.out.println("scegli le 2 posizioni delle carte da scartare:");
                            int i = 1;
                            do {
                                pos = scan.nextInt();
                                switch (pos) {
                                    case 1:
                                        toccaA.scarta(pos - i);
                                        i++;
                                        break;
                                    case 2:
                                        toccaA.scarta(pos - i);
                                        i++;
                                        break;
                                    case 3:
                                        toccaA.scarta(pos - i);
                                        i++;
                                        break;
                                    case 4:
                                        toccaA.scarta(pos - i);
                                        i++;
                                        break;
                                    case 5:
                                        toccaA.scarta(pos - i);
                                        i++;
                                        break;
                                    default:
                                        System.out.println("devi scegliere un numero tra 1 e 5");

                                }
                            }while((pos<1 || pos>5)||i<3);
                            break;
                        case 3:
                            System.out.println("scegli le 3 posizioni delle carte da scartare:");
                            int j = 1;
                            do {
                                pos = scan.nextInt();
                                switch (pos) {
                                    case 1:
                                        toccaA.scarta(pos - j);
                                        j++;
                                        break;
                                    case 2:
                                        toccaA.scarta(pos - j);
                                        j++;
                                        break;
                                    case 3:
                                        toccaA.scarta(pos - j);
                                        j++;
                                        break;
                                    case 4:
                                        toccaA.scarta(pos - j);
                                        j++;
                                        break;
                                    case 5:
                                        toccaA.scarta(pos - j);
                                        j++;
                                        break;
                                    default:
                                        System.out.println("devi scegliere un numero tra 1 e 5");

                                }
                            }while((pos<1 || pos>5)||j<4);
                            break;
                        default:
                            System.out.println("il numero non è corretto");
                    }
                }while(num<1 || num>3);
                System.out.println("carte in mano dopo lo scarto:" + Arrays.toString(toccaA.getMano())+"\n");
                toccaA.pesca(num, this.mazzo);
                System.out.println("carte in mano dopo aver pescato:" + Arrays.toString(toccaA.getMano())+"\n");

                toccaA.aggiungiPunti( valutaCarte(toccaA.getMano()));
                System.out.println("punti della mano: "+toccaA.getPunti()+"\n");
                break;

            case 2: System.out.println("mantiene le stesse carte");
                toccaA.aggiungiPunti( valutaCarte(toccaA.getMano()));
                System.out.println("punti della mano: "+toccaA.getPunti()+"\n");
                break;

        }
    }

    public int valutaCarte(Carta [] mano){
        int [] carteNum = new int[5];
        Seme [] carteSeme = new Seme[5];
        for(int i = 0; i<5; i++){
            carteNum[i] = mano[i].getNumero();
            carteSeme[i] = mano[i].getSeme();
        }
        Arrays.sort(carteNum);
        int punti = 0;
        //valutiamo se ha coppie,tris,poker,manita in mano
        switch (numUguali(carteNum)){
            case 2:
                System.out.println("hai fatto coppia");
                punti = punti + 10;
                break;
            case 3:
                System.out.println("hai fatto tris");
                punti = punti + 20;
                break;
            case 4:
                System.out.println("hai fatto poker");
                punti = punti + 45;
                break;
            case 5:
                System.out.println("hai fatto manita");
                punti = punti + 100;
                break;
            default: System.out.println("nessun punto per coppie, tris, poker, manita");
        }
        //ora verifichiamo se ci sono scale
        switch (verificaScala(carteNum)){
            case 3:
                System.out.println("scala da 3 carte");
                punti = punti + 15;
                break;
            case 4:
                System.out.println("scala da 4 carte");
                punti = punti + 35;
                break;
            case 5:
                System.out.println("scala da 5 carte");
                punti = punti + 60;
                break;
            default: System.out.println("nessuna scala");
        }

        switch (verificaColore(carteSeme)){
            case 3:
                System.out.println("3 carte dello stesso colore");
                punti = punti + 5;
                break;
            case 4:
                System.out.println("4 carte dello stesso colore");
                punti = punti + 30;
                break;
            case 5:
                System.out.println("5 carte dello stesso colore");
                punti = punti + 80;
                break;
            default:System.out.println("non hai fatto colore");
        }
        return punti;
    }

    public int verificaColore(Seme [] carteSeme){
        Map<Seme, Integer> mappa = new HashMap<>();

        for(Seme chiave : carteSeme){
            mappa.put(chiave, mappa.getOrDefault(chiave,0)+1);
        }
        int numColorePiuVolteRipetuto = 0;
        for(Seme chiave : mappa.keySet()){
            int valore = mappa.get(chiave);
            if(valore>numColorePiuVolteRipetuto){
                numColorePiuVolteRipetuto = valore;
            }
        }
        return numColorePiuVolteRipetuto;
    }

    public int numUguali(int[] carteNum){
        Map<Integer, Integer> mappa = new HashMap<>();  //capisco quale sia il numero che compare più volte e utilizzo quello per valutare la coppia\tris\poker\manita
        //inserisco come valore il numero di volte in cui viene ripetuto la stessa chiave
        for(int chiave : carteNum){
            mappa.put(chiave, mappa.getOrDefault(chiave, 0) + 1);
        }
        //troviamo il numero che viene ripetutuo più volte
        int numVoltePiuRipetuto = 0;
        for (int chiave : mappa.keySet()) {
            int valore = mappa.get(chiave);
            if (valore > numVoltePiuRipetuto) {
                numVoltePiuRipetuto = valore;
            }
        }

        return numVoltePiuRipetuto;
    }

    public int verificaScala(int [] carteNum){
        int cont = 1;
        int contMax = 0;
        for(int i = 1; i<5; i++){
            if(carteNum[i]==carteNum[i-1]+1){
                cont++;
                if(cont>contMax){
                    contMax = cont;
                }
            }
            else{
                cont = 1;
            }
        }
        return contMax;
    }

}


