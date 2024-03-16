package gioco.progettospacca.classi;

public class Amministratore extends Giocatore {
    private String password;

    public Amministratore(String nome, String password){
        super(nome);
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public String toString(){
        return "{Nome: "+this.getNome()+", Password: "+password+"}";
    }
}
