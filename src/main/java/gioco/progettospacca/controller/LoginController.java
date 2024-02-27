package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Utili;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public Label lbl_username;
    @FXML
    public Label lbl_password;
    @FXML
    public Label lbl_error;
    @FXML
    private TextField txt_user;
    @FXML
    private TextField txt_psw;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_back;
    @FXML
    private Label lbl_titolo;

    public void BackToHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

        // Ottieni la finestra corrente
        Stage currentStage = (Stage) btn_back.getScene().getWindow();

        // Ottieni la scena corrente
        Scene currentScene = currentStage.getScene();

        // Imposta la nuova radice della scena
        currentScene.setRoot(root);

        // Imposta il titolo della finestra
        currentStage.setTitle(Utili.traduci("pokermon"));
    }

    public void LoginAdmin() throws IOException{
            String user= String.valueOf((txt_user.getText()));
            String psw= String.valueOf((txt_psw.getText()));
            //aggiungere usrrname e password
            if ("admin".equals(user) && "spacca".equals(psw)) {
                Parent root = FXMLLoader.load(getClass().getResource("CreaPartita.fxml"));

                // Ottieni la finestra corrente
                Stage currentStage = (Stage) btn_login.getScene().getWindow();

                // Ottieni la scena corrente
                Scene currentScene = currentStage.getScene();

                // Imposta la nuova radice della scena
                currentScene.setRoot(root);

                // Imposta il titolo della finestra
                currentStage.setTitle(Utili.traduci("login"));
            } else
            {
                System.out.println(user);
                System.out.println(psw);
                System.out.println("Email o password errata");
            }
    }
    public void backToHome(MouseEvent mouseEvent) throws IOException {
        BackToHome();
    }
    public void loginAdmin(MouseEvent mouseEvent) throws IOException {
       LoginAdmin();
    }
    public void keyEvent(KeyEvent keyEvent) throws IOException {
        pulisci();
        if(keyEvent.getCode() == KeyCode.ESCAPE ){
            BackToHome();
        }
        if(keyEvent.getCode() == KeyCode.ENTER ){
            if(btn_login.isFocused()){
                LoginAdmin();
            } else if(btn_back.isFocused()){
                BackToHome();
            } else if(txt_user.isFocused()){
                txt_psw.requestFocus();
            } else if(txt_psw.isFocused()){
                btn_login.requestFocus();
            }
        }

        if(keyEvent.getCode() == KeyCode.UP){
            if(btn_back.isFocused()){
                System.out.println("sei già in alto");
            } else if (txt_user.isFocused()) {
                btn_back.requestFocus();
            } else if (txt_psw.isFocused()) {
                txt_user.requestFocus();
            } else if (btn_login.isFocused()) {
                txt_psw.requestFocus();
            }
        }
        if(keyEvent.getCode() == KeyCode.DOWN){
            if(btn_login.isFocused()){
                System.out.println("sei già in basso");
            } else if (btn_back.isFocused()) {
                txt_user.requestFocus();
            } else if (txt_user.isFocused()) {
                txt_psw.requestFocus();
            } else if (txt_psw.isFocused()) {
                btn_login.requestFocus();
            }
        }
    }
    private void pulisci(){
        txt_psw.setFocusTraversable(false);
        txt_user.setFocusTraversable(false);
        btn_login.setFocusTraversable(false);
        btn_back.setFocusTraversable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_password.setText(Utili.traduci("password"));
        lbl_username.setText(Utili.traduci("username"));
        lbl_titolo.setText(Utili.traduci("login_amministratore"));
        btn_login.setText(Utili.traduci("login"));
    }
}
