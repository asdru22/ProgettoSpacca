package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Utili {
    public static void salva(String tipo, String nome,Object o){
        try (FileWriter writer = new FileWriter("src/main/java/gioco/progettospacca/salvataggi/"+tipo+"/"+nome+".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(o));
        } catch (IOException e) {
            System.err.println("Errore salvataggio oggetto");
        }
    }
    public static String leggiFileJson(String tipo,String nome) {
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
            System.err.println("Errore lettura file");
            return "errore";
        }
    }
    public static String getLeaderboard(){
        File folder = new File("src/main/java/gioco/progettospacca/salvataggi/giocatori");
        File[] file_giocatori = folder.listFiles();
        ArrayList<Giocatore> temp = new ArrayList<>();
        String r = "";
        Gson gson = new Gson();
        for (File file : file_giocatori) {
            if (file.isFile()) {
                String s = file.getName().substring(0,file.getName().length()-5);
                temp.add(gson.fromJson(Utili.leggiFileJson("giocatori",s), Giocatore.class));
            }
        }
        ArrayList<Giocatore> copia_temp = new ArrayList<>(temp);
        Giocatore max_vittorie;
        for(int i = 1 ; i<=10;i++){
            max_vittorie = new Giocatore("temp");
            for (Giocatore g: copia_temp) {
                if (g.getPartiteVinte() >= max_vittorie.getPartiteVinte()){
                    max_vittorie = g;

                }
            }
            if(Objects.equals(max_vittorie.getNome(), "temp")) r+=i+".\n";
            else r+=i+". "+max_vittorie.getNome()+" - "+max_vittorie.getPartiteVinte()+"\n";
            temp.remove(max_vittorie);
            copia_temp = temp;
        }
        return r;
    }
    public static int intCasuale(int min,int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
