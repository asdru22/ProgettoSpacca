module gioco.progettospacca {
    requires javafx.controls;
    requires javafx.fxml;


    opens gioco.progettospacca to javafx.fxml;
    exports gioco.progettospacca;
}