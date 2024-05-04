package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.MailThread;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import gioco.progettospacca.classi.Utili;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class ModificaGiocatoreController implements Initializable {
    @FXML
    Label lbl_caNome, lbl_email;
    @FXML
    private ComboBox cmb_giocatori;
    @FXML
    private TextField txt_nuovoNome, txt_email, txt_nuovoGiocatore;
    @FXML
    private Button btn_modifica, btn_elimina, btn_nuovoGiocatore;
    @FXML
    private Button btn_back;
    @FXML
    private Label lbl_veNome;
    @FXML
    private Label lbl_nuNome;
    @FXML
    private Label lbl_output;
    @FXML
    private ImageView imageFocus;

    public void modificaNome() {
        OPZ.premiBottone();
        EventoCambioNome();
    }

    public void creaGiocatore() {
        OPZ.premiBottone();
        Utili.fadeText(lbl_output);
        String s = txt_nuovoGiocatore.getText();
        if (!Objects.equals(s, "")) {
            if (!Utili.esisteGiocatore(s)) {
                Giocatore g = new Giocatore(s);
                g.salva();
                lbl_output.setText(OPZ.traduci("giocatore_creato"));
                aggiornaLista(cmb_giocatori);
            } else {
                lbl_output.setText(OPZ.traduci("giocatore_esistente"));
            }
        } else lbl_output.setText(OPZ.traduci("inserisci_nome"));
    }

    public void aggiornaLista(ComboBox cb) {
        ArrayList<String> lista;
        lista = Utili.elencaGiocatori();
        ObservableList<String> items = FXCollections.observableArrayList(lista);
        cb.setItems(items);
        if (!items.isEmpty()) {
            cb.setValue(items.get(0));
        }
        txt_email.setText("");
        txt_nuovoNome.setText("");

    }

    public void EventoCambioNome() {
        String out = "";
        String vecchio = String.valueOf((cmb_giocatori.getSelectionModel().getSelectedItem()));
        Giocatore g = Giocatore.carica(vecchio);
        String nuovo = String.valueOf((txt_nuovoNome.getText()));
        String email = String.valueOf((txt_email.getText()));

        if (Objects.equals(nuovo, "")) out = OPZ.traduci("inserisci_nome");

        if (!Objects.equals(nuovo, "")) {
            out = Utili.cambiaNomeGiocatore(vecchio, nuovo);
            aggiornaLista(cmb_giocatori);
        }
        if (!Objects.equals(email, "")) {
            if (Objects.equals(nuovo, "")) {
                out = Utili.cambiaEmail(vecchio, email);
            } else {
                out = Utili.cambiaEmail(nuovo, email);
            }
        }
        Utili.fadeText(lbl_output);
        lbl_output.setText(out);

        if (!g.getEmail().isEmpty() && g.getEmail() != "") {
            MailThread thread = new MailThread(g.getEmail(), "Modifiche profilo giocatore spacca", "Nome giocatore modificato\nVecchio nome: " + g.getNome() + "\nNuovo nome: " + nuovo);
            thread.start();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_veNome.setText(OPZ.traduci("vecchio_nome"));
        lbl_nuNome.setText(OPZ.traduci("nuovo_nome"));
        lbl_caNome.setText(OPZ.traduci("cambia_nome"));
        lbl_email.setText(OPZ.traduci("cambia_email"));

        btn_modifica.setText(OPZ.traduci("modifica"));
        btn_elimina.setText(OPZ.traduci("elimina"));
        btn_nuovoGiocatore.setText(OPZ.traduci("aggiungi_giocatore"));

        aggiornaLista(cmb_giocatori);

    }

    public void eliminaGiocatore() {
        OPZ.premiBottone();
        String nome = String.valueOf((cmb_giocatori.getSelectionModel().getSelectedItem()));
        Utili.fadeText(lbl_output);
        if (!Objects.equals(nome, "")) {
            Giocatore g = Giocatore.carica(nome);
            if (g.partiteInSospeso()) {
                lbl_output.setText(OPZ.traduci("partite_in_sospeso"));
            } else {
                if (g.isBot() || Objects.equals(g.getNome(), "admin")) {
                    lbl_output.setText(OPZ.traduci("impossibile_eliminare"));
                } else {
                    g.elimina();
                    lbl_output.setText(OPZ.traduci("giocatore_eliminato"));
                    aggiornaLista(cmb_giocatori);
                }
            }
        }
    }
    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
        }
        cmb_giocatori.setFocusTraversable(false);

        if(imageFocus.isFocusTraversable()){
            imageFocus.setFocusTraversable(false);
            cmb_giocatori.requestFocus();
            return;
        }
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            if(cmb_giocatori.isFocused()){
                txt_nuovoNome.requestFocus();
            } else if (btn_elimina.isFocused()) {
                btn_modifica.requestFocus();
            } else if (btn_nuovoGiocatore.isFocused()) {
                txt_nuovoGiocatore.requestFocus();
            } else if (btn_back.isFocused()) {
                cmb_giocatori.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.LEFT){
            if(txt_nuovoNome.isFocused()){
                cmb_giocatori.requestFocus();
            } else if (btn_modifica.isFocused()) {
                btn_elimina.requestFocus();
            } else if (txt_nuovoGiocatore.isFocused()) {
                btn_nuovoGiocatore.requestFocus();
            } else if (cmb_giocatori.isFocused()) {
                btn_back.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.DOWN){
            if(txt_nuovoNome.isFocused()){
                txt_email.requestFocus();
            } else if (txt_email.isFocused()) {
                btn_modifica.requestFocus();
            } else if (btn_modifica.isFocused()) {
                txt_nuovoGiocatore.requestFocus();
            } else if (btn_elimina.isFocused()) {
                btn_nuovoGiocatore.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.UP){
            if(btn_nuovoGiocatore.isFocused()){
                btn_elimina.requestFocus();
            } else if (txt_nuovoGiocatore.isFocused()) {
                btn_modifica.requestFocus();
            } else if (btn_modifica.isFocused()||btn_elimina.isFocused()) {
                txt_email.requestFocus();
            } else if (txt_email.isFocused()) {
                txt_nuovoNome.requestFocus();
            }
        }
        if(keyEvent.getCode() == KeyCode.ENTER){
            if(btn_modifica.isFocused()){
                modificaNome();
            } else if (btn_elimina.isFocused()) {
                eliminaGiocatore();
            } else if (btn_nuovoGiocatore.isFocused()) {
                creaGiocatore();
            } else if (cmb_giocatori.isFocused()) {
                cmb_giocatori.show();
            } else if(btn_back.isFocused()){
                back();
            }
        }
    }
}
