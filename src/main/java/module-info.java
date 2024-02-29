module gioco.progettospacca {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    opens gioco.progettospacca to javafx.fxml;
    exports gioco.progettospacca.controller;
    opens gioco.progettospacca.controller to javafx.fxml;
    opens gioco.progettospacca.classi to com.google.gson;
}