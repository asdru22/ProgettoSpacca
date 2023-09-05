package gioco.progettospacca.classi;

public class Amministratore {
    private String nome;
    private String password;

    public Amministratore(String nome, String password){
        this.nome = nome;
        this.password = password;
    }
    public String getNome(){
        return nome;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public String toString(){
        return "{Nome: "+nome+", Password: "+password+"}";
    }
}
