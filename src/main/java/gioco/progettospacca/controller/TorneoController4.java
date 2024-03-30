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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.*;


public class TorneoController4 implements Initializable {
    @FXML
    Button btn_semi1, btn_semi2, btn_finale, btn_home;
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
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        ArrayList<Integer> partite = new ArrayList<>(t.getPartite());
        Utili.bottoneTorneo(btn_semi1,partite.get(0));
        Utili.bottoneTorneo(btn_semi2,partite.get(1));
        if(t.getRound()==1)
            Utili.bottoneTorneo(btn_finale,partite.get(2));
        else {
            btn_finale.setDisable(true);
            btn_finale.setText(OPZ.traduci("finale"));
        }
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
        if(btn_finale.isDisabled()) {
            {
                btn_finale.setOnMouseEntered(e -> {
                    if (btn_finale.getScene() != null) {
                        btn_finale.getScene().setCursor(Cursor.NONE);
                    }
                });
            }
        }else
        {
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
        }

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

    public void cliccaSemi1(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(0);
        giocaPartita(b);
    }
    public void cliccaSemi2(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(1);
        giocaPartita(b);
    }
    public void cliccaFinale(MouseEvent mouseEvent) throws IOException {
        Button b = (Button) mouseEvent.getSource();
        Torneo t = Torneo.carica(CODICE_GLOBALE_TORNEO);
        CODICE_GLOBALE_PARTITA = t.getPartite().get(2);
        giocaPartita(b);
    }
}
