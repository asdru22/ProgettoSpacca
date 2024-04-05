package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.*;

public class TorneoController16 implements Initializable {
    @FXML
    Button btn_quarti1, btn_quarti2, btn_quarti3, btn_quarti4, btn_semi1, btn_semi2,
    btn_ottavi1,btn_ottavi2,btn_ottavi3,btn_ottavi4,btn_ottavi5,btn_ottavi6,btn_ottavi7,btn_ottavi8, btn_finale;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        ArrayList<Integer> partite = new ArrayList<>(t.getPartite());
        System.out.println("round: " + t.getRound());

        Utili.bottoneTorneo(btn_ottavi1, partite.get(0));
        Utili.bottoneTorneo(btn_ottavi2, partite.get(1));
        Utili.bottoneTorneo(btn_ottavi3, partite.get(2));
        Utili.bottoneTorneo(btn_ottavi4, partite.get(3));
        Utili.bottoneTorneo(btn_ottavi5, partite.get(4));
        Utili.bottoneTorneo(btn_ottavi6, partite.get(5));
        Utili.bottoneTorneo(btn_ottavi7, partite.get(6));
        Utili.bottoneTorneo(btn_ottavi8, partite.get(7));

        if (t.getRound() == 0) {
            btn_quarti1.setDisable(true);
            btn_quarti1.setText(OPZ.traduci("quarto"));
            btn_quarti2.setDisable(true);
            btn_quarti2.setText(OPZ.traduci("quarto"));
            btn_quarti3.setDisable(true);
            btn_quarti3.setText(OPZ.traduci("quarto"));
            btn_quarti4.setDisable(true);
            btn_quarti4.setText(OPZ.traduci("quarto"));

            btn_semi1.setDisable(true);
            btn_semi1.setText(OPZ.traduci("semifinale"));
            btn_semi2.setDisable(true);
            btn_semi2.setText(OPZ.traduci("semifinale"));

            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));

        } else if (t.getRound() == 1) {
            Utili.bottoneTorneo(btn_quarti1, partite.get(8));
            Utili.bottoneTorneo(btn_quarti2, partite.get(9));
            Utili.bottoneTorneo(btn_quarti3, partite.get(10));
            Utili.bottoneTorneo(btn_quarti4, partite.get(11));

            btn_semi1.setDisable(true);
            btn_semi1.setText(OPZ.traduci("semifinale"));
            btn_semi2.setDisable(true);
            btn_semi2.setText(OPZ.traduci("semifinale"));
            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));

        } else if (t.getRound() == 2) {
            Utili.bottoneTorneo(btn_quarti1, partite.get(8));
            Utili.bottoneTorneo(btn_quarti2, partite.get(9));
            Utili.bottoneTorneo(btn_quarti3, partite.get(10));
            Utili.bottoneTorneo(btn_quarti4, partite.get(11));

            Utili.bottoneTorneo(btn_semi1, partite.get(12));
            Utili.bottoneTorneo(btn_semi2, partite.get(13));

            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));

        } else if (t.getRound() == 3){
            Utili.bottoneTorneo(btn_quarti1, partite.get(8));
            Utili.bottoneTorneo(btn_quarti2, partite.get(9));
            Utili.bottoneTorneo(btn_quarti3, partite.get(10));
            Utili.bottoneTorneo(btn_quarti4, partite.get(11));

            Utili.bottoneTorneo(btn_semi1, partite.get(12));
            Utili.bottoneTorneo(btn_semi2, partite.get(13));

            Utili.bottoneTorneo(btn_finale, partite.get(14));
        }
    }

    public void giocaPartita(Button b) throws IOException {
        OPZ.premiBottone();
        OPZ.playMusica("gioco.mp3");
        Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) b.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
    }
    public void cliccaOttavi1(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(0);
        giocaPartita(b);
    }
    public void cliccaOttavi2(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(1);
        giocaPartita(b);
    }public void cliccaOttavi3(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(2);
        giocaPartita(b);
    }
    public void cliccaOttavi4(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(3);
        giocaPartita(b);
    }
    public void cliccaOttavi5(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(4);
        giocaPartita(b);
    }
    public void cliccaOttavi6(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(5);
        giocaPartita(b);
    }
    public void cliccaOttavi7(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(6);
        giocaPartita(b);
    }
    public void cliccaOttavi8(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(7);
        giocaPartita(b);
    }

    public void cliccaQuarti1(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(8);
        giocaPartita(b);
    }

    public void cliccaQuarti2(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(9);
        giocaPartita(b);
    }

    public void cliccaQuarti3(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(10);
        giocaPartita(b);
    }

    public void cliccaQuarti4(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(11);
        giocaPartita(b);
    }

    public void cliccaSemi1(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(12);
        giocaPartita(b);
    }

    public void cliccaSemi2(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(13);
        giocaPartita(b);
    }

    public void cliccaFinale(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(14);
        giocaPartita(b);
    }

}