package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.MailThread;
import gioco.progettospacca.classi.Partita;
import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static gioco.progettospacca.classi.Utili.checkBox;
import static gioco.progettospacca.controller.Main.OPZ;

public class CreaController implements Initializable {

    @FXML
    private Label lbl_nomi_giocatori;
    @FXML
    private Label lbl_codice_partita;
    @FXML
    private Button btn_backHome;
    @FXML
    private TextField txt_code;
    @FXML
    private Button btn_crea;
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
    private TextField txt_email1;
    @FXML
    private TextField txt_email2;
    @FXML
    private TextField txt_email3;
    @FXML
    private TextField txt_email4;
    @FXML
    private TextField txt_email5;
    @FXML
    private CheckBox chk_gioc1;
    @FXML
    private CheckBox chk_gioc2;
    @FXML
    private CheckBox chk_gioc3;
    @FXML
    private CheckBox chk_gioc4;
    @FXML
    private CheckBox chk_gioc5;
    @FXML
    private Label lbl_errore;
    private int checkBoxSelezionati = 0;

    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_backHome.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
    }


    public void EventoCreaCodicePartita() {
        OPZ.premiBottone();
        try {
            int id = Utili.intCasuale(10000, 99999);

            List<Giocatore> g = new ArrayList<>();

            Utili.controllaNome(txt_gioc1.getText(), id, chk_gioc1.isSelected(), g);
            Utili.controllaNome(txt_gioc2.getText(), id, chk_gioc2.isSelected(), g);
            Utili.controllaNome(txt_gioc3.getText(), id, chk_gioc3.isSelected(), g);
            Utili.controllaNome(txt_gioc4.getText(), id, chk_gioc4.isSelected(), g);
            Utili.controllaNome(txt_gioc5.getText(), id, chk_gioc5.isSelected(), g);

            if (g.size() < 2) {
                Utili.fadeText(lbl_errore);
                lbl_errore.setText(OPZ.traduci("errore_crea_partita"));
                return;
            }

            txt_code.setText(String.valueOf(id));

            Giocatore[] giocatori = g.toArray(new Giocatore[g.size()]);

            //per invio mail relativi alla creazione della partita
            if (!txt_email1.getText().isEmpty()) {
                giocatori[0].setEmail(txt_email1.getText());
            }
            if (!txt_email2.getText().isEmpty()) {
                giocatori[1].setEmail(txt_email2.getText());
            }
            if (!txt_email3.getText().isEmpty()) {
                giocatori[2].setEmail(txt_email3.getText());
            }
            if (!txt_email4.getText().isEmpty()) {
                giocatori[3].setEmail(txt_email4.getText());
            }
            if (!txt_email5.getText().isEmpty()) {
                giocatori[4].setEmail(txt_email5.getText());
            }
            //creo un thread per ogni mail da inviare
            for (Giocatore giocatore : giocatori) {
                if (!giocatore.getEmail().isEmpty() && giocatore.getEmail() != "") {
                    MailThread thread = new MailThread(giocatore.getEmail(), "Iscrizione partita spacca", "Giocatore: " + giocatore + "\nCodice partita: " + txt_code.getText());
                    thread.start();
                }
            }

            new Partita(id, giocatori, 0);

        } catch (Exception e) {
            System.err.println(e);
        }
        lbl_errore.setText(OPZ.traduci("partita_creata"));
        Utili.fadeText(lbl_errore);
    }

    public void checkBox1() {
        checkBoxSelezionati = checkBox(txt_gioc1, chk_gioc1, checkBoxSelezionati);
        txt_email1.setDisable(!txt_email1.isDisable());
        txt_email1.setText("");
        txt_gioc2.requestFocus();
    }


    public void checkBox2() {
        checkBoxSelezionati = checkBox(txt_gioc2, chk_gioc2, checkBoxSelezionati);
        txt_email2.setDisable(!txt_email2.isDisable());
        txt_email2.setText("");
        txt_gioc3.requestFocus();
    }

    public void checkBox3() {
        checkBoxSelezionati = checkBox(txt_gioc3, chk_gioc3, checkBoxSelezionati);
        txt_email3.setDisable(!txt_email3.isDisable());
        txt_email3.setText("");
        txt_gioc4.requestFocus();
    }

    public void checkBox4() {
        checkBoxSelezionati = checkBox(txt_gioc4, chk_gioc4, checkBoxSelezionati);
        txt_email4.setDisable(!txt_email4.isDisable());
        txt_email4.setText("");
        txt_gioc5.requestFocus();
    }

    public void checkBox5() {
        checkBoxSelezionati = checkBox(txt_gioc5, chk_gioc5, checkBoxSelezionati);
        txt_email5.setDisable(!txt_email5.isDisable());
        txt_email5.setText("");
        btn_crea.requestFocus();
    }


    public void keyEvent(KeyEvent keyEvent) {
        txt_gioc1.setFocusTraversable(false);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (txt_gioc5.isFocused()) {
                btn_crea.requestFocus();
            } else if (txt_gioc1.isFocused()) {
                txt_gioc2.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                txt_gioc3.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                txt_gioc4.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                txt_gioc5.requestFocus();
            } else if (btn_crea.isFocused()) {
                EventoCreaCodicePartita();
            } else if (btn_backHome.isFocused()) {
                try {
                    BackToHome();
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


        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (txt_gioc1.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc2.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc3.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc4.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc5.requestFocus();
            } else if (txt_gioc5.isFocused()) {
                OPZ.premiFreccia();
                btn_crea.requestFocus();
            } else if (btn_backHome.isFocused()) {
            } else if (txt_code.isFocused()) {
                OPZ.premiFreccia();
                btn_backHome.requestFocus();
            } else if (btn_crea.isFocused()) {
                OPZ.premiFreccia();
                txt_code.requestFocus();
            }
        }

        if (keyEvent.getCode() == KeyCode.UP) {
            if (txt_gioc1.isFocused()) {
            } else if (txt_gioc5.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc4.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc3.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc2.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc1.requestFocus();
            } else if (btn_crea.isFocused()) {
                OPZ.premiFreccia();
                txt_gioc5.requestFocus();
            } else if (txt_code.isFocused()) {
                OPZ.premiFreccia();
                btn_crea.requestFocus();
            } else if (btn_backHome.isFocused()) {
                OPZ.premiFreccia();
                txt_code.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.TAB) {
            if (txt_gioc1.isFocused()) {
                chk_gioc1.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                chk_gioc2.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                chk_gioc3.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                chk_gioc4.requestFocus();
            } else if (txt_gioc5.isFocused()) {
                chk_gioc5.requestFocus();
            } else if (chk_gioc1.isFocused()) {
                txt_gioc1.requestFocus();
            } else if (chk_gioc2.isFocused()) {
                txt_gioc2.requestFocus();
            } else if (chk_gioc3.isFocused()) {
                txt_gioc3.requestFocus();
            } else if (chk_gioc4.isFocused()) {
                txt_gioc4.requestFocus();
            } else if (chk_gioc5.isFocused()) {
                txt_gioc5.requestFocus();
            }
        }

    }

    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (chk_gioc1.isFocused()) {
                checkBox1();
            } else if (chk_gioc2.isFocused()) {
                checkBox2();
            } else if (chk_gioc3.isFocused()) {
                checkBox3();
            } else if (chk_gioc4.isFocused()) {
                checkBox4();
            } else if (chk_gioc5.isFocused()) {
                checkBox5();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_crea.setOnMouseEntered(e -> {
            if (btn_crea.getScene() != null) {
                btn_crea.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_crea.setOnMouseExited(e -> {
            if (btn_crea.getScene() != null) {
                btn_crea.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_backHome.setOnMouseEntered(e -> {
            if (btn_backHome.getScene() != null) {
                btn_backHome.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_backHome.setOnMouseExited(e -> {
            if (btn_backHome.getScene() != null) {
                btn_backHome.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_crea.setText(OPZ.traduci("crea_partita"));
        btn_backHome.setText(OPZ.traduci("torna_alla_home"));
        lbl_nomi_giocatori.setText(OPZ.traduci("nomi_giocatori"));
        lbl_codice_partita.setText(OPZ.traduci("codice_partita"));
    }
}