# SPACCA
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
[Canva](https://www.canva.com/it_it/): Editor di immagini
## Partita
1. Cliccare crea partita
2. Fare login con username `admin` e password `spacca`
3. Inserire i nomi dei giocatori e selezionare eventuali bot
4. Cliccare "crea partita" e copiare il codice generato
5. Tornare alla home
6. Cliccare gioca partita
7. Inserire per ogni giocatore il codice della parita da giocare (Admin possono entrare in tutte le partite)
## Torneo
1. Cliccare crea torneo
2. Fare login con username `admin` e password `spacca`
3. Scegliere il numero di giocatori per il torneo
4. Inserire il nome dei giocatori. Se il numero dei giocatori è inferiore a quello dei giocatori selezionati, verranno automaticamente creati bot per raggiungere il numero richiesto
5. Tornare alla home
6. Cliccare gioca torneo e inserire il codice del torneo
7. Cliccare la partita da giocare
## Privilegi Amministratore
Eseguendo l'accesso da amministratore, oltre che creare partite e tornei, è possibile modificare i nomi dei giocatori, cancellare partite e tornei in sospeso e cambiare il numero dei turni delle partite.
## Opzioni
Dal menù impostazioni si può
- cambiare lingua
- attivare o disattivare effetti sonori e musica
- uscire da una partita salvando i progressi
## Suddivisione del lavoro
- Nanni: Tutte le classi nella cartella [classi](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/classi) ad eccezione di qualche metodo in `Partita.java` e `Carta.java` . Vari collegamenti con le opzioni, salvataggi e metodi della classe Utili all'interno dei [controller](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller)
- Manieri: Grafica e design (FXML), collegamenti tra le scene e classi [controller](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller) associate, creazione di tutte le immagini.
- Silvestri: [PartitaController.java](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller/PartitaController.java): Implementazione partita vera e propria (animazioni, bot, eventi e algoritmo principale di gioco). Gestione di tutti gli eventi dell'intera applicazione (eventi javafx: mouseEvent, clickEvent, keyEvent ecc...), metodi funzionali alla partita nelle varie classi nella cartella [classi](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/classi). Meccanismo di invio mail.
