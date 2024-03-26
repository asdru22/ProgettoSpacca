package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.CODICE_GLOBALE_PARTITA;
import static gioco.progettospacca.controller.Main.CODICE_GLOBALE_TORNEO;


public class TorneoController implements Initializable {
    public void inizio(){
        CODICE_GLOBALE_PARTITA = 1;
        PartitaController partitaController = new PartitaController();
        partitaController.initialize(null,null);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        if(!t.isIniziato()){
            inizio();
        }
    }
}
