package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class RegoleController implements Initializable {
    @FXML Label lbl_regolamento;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_regolamento.setText(OPZ.traduci("regolamento"));
    }
}
