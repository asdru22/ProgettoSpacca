package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Partita;
import gioco.progettospacca.classi.Utili;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreaController {
    public void BackToHome() throws IOException {
        Stage currentStage = (Stage) btn_backHome.getScene().getWindow();

        // Carica la nuova finestra
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Chiudi la finestra corrente
        currentStage.close();

        // Imposta la nuova finestra
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
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
    private CheckBox chk_gioc1;

    @FXML
    private CheckBox chk_gioc2;
    @FXML
    private CheckBox chk_gioc3;

    @FXML
    private CheckBox chk_gioc4;
    @FXML
    private CheckBox chk_gioc5;

    TextField [] txt = {txt_gioc1,txt_gioc2,txt_gioc3,txt_gioc4,txt_gioc5};
    public void EventoCreaCodicePartita() {
        try {
            Giocatore temp;

            int id = Utili.intCasuale(10000, 99999);

            txt_code.setText(String.valueOf(id));

            List<Giocatore> g = new ArrayList<>();

            temp = Utili.controllaNome(txt_gioc1.getText(), id, chk_gioc1.isSelected());
            if (temp != null) {
                g.add(temp);
            }
            temp = Utili.controllaNome(txt_gioc2.getText(), id, chk_gioc2.isSelected());
            if (temp != null) {
                g.add(temp);
            }

            temp = Utili.controllaNome(txt_gioc3.getText(), id, chk_gioc3.isSelected());
            if (temp != null) {
                g.add(temp);
            }

            temp = Utili.controllaNome(txt_gioc4.getText(), id, chk_gioc4.isSelected());
            if (temp != null) {
                g.add(temp);
            }

            temp = Utili.controllaNome(txt_gioc5.getText(), id, chk_gioc5.isSelected());
            if (temp != null) {
                g.add(temp);
            }

            Giocatore[] giocatori = g.toArray(new Giocatore[g.size()]);

            Partita p = new Partita(id, giocatori, 0);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void checkBox1(MouseEvent mouseEvent) {
        if (chk_gioc1.isSelected()) {
            txt_gioc1.setDisable(true);
            txt_gioc1.setText("bot1");
        } else {
            txt_gioc1.setDisable(false);
            txt_gioc1.setText("");
        }
        txt_gioc2.requestFocus();
    }


    public void checkBox2(MouseEvent mouseEvent) {
        if (chk_gioc2.isSelected()) {
            txt_gioc2.setDisable(true);
            txt_gioc2.setText("bot2");
        } else {
            txt_gioc2.setDisable(false);
            txt_gioc2.setText("");
        }
        txt_gioc3.requestFocus();
    }

    public void checkBox3(MouseEvent mouseEvent) {
        if (chk_gioc3.isSelected()) {
            txt_gioc3.setDisable(true);
            txt_gioc3.setText("bot3");
        } else {
            txt_gioc3.setDisable(false);
            txt_gioc3.setText("");
        }
        txt_gioc4.requestFocus();
    }

    public void checkBox4(MouseEvent mouseEvent) {
        if (chk_gioc4.isSelected()) {
            txt_gioc4.setDisable(true);
            txt_gioc4.setText("bot4");
        } else {
            txt_gioc4.setDisable(false);
            txt_gioc4.setText("");
        }
        txt_gioc5.requestFocus();
    }

    public void checkBox5(MouseEvent mouseEvent) {
        if (chk_gioc5.isSelected()) {
            txt_gioc5.setDisable(true);
            txt_gioc5.setText("bot5");
            txt_gioc5.setText("");
        } else {
            txt_gioc5.setDisable(false);
            txt_gioc5.setText("");
        }
        btn_crea.requestFocus();
    }


    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER && btn_crea.isFocused()) {
            EventoCreaCodicePartita();
        }

        if ((txt_gioc1.isFocused() || txt_gioc2.isFocused() || txt_gioc3.isFocused() || txt_gioc4.isFocused() || txt_gioc5.isFocused())) {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (txt_gioc5.isFocused()) {
                    btn_crea.requestFocus();
                } else {
                    if (txt_gioc1.isFocused()) {
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
        }
        if(keyEvent.getCode() == KeyCode.ESCAPE){
            BackToHome();
        }

        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (txt_gioc5.isFocused()) {
                System.out.println("sei già nell'ultima casella");
            }
            else {
                if (txt_gioc1.isFocused()) {
                    txt_gioc2.requestFocus();
                } else if (txt_gioc2.isFocused()) {
                    txt_gioc3.requestFocus();
                } else if (txt_gioc3.isFocused()) {
                    txt_gioc4.requestFocus();
                } else if (txt_gioc4.isFocused()) {
                    txt_gioc5.requestFocus();
                }
            }
            if (chk_gioc5.isFocused()) {
                System.out.println("sei già nell'ultima casella");
            }
            else {
                if (chk_gioc1.isFocused()) {
                    txt_gioc2.requestFocus();
                } else if (chk_gioc2.isFocused()) {
                    chk_gioc3.requestFocus();
                } else if (chk_gioc3.isFocused()) {
                    chk_gioc4.requestFocus();
                } else if (chk_gioc4.isFocused()) {
                    chk_gioc5.requestFocus();
                }
            }
        }


        if (keyEvent.getCode() == KeyCode.UP) {
            if (txt_gioc1.isFocused()) {
                System.out.println("sei già nella prima casella");
            } else {
                if (txt_gioc5.isFocused()) {
                    txt_gioc4.requestFocus();
                } else if (txt_gioc4.isFocused()) {
                    txt_gioc3.requestFocus();
                } else if (txt_gioc3.isFocused()) {
                    txt_gioc2.requestFocus();
                } else if (txt_gioc2.isFocused()) {
                    txt_gioc1.requestFocus();
                }
            }
            if (chk_gioc1.isFocused()) {
                System.out.println("sei già nella prima casella");
            }
            else {
                if (chk_gioc5.isFocused()) {
                    txt_gioc4.requestFocus();
                } else if (chk_gioc4.isFocused()) {
                    chk_gioc3.requestFocus();
                } else if (chk_gioc3.isFocused()) {
                    chk_gioc2.requestFocus();
                } else if (chk_gioc2.isFocused()) {
                    chk_gioc1.requestFocus();
                }
            }
        }

    }


}