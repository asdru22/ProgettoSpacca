# SPACCA
### Requisiti
Per eseguire il jar è necessaria almeno la versione 21 di java ([Download](https://www.oracle.com/it/java/technologies/downloads/#java21))
### Software usato
IntelliJ: IDE e download librerie.  
Github Dekstop: Upload e download modifiche dalla repository online.  
Scene Builder: Creazione file `.fxml`.
### Librerie Usate
JavaFX: Parte grafica del progeto  
GSON: Lettura/scrittura/caricamento file Json
Javax.mail: API per invio mail
### Siti web usati
[GitHub](https://github.com/): Workspace condiviso  
[Freesound](https://freesound.org/): Audio ed effetti sonori  
[Canva](https://www.canva.com/it_it/): Editor di immagini
## Partita
1. Cliccare crea partita
2. Fare login con username `admin` e password `spacca`
3. L'admin inserisce i nomi dei giocatori ed eventualmente le loro mail
4. L'admin sceglie eventuali bot 
5. Cliccare "crea partita" e copiare il codice generato
6. Tornare alla home
7. Cliccare gioca partita
8. Ogni giocatore inseirsce il proprio nome e il codice della parita da giocare (Admin possono entrare in tutte le partite)
## Torneo
1. Cliccare crea torneo
2. Fare login con username `admin` e password `spacca`
3. L'admin sceglie il numero di giocatori per il torneo
4. L'admin inserisce i giocatori. Se il numero dei giocatori è inferiore a quello dei giocatori selezionati, verranno automaticamente creati bot per raggiungere il numero richiesto
5. Tornare alla home
6. Cliccare gioca torneo e inserire il codice del torneo
7. Appare la schermata del tabellone con le varie partite da giocare. Certe partite saranno bloccate fino a quando non terminano tutte quelle del round precedente 
8. Cliccare la partita da giocare
9. Finita la finale, il torneo verrà eliminato
## Privilegi Amministratore
Eseguendo l'accesso da admin, oltre che creare partite e tornei, è possibile
- modificare i nomi dei giocatori e le loro mail
- creare giocatori nuovi
- cancellare partite e tornei in sospeso
- cambiare il numero dei turni delle partite
## Opzioni
Dal menù impostazioni si può
- cambiare lingua
- attivare o disattivare effetti sonori e musica
- uscire da una partita salvando i progressi
- visualizzare le regole
- visualizzare la classifica
## Sviluppo del progetto
### Funzionalità obbligatorie
#### Partita
#### Torneo
Quando viene creato un nuovo torneo, si crea un numero di partite uguale alla metà dei giocatori inseriti. A ogni coppia di giocatori viene assoicata una di queste partite i cui codici vengono salvati nel torneo. Quando tutte le partite salvate sono finite si passa al round successivo. I vincitori delle partite prrecedenti diventano i giocatori delle nuove partite fino a quando rimane un solo vincitore.
#### Salvataggi
Le classi Partita, Giocatore e Torneo hanno una funzione `salva` e `carica`. Il metodo carica è statico è quindi può essere chiamato in qualsiasi momento per creare una nuova istanza di quella classe. Il metodo salva converte la classe in un file `.json`. Entrambi i metodi richiedono la creazione di un nuovo oggetto di tipo `Gson`.
#### Gestione bot
#### Layout
#### Modifica dati dei giocatori
Funzioni nella classe di utilità quali `elencaGiocatori`, `esisteGiocatore` e `eliminaGiocatore` hanno facilitato la gestione dei giocatori. Sono usati controlli per evitare che queste modifiche possano essere fatte a Bot o all'admin.
### Funzionalità facoltative
#### Invio Mail
#### Suoni e Audio
All'avvio dell'applicazione viene creata una variabile statica di tipo `Opzioni` che contiene un `MediaPlayer` per la musica e uno per gli effetti sonori. Dal menù impostazioni si possono pausare/riprendere questi due oggetti per disattivare e riattivare le traccie audio separatamente.
#### Cambio Lingua
Sempre nella classe statica `Opzioni` è memorizzata la lingua. `Opzioni` ha un metodo che data una stringa restituisce la traduzione corrispondente nella lingua selezionata usando una `ResourceBundle`.
#### CSS
#### Applicazione interamente utilizzabile da tastiera
#### Tabellone Torneo
Ogni torneo ha una variabile che memorizza il round corrente e quello massimo (`=log_2(num_giocatori_iniziali)`). Per esempio nel torneo da 4 giocatori, quando il numero round è uguale a 2 significa che si sta già giocando la finale, e quindi il bottone per giocare le due semifinali non sono più cliccabili (anche perché le due partite sono finite).
#### Interruzione e ripristino partite di un torneo
Nella classe torneo sono salvati i codici di tutte le partite giocate. Questo permette di caricare e salvare una partita come se fosse giocata singolarmente. Il tabellone inoltre permette facilmente di riaccedere a una partita iniziata ma non finita.
#### Cursore
## Suddivisione del lavoro
- Nanni: Tutte le classi nella cartella [classi](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/classi) a eccezione di `MailThread.java`, `Partita.java` e `Giocatore.java`. Vari collegamenti con le partite, opzioni, salvataggi e metodi della classe `Utili.java` all'interno dei [controller](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller), in particolare quelli usati per i tornei e i privilegi admin.
- Manieri: Grafica e design (FXML), collegamenti tra le scene e classi [controller](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller) associate, creazione di tutte le immagini. Cursore personalizzato.
- Silvestri: [PartitaController.java](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller/PartitaController.java): Implementazione partita vera e propria (animazioni, bot, eventi e algoritmo principale di gioco). Gestione di tutti gli eventi dell'intera applicazione (eventi javafx: mouseEvent, clickEvent, keyEvent ecc...), metodi funzionali alla partita nelle classi `Partita.java` e `Giocatore.java` nella cartella [classi](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/classi). Meccanismo di invio mail.