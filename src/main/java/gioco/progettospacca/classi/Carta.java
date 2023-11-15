package gioco.progettospacca.classi;

public class Carta {
    private String id;
    private Seme seme;
    private Giocatore scartataDa;

    public Carta(String id,Seme seme){
        this.id = id;
        this.seme = seme;
        this.scartataDa = null;
    }
    public Carta(String id,Seme seme, Giocatore giocatore){
        this.id = id;
        this.seme = seme;
        this.scartataDa = giocatore;
    }
    public String getId(){
        return id;
    }
    public Seme getSeme(){
        return seme;
    }
    public Giocatore getScartataDa(){
        return scartataDa;
    }
    @Override
    public String toString() {
        return "{Carta: " + id + "Tipo: "+seme+", Scartata Da:" + scartataDa.toString()+"}";
    }
}