package gioco.progettospacca.classi;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.ArrayList;

public class Torneo {
    private final int MAX_ROUND = 4;
    private Giocatore vincitore;
    private int round_salvato = 0;
    private int numero_round = 0;
    private int partita_salvata = 0;
    private int numero_partite = 0;

    private ArrayList<Giocatore> giocatori;
    private ArrayList<Partita> partite = new ArrayList<>();
    private int id;
    public Torneo(ArrayList<Giocatore> giocatori,int id){
        this.giocatori = giocatori;
        this.id = id;
    }
    public static Torneo carica(int id) {
        Gson gson = new Gson();
        return gson.fromJson(Utili.leggiFileJson("tornei", Integer.toString(id)), Torneo.class);
    }
    public void salva() {
        Utili.salva("tornei", Integer.toString(id), this);
    }
    public ArrayList<Partita> getPartite(){
        return partite;
    }
    public void iniziaMain(){
        int bot_esistenti=0;
        for(Giocatore g: giocatori){
            if(g.isBot()) bot_esistenti+=1;
        }
        Object[] o = numeroGiocatoriGiusti(giocatori.size());
        if ((boolean)o[0]){
            inizia();
        } else if((int)o[1]>=1){
            int n_bot = (int)o[1];
            for(int i = 0;i<n_bot;i++){
                bot_esistenti+=1;
                giocatori.add(new Giocatore("bot"+bot_esistenti,true));
            }
            System.out.println("Aggiunti "+n_bot+ " bot per raggiungere il numero di giocatori richiesti ("+giocatori.size()+")");
            inizia();
        } else throw new Error("Errore inizio torneo");
    }
    public void elimina(){
        Utili.elimina(id,"tornei");
    }

    private void riprendi(){
        riprendiRound();
    }
    private void inizia(){
        salva();
        numero_round = (int)(Math.log(giocatori.size()) / Math.log(2)); // caso con 4 giocatori, n_round = 2
        System.out.println("Numero round: "+numero_round+", numero giocatori:"+giocatori.size());
        riprendiRound();
    }
    private void riprendiRound(){

        for(;round_salvato<numero_round;round_salvato++){
            creaPartite(); // crea
            riprendiPartita(); // esegui
            // fine round
        }
        vincitore = giocatori.get(0);
        // fine torneo
        System.out.println(vincitore.getNome()+" ha vinto il torneo!");
        vincitore.setPartiteVinte(vincitore.getPartiteVinte()+numero_round);
        elimina();
    }
    private void riprendiPartita(){
        Partita partita_corrente;
        numero_partite = partite.size();
        for(; partita_salvata<numero_partite; partita_salvata++){
            System.out.println(">>> Partita: "+(partita_salvata+1)+"/"+numero_partite+", Round: "+(round_salvato+1)+"/"+numero_round);

            partita_corrente = partite.get(partita_salvata);
            // controllo per riprendi partita
            if(partita_corrente.isIniziata()){partita_corrente.riprendi();}
            else{partita_corrente.inizio();}

            giocatori.add(partita_corrente.getVincitore());
        }
        partita_salvata = 0;
        partite.clear(); // elimina partite
    }
    private void creaPartite(){
        numero_partite = giocatori.size()/2;
        Giocatore[] coppia = new Giocatore[2];
        Collections.shuffle(giocatori);
        for(int i = 0 ; i<numero_partite;i++){

            coppia[0] = giocatori.get(0);
            giocatori.remove(0);
            coppia[1] = giocatori.get(0);
            giocatori.remove(0);
            partite.add(new Partita(coppia,id));
            System.out.println("Creata nuova partita con:"+coppia[0].getNome()+" e " + coppia[1].getNome());
        }
    }
    private Object[] numeroGiocatoriGiusti(int n){
        Object[] r = new Object[]{false,0};
        int i = 1;
        // controlla se il numero di giocatori è una potenza di 2 e dunque è possibile fare un torneo
        while(!(boolean)r[0] && i <=MAX_ROUND){
            r[0] = (n == Math.pow(i,2));
            i+=1;
        }
        if(!(boolean)r[0]){
            r[1] = numeroBot(n);
        }
        return r;
    }
    private int numeroBot(int n){
        // Trova la potenza di 2 più grande vicina a n
        int i = 1;
        while (Math.pow(2, i) <= n) {
            i++;
        }
        // Restituisci la potenza di 2 più grande e più vicina a n
        return (int) Math.pow(2, i)-n;
    }
}
