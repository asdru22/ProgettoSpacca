package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.animation.FadeTransition;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static gioco.progettospacca.controller.Main.OPZ;

public class Utili {
    public static void salva(String tipo, String nome, Object o) {
        // metodo base per salvare un oggetto in fila json
        try (FileWriter writer = new FileWriter("salvataggi/" + tipo + "/" + nome + ".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(o));
        } catch (IOException e) {
            System.err.println("Errore salvataggio oggetto");
        }
    }

    public static String leggiFileJson(String tipo, String nome) {
        // metodo base per caricare un file json in un oggetto
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
        // itera sui file salvati per trovare i giocatori
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


    public static void elimina(String id, String cartella) {
        // elimina file
        File fileDaEliminare = new File("salvataggi/" + cartella + "/" + id + ".json");
        fileDaEliminare.delete();
    }

    public static void eliminaGiocatore(String nome) {
        elimina(nome, "giocatori");
    }
    public static void eliminaTorneo(String id) {
        elimina(id, "tornei");
    }

    public static void eliminaPartita(int id) {
        elimina(id + "", "partite");
    }

    public static boolean esisteGiocatore(String nome) {
        // controlla se esiste un giocatore con un certo nome
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
        // controlla se esiste una partita/torneo con un dato id
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

    public static void controllaNome(String nome, int id, boolean bot, List<Giocatore> lista) {
        // metodo che determina se il giocatore inserito è giocatore/bot
        if (!Objects.equals(nome, "")) {
            boolean esiste = esisteGiocatore(nome);
            Giocatore g;

            if (esiste) {
                g = Giocatore.carica(nome);
            } else {
                g = new Giocatore(nome, bot);
            }

            if (!lista.contains(g)) {
                g.aggiungiPartita(id);
                g.salva();
                lista.add(g);
            }
        }
    }

    public static Giocatore controllaNomeTorneo(String nome, boolean bot, List<Giocatore> lista) {
        // controlla se un giocatore appartiene a un torneo
        if (!Objects.equals(nome, "")) {
            boolean esiste = esisteGiocatore(nome);
            Giocatore g;
            if (esiste) {
                g = Giocatore.carica(nome);
            } else {
                g = new Giocatore(nome, bot);
            }

            if (lista.contains(g)) return null;
            g.salva();
            return g;
        } else {
            return null;
        }
    }

    public static int leggiInt(TextField tx) {
        try{
            return Integer.parseInt(tx.getText());
        } catch (Exception e){
            return -1;
        }
    }

    public static void gestisciMusica(ToggleButton tglb) {
        // funzionalità bottone musica
        if (tglb.isSelected()) {
            OPZ.pausaMusica();
            tglb.setText(OPZ.traduci("musica_off"));
        } else {
            OPZ.riprendiMusica();
            tglb.setText(OPZ.traduci("musica_on"));
        }
    }

    public static void gestisciSuoni(ToggleButton tglb) {
        // funzionalità bottone effetti sonori
        if (tglb.isSelected()) {
            OPZ.pausaSfx();
            tglb.setText(OPZ.traduci("suono_off"));
        } else {
            OPZ.riprendiSfx();
            tglb.setText(OPZ.traduci("suono_on"));
        }
    }

    public static String cambiaNomeGiocatore(String vecchio, String nuovo) {
        // cambia nome di un giocatore e modifica tutti i file in cui è salvato
        if (esisteGiocatore(vecchio) && !esisteGiocatore(nuovo)) {
            Giocatore g = Giocatore.carica(vecchio);
            if (g.isBot()) {
                return OPZ.traduci("bot_non_rinominabile");
            }
            eliminaGiocatore(vecchio);
            g.setNome(nuovo);
            g.salva();
            // cambia i nomi salvati nelle partite
            ArrayList<Integer> idPartite = elencaPartite(true, true);
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
            ArrayList<Integer> idTornei = elencaPartite(false, false);
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

    public static ArrayList<Integer> elencaPartite(boolean partite, boolean includiTorneo) {
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
                    if (partite) {
                        int n = gson.fromJson(Utili.leggiFileJson("partite", s), Partita.class).getId();
                        if (Partita.carica(n).getIdTorneo() == 0 || includiTorneo) out.add(n);
                    } else out.add(gson.fromJson(Utili.leggiFileJson("tornei", s), Torneo.class).getId());
                }
            }
        }
        return out;
    }

    public static ArrayList<String> elencaGiocatori() {
        // partite true per partite, false per tornei
        // includi torneo true per includere le partite che fanno parte del torneo
        File folder = new File("salvataggi/giocatori");
        ArrayList<String> out = new ArrayList<>();
        if (folder.isDirectory()) {
            File[] cartella = folder.listFiles();
            Gson gson = new Gson();
            assert cartella != null;
            for (File file : cartella) {
                if (file.isFile()) {
                    String s = file.getName().substring(0, file.getName().length() - 5);
                    out.add(gson.fromJson(Utili.leggiFileJson("giocatori", s), Giocatore.class).getNome());
                }
            }
        }
        return out;
    }

    public static ArrayList<Integer> elencaPartiteNormali() {
        return elencaPartite(true, false);
    }

    public static ArrayList<Integer> elencaTornei() {
        return elencaPartite(false, false);
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
            Torneo.carica(id).elimina();
            return OPZ.traduci("torneo_eliminato");
        } else return OPZ.traduci("torneo_non_trovato");
    }

    public static void bottoneTorneo(Button b, int codice) {
        // testo bottoni del tabellone dei tornei
        Partita p = Partita.carica(codice);
        if (p.getVincitore() == null) {
            b.setDisable(false);
            b.setText(p.getGiocatori()[0].getNome() + " - " + p.getGiocatori()[1].getNome());
        } else {
            b.setDisable(true);
            b.setText(OPZ.traduci("vincitore") + ": " + p.getVincitore().getNome());
        }
    }

    public static int checkBox(TextField text, CheckBox check, int c) {
        // imposta il nome dei bot se selezionato
        OPZ.premiBottone();
        if (check.isSelected() && !text.isDisable()) {
            text.setDisable(true);
            c = c + 1;
            text.setText("bot" + c);
        } else {
            text.setDisable(false);
            text.setText("");
            c = c - 1;
        }
        return c;
    }

    public static String cambiaEmail(String nome, String email) {
        Giocatore g = Giocatore.carica(nome);
        if (g.isBot()) {
            return OPZ.traduci("bot_non_hanno_mail");
        } else {
            g.setEmail(email);
            return OPZ.traduci("mail_cambiata");
        }
    }

    public static void giocatoriMancanti(int n, Label lblBot) {
        if(n>=1){
            lblBot.setText(OPZ.traduci("bot_aggiunti")+": "+n);
        }
    }
    public static void erroreLogin(Label lbl){
        Utili.fadeText(lbl);
        lbl.setText(OPZ.traduci("credenziali_sbagliate"));
    }

    public static void fadeText(Label lbl) {
        lbl.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), lbl);
        fadeTransition.setFromValue(1.0); // Opacità iniziale
        fadeTransition.setToValue(0.0);   // Opacità finale (scomparirà)
        fadeTransition.play();
        fadeTransition.setOnFinished(event -> {
            lbl.setVisible(false);
            lbl.setText("");
        });
    }

    public static void cancellaTorneiInSospeso(){
        ArrayList<Integer> lista = elencaTornei();
        for(int i : lista){
            Torneo t = Torneo.carica(i);
            if(t.isFinito()) t.elimina();
        }
    }
}