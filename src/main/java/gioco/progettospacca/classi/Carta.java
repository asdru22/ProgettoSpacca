package gioco.progettospacca.classi;

public class Carta {
    private String id;
    private Giocatore scartataDa;

    public Carta(String id, Giocatore scartataDa){
        this.id = id;
        this.scartataDa = scartataDa;
    }
    public String getId(){
        return id;
    }
    public Giocatore getScartataDa(){
        return scartataDa;
    }
    @Override
    public String toString() {
        return "{Carta: " + id + ", Scartata Da:" + scartataDa.toString()+"}";
    }
}