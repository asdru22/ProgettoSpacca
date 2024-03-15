package gioco.progettospacca.classi;
import java.io.File;
import java.io.IOException;


import java.nio.file.FileSystems;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class test{
        public static Opzioni opzioni = Opzioni.carica();

        public static void main(String[] args) {
                System.out.println(opzioni.getLingua());
        }
}