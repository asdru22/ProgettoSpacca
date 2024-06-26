package gioco.progettospacca.classi;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

import static gioco.progettospacca.controller.Main.OPZ;


public class Partita {
    private int NUMERO_TURNI = OPZ.getTurniPartita();
    private final int id;
    private int turno_salvato = 0;
    private int giocatore_salvato = 0;
    private int id_torneo;
    private Giocatore[] giocatori;
    private Giocatore vincitore = null;
    private Giocatore toccaA;
    private Mazzo mazzo;
    private Carta seme_che_comanda;
    private Seme semeComandante;
    private int cont = 0; //cpntatore turni totali
    private boolean primoTurnoScena = true;
    private int partitaTorneoNumGiocatori = 0; //se 0 è una prtita a se, se è diverso mi dice di quale tipo di torneo fa parte (se da 4 , da 8 o da 16 giocatori)
    private Seme semeCatenaColore;
    private int moltiplicatoreImprevisti = 1; //per il primo imprevisto
    private int sommaRubata = 0; //per il secondo imprevisto

    public Partita(int id, Giocatore[] giocatori, int id_torneo) {
        this.id = id;
        this.giocatori = giocatori;
        this.mazzo = new Mazzo();
        this.id_torneo = id_torneo;
        toccaA = giocatori[0];
        this.salva();
    }

    public Partita(Giocatore[] giocatori, int id_torneo) {
        this(Utili.intCasuale(10000, 99999), giocatori, id_torneo);
    }

    public void setSemeComandante() {
        this.semeComandante = seme_che_comanda.getSeme();
    }

    public int getPartitaTorneoNumGiocatori() {
        return this.partitaTorneoNumGiocatori;
    }

    public void setPartitaTorneoNumGiocatori(int partitaTorneoNumGiocatori) {
        this.partitaTorneoNumGiocatori = partitaTorneoNumGiocatori;
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

    public void setNumeroTurni(int n) {
        this.NUMERO_TURNI = n;
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

    private String getClassificaFinalePartita() {
        String s = "";
        Giocatore[] temp = giocatori.clone();
        Arrays.sort(temp, Comparator.comparingInt(Giocatore::getPunti).reversed());
        for (int j = 0; j < temp.length; j++) {
            s = s + (j + 1) + ") " + temp[j].getNome() + ":  " + temp[j].getPunti() + "\n";
        }
        return s;
    }

    public void finePartita() {
        // viene eseguito alla fine di una partita
        OPZ.vittoria();
        int max = 0;
        vincitore = giocatori[0];
        int temp;
        // trova vincitore
        for (Giocatore g : giocatori) {
            temp = g.getPunti();
            if (temp > max) {
                max = temp;
                vincitore = g;
            }
        }
        String classifica = getClassificaFinalePartita();
        for (Giocatore g : giocatori) {
            if (!g.getEmail().isEmpty() && g.getEmail() != "") {        //invio la mail di fine partita a tutti i giocatori della partita che hanno mregistrato la mail nel giocatore
                if (id_torneo == 0) {
                    MailThread thread = new MailThread(g.getEmail(), "Fine partita", "Partita conclusa \nVincitore: " + vincitore + "\nClassifica finale:\n" + classifica);
                    thread.start();
                }
            }

            g.rimuoviPartita(this.id);
            g.resetPunti();
            g.resetMazzo();
            g.salva();
        }

        vincitore.aumentaVittorie();
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
        Torneo t = Torneo.carica(id_torneo);
        t.getGiocatori().add(vincitore);
        if (t.tuttiVincitori()) {
            // prossimo round
            t.aumentaRound();
            t.creaPartite();
            if (t.getGiocatori().size() == 1) {
                t.setFinito();
                t.setVincitore(vincitore);
                for (String g : t.getListaGiocatoriIniziali()) {        //invia la mail di fine torneo a tutti coloro che hanno registrato una mail nel giocatore e che hanno partecipato al torneo
                    Giocatore gioc = Giocatore.carica(g);
                    if (!gioc.getEmail().isEmpty() && gioc.getEmail() != "") {
                        MailThread thread = new MailThread(gioc.getEmail(), "Fine torneo", "Torneo con codice " + t.getId() + " concluso \nVincitore: " + t.getVincitore());
                        thread.start();
                    }
                }
            }
        }
        for (Giocatore g : giocatori) {         //invia la mail con le informazioni riguardanti la partita del torneo solo ai due della partita del torneo nel caso avessero registrato la mail nel giocatore
            if (!g.getEmail().isEmpty() && g.getEmail() != "") {
                MailThread thread = new MailThread(g.getEmail(), "Fine partita torneo", "Partita del torneo con codice " + t.getId() + " conclusa\n" + giocatori[0] + " VS " + giocatori[1] + "\nVincitore: " + vincitore);
                thread.start();
            }
        }

        t.salva();
    }

    public void elimina() {
        Utili.eliminaPartita(id);
    }

    //valutazione complessiva della mano
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
                // scala da 3 carte
                punti = punti + 15;
                break;
            case 4:
                // scala da 4 carte
                punti = punti + 35;
                break;
            case 5:
                // scala da 5 carte
                punti = punti + 60;
                break;
            default:
        }
        switch (verificaColore(carteSeme)) {
            case 3:
                //3 carte dello stesso colore
                punti = ((punti + 5));
                break;
            case 4:
                // 4 carte dello stesso colore
                punti = ((punti + 25));
                break;
            case 5:
                // 5 carte dello stesso colore
                punti = ((punti + 50));
                break;
            default:
                //non hai fatto colore
        }
        punti = (int) (punti * this.moltiplicatoreImprevisti * ModificaPuntiSemeCheComanda());
        moltiplicatoreImprevisti = 1;

        return punti;
    }

