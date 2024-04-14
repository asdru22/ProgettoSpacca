package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class PartiteSalvateController implements Initializable {
    @FXML
    Button btn_eliminaPartita,btn_eliminaTorneo,btn_back;
    @FXML
    Label lbl_titoloPartite, lbl_titoloTornei;
    @FXML
    ListView list_partite,list_tornei;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_eliminaPartita.setText(OPZ.traduci("elimina_partita"));
        btn_eliminaTorneo.setText(OPZ.traduci("elimina_torneo"));
        lbl_titoloPartite.setText(OPZ.traduci("partite"));
        lbl_titoloTornei.setText(OPZ.traduci("tornei"));
    }


    public void EventoEliminaPartita(MouseEvent event) {
        //Utili.eliminaPartita(idSelected);
    }

    public void EventoEliminaTorneo(MouseEvent event) {
        //Utili.eliminaTorneo(idSelected);
    }



    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("PrivilegiAdminView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_back.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
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
