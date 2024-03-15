package gioco.progettospacca.controller;


import gioco.progettospacca.classi.Suoni;
import gioco.progettospacca.classi.Utili;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public MenuItem btn_audio;
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
    Pane pane_suoni;
    @FXML
    private ToggleButton tglbtn_suono;
    @FXML
    private ToggleButton tglbtn_musica;
    @FXML
    private Button btn_chiudiPaneSuoni;
    @FXML
    private AnchorPane anchorPane;

    public void ridaje(MouseEvent mouseEvent) throws IOException {
        Utili.premiBottone();
        Utili.suono("gioco.mp3", Suoni.Musica);
        Parent root = FXMLLoader.load(getClass().getResource("PartitaView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaPartita.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

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
        currentStage.setTitle("Gioca Partita");
    }

    public void EventoCreaPartita() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdminView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_creaPartita.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle("Login");
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
        stage.setTitle(Utili.traduci("classifica"));
        stage.setScene(scene);
        stage.show();
    }

    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.M){
            menuBar.show();
        }

        if (keyEvent.getCode() == KeyCode.ENTER && btn_giocaPartita.isFocused()) {
            EventoGiocaPartita();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_creaPartita.isFocused()) {
            EventoCreaPartita();
        }
        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_musica.isFocused()) {
            if(tglbtn_musica.isSelected()){
                tglbtn_musica.setText("musica ON");
            }else{
                tglbtn_musica.setText("musica OFF");
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && tglbtn_suono.isFocused()) {
            if(tglbtn_suono.isSelected()){
                tglbtn_suono.setText("suono ON");
            }else{
                tglbtn_suono.setText("suono OFF");
            }
        }
        if (keyEvent.getCode() == KeyCode.ENTER && btn_chiudiPaneSuoni.isFocused()) {
            pane_suoni.setVisible(false);
            btn_giocaPartita.requestFocus();
        }
        if(keyEvent.getCode() == KeyCode.UP){
            if (btn_giocaPartita.isFocused()) {
                System.out.println(" Sei già in alto.");
            } else if (btn_creaPartita.isFocused()) {
                Utili.premiFreccia();
                btn_giocaPartita.requestFocus();
            } else if (btn_giocaTorneo.isFocused()) {
                Utili.premiFreccia();
                btn_creaPartita.requestFocus();
            } else if (btn_creaTorneo.isFocused()) {
                Utili.premiFreccia();
                btn_giocaTorneo.requestFocus();
            }
        }
        if(keyEvent.getCode() == KeyCode.DOWN){
            if(btn_creaTorneo.isFocused()){
                System.out.println("sei gia in basso");
            } else if (btn_giocaPartita.isFocused()) {
                Utili.premiFreccia();
                btn_creaPartita.requestFocus();
            } else if (btn_creaPartita.isFocused()) {
                Utili.premiFreccia();
                btn_giocaTorneo.requestFocus();
            } else if (btn_giocaTorneo.isFocused()) {
                Utili.premiFreccia();
                btn_creaTorneo.requestFocus();
            }
        }
        if(pane_suoni.isVisible()) {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                if(tglbtn_suono.isFocused()){
                    tglbtn_musica.requestFocus();
                } else if (tglbtn_musica.isFocused()) {
                    btn_chiudiPaneSuoni.requestFocus();
                } else{
                    System.out.println("sei già in basso");
                }
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                if(btn_chiudiPaneSuoni.isFocused()){
                    tglbtn_musica.requestFocus();
                } else if(tglbtn_musica.isFocused()){
                    tglbtn_suono.requestFocus();
                }else{
                    System.out.println("sei già in alto");
                }
            }

        }
        pulisci();
    }

    private void pulisci(){
        btn_giocaPartita.setFocusTraversable(false);
        btn_creaPartita.setFocusTraversable(false);
        btn_giocaTorneo.setFocusTraversable(false);
        btn_creaTorneo.setFocusTraversable(false);
    }
    public void giocaPartita(MouseEvent mouseEvent) throws IOException {
        Utili.premiBottone();
        EventoGiocaPartita();
    }

    public void creaPartita(MouseEvent mouseEvent) throws IOException {
        Utili.premiBottone();
        EventoCreaPartita();
    }

    public void apriMenu(ActionEvent ActionEvent) throws IOException {
        showLeaderboard();
        Utili.premiBottone();
    }

    public void cambiaLingua(ActionEvent ActionEvent) throws IOException {
        showCambiaLingua();
        Utili.premiBottone();
    }

    public void apriRegole(ActionEvent actionEvent) {
        Utili.premiBottone();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_classifica.setText(Utili.traduci("classifica"));
        btn_regole.setText(Utili.traduci("regole"));
        btn_cambiaLingua.setText(Utili.traduci("cambia_lingua"));

        btn_creaPartita.setText(Utili.traduci("crea_partita"));
        btn_giocaPartita.setText(Utili.traduci("gioca_partita"));
        btn_creaTorneo.setText(Utili.traduci("crea_torneo"));
        btn_giocaTorneo.setText(Utili.traduci("gioca_torneo"));
    }

    public void suonoMenu(ActionEvent actionEvent) {
        Utili.premiBottone();
    }

    public void apriSuoni(ActionEvent actionEvent) {
        pane_suoni.setVisible(true);
        tglbtn_suono.requestFocus();
        anchorPane.setDisable(true);
    }

    public void setSuono(MouseEvent mouseEvent) {
        if(tglbtn_suono.isSelected()){
            tglbtn_suono.setText("suono OFF");
        }else{
            tglbtn_suono.setText("suono ON");
        }
    }

    public void setMusica(MouseEvent mouseEvent) {
        if(tglbtn_musica.isSelected()){
            tglbtn_musica.setText("musica OFF");
        }else{
            tglbtn_musica.setText("musica ON");
        }
    }

    public void chiudiPaneSuoni(MouseEvent mouseEvent) {
        pane_suoni.setVisible(false);
        btn_giocaPartita.requestFocus();
        anchorPane.setDisable(false);

    }
}