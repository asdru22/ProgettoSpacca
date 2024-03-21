package gioco.progettospacca.classi;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class Carta {
    private Seme seme;
    private int numero;

    private String image;

    private boolean cliccata = false;

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

    public static ImageView makeImageView(String path) {
        try {
            // solleva errore se il path è null
            return new ImageView(new Image(Objects.requireNonNull(Carta.class.getResourceAsStream(path))));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Immagine non trovata: " + path, e);
        }
    }
}