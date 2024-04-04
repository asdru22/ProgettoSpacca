package gioco.progettospacca.classi;


public class test {
    public static void main(String[] args) {
        Torneo t = Torneo.carica(76048);
        t.skip();
        System.out.println(t.getGiocatori().size());
    }
}
