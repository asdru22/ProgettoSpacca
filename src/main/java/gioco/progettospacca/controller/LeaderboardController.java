package gioco.progettospacca.controller;
import gioco.progettospacca.classi.Utili;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static gioco.progettospacca.controller.Main.OPZ;

public class LeaderboardController {
    @FXML
    public Label lbl_classifica;
    @FXML
    private Label lbl_pos1;

    @FXML
    private Label lbl_pos2;

    @FXML
    private Label lbl_pos3;

    @FXML
    private Label lbl_pos4;

    @FXML
    private Label lbl_pos5;

    @FXML
    private Label lbl_pos6;

    @FXML
    private Label lbl_pos7;

    @FXML
    private Label lbl_pos8;

    @FXML
    private Label lbl_pos9;

    @FXML
    private Label lbl_pos10;

    @FXML
    public void initialize() {
        String [] lead = Utili.getLeaderboard();
        lbl_pos1.setText(lead[0]);
        lbl_pos2.setText(lead[1]);
        lbl_pos3.setText(lead[2]);
        lbl_pos4.setText(lead[3]);
        lbl_pos5.setText(lead[4]);
        lbl_pos6.setText(lead[5]);
        lbl_pos7.setText(lead[6]);
        lbl_pos8.setText(lead[7]);
        lbl_pos9.setText(lead[8]);
        lbl_pos10.setText(lead[9]);
        lbl_classifica.setText(OPZ.traduci("classifica"));


    }
}
