package gioco.progettospacca.classi;

public class Carta {
    private Seme seme;
    private Giocatore scartataDa;
    private int numero;

    public Carta(Seme seme,int numero){
        this.seme = seme;
        this.numero = numero;
        this.scartataDa = null;
    }
    /*
    public Carta(Seme seme,int numero, Giocatore giocatore){
        this.seme = seme;
        this.numero = numero;
        this.scartataDa = giocatore;
    }
    */
    public Seme getSeme(){
        return seme;
    }

    /*
    public Giocatore getScartataDa(){
        return scartataDa;
    }
     */
    @Override
    public String toString() {
        return ""+seme+" "+numero;
    }
}