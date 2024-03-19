package gioco.progettospacca.classi;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Objects;

public class ValoriGioca {
    private String nome;
    private int codice;

    public ValoriGioca(TextField nome, TextField codice) {
        this.nome = nome.getText();
        this.codice = Utili.leggiInt(codice);
    }

    public boolean controlloGiocatore() {
        if (Utili.esisteGiocatore(nome)) { // se esiste questo giocatore
            Giocatore g = Giocatore.carica(nome); // carica il suo file
            ArrayList<Integer> partite = g.getPartite(); // prendi le partite
            // se non è bot
            if (Objects.equals(nome, "admin")) {
                return true;
            } else if (!partite.contains(codice)) {
                System.out.println("Giocatore " + nome + " non ha questo id");
            }
            return partite.contains(codice);
        }
        System.out.println("Giocatore " + nome + " non esiste");
        return false;
    }
}
