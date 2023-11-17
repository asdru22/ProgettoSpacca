package gioco.progettospacca.classi;

import com.google.gson.Gson;

import java.io.File;
import java.util.Arrays;

public class Giocatore {
    private String nome;
    private Carta[] mano ;
    private int partiteVinte;
    public Giocatore(String nome){
        this.nome = nome;
        this.mano = new Carta[5];
        this.partiteVinte=0;
    }
    public String getNome(){return nome;}
    public Carta[] getMano() {return mano;}
    public int getPartiteVinte() {return partiteVinte;}

    public void setPartiteVinte(int partiteVinte) {this.partiteVinte = partiteVinte;}
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
    public void aggiungiSalvataggio(){
        File folder = new File("src/main/java/gioco/progettospacca/salvataggi/giocatori");
        File[] file_giocatori = folder.listFiles();
        boolean trovato = false;
        for (File file : file_giocatori) {
            if (file.isFile()) {
                String s = file.getName().substring(0,file.getName().length()-5);
                if(s.equals(nome)) trovato = true;
            }
        }
        if(!trovato) this.salva();
    }
    @Override
    public String toString(){
        return "Nome: "+nome+", mano: "+ Arrays.toString(mano);
    }
}
