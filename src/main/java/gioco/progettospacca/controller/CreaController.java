package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Partita;
import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static gioco.progettospacca.classi.Utili.checkBox;
import static gioco.progettospacca.controller.Main.OPZ;

public class CreaController implements Initializable {
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
        currentStage.setTitle(OPZ.traduci("spacca"));
    }

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
    private int checkBoxSelezionati;

    public void EventoCreaCodicePartita() {
        OPZ.premiBottone();
        try {
            Giocatore temp;

            int id = Utili.intCasuale(10000, 99999);

            txt_code.setText(String.valueOf(id));

            List<Giocatore> g = new ArrayList<>();

            temp = Utili.controllaNome(txt_gioc1.getText(), id, chk_gioc1.isSelected(),g);
            if (temp != null) {
                g.add(temp);
            }
            temp = Utili.controllaNome(txt_gioc2.getText(), id, chk_gioc2.isSelected(),g);
            if (temp != null) {
                g.add(temp);
            }

            temp = Utili.controllaNome(txt_gioc3.getText(), id, chk_gioc3.isSelected(),g);
            if (temp != null) {
                g.add(temp);
            }

            temp = Utili.controllaNome(txt_gioc4.getText(), id, chk_gioc4.isSelected(),g);
            if (temp != null) {
                g.add(temp);
            }

            temp = Utili.controllaNome(txt_gioc5.getText(), id, chk_gioc5.isSelected(),g);
            if (temp != null) {
                g.add(temp);
            }

            Giocatore[] giocatori = g.toArray(new Giocatore[g.size()]);
            if(!txt_email1.getText().isEmpty()){
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
            /*
            for(int i =0; i<giocatori.length; i++) {
                System.out.println(giocatori[i].getEmail());
            }
            */
            Partita p = new Partita(id, giocatori, 0);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void checkBox1(MouseEvent mouseEvent) {
        checkBox(txt_gioc1,chk_gioc1,checkBoxSelezionati);
        txt_gioc2.requestFocus();
    }


    public void checkBox2(MouseEvent mouseEvent) {
        checkBox(txt_gioc2,chk_gioc2,checkBoxSelezionati);
        txt_gioc3.requestFocus();
    }

    public void checkBox3(MouseEvent mouseEvent) {
        checkBox(txt_gioc3,chk_gioc3,checkBoxSelezionati);
        txt_gioc4.requestFocus();
    }

    public void checkBox4(MouseEvent mouseEvent) {
        checkBox(txt_gioc4,chk_gioc4,checkBoxSelezionati);
        txt_gioc5.requestFocus();
    }

    public void checkBox5(MouseEvent mouseEvent) {
        checkBox(txt_gioc5,chk_gioc5,checkBoxSelezionati);
        btn_crea.requestFocus();
    }


    public void keyEvent(KeyEvent keyEvent) throws IOException {
        txt_gioc1.setFocusTraversable(false);
        if (keyEvent.getCode() == KeyCode.ENTER && btn_crea.isFocused()) {
            EventoCreaCodicePartita();
        }
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_backHome.isFocused()) || keyEvent.getCode() == KeyCode.ESCAPE) {
            BackToHome();
        }

        if ((txt_gioc1.isFocused() || txt_gioc2.isFocused() || txt_gioc3.isFocused() || txt_gioc4.isFocused() || txt_gioc5.isFocused())) {
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
                }
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
            } else if(txt_gioc5.isFocused()){
                OPZ.premiFreccia();
                btn_crea.requestFocus();
            } else if (btn_backHome.isFocused()) {
                System.out.println("sei già in basso");
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
                System.out.println("sei già nella prima casella");
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
            }else if (btn_crea.isFocused()) {
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