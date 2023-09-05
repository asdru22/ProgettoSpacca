package gioco.progettospacca.classi;
// librerie esterne
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
// librerie interne
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Gestore {

    public int scriviPartita(Giocatore[] giocatori){
        Partita p = new Partita(randInt(1,100),giocatori);
        p.impostaIdGiocatori();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("src/main/java/gioco/progettospacca/salvataggi/"+p.getId()+".json")) {
            writer.write(gson.toJson(p));
        } catch (IOException e) {
            System.err.println("Errore di scrittura file");
        }
        return p.getId();
    }
    public String leggiFileJson(String nomeFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/gioco/progettospacca/salvataggi/"+nomeFile));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
            return  jsonBuilder.toString();
        }   catch (IOException e) {
            System.err.println("Errore di lettura file");
            return "file not found error";
        }
    }
    public static int randInt(int min,int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    public Giocatore[] aggiungiGiocatori(){
        Scanner scanner = new Scanner(System.in);
        String giocatore;
        ArrayList<Giocatore> temp = new ArrayList<>();
        System.out.print("Inserisci giocatori (digitare \"exit\" per finire)\n> ");
        do {
            giocatore = scanner.nextLine();
            if(!Objects.equals(giocatore, "exit")) {
                System.out.print("Aggiunto giocatore: "+giocatore+"\n> ");
                temp.add(new Giocatore(giocatore, -1));
            }

        } while (!Objects.equals(giocatore, "exit"));
        // converti arraylist in array
        Giocatore[] g = new Giocatore[temp.size()];
        g = temp.toArray(g);
        return g;
    }
    public Partita leggiPartita(int id){
        Gson gson = new Gson();
        // convertire partita.json in classi
        Partita partita = gson.fromJson(leggiFileJson(id+".json"), Partita.class);
        // stampa
        System.out.println();
        System.out.println("Partita:\n"+partita);
        return partita;
    }
    public void autenticazione(){
        Gson gson = new Gson();
        // piglia a passwor da file admin
        Amministratore amministratore = gson.fromJson(leggiFileJson("_admin.json"), Amministratore.class);
        String password_admin = amministratore.getPassword();
        Scanner scanner = new Scanner(System.in);
        String password_tentativo;

        // input utent
        System.out.print("Benvenuto, "+ amministratore.getNome()+". Inserisci la password per continuare: ");
        do {
            password_tentativo = scanner.nextLine();
            if(!Objects.equals(password_admin, password_tentativo)) System.out.print("Password Errata! Riprova: ");

        } while (!Objects.equals(password_admin, password_tentativo));
        System.out.println("Benvenuto, "+amministratore.getNome());
    }
}
