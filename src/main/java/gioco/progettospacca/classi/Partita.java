package gioco.progettospacca.classi;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

import static gioco.progettospacca.controller.Main.OPZ;


public class Partita {
    private final int NUMERO_TURNI = OPZ.getTurniPartita();
    private final int id;
    private int turno_salvato = 0;
    private int giocatore_salvato = 0;
    private int id_torneo;
    private Giocatore[] giocatori;
    private Giocatore vincitore = null;
    private Giocatore toccaA;
    private Mazzo mazzo;
    private Carta seme_che_comanda;
    private Seme semeComandante = seme_che_comanda.getSeme();
    private int cont = 0; //cpntatore turni totali
    private boolean primoTurnoScena = true;

    private Seme semeCatenaColore;

    public Partita(int id, Giocatore[] giocatori, int id_torneo) {
        this.id = id;
        this.giocatori = giocatori;
        this.mazzo = new Mazzo();
        this.id_torneo = id_torneo;
        toccaA = giocatori[0];
        this.salva();
        this.seme_che_comanda = this.mazzo.getMazzoArrayList().get(0);
    }

    public Partita(Giocatore[] giocatori, int id_torneo) {
        this(Utili.intCasuale(10000, 99999), giocatori, id_torneo);
    }

    public Giocatore getToccaA() {
        return toccaA;
    }

    public void setToccaA(Giocatore toccaA) {
        this.toccaA = toccaA;
    }

    public int getId() {
        return id;
    }

    public Giocatore[] getGiocatori() {
        return giocatori;
    }

    public ArrayList<String> getNomiGiocatori() {
        ArrayList<String> r = new ArrayList<>();
        for (Giocatore giocatore : giocatori) {
            r.add(giocatore.getNome());
        }
        return r;
    }

    public Giocatore getVincitore() {
        return vincitore;
    }

    public Mazzo getMazzo() {
        return mazzo;
    }

    // classe->.json
    public void salva() {
        Utili.salva("partite", Integer.toString(id), this);
    }

    // .json->classe
    public static Partita carica(int id) {
        return new Gson().fromJson(Utili.leggiFileJson("partite", Integer.toString(id)), Partita.class);
    }

    @Override
    public String toString() {
        if (vincitore == null) {
            vincitore = new Giocatore("vincitore non trovato");
        }
        return "> Id Partita: " + id + ", Giocatori: " + Arrays.toString(giocatori) + ", Vincitore: " + vincitore.toString();
    }

    public int getIdTorneo() {
        return id_torneo;
    }

    public Carta getSeme() {
        return this.seme_che_comanda;
    }

    public void setSeme(Carta seme) {
        this.seme_che_comanda = seme;
    }

    public void newMazzo() {
        this.mazzo = new Mazzo(Mazzo.creaMazzoIniziale());
    }

    public void setTurnoSalvato(int val) {
        this.turno_salvato = val;
    }

    public void setGiocatoreSalvato(int val) {
        this.giocatore_salvato = val;
    }

    public void finePartita() {
        OPZ.vittoria();
        int max = 0;
        vincitore = giocatori[0];
        int temp;
        for (Giocatore g : giocatori) {
            temp = g.getPunti();
            if (temp > max) {
                max = temp;
                vincitore = g;
            }
        }

        for (Giocatore g : giocatori) {
            g.rimuoviPartita(this.id);
            g.resetPunti();
            g.resetMazzo();
            g.salva();
        }

        System.out.println(vincitore.getNome() + " ha vinto!");

        vincitore.setPartiteVinte(vincitore.getPartiteVinte() + 1);
        vincitore.rimuoviPartita(this.id);
        vincitore.resetPunti();
        vincitore.resetMazzo();
        vincitore.salva();


        Utili.getLeaderboard();

        if (id_torneo == 0) {
            elimina();
        } else {
            finePartitaTorneo();
        }
    }

