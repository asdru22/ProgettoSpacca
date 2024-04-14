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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
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

    public void ridaje(MouseEvent mouseEvent) throws IOException {
        OPZ.premiBottone();
        OPZ.playMusica("gioco.mp3");
        Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaPartita.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();
        currentScene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

    }

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
        currentStage.setTitle(OPZ.traduci("gioca_partita"));
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
        currentStage.setTitle(OPZ.traduci("login"));
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

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("login"));
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

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("gioca_torneo"));
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

        // Carica la nuova finestra
        Parent root = FXMLLoader.load(getClass().getResource("LeaderBoardView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Imposta la nuova finestra
        stage.setTitle(OPZ.traduci("classifica"));
        scene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void showRegole() throws IOException {

        // Carica la nuova finestra
        Parent root = FXMLLoader.load(getClass().getResource("RegoleView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scene.setCursor(Cursor.cursor(getClass().getResource("/gioco/progettospacca/cursoreBase.png").toExternalForm()));
        // Imposta la nuova finestra
        //stage.setTitle(OPZ.traduci("regole"));
        stage.setScene(scene);
        stage.show();
    }

    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.M) {
            menuImpostazioni.show();
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
        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_suono.isFocused()) {
            OPZ.premiBottone();
            Utili.gestisciSuoni(tglbtn_suono);

        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_chiudiPaneSuoni.isFocused()) {
            anchorPane_suoni.setVisible(false);
            btn_giocaPartita.requestFocus();
            anchorPane.setDisable(false);
        }
        if (keyEvent.getCode() == KeyCode.UP) {
            if (btn_giocaPartita.isFocused()) {
                System.out.println(" Sei già in alto.");
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
                System.out.println("sei gia in basso");
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
                    System.out.println("sei già in basso");
                }
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                if (btn_chiudiPaneSuoni.isFocused()) {
                    OPZ.premiFreccia();
                    tglbtn_musica.requestFocus();
                } else if (tglbtn_musica.isFocused()) {
                    OPZ.premiFreccia();
                    tglbtn_suono.requestFocus();
                } else {
                    System.out.println("sei già in alto");
                }
            }

        }
        pulisci();
    }

    private void pulisci() {
        btn_giocaPartita.setFocusTraversable(false);
        btn_creaPartita.setFocusTraversable(false);
        btn_giocaTorneo.setFocusTraversable(false);
        btn_creaTorneo.setFocusTraversable(false);
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

    public void apriRegole()throws IOException {
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
        pause.setOnFinished(event ->{

        });
    }

    public void suonoMenu() {
        OPZ.premiBottone();
    }

    public void apriSuoni() {
        anchorPane_suoni.setVisible(true);
        tglbtn_suono.requestFocus();
        anchorPane.setDisable(true);
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
    }
    public void apriLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdminViewPrivilegi.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaTorneo.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(OPZ.traduci("login"));
    }
}