package gioco.progettospacca.classi;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class Carta {
    private Seme seme;
    private Giocatore scartataDa;
    private int numero;

    private String image;

    private boolean cliccata = false;

    public Carta(Seme seme, int numero, String image) {
        this.seme = seme;
        this.numero = numero + 1;
        this.scartataDa = null;
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

    /*
    public Giocatore getScartataDa(){
        return scartataDa;
    }
     */
    @Override
    public String toString() {
        return "" + seme + " " + numero;
    }

    public static ImageView makeImageView(String path) {
        try {
            return new ImageView(new Image(Objects.requireNonNull(Carta.class.getResourceAsStream(path))));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Immagine non trovata: " + path, e);
        }
    }
}