    private void finePartitaTorneo() {
        salva();
        System.out.println("partita salvata");
        Torneo t = Torneo.carica(id_torneo);
        t.getGiocatori().add(vincitore);
        if(t.tuttiVincitori()){
            // prossimo round
            System.out.println("Prossimo Round");
            t.aumentaRound();
            t.creaPartite();
        }
        t.salva();
    }

    public void elimina() {
        Utili.eliminaPartita(id);
    }

    public int valutaCarte(Carta[] mano) {
        int[] carteNum = new int[5];
        Seme[] carteSeme = new Seme[5];
        for (int i = 0; i < 5; i++) {
            carteNum[i] = mano[i].getNumero();
            carteSeme[i] = mano[i].getSeme();
        }
        Arrays.sort(carteNum);
        int punti = 0;
        //valutiamo se ha coppie,tris,poker,manita, full, doppia coppia in mano
        punti = punti + numUguali(carteNum);

        //ora verifichiamo se ci sono scale
        switch (verificaScala(carteNum)) {
            case 3:
                //System.out.println("scala da 3 carte");
                punti = punti + 15;
                break;
            case 4:
                //System.out.println("scala da 4 carte");
                punti = punti + 35;
                break;
            case 5:
                //System.out.println("scala da 5 carte");
                punti = punti + 60;
                break;
            default:
                //System.out.println("nessuna scala");
        }

        switch (verificaColore(carteSeme)) {
            case 3:
                //System.out.println("3 carte dello stesso colore");
                punti = (int) ((punti + 10)*ModificaPuntiSemeCheComanda());
                break;
            case 4:
                //System.out.println("4 carte dello stesso colore");
                punti = (int) ((punti + 30)*ModificaPuntiSemeCheComanda());
                break;
            case 5:
                //System.out.println("5 carte dello stesso colore");
                punti = (int) ((punti + 80)*ModificaPuntiSemeCheComanda());
                break;
            default:
                //System.out.println("non hai fatto colore");
        }
        return punti;
    }
    //per valutare la sequenza colore in modo differente in base al tipo di seme che comanda, restituirà un moltiplicatore
    //(se il seme che comanda è fuoco e la mia sequenza colore è di tipo acqua allora il valore dei punti verrà raddoppiato, se la sequenza fosse di tipo erba allora verrà dimezzata)
    private double ModificaPuntiSemeCheComanda(){
        double moltiplicatore = 1;
        if(this.semeCatenaColore == Seme.Acqua){
            if(this.semeComandante == Seme.Acqua){
                moltiplicatore = 1;
            } else if (this.semeComandante == Seme.Terra) {
                moltiplicatore = 2;
            } else if (this.semeComandante == Seme.Erba) {
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Elettro) {
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Fuoco) {
                moltiplicatore = 2;
            }
        }
        if(this.semeCatenaColore == Seme.Fuoco){
            if(this.semeComandante == Seme.Acqua){
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Terra) {
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Erba) {
                moltiplicatore = 2;
            } else if (this.semeComandante == Seme.Elettro) {
                moltiplicatore = 1;
            } else if (this.semeComandante == Seme.Fuoco) {
                moltiplicatore = 1;
            }
        }
        if(this.semeCatenaColore == Seme.Erba){
            if(this.semeComandante == Seme.Acqua){
                moltiplicatore = 2;
            } else if (this.semeComandante == Seme.Terra) {
                moltiplicatore = 2;
            } else if (this.semeComandante == Seme.Erba) {
                moltiplicatore = 1;
            } else if (this.semeComandante == Seme.Elettro) {
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Fuoco) {
                moltiplicatore = 0.5;
            }
        }
        if(this.semeCatenaColore == Seme.Elettro){
            if(this.semeComandante == Seme.Acqua){
                moltiplicatore = 2;
            } else if (this.semeComandante == Seme.Terra) {
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Erba) {
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Elettro) {
                moltiplicatore = 1;
            } else if (this.semeComandante == Seme.Fuoco) {
                moltiplicatore = 1;
            }
        }
        if(this.semeCatenaColore == Seme.Terra){
            if(this.semeComandante == Seme.Acqua){
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Terra) {
                moltiplicatore = 1;
            } else if (this.semeComandante == Seme.Erba) {
                moltiplicatore = 0.5;
            } else if (this.semeComandante == Seme.Elettro) {
                moltiplicatore = 2;
            } else if (this.semeComandante == Seme.Fuoco) {
                moltiplicatore = 2;
            }
        }
        return moltiplicatore;
    }

