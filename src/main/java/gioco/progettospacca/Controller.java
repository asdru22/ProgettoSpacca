package gioco.progettospacca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    Label scritta;
    public void clicca(ActionEvent actionEvent) {
        scritta.setText("fica la figa");
    }
}