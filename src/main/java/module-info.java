module gioco.progettospacca {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens gioco.progettospacca to javafx.fxml;
    exports gioco.progettospacca;
}