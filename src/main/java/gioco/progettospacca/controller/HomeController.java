package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Utili;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class HomeController implements Initializable {

    @FXML
    Menu menuImpostazioni;
    @FXML
    Menu menuClassifica;
    @FXML
    MenuItem btn_privilegi;
    @FXML
    Label lbl_suoni;
    @FXML
    MenuItem btn_cambiaLingua;
    @FXML
    MenuItem btn_regole;
    @FXML
    MenuItem btn_classifica;
    @FXML
    MenuItem btn_suoni;
    @FXML
    Button btn_giocaPartita;
    @FXML
    Button btn_creaPartita;
    @FXML
    Button btn_creaTorneo;
    @FXML
    Button btn_giocaTorneo;
    @FXML
    Menu menuBar;
    @FXML
    AnchorPane anchorPane_suoni;
    @FXML
    private ToggleButton tglbtn_suono;
    @FXML
    private ToggleButton tglbtn_musica;
    @FXML
    private Button btn_chiudiPaneSuoni;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imageFocus;
    @FXML
    private ImageView imageSuonoFocus;
    public void EventoGiocaPartita() throws IOException {

        // Carica la nuova radice della scena
        Parent root = FXMLLoader.load(getClass().getResource("GiocaPartita.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_giocaPartita.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        currentScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


        // Imposta il titolo della finestra
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
    }

    public void EventoCreaPartita() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdminView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaPartita.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
    }

    public void EventoCreaTorneo() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdminViewTorneo.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaTorneo.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
    }

    public void EventoGiocaTorneo() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GiocaTorneoView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_giocaTorneo.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
    }

    public void showCambiaLingua() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("CambioLinguaView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaPartita.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
        currentScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

    public void showLeaderboard() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("LeaderboardView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaPartita.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
        currentScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public void showRegole() throws IOException {

        // Carica la nuova finestra
        Parent root = FXMLLoader.load(getClass().getResource("RegoleView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/gioco/progettospacca/Logo.png"))));

        stage.setTitle(OPZ.traduci("regole"));
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void keyEvent(KeyEvent keyEvent) throws IOException {
        btn_giocaPartita.setFocusTraversable(false);

        if (keyEvent.getCode() == KeyCode.M) {
            menuImpostazioni.show();
            return;
        }

        if(imageFocus.isFocusTraversable()){
            imageFocus.setFocusTraversable(false);
            btn_giocaPartita.requestFocus();
            return;
        }
        if(imageSuonoFocus.isFocusTraversable() && anchorPane_suoni.isVisible()){
            imageSuonoFocus.setFocusTraversable(false);
            tglbtn_suono.requestFocus();
            return;
        }

        if (keyEvent.getCode() == KeyCode.ENTER && btn_giocaPartita.isFocused()) {
            EventoGiocaPartita();
            OPZ.premiBottone();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_creaPartita.isFocused()) {
            EventoCreaPartita();
            OPZ.premiBottone();

        }
        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_musica.isFocused()) {
            Utili.gestisciMusica(tglbtn_musica);
            OPZ.premiBottone();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_creaTorneo.isFocused()) {
            EventoCreaTorneo();
            OPZ.premiBottone();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_giocaTorneo.isFocused()) {
            EventoGiocaTorneo();
            OPZ.premiBottone();
        }
        if (keyEvent.getCode() == KeyCode.ESCAPE && anchorPane_suoni.isVisible()) {
            chiudiPaneSuoni();
        }

        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_suono.isFocused()) {
            OPZ.premiBottone();
            Utili.gestisciSuoni(tglbtn_suono);

        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_chiudiPaneSuoni.isFocused()) {
            chiudiPaneSuoni();
        }
        if (keyEvent.getCode() == KeyCode.UP) {
            if (btn_giocaPartita.isFocused()) {
            } else if (btn_creaPartita.isFocused()) {
                OPZ.premiFreccia();
                btn_giocaPartita.requestFocus();
            } else if (btn_giocaTorneo.isFocused()) {
                OPZ.premiFreccia();
                btn_creaPartita.requestFocus();
            } else if (btn_creaTorneo.isFocused()) {
                OPZ.premiFreccia();
                btn_giocaTorneo.requestFocus();
            }
        }
        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (btn_creaTorneo.isFocused()) {
            } else if (btn_giocaPartita.isFocused()) {
                OPZ.premiFreccia();
                btn_creaPartita.requestFocus();
            } else if (btn_creaPartita.isFocused()) {
                OPZ.premiFreccia();
                btn_giocaTorneo.requestFocus();
            } else if (btn_giocaTorneo.isFocused()) {
                OPZ.premiFreccia();
                btn_creaTorneo.requestFocus();
            }
        }
        if (anchorPane_suoni.isVisible()) {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                if (tglbtn_suono.isFocused()) {
                    OPZ.premiFreccia();
                    tglbtn_musica.requestFocus();
                } else if (tglbtn_musica.isFocused()) {
                    OPZ.premiFreccia();
                    btn_chiudiPaneSuoni.requestFocus();
                } else {
                }
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                if (btn_chiudiPaneSuoni.isFocused()) {
                    OPZ.premiFreccia();
                    tglbtn_musica.requestFocus();
                } else if (tglbtn_musica.isFocused()) {
                    OPZ.premiFreccia();
                    tglbtn_suono.requestFocus();
                }
            }

        }

    }

    public void giocaPartita() throws IOException {
        OPZ.premiBottone();
        EventoGiocaPartita();
    }

    public void creaPartita() throws IOException {
        OPZ.premiBottone();
        EventoCreaPartita();
    }

    public void creaTorneo() throws IOException {
        OPZ.premiBottone();
        EventoCreaTorneo();
    }

    public void giocaTorneo() throws IOException {
        OPZ.premiBottone();
        EventoGiocaTorneo();
    }

    public void apriMenu() throws IOException {
        showLeaderboard();
        OPZ.premiBottone();
    }

    public void cambiaLingua() throws IOException {
        showCambiaLingua();
        OPZ.premiBottone();
    }

    public void apriRegole() throws IOException {
        showRegole();
        OPZ.premiBottone();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        btn_giocaPartita.setOnMouseEntered(e -> {
            if (btn_giocaPartita.getScene() != null) {
                btn_giocaPartita.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_giocaPartita.setOnMouseExited(e -> {
            if (btn_giocaPartita.getScene() != null) {
                btn_giocaPartita.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_creaPartita.setOnMouseEntered(e -> {
            if (btn_giocaPartita.getScene() != null) {
                btn_creaPartita.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_creaPartita.setOnMouseExited(e -> {
            if (btn_creaPartita.getScene() != null) {
                btn_creaPartita.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_giocaTorneo.setOnMouseEntered(e -> {
            if (btn_giocaTorneo.getScene() != null) {
                btn_giocaTorneo.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_giocaTorneo.setOnMouseExited(e -> {
            if (btn_giocaTorneo.getScene() != null) {
                btn_giocaTorneo.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });
        btn_creaTorneo.setOnMouseEntered(e -> {
            if (btn_creaTorneo.getScene() != null) {
                btn_creaTorneo.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_creaTorneo.setOnMouseExited(e -> {
            if (btn_creaTorneo.getScene() != null) {
                btn_creaTorneo.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        tglbtn_musica.setOnMouseEntered(e -> {
            if (tglbtn_musica.getScene() != null) {
                tglbtn_musica.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        tglbtn_musica.setOnMouseExited(e -> {
            if (tglbtn_musica.getScene() != null) {
                tglbtn_musica.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        tglbtn_suono.setOnMouseEntered(e -> {
            if (tglbtn_suono.getScene() != null) {
                tglbtn_suono.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        tglbtn_suono.setOnMouseExited(e -> {
            if (tglbtn_suono.getScene() != null) {
                tglbtn_suono.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_chiudiPaneSuoni.setOnMouseEntered(e -> {
            if (btn_chiudiPaneSuoni.getScene() != null) {
                btn_chiudiPaneSuoni.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreMano.png").toExternalForm()));
            }
        });

        // Reimposta il cursore predefinito quando il mouse esce dal bottone
        btn_chiudiPaneSuoni.setOnMouseExited(e -> {
            if (btn_chiudiPaneSuoni.getScene() != null) {
                btn_chiudiPaneSuoni.getScene().setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
            }
        });

        btn_classifica.setText(OPZ.traduci("classifica"));
        btn_regole.setText(OPZ.traduci("regole"));
        btn_cambiaLingua.setText(OPZ.traduci("cambia_lingua"));
        btn_privilegi.setText(OPZ.traduci("privilegi_amministratore"));

        btn_suoni.setText(OPZ.traduci("suoni"));
        lbl_suoni.setText(OPZ.traduci("suoni"));

        menuImpostazioni.setText(OPZ.traduci("impostazioni"));
        menuClassifica.setText(OPZ.traduci("classifica"));

        btn_creaPartita.setText(OPZ.traduci("crea_partita"));
        btn_giocaPartita.setText(OPZ.traduci("gioca_partita"));
        btn_creaTorneo.setText(OPZ.traduci("crea_torneo"));
        btn_giocaTorneo.setText(OPZ.traduci("gioca_torneo"));

        tglbtn_suono.setSelected(OPZ.getSuono());
        tglbtn_musica.setSelected(OPZ.getMusica());
        Utili.gestisciSuoni(tglbtn_suono);
        Utili.gestisciMusica(tglbtn_musica);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.play();
        pause.setOnFinished(event -> {

        });
    }

    public void suonoMenu() {
        OPZ.premiBottone();
    }

    public void apriSuoni() {
        anchorPane_suoni.setVisible(true);
        anchorPane.setDisable(true);
        imageFocus.setFocusTraversable(false);
    }

    public void setSuono() {
        Utili.gestisciSuoni(tglbtn_suono);
        OPZ.premiBottone();
    }

    public void setMusica() {
        Utili.gestisciMusica(tglbtn_musica);
        OPZ.premiBottone();
    }

    public void chiudiPaneSuoni() {
        anchorPane_suoni.setVisible(false);
        btn_giocaPartita.requestFocus();
        anchorPane.setDisable(false);
        OPZ.premiBottone();
        imageFocus.setFocusTraversable(true);
        imageSuonoFocus.setFocusTraversable(true);
    }

    public void apriLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdminViewPrivilegi.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaTorneo.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);
    }
}