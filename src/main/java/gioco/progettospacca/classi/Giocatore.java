package gioco.progettospacca.classi;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private int codicePartita;
    private Carta[] mano ;
    private int partiteVinte;
    public Giocatore(String nome){
        this.nome = nome;
        this.codicePartita = -1;
        this.mano = new Carta[5];
        this.partiteVinte=0;
    }
    public String getNome(){return nome;}
    public int getCodicePartita(){return codicePartita;}
    public Carta[] getMano() {return mano;}
    public int getPartiteVinte() {return partiteVinte;}

    public void setPartiteVinte(int partiteVinte) {this.partiteVinte = partiteVinte;}
    public void setCodicePartita(int id) {this.codicePartita = id; }
    public void scarta(Carta carta){

    }
    public void pesca(int n,Mazzo m){
        for(int i = 0 ; i<n;i++){
            this.mano[i] = m.getMazzoArrayList().remove(0);
        }
    }

    // classe->.json
    public void salva(){
        Utili.salva("giocatori",nome,this);
    }
    // .json->classe
    public static Giocatore carica(String nome){
        Gson gson = new Gson();
        return gson.fromJson(Utili.leggiFileJson("giocatori",nome), Giocatore.class);
    }
    @Override
    public String toString(){
        return "> Nome: "+nome+", Codice Partita: "+codicePartita;
    }
}
