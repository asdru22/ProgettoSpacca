package gioco.progettospacca.classi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        try (FileWriter writer = new FileWriter("src/main/java/gioco/progettospacca/salvataggi/impostazioni.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(this));
        } catch (IOException e) {
            System.err.println("Errore salvataggio impostazioni");
        }
    }

    public static Opzioni carica() {
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/gioco/progettospacca/salvataggi/impostazioni.json"));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
            return gson.fromJson(jsonBuilder.toString(), Opzioni.class);
        } catch (IOException e) {
            System.err.println("File impostazioni non trovato, creazione nuovo");
            Opzioni o = new Opzioni();
            o.salva();
            return o ;
        }
    }

    public void playMusica(String nome){
        if (musica != null) {
            musica.stop();
            musicaPausa = true;
        }
        musica = new MediaPlayer(new Media(new File("src/main/resources/gioco/progettospacca/suoni/" + nome).toURI().toString()));
        musica.setCycleCount(MediaPlayer.INDEFINITE);
        musica.play();
        musicaPausa = false;
        System.out.println("playing music");
        this.salva();
    }
    public void pausaMusica(){
        if(musica!=null) musica.pause();

        musicaPausa = true;
        this.salva();
    }
    public void riprendiMusica(){
        if(musica!=null) musica.play();
    }

    public void playSfx(String nome){
        sfx = new MediaPlayer(new Media(new File("src/main/resources/gioco/progettospacca/suoni/" + nome).toURI().toString()));
        sfx.setCycleCount(1);
        if(!sfxPausa) sfx.play();
        this.salva();
        System.out.println("playing sfx");
    }
    public void pausaSfx(){
        if(sfx!=null) sfx.stop();
        sfxPausa = true;
        this.salva();
    }
    public void riprendiSfx(){
        if(sfx!=null) sfx.play();
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

    public boolean getSuono() {
        return sfxPausa;
    }
    public boolean getMusica() {
        return musicaPausa;
    }
}
