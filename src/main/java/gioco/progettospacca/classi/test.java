package gioco.progettospacca.classi;

import com.google.gson.Gson;

public class test{
        public static void main(String[] args) {
                Gson gson = new Gson();
                Partita p = gson.fromJson(Utili.leggiFileJson("partite", Integer.toString(25066)), Partita.class);
        }
}