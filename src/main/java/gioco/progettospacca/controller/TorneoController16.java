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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.*;

public class TorneoController16 implements Initializable {
    @FXML
    Button btn_quarti1, btn_quarti2, btn_quarti3, btn_quarti4, btn_semi1, btn_semi2,
            btn_ottavi1, btn_ottavi2, btn_ottavi3, btn_ottavi4, btn_ottavi5, btn_ottavi6, btn_ottavi7, btn_ottavi8, btn_finale, btn_home;
    @FXML
    Label lbl_titolo;

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
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_titolo.setText(OPZ.traduci("torneo_16"));


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
        btn_ottavi1.setOnMouseEntered(e -> {
            if (btn_quarti1.getScene() != null) {
                btn_quarti1.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_ottavi1.setOnMouseExited(e -> {
            if (btn_quarti1.getScene() != null) {
                btn_quarti1.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_ottavi2.setOnMouseEntered(e -> {
            if (btn_ottavi2.getScene() != null) {
                btn_ottavi2.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_ottavi2.setOnMouseExited(e -> {
            if (btn_ottavi2.getScene() != null) {
                btn_ottavi2.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_ottavi3.setOnMouseEntered(e -> {
            if (btn_ottavi3.getScene() != null) {
                btn_ottavi3.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_ottavi3.setOnMouseExited(e -> {
            if (btn_ottavi3.getScene() != null) {
                btn_ottavi3.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_ottavi4.setOnMouseEntered(e -> {
            if (btn_ottavi4.getScene() != null) {
                btn_ottavi4.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_ottavi4.setOnMouseExited(e -> {
            if (btn_ottavi4.getScene() != null) {
                btn_ottavi4.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_ottavi5.setOnMouseEntered(e -> {
            if (btn_ottavi5.getScene() != null) {
                btn_ottavi5.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_ottavi5.setOnMouseExited(e -> {
            if (btn_ottavi5.getScene() != null) {
                btn_ottavi5.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_ottavi6.setOnMouseEntered(e -> {
            if (btn_ottavi6.getScene() != null) {
                btn_ottavi6.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_ottavi6.setOnMouseExited(e -> {
            if (btn_ottavi6.getScene() != null) {
                btn_ottavi6.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_ottavi7.setOnMouseEntered(e -> {
            if (btn_ottavi7.getScene() != null) {
                btn_ottavi7.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_ottavi7.setOnMouseExited(e -> {
            if (btn_ottavi7.getScene() != null) {
                btn_ottavi7.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_ottavi8.setOnMouseEntered(e -> {
            if (btn_ottavi8.getScene() != null) {
                btn_ottavi8.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_ottavi8.setOnMouseExited(e -> {
            if (btn_ottavi8.getScene() != null) {
                btn_ottavi8.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });


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

        Utili.bottoneTorneo(btn_ottavi1, partite.get(0));
        Utili.bottoneTorneo(btn_ottavi2, partite.get(1));
        Utili.bottoneTorneo(btn_ottavi3, partite.get(2));
        Utili.bottoneTorneo(btn_ottavi4, partite.get(3));
        Utili.bottoneTorneo(btn_ottavi5, partite.get(4));
        Utili.bottoneTorneo(btn_ottavi6, partite.get(5));
        Utili.bottoneTorneo(btn_ottavi7, partite.get(6));
        Utili.bottoneTorneo(btn_ottavi8, partite.get(7));

        if (t.getRound() == 0) {
            btn_quarti1.setDisable(true);
            btn_quarti1.setText(OPZ.traduci("quarto"));
            btn_quarti2.setDisable(true);
            btn_quarti2.setText(OPZ.traduci("quarto"));
            btn_quarti3.setDisable(true);
            btn_quarti3.setText(OPZ.traduci("quarto"));
            btn_quarti4.setDisable(true);
            btn_quarti4.setText(OPZ.traduci("quarto"));

            btn_semi1.setDisable(true);
            btn_semi1.setText(OPZ.traduci("semifinale"));
            btn_semi2.setDisable(true);
            btn_semi2.setText(OPZ.traduci("semifinale"));

            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));

        } else if (t.getRound() == 1) {
            Utili.bottoneTorneo(btn_quarti1, partite.get(8));
            Utili.bottoneTorneo(btn_quarti2, partite.get(9));
            Utili.bottoneTorneo(btn_quarti3, partite.get(10));
            Utili.bottoneTorneo(btn_quarti4, partite.get(11));

            btn_semi1.setDisable(true);
            btn_semi1.setText(OPZ.traduci("semifinale"));
            btn_semi2.setDisable(true);
            btn_semi2.setText(OPZ.traduci("semifinale"));
            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));

        } else if (t.getRound() == 2) {
            Utili.bottoneTorneo(btn_quarti1, partite.get(8));
            Utili.bottoneTorneo(btn_quarti2, partite.get(9));
            Utili.bottoneTorneo(btn_quarti3, partite.get(10));
            Utili.bottoneTorneo(btn_quarti4, partite.get(11));

            Utili.bottoneTorneo(btn_semi1, partite.get(12));
            Utili.bottoneTorneo(btn_semi2, partite.get(13));

            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));

        } else if (t.getRound() == 3) {
            Utili.bottoneTorneo(btn_quarti1, partite.get(8));
            Utili.bottoneTorneo(btn_quarti2, partite.get(9));
            Utili.bottoneTorneo(btn_quarti3, partite.get(10));
            Utili.bottoneTorneo(btn_quarti4, partite.get(11));

            Utili.bottoneTorneo(btn_semi1, partite.get(12));
            Utili.bottoneTorneo(btn_semi2, partite.get(13));

            Utili.bottoneTorneo(btn_finale, partite.get(14));
        }
    }

    public void giocaPartita(Button b) throws IOException {
        OPZ.premiBottone();
        OPZ.playMusica("gioco.mp3");
        Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) b.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
    }

    public void cliccaOttavi1(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(0);
        giocaPartita(b);
    }

    public void cliccaOttavi2(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(1);
        giocaPartita(b);
    }

    public void cliccaOttavi3(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(2);
        giocaPartita(b);
    }

    public void cliccaOttavi4(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(3);
        giocaPartita(b);
    }

    public void cliccaOttavi5(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(4);
        giocaPartita(b);
    }

    public void cliccaOttavi6(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(5);
        giocaPartita(b);
    }

    public void cliccaOttavi7(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(6);
        giocaPartita(b);
    }

    public void cliccaOttavi8(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(7);
        giocaPartita(b);
    }

    public void cliccaQuarti1(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(8);
        giocaPartita(b);
    }

    public void cliccaQuarti2(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(9);
        giocaPartita(b);
    }

    public void cliccaQuarti3(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(10);
        giocaPartita(b);
    }

    public void cliccaQuarti4(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(11);
        giocaPartita(b);
    }

    public void cliccaSemi1(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(12);
        giocaPartita(b);
    }

    public void cliccaSemi2(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(13);
        giocaPartita(b);
    }

    public void cliccaFinale(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(14);
        giocaPartita(b);
    }

    public void keyEvent(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ENTER){
            if(btn_ottavi1.isFocused()){

            } else if (btn_ottavi2.isFocused()) {

            } else if (btn_ottavi3.isFocused()) {

            } else if (btn_ottavi4.isFocused()) {

            } else if (btn_ottavi5.isFocused()) {

            } else if (btn_ottavi6.isFocused()) {

            } else if (btn_ottavi7.isFocused()) {

            } else if (btn_ottavi8.isFocused()) {

            } else if (btn_quarti1.isFocused()) {

            } else if (btn_quarti2.isFocused()) {

            } else if (btn_quarti3.isFocused()) {

            } else if (btn_quarti4.isFocused()) {

            } else if (btn_semi1.isFocused()) {

            } else if (btn_semi2.isFocused()) {

            } else if (btn_finale.isFocused()) {

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
