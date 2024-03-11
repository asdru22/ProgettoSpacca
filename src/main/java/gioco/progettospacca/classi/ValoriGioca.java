package gioco.progettospacca.classi;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ValoriGioca {
    private String nome;
    private int codice;

    public ValoriGioca(TextField nome, TextField codice){
        this.nome = nome.getText();
        this.codice = Utili.leggiInt(codice);
    }
    public boolean controlloGiocatore() {
        if (Utili.esisteGiocatore(nome)) {
            Giocatore g = Giocatore.carica(nome);
            ArrayList<Integer> partite = g.getPartite();
            String nome = g.getNome();
            if(nome=="admin") return true; // admin può entrare in tutte le partite
            else if (!partite.contains(codice)) {
                System.out.println("Giocatore " + nome + " non ha questo id");
            }
            return partite.contains(codice);
        } else if (Utili.nomiBot(nome)) {
            return true;
        } else {
            System.out.println("Giocatore " + nome + " non esiste");
            return false;
        }
    }
}
