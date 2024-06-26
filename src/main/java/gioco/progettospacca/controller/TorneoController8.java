package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Torneo;
import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.*;


public class TorneoController8 implements Initializable {
    @FXML
    Button btn_quarti1, btn_quarti2, btn_quarti3, btn_quarti4, btn_semi1, btn_semi2, btn_finale, btn_home;
    @FXML
    Label lbl_titolo;
    @FXML
    AnchorPane anchPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_titolo.setText(OPZ.traduci("torneo_8"));


        btn_quarti1.setOnMouseEntered(e -> {
            if (btn_quarti1.getScene() != null) {
                btn_quarti1.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_quarti1.setOnMouseExited(e -> {
            if (btn_quarti1.getScene() != null) {
                btn_quarti1.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_quarti2.setOnMouseEntered(e -> {
            if (btn_quarti2.getScene() != null) {
                btn_quarti2.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_quarti2.setOnMouseExited(e -> {
            if (btn_quarti2.getScene() != null) {
                btn_quarti2.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_quarti3.setOnMouseEntered(e -> {
            if (btn_quarti3.getScene() != null) {
                btn_quarti3.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_quarti3.setOnMouseExited(e -> {
            if (btn_quarti3.getScene() != null) {
                btn_quarti3.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_quarti4.setOnMouseEntered(e -> {
            if (btn_quarti4.getScene() != null) {
                btn_quarti4.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_quarti4.setOnMouseExited(e -> {
            if (btn_quarti4.getScene() != null) {
                btn_quarti4.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_semi1.setOnMouseEntered(e -> {
            if (btn_semi1.getScene() != null) {
                btn_semi1.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_semi1.setOnMouseExited(e -> {
            if (btn_semi1.getScene() != null) {
                btn_semi1.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_semi2.setOnMouseEntered(e -> {
            if (btn_semi2.getScene() != null) {
                btn_semi2.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_semi2.setOnMouseExited(e -> {
            if (btn_semi2.getScene() != null) {
                btn_semi2.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_finale.setOnMouseEntered(e -> {
            if (btn_finale.getScene() != null) {
                btn_finale.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_finale.setOnMouseExited(e -> {
            if (btn_finale.getScene() != null) {
                btn_finale.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });


        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        ArrayList<Integer> partite = new ArrayList<>(t.getPartite());

        Utili.bottoneTorneo(btn_quarti1, partite.get(0));
        Utili.bottoneTorneo(btn_quarti2, partite.get(1));
        Utili.bottoneTorneo(btn_quarti3, partite.get(2));
        Utili.bottoneTorneo(btn_quarti4, partite.get(3));

        if (t.getRound() == 0) {

            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));
            btn_semi1.setDisable(true);
            btn_semi1.setText(OPZ.traduci("semifinale"));
            btn_semi2.setDisable(true);
            btn_semi2.setText(OPZ.traduci("semifinale"));

        } else if (t.getRound() == 1) {
            Utili.bottoneTorneo(btn_semi1, partite.get(4));
            Utili.bottoneTorneo(btn_semi2, partite.get(5));
            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));

        } else if (t.getRound() == 2) {
            Utili.bottoneTorneo(btn_semi1, partite.get(4));
            Utili.bottoneTorneo(btn_semi2, partite.get(5));
            Utili.bottoneTorneo(btn_finale, partite.get(6));

        }
    }

    public void giocaPartita() throws IOException {
        OPZ.premiBottone();
        OPZ.playMusica("gioco.mp3");
        Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_home.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
    }

    public void cliccaQuarti1() throws IOException {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(0);
        giocaPartita();
    }

    public void cliccaQuarti2() throws IOException {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(1);
        giocaPartita();
    }

    public void cliccaQuarti3() throws IOException {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(2);
        giocaPartita();
    }

    public void cliccaQuarti4() throws IOException {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(3);
        giocaPartita();
    }

    public void cliccaSemi1() throws IOException {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(4);
        giocaPartita();
    }

    public void cliccaSemi2() throws IOException {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(5);
        giocaPartita();
    }

    public void cliccaFinale() throws IOException {
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(6);
        giocaPartita();
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

        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
    }
    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode()== KeyCode.UP || keyEvent.getCode()== KeyCode.DOWN || keyEvent.getCode()== KeyCode.LEFT || keyEvent.getCode()== KeyCode.RIGHT){
            OPZ.premiFreccia();
        }
        if(keyEvent.getCode()== KeyCode.ENTER){
            if (btn_quarti1.isFocused()) {
                cliccaQuarti1();
            } else if (btn_quarti2.isFocused()) {
                cliccaQuarti2();
            } else if (btn_quarti3.isFocused()) {
                cliccaQuarti3();
            } else if (btn_quarti4.isFocused()) {
                cliccaQuarti4();
            } else if (btn_semi1.isFocused()) {
                cliccaSemi1();
            } else if (btn_semi2.isFocused()) {
                cliccaSemi2();
            } else if (btn_finale.isFocused()) {
                cliccaFinale();
            } else if (btn_home.isFocused()) {
                try {
                    BackToHome();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(keyEvent.getCode()==KeyCode.ESCAPE){
            try {
                BackToHome();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
