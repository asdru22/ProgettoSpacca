package gioco.progettospacca.classi;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Objects;

public class ValoriGioca {
    // coppia di valori usati quando si gioca una partita
    private final String nome;
    private final int codice;

    public ValoriGioca(TextField nome, TextField codice) {
        this.nome = nome.getText();
        this.codice = Utili.leggiInt(codice);
    }

    public boolean controlloGiocatore() {
        if (Utili.esisteGiocatore(nome)) { // se esiste questo giocatore
            Giocatore g = Giocatore.carica(nome); // carica il suo file
            ArrayList<Integer> partite = g.getPartite(); // prendi le partite
            // se è admin può giocare a tutte
            if (Objects.equals(nome, "admin")) {
                return true;
            } // altrimenti guarda se c'è
            return partite.contains(codice);
        }
        return false;
    }
}
