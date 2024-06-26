package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Partita;
import gioco.progettospacca.classi.Utili;
import gioco.progettospacca.classi.ValoriGioca;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.CODICE_GLOBALE_PARTITA;
import static gioco.progettospacca.controller.Main.OPZ;

public class GiocaController implements Initializable {

    @FXML
    private TextField txt_gioc1;
    @FXML
    private TextField txt_gioc2;
    @FXML
    private TextField txt_gioc3;
    @FXML
    private TextField txt_gioc4;
    @FXML
    private TextField txt_gioc5;
    @FXML
    private TextField txt_cod;
    @FXML
    private Label lbl_nomi;
    @FXML
    private Label lbl_codice;
    @FXML
    private Button btn_entra;
    @FXML
    private Button btn_back;
    @FXML
    private Label lbl_errore;

    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_back.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

    }

    public void entraInPartita() throws IOException {
        OPZ.premiBottone();
        if (!Objects.equals(txt_cod.getText(), "")) {
            int codice = Utili.leggiInt(txt_cod);
            if (Utili.esistePartita(codice, true)) {
                // vai al controllo giocatori / codice
                partitaEsiste(codice);
            } else {
                Utili.fadeText(lbl_errore);
                lbl_errore.setText(OPZ.traduci("partita_non_trovata"));
            }
        }
        if(txt_cod.getText()==null || txt_cod.getText().isEmpty()){
            Utili.fadeText(lbl_errore);
            lbl_errore.setText(OPZ.traduci("inserisci_codice"));
        }
    }

    private void partitaEsiste(int codice) throws IOException {
        Partita p = Partita.carica(codice);
        int n = p.getGiocatori().length;

        ArrayList<ValoriGioca> temp = new ArrayList<>();
        if (!Objects.equals(txt_gioc1.getText(), "")) temp.add(new ValoriGioca(txt_gioc1, txt_cod));
        if (!Objects.equals(txt_gioc2.getText(), "")) temp.add(new ValoriGioca(txt_gioc2, txt_cod));
        if (!Objects.equals(txt_gioc3.getText(), "")) temp.add(new ValoriGioca(txt_gioc3, txt_cod));
        if (!Objects.equals(txt_gioc4.getText(), "")) temp.add(new ValoriGioca(txt_gioc4, txt_cod));
        if (!Objects.equals(txt_gioc5.getText(), "")) temp.add(new ValoriGioca(txt_gioc5, txt_cod));

        boolean inizia_partita = (n == temp.size());
        // inizia la partita se n = giocatori previsiti
        if (inizia_partita) {
            for (ValoriGioca v : temp) {
                inizia_partita = v.controlloGiocatore();
                if (!inizia_partita) break;
            }
        }
        System.out.println("inizia parti"+inizia_partita);
        if (inizia_partita && (n >= 2)) {
            CODICE_GLOBALE_PARTITA = Utili.leggiInt(txt_cod);
            OPZ.playMusica("gioco.mp3");
            Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));

            // Ottieni la finestra corrente
            Stage currentStage = (Stage) btn_entra.getScene().getWindow();

            // Ottieni la scena corrente
            Scene currentScene = currentStage.getScene();

            // Imposta la nuova radice della scena
            currentScene.setRoot(root);
        } else {
            Utili.fadeText(lbl_errore);
            lbl_errore.setText(OPZ.traduci("valori_invalidi"));
        }

    }

    public void keyEvent(KeyEvent keyEvent) {
        txt_gioc1.setFocusTraversable(false);
        if (keyEvent.getCode() == KeyCode.DOWN) {
            OPZ.premiFreccia();
            if (txt_gioc1.isFocused()) {
                txt_gioc2.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                txt_gioc3.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                txt_gioc4.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                txt_gioc5.requestFocus();
            } else if (txt_gioc5.isFocused()) {
                txt_cod.requestFocus();
            } else if (txt_cod.isFocused()) {
                btn_entra.requestFocus();
            } else if (btn_back.isFocused()) {
                txt_gioc1.requestFocus();
            }

        }
        if (keyEvent.getCode() == KeyCode.UP) {
            OPZ.premiFreccia();
            if (btn_entra.isFocused()) {
                txt_cod.requestFocus();
            } else if (txt_cod.isFocused()) {
                txt_gioc5.requestFocus();
            } else if (txt_gioc5.isFocused()) {
                txt_gioc4.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                txt_gioc3.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                txt_gioc2.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                txt_gioc1.requestFocus();
            } else if (txt_gioc1.isFocused()) {
                btn_back.requestFocus();
            }
        }

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (txt_gioc1.isFocused()) {
                txt_gioc2.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                txt_gioc3.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                txt_gioc4.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                txt_gioc5.requestFocus();
            } else if (txt_gioc5.isFocused()) {
                txt_cod.requestFocus();
            } else if (txt_cod.isFocused()) {
                btn_entra.requestFocus();
            } else if (btn_entra.isFocused()) {
                try {
                    OPZ.premiBottone();
                    entraInPartita();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            try {
                BackToHome();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        btn_entra.setOnMouseEntered(e -> {
            if (btn_entra.getScene() != null) {
                btn_entra.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_entra.setOnMouseExited(e -> {
            if (btn_entra.getScene() != null) {
                btn_entra.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
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

        lbl_nomi.setText(OPZ.traduci("nomi"));
        lbl_codice.setText(OPZ.traduci("inserisci_codice"));
        btn_entra.setText(OPZ.traduci("entra"));
    }

}

