# Pokermon
### Requisiti
Per eseguire il jar è necessaria almeno la versione 21 di java ([Download](https://www.oracle.com/it/java/technologies/downloads/#java21))
### Software usato
IntelliJ: IDE e download librerie  
Github Dekstop: Upload e download modifiche dalla repository online  
Scene Builder: Creazione file fxml
### Librerie Usate
JavaFX: Parte grafica del progeto  
GSON: Lettura/scrittura/caricamento file Json
### Siti web usati
[GitHub](https://github.com/): Workspace condiviso  
[Freesound](https://freesound.org/): Audio ed effetti sonori
## Utilizzo
1. Cliccare crea partita
2. Fare login con username `admin` e password `spacca`
3. Inserire i nomi dei giocatori e selezionare eventuali bot
4. Cliccare "crea partita" e copiare il codice generato
5. Tornare alla home
6. Cliccare gioca partita
7. Inserire per ogni giocatore il codice della parita da giocare (Admin possono entrare in tutte le partite)

### Regole
| Combinazione  | Punteggio |
|---------------|-----------|
| Coppia        | 10        |
| Tris          | 20        |
| Poker         | 45        |
| Manita        | 100       |
| Scala 3       | 15        |
| Scala 4       | 35        |
| Scala 5       | 60        |
| 3 Stesso seme | 5         |
| 4 Stesso seme | 30        |
| 5 Stesso seme | 80        |

| Comanda Fuoco | Comanda Acqua | Comanda Erba | Comanda Elettro | Comanda Terra |
|---------------|---------------|--------------|-----------------|---------------|
| 0.5x Erba     | 0.5x Fuoco    | 0.5x Fuoco   | 0.5x Terra      | 0.5x Elettro  |
| 0.5x Acqua    | 0.5x Terra    | 1x Elettro   | 1x Fuoco        | 0.5x Fuoco    |
| 1x Elettro    | 2x Erba       | 2x Terra     | 1x Erba         | 2x Erba       |
| 2x Terra	     | 2x Elettro    | 2x Acqua     | 2x Acqua        | 2x Acqua      |