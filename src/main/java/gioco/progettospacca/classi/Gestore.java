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
    private Partita partita = null;
    private Torneo torneo = null;
    //Si occupa di tutte le robe riguardanti login, iniziare partite, leggere e scrivere da file
    public Partita scriviPartita(Giocatore[] giocatori){
        Partita p = new Partita(randInt(1,100),giocatori);
        p.impostaIdGiocatori();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("src/main/java/gioco/progettospacca/salvataggi/"+p.getId()+".json")) {
            writer.write(gson.toJson(p));
        } catch (IOException e) {
            System.err.println("Errore salvataggio file");
        }
        return p;
    }
    private static int randInt(int min,int max){
        return min + (int)(Math.random() * ((max - min) + 1));
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
            System.err.println("Errore di aprtura file");
            return "errore";
        }
    }

    public Giocatore[] aggiungiGiocatori(String tipo){
        Scanner scanner = new Scanner(System.in);

        Giocatore[] r = null;
        if(!tipo.equals("PartitaCPU")) r = aggiungiGiocatori(scanner,tipo); // per partite tra giocatori e tornei
        //if(tipo.equals("PartitaCPU")) aggiungiGiocatoreCPU(scanner);
        return r;
    }
    private Giocatore[] aggiungiGiocatoreCPU(Scanner scanner){
        System.out.print("Inserisci giocatore\n> ");
        String giocatore = scanner.nextLine();
        Giocatore[] r = new Giocatore[1];
        r[0] = new Giocatore(giocatore, -1);
        return r;
    }
    private Giocatore[] aggiungiGiocatori(Scanner scanner,String tipo){
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
        inizia(g,tipo);
        return g;
    }
    private void inizia(Giocatore[] g,String tipo){
        if(Objects.equals(tipo, "Partita")) iniziaPartita(g);
        else if(Objects.equals(tipo, "Torneo")) iniziaTorneo(g);
    }

    private void iniziaPartita(Giocatore[] g){
        // cose temporanee per testare
        Partita p = scriviPartita(g);
        // crea partita dato id
        this.partita =leggiPartita(p.getId());
    }
    private void iniziaTorneo(Giocatore[] g){
        this.torneo = new Torneo(g);
        torneo.inizia();
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
