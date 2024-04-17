module gioco.progettospacca {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.media;
    requires java.mail;
    opens gioco.progettospacca to javafx.fxml;
    exports gioco.progettospacca.controller;
    exports gioco.progettospacca.classi;

    opens gioco.progettospacca.classi to com.google.gson;
    opens gioco.progettospacca.controller to com.google.gson, javafx.fxml;
}