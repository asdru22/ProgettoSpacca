package gioco.progettospacca.classi;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ValoriTorneo {
    private final TextField testo;
    private final CheckBox checkBox;

    public ValoriTorneo(TextField testo, CheckBox checkBox){
        // valori usati quando si inseriscono i giocatori di un torneo
        this.testo = testo;
        this.checkBox = checkBox;
    }
    public String getText(){
        return testo.getText();
    }
    public boolean isSelected(){
        return checkBox.isSelected();
    }
}
