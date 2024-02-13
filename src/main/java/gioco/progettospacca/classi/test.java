package gioco.progettospacca.classi;
import java.util.*;

import java.nio.file.FileSystems;

public class test {
        public static void main(String[] args) {
                Giocatore g = Giocatore.carica("dani");
                g.rimuoviPartita(31095);
                g.salva();
        }
}
