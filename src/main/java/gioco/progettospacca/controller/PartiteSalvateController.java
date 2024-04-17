package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import gioco.progettospacca.classi.Utili;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class PartiteSalvateController implements Initializable {
    @FXML
    Button btn_eliminaPartita,btn_eliminaTorneo,btn_back;
    @FXML
    Label lbl_titoloPartite, lbl_titoloTornei;

    @FXML
    ComboBox cmb_partite,cmb_tornei;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_eliminaPartita.setText(OPZ.traduci("elimina_partita"));
        btn_eliminaTorneo.setText(OPZ.traduci("elimina_torneo"));
        lbl_titoloPartite.setText(OPZ.traduci("partite"));
        lbl_titoloTornei.setText(OPZ.traduci("tornei"));
        aggiornaLista(cmb_partite,true);
        aggiornaLista(cmb_tornei,false);

    }

    public void aggiornaLista(ComboBox cb, boolean partita){
        ArrayList<Integer> lista;
        if(partita) lista = Utili.elencaPartiteNormali();
        else lista = Utili.elencaTornei();
        ObservableList<Integer> items = FXCollections.observableArrayList(lista);
        cb.setItems(items);
        if (!items.isEmpty()) {
            cb.setValue(items.get(0));
        }
    }

    public void EventoEliminaPartita(MouseEvent event) {
        int i = (int) cmb_partite.getSelectionModel().getSelectedItem();
        Utili.adminEliminaPartita(i);
        System.out.println("eliminata partita con codice "+i);
        aggiornaLista(cmb_partite,true);
    }

    public void EventoEliminaTorneo(MouseEvent event) {
        int i = (int) cmb_tornei.getSelectionModel().getSelectedItem();
        Utili.adminEliminaTorneo(i);
        System.out.println("eliminato torneo con codice "+i);
        aggiornaLista(cmb_tornei,false);
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


}
