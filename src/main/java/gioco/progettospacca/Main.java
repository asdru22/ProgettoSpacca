package gioco.progettospacca;
// import classi locali
import  gioco.progettospacca.classi.Giocatore;
import gioco.progettospacca.classi.Partita;
// import librerie locali
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import librerie esterne
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        // convertire partita.json in classi
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("partita.json"));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            String jsonData = jsonBuilder.toString();

            Partita partita = gson.fromJson(jsonData, Partita.class);

            System.out.println("Partita:");
            System.out.println(partita);

            // Print each page
            System.out.println("Pages:");
            for (Giocatore giocatore : partita.getGiocatori()) {
                System.out.println(giocatore);
            }
        } catch (IOException e) {
            System.err.println("Errore di lettura file");
        }
    }
}