    //per valutare la sequenza colore in modo differente in base al tipo di seme che comanda, restituirà un moltiplicatore
    //(se il seme che comanda è fuoco e la mia sequenza colore è di tipo acqua allora il valore dei punti verrà raddoppiato, se la sequenza fosse di tipo erba allora verrà dimezzata)
    private double ModificaPuntiSemeCheComanda() {
        double moltiplicatore = 1;
        if (this.semeCatenaColore == Seme.Acqua) {
            if (this.semeComandante == Seme.Acqua) {
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
        if (this.semeCatenaColore == Seme.Fuoco) {
            if (this.semeComandante == Seme.Acqua) {
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
        if (this.semeCatenaColore == Seme.Erba) {
            if (this.semeComandante == Seme.Acqua) {
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
        if (this.semeCatenaColore == Seme.Elettro) {
            if (this.semeComandante == Seme.Acqua) {
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
        if (this.semeCatenaColore == Seme.Terra) {
            if (this.semeComandante == Seme.Acqua) {
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

    //verifica se possiedo un sequenza di colori d 3 min a 5 max
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
                if (valore > 2) {
                    this.semeCatenaColore = chiave;
                } else {
                    this.semeCatenaColore = null;
                }
            }
        }
        return numColorePiuVolteRipetuto;
    }

    //verifica se ho coppia, tris, poker, manita, doppia coppia o full in mano
    public int numUguali(int[] carteNum) {
        int punteggio = 0;
        // Conta quante volte ogni numero appare nel vettore
        Map<Integer, Integer> conteggioNumeri = new HashMap<>();
        for (int numero : carteNum) {
            conteggioNumeri.put(numero, conteggioNumeri.getOrDefault(numero, 0) + 1);
        }

        // Trova e rimuovi tutte le voci con valore 1 che devono essere ignorate per la valutazione in quanto si comincia a valutare dalla coppia in su
        Iterator<Map.Entry<Integer, Integer>> iterator = conteggioNumeri.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getValue() == 1) {
                iterator.remove();
            }
        }

        //inserisco i valori in una collezione per sapere se ho doppia coppie o tris e coppia
        Collection<Integer> valori = conteggioNumeri.values();

        //converto in array così riesco a prendere le posizioni singole della collezione ed effettuare dei confronti
        Integer[] array = valori.toArray(new Integer[0]);
        // Verifica il tipo di mano
        switch (conteggioNumeri.size()) {
            case 1:
                if (array[0] == 2) {    //coppia
                    punteggio = punteggio + 5;
                } else if (array[0] == 3) {     //tris
                    punteggio = punteggio + 25;
                } else if (array[0] == 4) {     //poker
                    punteggio = punteggio + 70;
                } else if (array[0] == 5) {     //manita
                    punteggio = punteggio + 130;
                }
                break;
            case 2:
                if ((array[0] == 2 && array[1] == 3) || (array[0] == 3 && array[1] == 2)) {     //full
                    punteggio = punteggio + 45;
                } else if (array[0] == 2 && array[1] == 2) {    //doppia coppia
                    punteggio = punteggio + 15;
                }
                break;
        }
        return punteggio;
    }

    //per verificare se hai una scala in mano
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

    //vado ad impostare gli effetti degli imprevisti in base a quale viene pescato
    public void setImprevisti() {
        for (Carta carta : toccaA.getMano()) {
            if (carta.getImage() == "/gioco/progettospacca/carte/imprevisti/1.png") {
                this.moltiplicatoreImprevisti = 2;
                break;
            } else if (carta.getImage() == "/gioco/progettospacca/carte/imprevisti/2.png") {
                sommaRubata = 0;
                for (int i = 0; i < giocatori.length; i++) {
                    if (giocatori[i] != toccaA) {
                        if (giocatori[i].getPunti() >= 15) {
                            giocatori[i].setPunti(-15);
                            sommaRubata = sommaRubata + 15;
                        } else {
                            sommaRubata = sommaRubata + giocatori[i].getPunti();
                            giocatori[i].setPunti(-giocatori[i].getPunti());
                        }
                    }
                }
                toccaA.setPunti(sommaRubata);
                break;
            }
        }
    }

    public int getNumeroTurni() {
        return NUMERO_TURNI;
    }

    //serve per l'imprevisto che ruba i punti
    public int getSommaRubata() {
        return sommaRubata;
    }
}


