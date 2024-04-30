package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class RegoleController implements Initializable {
    @FXML
    Label lbl_regolamento, lbl_regole, lbl_fuoco,lbl_elettro,lbl_terra,lbl_acqua,lbl_erba,
    lbl_partita, lbl_regole2,lbl_regole3,lbl_regole4, lbl_regole5,lbl_regole6, lbl_regole7;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_regolamento.setText(OPZ.traduci("regolamento"));
        lbl_regole.setText(OPZ.traduci("regole1"));
        lbl_fuoco.setText(OPZ.traduci("fuoco"));
        lbl_acqua.setText(OPZ.traduci("acqua"));
        lbl_terra.setText(OPZ.traduci("terra"));
        lbl_erba.setText(OPZ.traduci("erba"));
        lbl_elettro.setText(OPZ.traduci("elettro"));
        lbl_elettro.setText(OPZ.traduci("partita"));
        lbl_regole2.setText(OPZ.traduci("regole2"));
        lbl_regole3.setText(OPZ.traduci("regole3"));
        lbl_regole4.setText(OPZ.traduci("regole4"));
        lbl_regole5.setText(OPZ.traduci("regole5"));
        lbl_regole6.setText(OPZ.traduci("regole6"));
        lbl_regole7.setText(OPZ.traduci("regole7"));

    }
}
