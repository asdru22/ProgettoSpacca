package gioco.progettospacca.classi;
import java.util.*;

import java.nio.file.FileSystems;

public class test {
        public static void main(String[] args) {
                String [] vett = Utili.getLeaderboard();
                for(int i = 0; i<=vett.length; i++){
                        System.out.println(vett[i]+"");
                }
        }
}