    public int verificaColore(Seme[] carteSeme) {
        Map<Seme, Integer> mappa = new HashMap<>();

        for (Seme chiave : carteSeme) {
            mappa.put(chiave, mappa.getOrDefault(chiave, 0) + 1);
        }
        int numColorePiuVolteRipetuto = 0;
        for (Seme chiave : mappa.keySet()) {
            int valore = mappa.get(chiave);
            if (valore > numColorePiuVolteRipetuto) {
                numColorePiuVolteRipetuto = valore;
                this.semeCatenaColore = chiave;
                System.out.println(this.semeCatenaColore);
            }
        }
        return numColorePiuVolteRipetuto;
    }

    public int numUguali(int[] carteNum) {
        int punti = 0;
        // Conta quante volte ogni numero appare nel vettore
        Map<Integer, Integer> conteggioNumeri = new HashMap<>();
        for (int numero : carteNum) {
            conteggioNumeri.put(numero, conteggioNumeri.getOrDefault(numero, 0) + 1);
        }
        //System.out.println(conteggioNumeri);

        // Trova e rimuovi tutte le voci con valore 1 che devono essere ignorate per la valutazione in quanto si comincia a valutare dalla coppia in su
        Iterator<Map.Entry<Integer, Integer>> iterator = conteggioNumeri.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getValue() == 1) {
                iterator.remove();
            }
        }

        //System.out.println(conteggioNumeri);
        //System.out.println("Size "+conteggioNumeri.size());

        //inserisco i valori in una collezione per sapere se ho doppia coppie o tris e coppia
        Collection<Integer> valori = conteggioNumeri.values();
        //System.out.println(valori);

        //converto in array così riesco a prendere le posizioni singole della collezione ed effettuare dei confronti
        Integer[] array = valori.toArray(new Integer[0]);
        // Verifica il tipo di mano
        switch (conteggioNumeri.size()) {
            case 1:
                if (array[0] == 2) {    //coppia
                    punti = punti + 5;
                } else if (array[0] == 3) {     //tris
                    punti = punti + 25;
                } else if (array[0] == 4) {     //poker
                    punti = punti + 50;
                } else if (array[0] == 5) {     //manita
                    punti = punti + 80;
                }
                break;
            case 2:
                if ((array[0] == 2 && array[1] == 3) || (array[0] == 3 && array[1] == 2)) {     //full
                    punti = punti + 30;
                } else if (array[0] == 2 && array[1] == 2) {    //doppia coppia
                    punti = punti + 15;
                }
                break;
        }
        return punti;
    }

    public int verificaScala(int[] carteNum) {
        int cont = 1;
        int contMax = 0;
        for (int i = 1; i < 5; i++) {
            if (carteNum[i] == carteNum[i - 1] + 1 || carteNum[i] == carteNum[i - 1]) {
                if (carteNum[i] == carteNum[i - 1]) {

                } else {
                    cont++;
                }
                if (cont > contMax) {
                    contMax = cont;
                }
            } else {
                cont = 1;
            }
        }
        return contMax;
    }

    public int getTurnoSalvato() {
        return this.turno_salvato;
    }

    public int getGiocatoreSalvato() {
        return this.giocatore_salvato;
    }

    public int getCont() {
        return this.cont;
    }

    public void setCont(int i) {
        this.cont = i;
    }

    public int getNumeroTurni() {
        return NUMERO_TURNI;
    }

    public void setVincitoreTemp() {
        vincitore = giocatori[0];
    }
}


