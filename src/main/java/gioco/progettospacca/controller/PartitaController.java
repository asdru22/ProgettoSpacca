package gioco.progettospacca.controller;

import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PartitaController implements Initializable {
    @FXML
    private AnchorPane anchorPane; // Assicurati di avere l'elemento AnchorPane nel tuo file FXML

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Sostituisci "percorso/immagine/1fuoco.png" con il percorso effettivo del tuo file immagine
            String percorsoImmagine = "src/main/resources/gioco/progettospacca/1Fuoco.png";

            // Crea un'ImageView e imposta l'immagine
            Image image = new Image(new FileInputStream(percorsoImmagine));
            ImageView imageView = new ImageView(image);

            // Aggiungi l'ImageView al tuo AnchorPane
            anchorPane.getChildren().add(imageView);

            // Imposta le posizioni e dimensioni dell'ImageView a seconda delle tue esigenze
            imageView.setLayoutX(100);
            imageView.setLayoutY(100);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
