module gioco.progettospacca {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    //opens gioco.progettospacca.classi to com.google.gson;
    opens gioco.progettospacca to javafx.fxml;
    exports gioco.progettospacca;
}