package gioco.progettospacca.classi;

public class Carta {
    private Seme seme;
    private int numero;
    private String image;
    private boolean cliccata = false;

    //costruttore per carte da gioco
    public Carta(Seme seme, int numero, String image) {
        this.seme = seme;
        this.numero = numero + 1;
        this.image = image;
    }

    public boolean getCliccata() {
        return this.cliccata;
    }

    public void setCliccata(boolean cliccata) {
        this.cliccata = cliccata;
    }

    public String getImage() {
        return image;
    }

    public Seme getSeme() {
        return this.seme;
    }

    public int getNumero() {
        return this.numero;
    }

    @Override
    public String toString() {
        return "Seme: " + seme + ", Numero: " + numero;
    }


}