package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class Giocatore {
    private String nome;
    private int codicePartita;
    private Mazzo mazzo;

    public Giocatore(String nome,int codicePartita){
        this.nome = nome;
        this.codicePartita = codicePartita;
        this.mazzo = null;
    }
    public String getNome(){return nome;}
    public int getCodicePartita(){return codicePartita;}
    public Mazzo getMazzo() {return mazzo;}

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
    public void convertiAJson(){
        try (FileWriter writer = new FileWriter("src/main/java/gioco/progettospacca/salvataggi/giocatori/"+nome+".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(this));
        } catch (IOException e) {
            System.err.println("Errore scrittura file");
        }
    }
    @Override
    public String toString(){
        return "{Nome: "+nome+", Codice Partita: "+codicePartita+"}";
    }
}
