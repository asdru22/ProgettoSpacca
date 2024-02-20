package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Utili {
    public static void salva(String tipo, String nome, Object o) {
        try (FileWriter writer = new FileWriter("src/main/java/gioco/progettospacca/salvataggi/" + tipo + "/" + nome + ".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(o));
        } catch (IOException e) {
            System.err.println("Errore salvataggio oggetto");
        }
    }

    public static String leggiFileJson(String tipo, String nome) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/gioco/progettospacca/salvataggi/" + tipo + "/" + nome + ".json"));
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

    public static String [] getLeaderboard() {
        File folder = new File("src/main/java/gioco/progettospacca/salvataggi/giocatori");
        File[] file_giocatori = folder.listFiles();
        ArrayList<Giocatore> temp = new ArrayList<>();
        Gson gson = new Gson();
        for (File file : file_giocatori) {
            if (file.isFile()) {
                String s = file.getName().substring(0, file.getName().length() - 5);
                temp.add(gson.fromJson(Utili.leggiFileJson("giocatori", s), Giocatore.class));
            }
        }
        ArrayList<Giocatore> copia_temp = new ArrayList<>(temp);
        Giocatore max_vittorie;
        String [] vett = new String[11];
        String r = "";
        for (int i = 1; i <= 10; i++) {
            max_vittorie = new Giocatore("temp");
            for (Giocatore g : copia_temp) {
                if (g.getPartiteVinte() >= max_vittorie.getPartiteVinte()) {
                    max_vittorie = g;
                }
            }
            if (Objects.equals(max_vittorie.getNome(), "temp")) {
                r = i +"";
            }
            else{
                r = i + ". " + max_vittorie.getNome() + " - " + max_vittorie.getPartiteVinte();
                vett[i]= r;
            }
            temp.remove(max_vittorie);
            copia_temp = temp;
        }
        return vett;
    }

    public static int intCasuale(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static Seme semeCasuale() {
        Seme[] s = Seme.values();
        return s[intCasuale(0, 4)];
    }

    public static void eliminaSalvataggio(int id) {

        File fileDaEliminare = new File("src/main/java/gioco/progettospacca/salvataggi/partite/" + id + ".json");

        if (fileDaEliminare.delete()) {
            System.out.println("File eliminato con successo: " + id + ".json");
        } else {
            System.out.println("Impossibile eliminare il file: " + id + ".json");
        }
    }

    public static boolean esisteGiocatore(String nome) {
        File folder = new File("src/main/java/gioco/progettospacca/salvataggi/giocatori");
        File[] file_giocatori = folder.listFiles();
        Gson gson = new Gson();
        boolean trovato = false;
        for (File file : file_giocatori) {
            if (file.isFile() && !trovato) {
                String s = file.getName().substring(0, file.getName().length() - 5);
                if (Objects.equals(nome, s)) {
                    trovato = true;
                }
            }
        }
        return trovato;
    }

    public static boolean esistePartita(int id) {
        File folder = new File("src/main/java/gioco/progettospacca/salvataggi/partite");
        File[] file_partite = folder.listFiles();
        Gson gson = new Gson();
        boolean trovato = false;
        int s;
        for (File file : file_partite) {
            if (file.isFile() && !trovato) {
                s = Integer.parseInt(file.getName().substring(0, file.getName().length() - 5));
                if (id == s) {
                    trovato = true;
                }
            }
        }
        return trovato;
    }

    public static Giocatore controllaNome(String nome, int id) {
        if (!Objects.equals(nome, "")) {
            boolean esiste = esisteGiocatore(nome);
            if (esiste) {
                Giocatore g = Giocatore.carica(nome);
                g.aggiungiPartita(id);
                g.salva();
                return g;
            }
            else {
                if(nome == "bot1" || nome == "bot2" || nome == "bot3" || nome == "bot4" || nome == "bot5"){
                    Giocatore g = new Bot(nome);
                    return g;
                }
                else {
                    Giocatore g = new Giocatore(nome);
                    g.aggiungiPartita(id);
                    g.salva();
                    return g;
                }

            }

        } else {
            return null;
        }
    }

    public static int leggiInt(TextField tx){
        return Integer.parseInt(tx.getText());
    }
}
