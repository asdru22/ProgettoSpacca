package gioco.progettospacca.classi;

public class Carta {
    private Seme seme;
    private Giocatore scartataDa;
    private int numero;

    private String image;
    public Carta(Seme seme,int numero, String image){
        this.seme = seme;
        this.numero = numero;
        this.scartataDa = null;
        this.image = image;
    }

    public String getImage() {
         return this.image = image;
    }

    public Seme getSeme(){
        return this.seme;
    }

    public int getNumero(){
        return this.numero;
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