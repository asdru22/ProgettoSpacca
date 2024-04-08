package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class PartiteSalvateController implements Initializable {
    @FXML
    Button btn_eliminaPartita,btn_eliminaTorneo;
    @FXML
    Label lbl_titoloPartite, lbl_titoloTornei;
    @FXML
    ListView list_partita,list_listaTornei;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_eliminaPartita.setText(OPZ.traduci("elimina_partita"));
        btn_eliminaTorneo.setText(OPZ.traduci("elimina_torneo"));
        lbl_titoloPartite.setText(OPZ.traduci("partite"));
        lbl_titoloTornei.setText(OPZ.traduci("tornei"));
    }
    /*
    File folder = new File("src/main/java/gioco/progettospacca/salvataggi/partite");
    File[] file_partite = folder.listFiles();
    for( File file : file_partite)
    {
        if (file.isFile()) {
            String s = file.getName().substring(0, file.getName().length() - 5);
        }
    }
    */
}
