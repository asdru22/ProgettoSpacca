package gioco.progettospacca.classi;

import com.google.gson.Gson;

public class Giocatore {
    private String nome;
    private int codicePartita;
    private Mazzo mazzo;
    private int partiteVinte;
    public Giocatore(String nome){
        this.nome = nome;
        this.codicePartita = -1;
        this.mazzo = null;
        this.partiteVinte=0;
    }
    public String getNome(){return nome;}
    public int getCodicePartita(){return codicePartita;}
    public Mazzo getMazzo() {return mazzo;}
    public int getPartiteVinte() {return partiteVinte;}

    public void setPartiteVinte(int partiteVinte) {this.partiteVinte = partiteVinte;}
    public void setCodicePartita(int id) {this.codicePartita = id; }
    public void scarta(Carta carta){
        //
        // qua codice per mettere carta nella partita attuale
        //
        mazzo.getMazzo().remove(carta);
    }
    public void pesca(Partita p){
        mazzo.getMazzo().add(p.getMazzo().getCartaInCima());
        p.getMazzo().getMazzo().remove(0);
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
