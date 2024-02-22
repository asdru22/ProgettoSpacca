package gioco.progettospacca.classi;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.ArrayList;

public class Torneo {
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
    private void creaPartite(){

        int n_partite = giocatori.size()/2;
        Giocatore[] coppia = new Giocatore[2];
        ArrayList<Giocatore> g = mischia(giocatori);
        for(int i = 0 ; i<n_partite;i++){

            coppia[0] = g.get(0);
            g.remove(0);
            coppia[1] = g.get(0);
            g.remove(0);
            partite.add(new Partita(coppia,id));
        }
    }
    private void inizia(){
        String vincitore = "";

        int n_round = (int)Math.sqrt(giocatori.size()); // caso con 4 giocatori, n_round = 2

        for(int i = 1;i<=n_round;i++){

            creaPartite(); // crea 2 partite nella prima iterazione, 1 nella seconda
            ArrayList<Partita> p_temp = new ArrayList<>(partite);

            for(Partita p:p_temp){ // for eseguito 2 volte nella prima, 1 nella seconda
                p.inizio();
                giocatori.add(p.getVincitore());
            }
            // fine round
            if (i == n_round) vincitore = giocatori.get(0).getNome();
            partite.clear();
        }
        // fine torneo
        System.out.println(vincitore+" ha vinto il torneo!");

    }
    private ArrayList<Giocatore> mischia(ArrayList<Giocatore> lista){
        Collections.shuffle(lista);
        return lista;
    }
    private Object[] numeroGiocatoriGiusti(int n){
        Object[] r = new Object[]{false,0};
        int i = 1;
        // controlla se il numero di giocatori è una potenza di 2 e dunque è possibile fare un torneo
        while(!(boolean)r[0] && i <=4){
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
