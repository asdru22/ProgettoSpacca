package gioco.progettospacca.classi;
import java.io.File;
import java.io.IOException;
import java.util.*;

import java.nio.file.FileSystems;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class test{
        public static void main(String[] args) {

                String nomeFile = "src/main/resources/gioco/progettospacca/1Fuoco.png";

                try {
                        File file = new File(nomeFile);

                        // Verifica se il file esiste prima di aprirlo
                        if (file.exists()) {
                                Desktop.getDesktop().open(file);
                        } else {
                                System.out.println("Il file non esiste.");
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }


        }
}