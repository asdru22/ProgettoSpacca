package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static gioco.progettospacca.controller.Main.OPZ;

public class Utili {
    public static void salva(String tipo, String nome, Object o) {
        try (FileWriter writer = new FileWriter("salvataggi/"+tipo + "/" + nome + ".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(o));
        } catch (IOException e) {
            System.err.println("Errore salvataggio oggetto");
        }
    }

    public static String leggiFileJson(String tipo, String nome) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("salvataggi/"+tipo + "/" + nome + ".json"));
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

    public static String[] getLeaderboard() {
        File folder = new File("salvataggi/giocatori");
        File[] file_giocatori = folder.listFiles();
        ArrayList<Giocatore> temp = new ArrayList<>();
        Gson gson = new Gson();
        assert file_giocatori != null;
        for (File file : file_giocatori) {
            if (file.isFile()) {
                String s = file.getName().substring(0, file.getName().length() - 5);
                temp.add(gson.fromJson(Utili.leggiFileJson("giocatori", s), Giocatore.class));
            }
        }
        ArrayList<Giocatore> copia_temp = new ArrayList<>(temp);
        Giocatore max_vittorie;
        String[] top10 = new String[10];
        String r;
        for (int i = 0; i < 10; i++) {
            max_vittorie = new Giocatore("temp");
            for (Giocatore g : copia_temp) {
                if (g.getPartiteVinte() >= max_vittorie.getPartiteVinte()) {
                    max_vittorie = g;
                }
            }
            if (!Objects.equals(max_vittorie.getNome(), "temp")) {
                r = (i <= 2 ? "" : i+1 + ". ") + max_vittorie.getNome() + " - " + max_vittorie.getPartiteVinte();
                top10[i] = r;
            }
            temp.remove(max_vittorie);
            copia_temp = temp;
        }
        return top10;
    }

    public static int intCasuale(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static Seme semeCasuale() {
        Seme[] s = Seme.values();
        return s[intCasuale(0, 4)];
    }

    public static void elimina(String id, String cartella) {

        File fileDaEliminare = new File("salvataggi/" + cartella + "/" + id + ".json");

        if (fileDaEliminare.delete()) {
            System.out.println("File eliminato con successo: " + id + ".json");
        } else {
            System.out.println("Impossibile eliminare il file: " + id + ".json");
        }
    }

    public static void eliminaGiocatore(String nome) {
        elimina(nome,"giocatori");
    }
    public static void eliminaPartita(int id) {
        elimina(id+"","partite");
    }
    public static void eliminaTorneo(int id) {
        elimina(id+"","tornei");
    }

    public static boolean esisteGiocatore(String nome) {
        File folder = new File("salvataggi/giocatori");
        File[] file_giocatori = folder.listFiles();
        boolean trovato = false;
        assert file_giocatori != null;
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
        File folder = new File("salvataggi/partite");
        File[] file_partite = folder.listFiles();
        assert file_partite != null; // controllo che il file esista
        boolean trovato = false;
        int s;
        for (File file : file_partite) {
            if (file.isFile() && !trovato) {
                // prendo il nome togliendo il suffisso .json
                s = Integer.parseInt(file.getName().substring(0, file.getName().length() - 5));
                if (id == s) {
                    trovato = true;
                }
            }
        }
        return trovato;
    }

    public static Giocatore controllaNome(String nome, int id, boolean bot) {
        if (!Objects.equals(nome, "")) {
            boolean esiste = esisteGiocatore(nome);
            Giocatore g;
            if (esiste) {
                g = Giocatore.carica(nome);
            } else {
                g = new Giocatore(nome, bot);
            }
            g.aggiungiPartita(id);
            g.salva();
            return g;
        } else {
            return null;
        }
    }

    public static int leggiInt(TextField tx) {
        return Integer.parseInt(tx.getText());
    }

    public static void gestisciMusica(ToggleButton tglb) {
        if (tglb.isSelected()) {
            OPZ.pausaMusica();
            tglb.setText(OPZ.traduci("musica_off"));
        } else {
            OPZ.riprendiMusica();
            tglb.setText(OPZ.traduci("musica_on"));
        }
    }

    public static void gestisciSuoni(ToggleButton tglb) {
        if (tglb.isSelected()) {
            OPZ.pausaSfx();
            tglb.setText(OPZ.traduci("suono_off"));
        } else {
            OPZ.riprendiSfx();
            tglb.setText(OPZ.traduci("suono_on"));
        }
    }
    public static void cambiaNomeGiocatore(String vecchio, String nuovo) {
        if(esisteGiocatore(vecchio)&&!esisteGiocatore(nuovo)){
            Giocatore g = Giocatore.carica(vecchio);
            eliminaGiocatore(vecchio);
            g.setNome(nuovo);
            g.salva();
        } else System.err.println("Impossibile eseguire l'operazione, assicurarsi che esista un giocatore col nome vecchio e che non esista un giocatore col nome nuovo.");
    }
    public static String adminEliminaPartita(int id){
        if(esistePartita(id)){
            eliminaPartita(id);
            return OPZ.traduci("partita_eliminata");
        } else return OPZ.traduci("partita_non_trovata");
    }

}
