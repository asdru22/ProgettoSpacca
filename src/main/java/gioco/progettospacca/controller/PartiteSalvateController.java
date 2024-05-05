package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Partita;
import gioco.progettospacca.classi.Utili;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class PartiteSalvateController implements Initializable {
    @FXML
    Button btn_eliminaPartita, btn_eliminaTorneo, btn_back, btn_mostraGiocatori;
    @FXML
    Label lbl_titoloPartite, lbl_titoloTornei, lbl_output, lbl_listaGgiocatori;
    @FXML
    ComboBox cmb_partite, cmb_tornei;
    @FXML
    ImageView imageFocus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_eliminaPartita.setText(OPZ.traduci("elimina_partita"));
        btn_eliminaTorneo.setText(OPZ.traduci("elimina_torneo"));
        btn_mostraGiocatori.setText(OPZ.traduci("mostra_giocatori"));
        lbl_titoloPartite.setText(OPZ.traduci("partite"));
        lbl_titoloTornei.setText(OPZ.traduci("tornei"));
        try {
            aggiornaLista(cmb_partite, true);
            aggiornaLista(cmb_tornei, false);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        btn_back.setOnMouseEntered(e -> {
            if (btn_back.getScene() != null) {
                btn_back.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_back.setOnMouseExited(e -> {
            if (btn_back.getScene() != null) {
                btn_back.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_eliminaPartita.setOnMouseEntered(e -> {
            if (btn_eliminaPartita.getScene() != null) {
                btn_eliminaPartita.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_eliminaPartita.setOnMouseExited(e -> {
            if (btn_eliminaPartita.getScene() != null) {
                btn_eliminaPartita.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_eliminaTorneo.setOnMouseEntered(e -> {
            if (btn_eliminaTorneo.getScene() != null) {
                btn_eliminaTorneo.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_eliminaTorneo.setOnMouseExited(e -> {
            if (btn_eliminaTorneo.getScene() != null) {
                btn_eliminaTorneo.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

    }

    public void aggiornaLista(ComboBox cb, boolean partita) throws FileNotFoundException {
        ArrayList<Integer> lista;
        if (partita) lista = Utili.elencaPartiteNormali();
        else lista = Utili.elencaTornei();
        ObservableList<Integer> items = FXCollections.observableArrayList(lista);
        cb.setItems(items);
        if (!items.isEmpty()) {
            cb.setValue(items.get(0));
        }
    }

    public void EventoEliminaPartita() {
        OPZ.premiBottone();
        Utili.fadeText(lbl_output);
        try {
            int i = (int) cmb_partite.getSelectionModel().getSelectedItem();
            lbl_output.setText(Utili.adminEliminaPartita(i));
            aggiornaLista(cmb_partite, true);
        } catch (NullPointerException e) {
            lbl_output.setText(OPZ.traduci("no_partite"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void EventoEliminaTorneo() {
        OPZ.premiBottone();
        Utili.fadeText(lbl_output);
        try {
            int i = (int) cmb_tornei.getSelectionModel().getSelectedItem();
            lbl_output.setText(Utili.adminEliminaTorneo(i));
            aggiornaLista(cmb_tornei, false);
        } catch (NullPointerException | FileNotFoundException e) {
            lbl_output.setText(OPZ.traduci("no_tornei"));
        }
    }


    public void back() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("PrivilegiAdminView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_back.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
    }
    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
        cmb_partite.setFocusTraversable(false);

        if(imageFocus.isFocusTraversable()){
            imageFocus.setFocusTraversable(false);
            cmb_partite.requestFocus();
            return;
        }

        if(keyEvent.getCode()== KeyCode.RIGHT){
            OPZ.premiFreccia();
            if(cmb_partite.isFocused()){
                btn_eliminaPartita.requestFocus();
            } else if (btn_eliminaPartita.isFocused()) {
                cmb_tornei.requestFocus();
            } else if (cmb_tornei.isFocused()) {
                btn_eliminaTorneo.requestFocus();
            } else if (btn_back.isFocused()) {
                cmb_partite.requestFocus();
            }
        }
        if(keyEvent.getCode()== KeyCode.LEFT){
            OPZ.premiFreccia();
            if(cmb_partite.isFocused()){
                btn_back.requestFocus();
            } else if (btn_eliminaPartita.isFocused()) {
                cmb_partite.requestFocus();
            } else if (cmb_tornei.isFocused()) {
                btn_eliminaPartita.requestFocus();
            } else if (btn_eliminaTorneo.isFocused()) {
                cmb_tornei.requestFocus();
            }
        }
        if(keyEvent.getCode()== KeyCode.ENTER){
            if(btn_back.isFocused()){
                back();
            } else if (btn_eliminaPartita.isFocused()) {
                EventoEliminaPartita();
            } else if (btn_eliminaTorneo.isFocused()) {
                EventoEliminaTorneo();
            } else if (cmb_partite.isFocused()) {
                cmb_partite.show();
            } else if (cmb_tornei.isFocused()) {
                cmb_tornei.show();
            } else if (btn_mostraGiocatori.isFocused()) {
                EventoMostraGiocatori();
            }
        }
        if(keyEvent.getCode()== KeyCode.UP){
            OPZ.premiFreccia();
            if(btn_mostraGiocatori.isFocused()){
                btn_eliminaPartita.requestFocus();
            }
        }
        if(keyEvent.getCode()== KeyCode.DOWN){
            OPZ.premiFreccia();
            if(btn_eliminaPartita.isFocused()){
                btn_mostraGiocatori.requestFocus();
            }
        }
    }

    public void EventoMostraGiocatori() {
        OPZ.premiBottone();
        Utili.fadeText(lbl_output);
        try {
            String out = "";
            int codice = (int) cmb_partite.getSelectionModel().getSelectedItem();
            Partita p = Partita.carica(codice);
            for(Giocatore g : p.getGiocatori()){
                out+="- "+g.getNome()+"\n";
            }
            lbl_listaGgiocatori.setText(out);
        } catch (NullPointerException e) {
            lbl_output.setText(OPZ.traduci("no_tornei"));
        }
    }
}
