package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CambiaLinguaController implements Initializable {
    @FXML
    public Label lbl_lingua;
    @FXML
    Button btn_ita;
    @FXML
    Button btn_ger;
    @FXML
    Button btn_ing;
    @FXML
    Button btn_backToHome;
    @FXML
    ImageView img_spunta1;
    @FXML
    ImageView img_spunta2;
    @FXML
    ImageView img_spunta3;


    public void linguaItaliana(MouseEvent mouseEvent) throws IOException {
        Utili.premiBottone();
        Utili.setLingua(Locale.ITALIAN);
        img_spunta1.setVisible(true);
        img_spunta2.setVisible(false);
        img_spunta3.setVisible(false);
        btn_ita.requestFocus();
    }

    public void linguaTedesca(MouseEvent mouseEvent) throws IOException {
        Utili.premiBottone();
        Utili.setLingua(Locale.GERMAN);
        img_spunta1.setVisible(false);
        img_spunta2.setVisible(false);
        img_spunta3.setVisible(true);
        btn_ger.requestFocus();
    }

    public void linguaInglese(MouseEvent mouseEvent) throws IOException {
        Utili.premiBottone();
        Utili.setLingua(Locale.ENGLISH);
        img_spunta1.setVisible(false);
        img_spunta2.setVisible(true);
        img_spunta3.setVisible(false);
        btn_ing.requestFocus();
    }

    public void BackToHome() throws IOException {Utili.premiBottone();

            Parent root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));

            // Ottieni la finestra corrente
            Stage currentStage = (Stage) btn_backToHome.getScene().getWindow();

            // Ottieni la scena corrente
            Scene currentScene = currentStage.getScene();

            // Imposta la nuova radice della scena
            currentScene.setRoot(root);

            // Imposta il titolo della finestra
            currentStage.setTitle(Utili.traduci("spacca"));
    }

    public void keyEvent(KeyEvent keyEvent) throws IOException {
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_backToHome.isFocused()) || keyEvent.getCode() == KeyCode.ESCAPE) {
            BackToHome();
        }
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_ita.isFocused())) {
            Utili.setLingua(Locale.ITALIAN);
            btn_ita.requestFocus();
        }
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_ing.isFocused())) {
            Utili.setLingua(Locale.ENGLISH);
            btn_ing.requestFocus();
        }
        if ((keyEvent.getCode() == KeyCode.ENTER && btn_ger.isFocused())) {
            Utili.setLingua(Locale.GERMAN);
            btn_ger.requestFocus();
        }

        if (keyEvent.getCode() == KeyCode.UP) {
            if (btn_backToHome.isFocused()) {
                System.out.println("Sei già in alto");
            } else if (btn_ita.isFocused()) {
                Utili.premiFreccia();
                btn_backToHome.requestFocus();
            } else if (btn_ger.isFocused()) {
                Utili.premiFreccia();
                btn_ing.requestFocus();
            } else if (btn_ing.isFocused()) {
                Utili.premiFreccia();
                btn_ita.requestFocus();
            }
        }

        if (keyEvent.getCode() == KeyCode.DOWN) {
            if (btn_ger.isFocused()) {
                System.out.println("Sei già in basso");
            } else if (btn_backToHome.isFocused()) {
                Utili.premiFreccia();

                btn_ita.requestFocus();
            } else if (btn_ita.isFocused()) {
                Utili.premiFreccia();

                btn_ing.requestFocus();
            } else if (btn_ing.isFocused()) {
                Utili.premiFreccia();

                btn_ger.requestFocus();
            }
        }
        pulisci();
    }
    public void pulisci(){
        btn_ita.setFocusTraversable(false);
        btn_ger.setFocusTraversable(false);
        btn_backToHome.setFocusTraversable(false);
        btn_ing.setFocusTraversable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String linea = null;
        try {
            Scanner scanner = new Scanner(new File("src/main/java/gioco/progettospacca/salvataggi/lingua.txt"));
            if (scanner.hasNextLine()) {
                linea = scanner.nextLine();
                //System.out.println(linea);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(linea.equals("inglese")){
            btn_ita.setFocusTraversable(false);
            btn_ger.setFocusTraversable(false);
            btn_ing.setFocusTraversable(true);
            btn_ing.requestFocus();
            img_spunta1.setVisible(false);
            img_spunta2.setVisible(true);
            img_spunta3.setVisible(false);
        } else if (linea.equals("italiano")) {
            btn_ita.setFocusTraversable(true);
            btn_ger.setFocusTraversable(false);
            btn_ing.setFocusTraversable(false);
            btn_ita.requestFocus();
            img_spunta1.setVisible(true);
            img_spunta2.setVisible(false);
            img_spunta3.setVisible(false);
        } else if (linea.equals("tedesco")) {
            btn_ita.setFocusTraversable(false);
            btn_ger.setFocusTraversable(true);
            btn_ing.setFocusTraversable(false);
            btn_ger.requestFocus();
            img_spunta1.setVisible(false);
            img_spunta2.setVisible(false);
            img_spunta3.setVisible(true);
        }
        lbl_lingua.setText(Utili.traduci("lingua"));
    }
}
