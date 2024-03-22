package gioco.progettospacca.classi;

import java.util.*;

public class test {
    public static void main(String[] args) {
        int[] numeri = {2, 2, 1, 2, 1}; // Cambia questi numeri per testare diversi casi

        // Conta quante volte ogni numero appare nel vettore
        Map<Integer, Integer> conteggioNumeri = new HashMap<>();
        for (int numero : numeri) {
            conteggioNumeri.put(numero, conteggioNumeri.getOrDefault(numero, 0) + 1);
        }
        System.out.println(conteggioNumeri);

        // Trova e rimuovi tutte le voci con valore 1 che devono essere ignorate per la valutazione in quanto si comincia a valutare dalla coppia in su
        Iterator<Map.Entry<Integer, Integer>> iterator = conteggioNumeri.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getValue() == 1) {
                iterator.remove();
            }
        }

        System.out.println(conteggioNumeri);
        System.out.println("Size "+conteggioNumeri.size());

        //inserisco i valori in una collezione per sapere se ho doppia coppie o tris e coppia
        Collection<Integer> valori = conteggioNumeri.values();
        System.out.println(valori);
        //converto in array così riesco a prendere le posizioni singole della collezione ed effettuare dei confronti
        Integer[] array = valori.toArray(new Integer[0]);
        // Verifica il tipo di mano
        switch (conteggioNumeri.size()) {
            case 1:
                if(array[0]==2){
                    System.out.println("coppia");
                } else if (array[0]==3) {
                    System.out.println("tris");
                } else if (array[0]==4) {
                    System.out.println("poker");
                } else if (array[0]==5) {
                    System.out.println("manita");
                }
                break;
            case 2:
                if((array[0]==2 && array[1]==3) || (array[0]==3 && array[1]==2)){
                    System.out.println("full");
                } else if (array[0]==2 && array[1]==2) {
                    System.out.println("doppia coppia");
                }
                break;

        }
    }
}
