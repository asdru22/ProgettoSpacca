package gioco.progettospacca.classi;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
public class Torneo {
    private Giocatore[] giocatori;
    private ArrayList<Partita> partite = new ArrayList<>();
    public Torneo(Giocatore[] giocatori){
        this.giocatori = giocatori;
    }

    public void prossimoRound(){
        Giocatore[] temp = new Giocatore[partite.size()/2];
        for(int i =0; i<partite.size();i++){
            temp[i] = partite.get(i).getVincitore();
        }
        partite.clear();
        inizia(temp,temp.length);
    }
    public void inizia(){
        int partecipanti = giocatori.length;
        if (numeroGiocatoriGiusti(partecipanti)){
            inizia(giocatori,partecipanti/2); // divido per 2 cosi so già quante volte iterare
        } else{
            throw new Error("Numero di giocatori non è potenza di 2");
        }
    }
    private void inizia(Giocatore[] giocatori,int partecipanti){
        Giocatore[] coppia = new Giocatore[2];
        ArrayList<Giocatore> g = mischia(giocatori);
        for(int i = 0 ; i<partecipanti;i++){
            coppia[0] = g.get(0);
            g.remove(0);
            coppia[1] = g.get(0);
            g.remove(0);
            partite.add(new Partita(coppia));
        }
    }
    private ArrayList<Giocatore> mischia(Giocatore[] giocatori){
        ArrayList<Giocatore> lista = new ArrayList<>(Arrays.asList(giocatori));
        Collections.shuffle(lista);
        return lista;
    }
    private boolean numeroGiocatoriGiusti(int n){
        // controlla se il numero di giocatori è una potenza di 2 e dunque è possibile fare un torneo
        for(int i = 2;i<=4;i++){
            if (n == Math.pow(i,2)) return true;
        }
        if (n==2) return true;
        else return false;
    }
}
