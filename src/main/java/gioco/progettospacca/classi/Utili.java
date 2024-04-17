package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static gioco.progettospacca.controller.Main.OPZ;

public class Utili {
    public static void salva(String tipo, String nome, Object o) {
        try (FileWriter writer = new FileWriter("salvataggi/" + tipo + "/" + nome + ".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(o));
        } catch (IOException e) {
            System.err.println("Errore salvataggio oggetto");
        }
    }

    public static String leggiFileJson(String tipo, String nome) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("salvataggi/" + tipo + "/" + nome + ".json"));
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
                r = (i <= 2 ? "" : i + 1 + ". ") + max_vittorie.getNome() + " - " + max_vittorie.getPartiteVinte();
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
        elimina(nome, "giocatori");
    }

    public static void eliminaPartita(int id) {
        elimina(id + "", "partite");
    }

    public static void eliminaTorneo(int id) {
        elimina(id + "", "tornei");
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

    public static boolean esistePartita(int id, boolean partita) {
        // true per partite, false per torneo
        File folder = null;
        if (partita) folder = new File("salvataggi/partite");
        if (!partita) folder = new File("salvataggi/tornei");

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

    public static Giocatore controllaNome(String nome, int id, boolean bot, List<Giocatore> lista) {
        if (!Objects.equals(nome, "")) {
            boolean esiste = esisteGiocatore(nome);
            Giocatore g;

            if (esiste) {
                g = Giocatore.carica(nome);
            } else {
                g = new Giocatore(nome, bot);
            }

            if(lista.contains(g)) return null;


            g.aggiungiPartita(id);
            g.salva();
            return g;
        } else {
            return null;
        }
    }

    public static Giocatore controllaNomeTorneo(String nome, boolean bot) {
        if (!Objects.equals(nome, "")) {
            boolean esiste = esisteGiocatore(nome);
            Giocatore g;
            if (esiste) {
                g = Giocatore.carica(nome);
            } else {
                g = new Giocatore(nome, bot);
            }
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

    public static String cambiaNomeGiocatore(String vecchio, String nuovo) {
        if (esisteGiocatore(vecchio) && !esisteGiocatore(nuovo)) {
            Giocatore g = Giocatore.carica(vecchio);
            if (g.isBot()) {
                return OPZ.traduci("bot_non_rinominabile");
            }
            eliminaGiocatore(vecchio);
            g.setNome(nuovo);
            g.salva();
            // cambia i nomi salvati nelle partite
            ArrayList<Integer> idPartite = elencaPartite(true,true);
            assert (!idPartite.isEmpty());
            for (Integer id : idPartite) {
                Partita p = Partita.carica(id);
                ArrayList<String> nome = p.getNomiGiocatori();
                if (nome.contains(vecchio)) { // se esiste quel giocatore nella partita
                    for (Giocatore gioc : p.getGiocatori()) {
                        if (Objects.equals(gioc.getNome(), vecchio)) {
                            gioc.setNome(nuovo);
                            p.salva();
                            break;
                        }
                    }
                }
            }
            ArrayList<Integer> idTornei = elencaPartite(false,false);
            assert (!idTornei.isEmpty());
            for (Integer id : idTornei) {
                Torneo t = Torneo.carica(id);
                ArrayList<String> nome = t.getNomiGiocatori();
                if (nome.contains(vecchio)) { // se esiste quel giocatore nella partita
                    for (Giocatore gioc : t.getGiocatori()) {
                        if (Objects.equals(gioc.getNome(), vecchio)) {
                            gioc.setNome(nuovo);
                            t.salva();
                            break;
                        }
                    }
                }
            }
            return OPZ.traduci("cambia_nome_successo");
        } else return OPZ.traduci("cambia_nome_fallito");
    }

    public static ArrayList<Integer> elencaPartite(boolean partite,boolean includiTorneo) {
        // partite true per partite, false per tornei
        // includi torneo true per includere le partite che fanno parte del torneo
        File folder;
        if (partite) folder = new File("salvataggi/partite");
        else folder = new File("salvataggi/tornei");
        ArrayList<Integer> out = new ArrayList<>();
        if (folder.isDirectory()) {
            File[] cartella = folder.listFiles();
            Gson gson = new Gson();
            assert cartella != null;
            for (File file : cartella) {
                if (file.isFile()) {
                    String s = file.getName().substring(0, file.getName().length() - 5);
                    if (partite){
                        int n = gson.fromJson(Utili.leggiFileJson("partite", s), Partita.class).getId();
                        if(Partita.carica(n).getId()==0||includiTorneo) out.add(n);
                    }
                    else out.add(gson.fromJson(Utili.leggiFileJson("tornei", s), Torneo.class).getId());
                }
            }
        }
        return out;
    }
    public static void elencaPartiteNormali(){
        elencaPartite(true, false);
    }

    public static void elencaTornei(){
        elencaPartite(false,false);
    }

    public static String adminEliminaPartita(int id) {
        if (esistePartita(id, true)) {
            Partita p = Partita.carica(id);
            if (p.getIdTorneo() == 0) {
                eliminaPartita(id);
                return OPZ.traduci("partita_eliminata");
            } else return OPZ.traduci("partita_in_torneo");
        } else return OPZ.traduci("partita_non_trovata");
    }

    public static String adminEliminaTorneo(int id) {
        if (esistePartita(id, false)) {
            eliminaTorneo(id);
            return OPZ.traduci("torneo_eliminato");
        } else return OPZ.traduci("torneo_non_trovato");
    }

    public static void bottoneTorneo(Button b, int codice) {
        System.out.println("inizializzato bottone"+b.getId()+",codiec: "+codice);

        Partita p = Partita.carica(codice);
        if (p.getVincitore() == null) {
            b.setDisable(false);
            b.setText(p.getGiocatori()[0].getNome() + " - " + p.getGiocatori()[1].getNome());
        } else {
            b.setDisable(true);
            b.setText(OPZ.traduci("vincitore")+": "+ p.getVincitore().getNome());
        }
    }
    public static void cancellaTorneiInSospeso(){
        ArrayList<Integer> lista = elencaPartite(false,false);
        for(int n : lista ){
            Torneo t = Torneo.carica(n);
            if(t.isFinito()) t.elimina();
        }
    }
}
