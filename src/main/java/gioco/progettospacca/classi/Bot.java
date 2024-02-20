package gioco.progettospacca.classi;

import java.io.File;

public class Bot extends Giocatore{
    public Bot(String nome){
        super(nome);
    }
    public String getNome(){
        return this.nome;
    }
    public void salva(){

    }
    public void aggiungiSalvataggio(){

    }

    public int sceltaCasuale(int min, int max){
        return Utili.intCasuale(min,max);
    }


}
