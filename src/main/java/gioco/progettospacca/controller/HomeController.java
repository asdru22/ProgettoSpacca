package gioco.progettospacca.controller;


import gioco.progettospacca.classi.Utili;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    Button btn_giocaPartita;
    @FXML
    Button btn_creaPartita;
    @FXML
    Button btn_creaTorneo;
    @FXML
    Button btn_giocaTorneo;
    @FXML
    MenuItem btn_classifica;
    @FXML
    Menu menuBar;
    public void EventoGiocaPartita() throws IOException {
        // Carica la nuova radice della scena
        Parent root = FXMLLoader.load(getClass().getResource("GiocaPartita.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_giocaPartita.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

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


    public void showLeaderboard() throws IOException {

        // Carica la nuova finestra
        Parent root = FXMLLoader.load(getClass().getResource("LeaderBoardView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Imposta la nuova finestra
        stage.setTitle("leaderboard");
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
        if(keyEvent.getCode() == KeyCode.UP){
            if (btn_giocaPartita.isFocused()) {
                System.out.println("Il focus è su btn_giocaPartita. Sei già in alto.");
            } else if (btn_creaPartita.isFocused()) {
                //System.out.println("Il focus è su btn_creaPartita. Cambio focus a btn_giocaPartita.");
                btn_giocaPartita.requestFocus();
            } else if (btn_giocaTorneo.isFocused()) {
                //System.out.println("Il focus è su btn_giocaTorneo. Cambio focus a btn_creaPartita.");
                btn_creaPartita.requestFocus();
            } else if (btn_creaTorneo.isFocused()) {
                //System.out.println("Il focus è su btn_creaTorneo. Cambio focus a btn_giocaTorneo.");
                btn_giocaTorneo.requestFocus();
            }
            pulisci();
        }
        if(keyEvent.getCode() == KeyCode.DOWN){
            if(btn_creaTorneo.isFocused()){
                System.out.println("sei gia in basso");
            } else if (btn_giocaPartita.isFocused()) {
                btn_creaPartita.requestFocus();
            } else if (btn_creaPartita.isFocused()) {
                btn_giocaTorneo.requestFocus();
            } else if (btn_giocaTorneo.isFocused()) {
                btn_creaTorneo.requestFocus();
            }
            pulisci();
        }
        keyEvent.consume();
    }

    private void pulisci(){
        btn_giocaPartita.setFocusTraversable(false);
        btn_creaPartita.setFocusTraversable(false);
        btn_giocaTorneo.setFocusTraversable(false);
        btn_creaTorneo.setFocusTraversable(false);
    }
    public void giocaPartita(MouseEvent mouseEvent) throws IOException {
        EventoGiocaPartita();
    }

    public void creaPartita(MouseEvent mouseEvent) throws IOException {
        EventoCreaPartita();
    }

    public void apriMenu(ActionEvent ActionEvent) throws IOException {
        showLeaderboard();
    }

    public void apriRegole(ActionEvent actionEvent) {
    }
}