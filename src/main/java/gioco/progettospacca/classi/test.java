package gioco.progettospacca.classi;


public class test {
    public static void main(String[] args) {
        Torneo t = Torneo.carica(19716);
        System.out.println(t.getPartite());
        t.fineTorneo();
    }
}
