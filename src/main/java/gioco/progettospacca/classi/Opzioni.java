package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gioco.progettospacca.controller.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;


public class Opzioni {
    private Locale lingua = Locale.ITALIAN;
    private boolean sfxPausa = false;
    private boolean musicaPausa = false;

    private static MediaPlayer musica;
    private static MediaPlayer sfx;

    public Opzioni() {
    }

    public void salva() {
        try (FileWriter writer = new FileWriter("salvataggi/impostazioni.json")) {
            // crea classe gson che permette l'indentazione del dizionario
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // scrivi il file json della classe convertita in stringa in formmato json
            writer.write(gson.toJson(this));
        } catch (IOException e) {
            System.err.println("Errore salvataggio impostazioni");
        }
    }

    public static Opzioni carica() throws IOException {
        try {
            Gson gson = new Gson();
            // creo lettore
            BufferedReader reader = new BufferedReader(new FileReader("salvataggi/impostazioni.json"));
            // classe speciale per estendere stringhe
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
            System.out.println(">>> Opzioni Caricate!");
            // restituisce la stringa json convertita in classe
            return gson.fromJson(jsonBuilder.toString(), Opzioni.class);
        } catch (IOException e) {
            System.err.println("[!] Esecuzione jar per la prima volta");

            return Opzioni.inizializza();
        }
    }

    private static Opzioni inizializza() {
        // crea cartelle salvataggi per la prima esecuzione in assoluto
        File d_salvataggi = new File("salvataggi");
        d_salvataggi.mkdir();

        File d_giocatori = new File("salvataggi/giocatori");
        d_giocatori.mkdir();
        new Giocatore("admin").salva();

        File d_partite = new File("salvataggi/partite");
        d_partite.mkdir();

        File d_tornei = new File("salvataggi/tornei");
        d_tornei.mkdir();

        Opzioni o = new Opzioni();
        o.salva();
        return o;
    }

    public void playMusica(String nome) {
        if (musica != null) { // stoppa musica precedente
            musica.stop();
        }
        // carica il path come path apposta delle risorse
        String pathRisorse = Main.class.getResource("/gioco/progettospacca/suoni/" + nome).toString();
        musica = new MediaPlayer(new Media(pathRisorse));
        musica.setCycleCount(MediaPlayer.INDEFINITE);
        if (!musicaPausa) {
            musica.play();
        }
        this.salva();
    }

    public void pausaMusica() {
        if (musica != null) musica.pause();
        musicaPausa = true;
        this.salva();
    }

    public void riprendiMusica() {
        if (musica != null) musica.play();
        musicaPausa = false;
        this.salva();
    }

    public void playSfx(String nome) {
        String pathRisorse = Main.class.getResource("/gioco/progettospacca/suoni/" + nome).toString();
        sfx = new MediaPlayer(new Media(pathRisorse));
        sfx.setCycleCount(1);
        if (!sfxPausa) {
            sfx.play();
        }
        this.salva();
    }

    public void pausaSfx() {
        if (sfx != null) sfx.stop();
        sfxPausa = true;
        this.salva();
    }

    public void riprendiSfx() {
        if (sfx != null) sfx.play();
        sfxPausa = false;
        this.salva();
    }

    public String traduci(String valore) {
        ResourceBundle bundle = ResourceBundle.getBundle("testo", lingua);
        return bundle.getString(valore);
    }

    public String getLingua() {
        return lingua.toString();
    }

    public void setLingua(Locale lingua) {
        this.lingua = lingua;
        this.salva();
    }

    public void premiBottone() {
        playSfx("bottone.wav");
    }

    public void premiFreccia() {
        playSfx("freccia.wav");
    }

    public void cliccaCarta() {
        playSfx("carta.wav");
    }

    public void vittoria() {
        playSfx("vittoria.wav");
    }

    public boolean getSuono() {
        return sfxPausa;
    }

    public boolean getMusica() {
        return musicaPausa;
    }
}
