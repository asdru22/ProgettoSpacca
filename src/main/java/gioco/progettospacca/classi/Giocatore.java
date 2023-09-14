package gioco.progettospacca.classi;
import java.util.ArrayList;
public class Giocatore {
    private String nome;
    private int codicePartita;
    private ArrayList<Carta> mazzo = new ArrayList<>();

    public Giocatore(String nome,int codicePartita){
        this.nome = nome;
        this.codicePartita = codicePartita;
    }
    public String getNome(){
        return nome;
    }
    public int getCodicePartita(){
        return codicePartita;
    }
    public void setCodicePartita(int id) {this.codicePartita = id; }
    public void scarta(Carta carta){
        //
        // qua codice per mettere carta nella partita attuale
        //
        mazzo.remove(carta);
    }
    public void pesca(Partita p){
        mazzo.add(p.getCartaInCima());
        p.getMazzo().remove(0);
    }
    @Override
    public String toString(){
        return "{Nome: "+nome+", Codice Partita: "+codicePartita+"}";
    }
}
