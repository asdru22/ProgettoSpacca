package gioco.progettospacca.classi;


import javafx.scene.control.Button;

import java.util.Objects;

import static gioco.progettospacca.controller.Main.CODICE_GLOBALE_PARTITA;
import static gioco.progettospacca.controller.Main.OPZ;

public class BottoneTorneo extends Button {

    private Integer partita;
    private String[] giocatori = new String[2];

    public String[] getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(String[] giocatori) {
        this.giocatori = giocatori;
    }

    public int getPartita() {
        return partita;
    }

    public void setPartita(int partita) {
        this.partita = partita;
    }

    public void inizializza(Torneo t) {
        int giocatoriIniziali = t.getGiocatoriIniziali();

        if (giocatoriIniziali == 4) {
            partita4(t);
        }

        if (giocatoriIniziali == 8) {
            partita8(t);
        }

        if (giocatoriIniziali == 16) {
            partita16(t);
        }

        if (isFinale()) {
            this.setText(OPZ.traduci("finale"));

        }
    }

    private void partita4(Torneo t) {
        if (isSemi()) {
            this.aggiungiPartita(t);
        }
    }
    private void partita8(Torneo t) {
        if (isQuarti()) {
            this.aggiungiPartita(t);
        }
    }
    private void partita16(Torneo t) {
        if (isOttavi()){
            this.aggiungiPartita(t);
        }
    }

    private void aggiungiPartita(Torneo t) {
        partita = t.getCopiaPartite().remove(0);
        Partita p = Partita.carica(partita);
        giocatori[0] = p.getGiocatori()[0].getNome();
        giocatori[1] = p.getGiocatori()[1].getNome();

        if (p.getVincitore() == null) {
            this.setText(giocatori[0] + " - " + giocatori[1]);
        } else {
            this.setDisable(true);
            this.setText("Vincitore: " + p.getVincitore().getNome());
        }

    }

    public boolean isFinale() {
        return Objects.equals(this.getId(), "finale");
    }

    public boolean isSemi() {
        return this.getId().contains("semi");
    }

    public boolean isQuarti() {
        return this.getId().contains("quarto");
    }

    public boolean isOttavi() {
        return this.getId().contains("ottavo");
    }
}
