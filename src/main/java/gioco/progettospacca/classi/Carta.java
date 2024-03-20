package gioco.progettospacca.classi;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

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

    public static ImageView createImageView(String percorsoImmagine) throws FileNotFoundException {
        InputStream inputStream = Carta.class.getResourceAsStream(percorsoImmagine);
        if (inputStream == null) {
            throw new IllegalArgumentException("Image not found: " + percorsoImmagine);
        }

        // Load the image from the input stream
        Image image = new Image(inputStream);

        // Return the image view
        return new ImageView(image);
    }
    public static ImageView makeImageView(String path,Partita p){
        try {
            return Carta.createImageView(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}