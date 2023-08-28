package gioco.progettospacca.classi;

public class Giocatore {
    private String nome;
    private int codicePartita;

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
    @Override
    public String toString(){
        return "{Nome: "+nome+", Codice Partita: "+codicePartita+"}";
    }
}
