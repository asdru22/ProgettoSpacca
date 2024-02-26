package gioco.progettospacca.classi;

import java.text.MessageFormat;
import java.util.Locale;


public class lang {
    public static void main(String[] args) {

        String s = Utili.traduci("prova");
        String f = MessageFormat.format(s, "paolo");
        System.out.println(f);

    }
}
