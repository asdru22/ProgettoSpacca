package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import gioco.progettospacca.classi.Utili;
import gioco.progettospacca.classi.ValoriTorneo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.classi.Utili.checkBox;
import static gioco.progettospacca.controller.Main.OPZ;

public class Torneo8giocatoriController implements Initializable {
    @FXML
    Label lbl_nomi_giocatori;
    @FXML
    Button btn_crea;
    @FXML
    Label lbl_codice, lbl_bot;
    @FXML
    Button btn_home,btn_back;
    @FXML
    TextField txt_gioc1, txt_gioc2, txt_gioc3, txt_gioc4, txt_gioc5, txt_gioc6, txt_gioc7, txt_gioc8, txt_codice;
    @FXML
    CheckBox chk_gioc1, chk_gioc2, chk_gioc3, chk_gioc4, chk_gioc5, chk_gioc6, chk_gioc7, chk_gioc8;
    private int checkBoxSelezionati = 0;

    public void checkBox1() {
        checkBoxSelezionati = checkBox(txt_gioc1, chk_gioc1, checkBoxSelezionati);
        txt_gioc2.requestFocus();
    }

    public void checkBox2() {
        checkBoxSelezionati = checkBox(txt_gioc2, chk_gioc2, checkBoxSelezionati);
        txt_gioc3.requestFocus();
    }

    public void checkBox3() {
        checkBoxSelezionati = checkBox(txt_gioc3, chk_gioc3, checkBoxSelezionati);
        txt_gioc4.requestFocus();
    }

    public void checkBox4() {
        checkBoxSelezionati = checkBox(txt_gioc4, chk_gioc4, checkBoxSelezionati);
        txt_gioc5.requestFocus();
    }

    public void checkBox5() {
        checkBoxSelezionati = checkBox(txt_gioc5, chk_gioc5, checkBoxSelezionati);
        txt_gioc6.requestFocus();
    }

    public void checkBox6() {
        checkBoxSelezionati = checkBox(txt_gioc6, chk_gioc6, checkBoxSelezionati);
        txt_gioc7.requestFocus();
    }

    public void checkBox7() {
        checkBoxSelezionati = checkBox(txt_gioc7, chk_gioc7, checkBoxSelezionati);
        txt_gioc8.requestFocus();
    }

    public void checkBox8() {
        checkBoxSelezionati = checkBox(txt_gioc8, chk_gioc8, checkBoxSelezionati);
        btn_crea.requestFocus();
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
        btn_home.setOnMouseEntered(e -> {
            if (btn_home.getScene() != null) {
                btn_home.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_home.setOnMouseExited(e -> {
            if (btn_home.getScene() != null) {
                btn_home.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        lbl_nomi_giocatori.setText(OPZ.traduci("nomi_giocatori"));
        btn_crea.setText(OPZ.traduci("crea_torneo"));
        lbl_codice.setText(OPZ.traduci("codice_torneo"));
        btn_home.setText(OPZ.traduci("torna_alla_home"));

    }

    public int creaTorneo() {
        ArrayList<ValoriTorneo> tf = new ArrayList<>();
        tf.add(new ValoriTorneo(txt_gioc1, chk_gioc1));
        tf.add(new ValoriTorneo(txt_gioc2, chk_gioc2));
        tf.add(new ValoriTorneo(txt_gioc3, chk_gioc3));
        tf.add(new ValoriTorneo(txt_gioc4, chk_gioc4));
        tf.add(new ValoriTorneo(txt_gioc5, chk_gioc5));
        tf.add(new ValoriTorneo(txt_gioc6, chk_gioc6));
        tf.add(new ValoriTorneo(txt_gioc7, chk_gioc7));
        tf.add(new ValoriTorneo(txt_gioc8, chk_gioc8));
        return Torneo.controlloLabel(tf, 8, txt_codice);
    }

    public void cliccaCreaTorneo() {
        int n = creaTorneo();
        Utili.giocatoriMancanti(n, lbl_bot);
    }

    public void BackToHome() throws IOException {
        OPZ.premiBottone();
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_home.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
    }
    public void back() {
        OPZ.premiBottone();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("creaTorneo.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_home.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("spacca"));
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
    }

    public void keyEvent(KeyEvent keyEvent) {
        txt_gioc1.setFocusTraversable(false);
        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (txt_gioc1.isFocused()) {
                txt_gioc2.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                txt_gioc3.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                txt_gioc4.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                txt_gioc5.requestFocus();
            } else if (txt_gioc5.isFocused()) {
                txt_gioc6.requestFocus();
            } else if (txt_gioc6.isFocused()) {
                txt_gioc7.requestFocus();
            } else if (txt_gioc7.isFocused()) {
                txt_gioc8.requestFocus();
            } else if (txt_gioc8.isFocused()) {
                btn_crea.requestFocus();
            } else if (btn_crea.isFocused()) {
                txt_codice.requestFocus();
            } else if (txt_codice.isFocused()) {
                btn_home.requestFocus();
            } else if (btn_home.isFocused()) {
            } else if (btn_back.isFocused()) {
                txt_gioc1.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.UP) {
            if (txt_gioc1.isFocused()) {
                btn_back.requestFocus();
            } else if (txt_gioc2.isFocused()) {
                txt_gioc1.requestFocus();
            } else if (txt_gioc3.isFocused()) {
                txt_gioc2.requestFocus();
            } else if (txt_gioc4.isFocused()) {
                txt_gioc3.requestFocus();
            } else if (txt_gioc5.isFocused()) {
                txt_gioc4.requestFocus();
            } else if (txt_gioc6.isFocused()) {
                txt_gioc5.requestFocus();
            } else if (txt_gioc7.isFocused()) {
                txt_gioc6.requestFocus();
            } else if (txt_gioc8.isFocused()) {
                txt_gioc7.requestFocus();
            } else if (btn_crea.isFocused()) {
                txt_gioc8.requestFocus();
            } else if (txt_codice.isFocused()) {
                btn_crea.requestFocus();
            } else if (btn_home.isFocused()) {
                txt_codice.requestFocus();
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
                txt_gioc6.requestFocus();
            } else if (txt_gioc6.isFocused()) {
                txt_gioc7.requestFocus();
            } else if (txt_gioc7.isFocused()) {
                txt_gioc8.requestFocus();
            } else if (txt_gioc8.isFocused()) {
                btn_crea.requestFocus();
            } else if (btn_crea.isFocused()) {
                cliccaCreaTorneo();
                txt_codice.requestFocus();
            } else if (btn_home.isFocused()) {
                try {
                    BackToHome();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if (btn_back.isFocused()) {
                back();
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
            } else if (txt_gioc6.isFocused()) {
                chk_gioc6.requestFocus();
            } else if (txt_gioc7.isFocused()) {
                chk_gioc7.requestFocus();
            } else if (txt_gioc8.isFocused()) {
                chk_gioc8.requestFocus();
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
            } else if (chk_gioc6.isFocused()) {
                txt_gioc6.requestFocus();
            } else if (chk_gioc7.isFocused()) {
                txt_gioc7.requestFocus();
            } else if (chk_gioc8.isFocused()) {
                txt_gioc8.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back();
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
            } else if (chk_gioc6.isFocused()) {
                checkBox6();
            } else if (chk_gioc7.isFocused()) {
                checkBox7();
            } else if (chk_gioc8.isFocused()) {
                checkBox8();
            }
        }
    }
}
