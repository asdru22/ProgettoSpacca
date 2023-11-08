package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utili {
    public static void salva(String tipo, String nome,Object o){
        try (FileWriter writer = new FileWriter("src/main/java/gioco/progettospacca/salvataggi/"+tipo+"/"+nome+".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(o));
        } catch (IOException e) {
            System.err.println("Errore salvataggio");
        }
    }
    public static String leggiFileJson(String tipo,String nome) {
        System.out.println("src/main/java/gioco/progettospacca/salvataggi/"+tipo+"/"+nome+".json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/gioco/progettospacca/salvataggi/"+tipo+"/"+nome+".json"));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
            return jsonBuilder.toString();
        } catch (IOException e) {
            System.err.println("Errore di caricamento file");
            return "errore";
        }
    }
}
