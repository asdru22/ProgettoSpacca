package gioco.progettospacca.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static gioco.progettospacca.controller.Main.OPZ;

public class RegoleController implements Initializable {
    @FXML Label lbl_coppia, lbl_doppiaCoppia,  lbl_full, lbl_tris, lbl_poker,
  lbl_manita,lbl_scala3, lbl_scala4, lbl_scala5, lbl_colore3, lbl_colore4, lbl_colore5;
    @FXML
    Label lbl_regolamento, lbl_regole, lbl_fuoco,lbl_elettro,lbl_terra,lbl_acqua,lbl_erba,
    lbl_partita, lbl_regole2,lbl_regole3,lbl_regole4, lbl_regole5,lbl_regole6, lbl_regole7,
            lbl_titoloPunteggi, lbl_regole8, lbl_tabellaSemi, lbl_regole9, lbl_semeComanda,lbl_coloreMano,
            lbl_titoloObiettivo, lbl_regole10;

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
        lbl_titoloPunteggi.setText(OPZ.traduci("punteggi"));
        lbl_regole8.setText(OPZ.traduci("regole8"));
        lbl_tabellaSemi.setText(OPZ.traduci("tabella_semi"));
        lbl_regole9.setText(OPZ.traduci("regole9"));
        lbl_semeComanda.setText(OPZ.traduci("seme_comanda"));
        lbl_coloreMano.setText(OPZ.traduci("colore_mano"));
        lbl_titoloObiettivo.setText(OPZ.traduci("obiettivo"));
        lbl_regole10.setText(OPZ.traduci("regole10"));
        lbl_partita.setText(OPZ.traduci("partita"));

    }
}
