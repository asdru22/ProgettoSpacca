package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

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
        for (int i = 0; i <= 10; i++) {
            max_vittorie = new Giocatore("temp");
            for (Giocatore g : copia_temp) {
                if (g.getPartiteVinte() >= max_vittorie.getPartiteVinte()) {
                    max_vittorie = g;
                }
            }
            if (Objects.equals(max_vittorie.getNome(), "temp")) {
                r = (i+1) +"";
            }
            else{
                r = (i+1) + ". " + max_vittorie.getNome() + " - " + max_vittorie.getPartiteVinte();
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

    public static void elimina(int id,String cartella) {

        File fileDaEliminare = new File("src/main/java/gioco/progettospacca/salvataggi/"+cartella+"/" + id + ".json");

        if (fileDaEliminare.delete()) {
            System.out.println("File eliminato con successo: " + id + ".json");
        } else {
            System.out.println("Impossibile eliminare il file: " + id + ".json");
        }
    }

    public static void eliminaGiocatore(String nome) {

        File fileDaEliminare = new File("src/main/java/gioco/progettospacca/salvataggi/giocatore/" + nome + ".json");

        if (fileDaEliminare.delete()) {
            System.out.println("File eliminato con successo: " + nome + ".json");
        } else {
            System.out.println("Impossibile eliminare il file: " + nome + ".json");
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

    public static Giocatore controllaNome(String nome, int id,boolean bot) {
        if (!Objects.equals(nome, "")) {
            boolean esiste = esisteGiocatore(nome);
            if (esiste) {
                Giocatore g = Giocatore.carica(nome);
                g.aggiungiPartita(id);
                g.salva();
                return g;
            }
            else {
                Giocatore g = new Giocatore(nome,bot);
                g.aggiungiPartita(id);
                g.salva();
                return g;
            }

        } else {
            return null;
        }
    }

    public static int leggiInt(TextField tx){
        return Integer.parseInt(tx.getText());
    }

    public static boolean nomiBot(String nome){
        return Objects.equals(nome, "bot1") || Objects.equals(nome, "bot2") || Objects.equals(nome, "bot3") || Objects.equals(nome, "bot4") || Objects.equals(nome, "bot5");
    }
    private static Locale lang;
    public static void getLingua(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/gioco/progettospacca/salvataggi/lingua.txt"));
            String s = reader.readLine();
            if (Objects.equals(s, "inglese")){
                lang = Locale.ENGLISH;
            }
            else if (Objects.equals(s, "tedesco")){
                lang = Locale.GERMAN;
            }
            else if (Objects.equals(s, "gatto")){
                lang = Locale.CHINA;
            }
            else{
                lang = Locale.ITALIAN;
            }
        } catch (IOException e) {
            System.err.println("lingua.txt non trovato");
        }
    }
    public static void setLingua(Locale lingua){
        lang = lingua;
        try (FileWriter writer = new FileWriter("src/main/java/gioco/progettospacca/salvataggi/lingua.txt")) {

            if(lang==Locale.ITALIAN) writer.write("italiano");
            if(lang==Locale.ENGLISH) writer.write("inglese");
            if(lang==Locale.GERMAN) writer.write("tedesco");

        } catch (IOException e) {
            System.err.println("impossibile accedere a lingua.txt");
        }
    }
    public static String traduci(String valore) {
        ResourceBundle bundle = ResourceBundle.getBundle("testo", lang);
        return bundle.getString(valore);
    }
    private static boolean suono = true;
    private static MediaPlayer musica;
    private static MediaPlayer sfx;

    public static void suono(String nome, Suoni tipo) {
        if(suono){
            if(tipo==Suoni.Musica){
                if (musica != null) {
                    musica.stop(); // Stop any previously playing media
                }
                musica = new MediaPlayer(new Media(new File("src/main/resources/gioco/progettospacca/suoni/" + nome).toURI().toString()));
                musica.setCycleCount(MediaPlayer.INDEFINITE);
                musica.play();
                System.out.println("play music");
            } else if (tipo==Suoni.SFX){
                sfx = new MediaPlayer(new Media(new File("src/main/resources/gioco/progettospacca/suoni/" + nome).toURI().toString()));
                sfx.setCycleCount(1);
                sfx.play();
                System.out.println("play sfx");
            }
        }
    }
    public static void premiBottone(){
        Utili.suono("bottone.wav",Suoni.SFX);
    }
    public static void premiFreccia(){
        Utili.suono("freccia.wav",Suoni.SFX);
    }
    public static void cliccaCarta(){
        Utili.suono("carta.wav",Suoni.SFX);
    }

    public static void toggleAudio(){suono = !suono;}
}
