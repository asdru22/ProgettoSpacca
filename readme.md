### Requisiti
Per eseguire il file `.jar` è necessaria java 21+ di java ([Download](https://www.oracle.com/it/java/technologies/downloads/#java21))
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
# Progetto
Spacca è un gioco di carte che unisce l'abilità strategica tipica del poker al magico mondo degli elementi naturali: Elettro, Fuoco, Acqua, Terra, Erba.  
Vince chi alla fine del numero di round prestabiliti ha più punti.
## Partita
1. Cliccare crea partita
2. Fare login con username `admin` e password `spacca`
3. L'admin inserisce i nomi dei giocatori ed eventualmente le loro mail
4. L'admin sceglie eventuali bot
5. Cliccare "crea partita" e copiare il codice generato
6. Tornare alla home
7. Cliccare gioca partita
8. Ogni giocatore inserisce il proprio nome e il codice della partita da giocare (Admin possono entrare in tutte le partite)
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
## Suddivisione del lavoro
- Nanni: Classi nella cartella [classi](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/classi) a eccezione di `MailThread.java`, `Partita.java` e `Giocatore.java`. Vari collegamenti tra i metodi della classe `Utili.java`  e `Opzioni.java` con quelli dei [controller](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller). Metodi usati nei controller per tornei e privilegi amministratore.
- Manieri: Grafica e design (FXML), collegamenti tra le scene e classi [controller](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller) associate, creazione di tutte le immagini. Cursore personalizzato.
- Silvestri: [PartitaController.java](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller/PartitaController.java): Implementazione e funzionalità della partita (algoritmo principale di gioco, animazioni, bot). Gestione e azioni per eventi di input utente (eventi javafx: `mouseEvent`, `clickEvent`, `keyEvent`, ...). Metodi funzionali alla partita nelle classi `Partita.java` e `Giocatore.java` nella cartella [classi](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/classi). Sistema di invio mail.
## Sviluppo del progetto
### Funzionalità obbligatorie
#### Partita
La partita è gestita a turni, a ogni nuovo turno viene ricaricata la scena con i dati aggiornati della partita e del giocatore interessato. Grazie a questo è possibile interrompere sempre la partita e riprenderla nel punto in cui ci si era fermati. Abbiamo utilizzato le animazioni di javafx per visualizzare il movimento delle carte e pause per attendere che le animazioni vengano completate. Questo permette anche di rendere le azioni dei bot visibili e comprensibili. Esiste inoltre una funzione pausa richiamabile tramite il pulsante `esc` in cui si può visualizzare le regole, impostare l'audio e la musica e uscire dalla partita.
#### Torneo
Quando viene creato un nuovo torneo, si crea un numero di partite uguale alla metà dei giocatori inseriti. A ogni coppia di giocatori viene assoicata una di queste partite i cui codici vengono salvati nel torneo. Quando tutte le partite salvate sono finite si passa al round successivo. I vincitori delle partite del round precedente diventano i giocatori di quelle del round successivo fino a quando rimane un solo vincitore.
#### Salvataggi
Le classi Partita, Giocatore e Torneo hanno una funzione `salva` e `carica`. Il metodo carica è statico è quindi può essere chiamato in qualsiasi momento per creare una nuova istanza di quella classe. Il metodo salva converte la classe in un file `.json`. Entrambi i metodi richiedono la creazione di un nuovo oggetto di tipo `Gson`, che è alla base della lettura dei file e creazione di oggetti.
#### Gestione bot
Il bot è un oggetto della classe Giocatore che utilizza gli stessi metodi ma con una particolarità, che ha la variabile booleana `bot` impostata a true, in questo modo saremo in grado di capire come dirigere il turno del bot nella partita attraverso un semplice if.  
Per la gestione del bot viene distinto il blocco di codice che segue il bot rispetto a un utente reale. La prima scelta  
del bot è quella di scartare oppure stare, viene deciso in modo randomico attraverso un metodo che restituisce un numero casuale tra 1 e 2. La seconda scelta avviene soltanto se il bot ha deciso di scartare e il funzionamento è il medesimo della scelta tra stare e scartare (numero casuale tra 1 e 3 carte da scartare).  
Il bot inoltre riesce anche a terminare il turno in modo indipendente, quindi una partita tra soli bot non ha bisogno di input di persone reali per continuare.
#### Layout
Il layout è stato gestito tramite Pane e Anchor Pane come contenitori generali e Grid pane per la suddivisione ordinata degli elementi al fine di creare un'interfaccia semplice, pulita e intuitiva.
#### Modifica dati dei giocatori
Nella schermata "Privilegi Admin" è possibile modificare i dati dei giocatori (nome, mail), ed eliminarli se non hanno partite in sospeso.  
Funzioni nella classe `Utili` quali `elencaGiocatori`, `esisteGiocatore` e `eliminaGiocatore` hanno facilitato la gestione dei giocatori. Sono usati controlli per evitare che queste modifiche possano essere fatte a Bot o all'admin.
#### Carte Imprevisto
Sono presenti due carte imprevisto implementate con un seme "neutro" all'interno della definizione carta così per poterle aggiungere al mazzo. Le carte imprevisto, per scelta, si possono trovare soltanto alla prima mano del turno e non possono essere pescate dopo aver scartato. Una volta pescato un imprevisto viene mostrato l'effetto e la carta è spostata nello spazio imprevisti sul campo. Al suo posto verrà pescata in automatico una nuova carta (senza interferire con la possibilità di scartare comunque 3 carte successivamente).
### Funzionalità facoltative
#### Invio Mail
Per l'invio delle mail abbiamo utilizzato i thread in quanto l'invio non è immediato, quindi per evitare di bloccare l'applicazione per svariati secondi abbiamo deciso di optare per questa soluzione. La libreria utilizzata è `javax.mail`, installata con le dipendenze maven (come `GSON` e `JavaFX`). Le mail vengono inviate da un account Google creato da noi.  
Per accedere ai servizi di Gmail abbiamo richiesto dall'account una password apposita per avere accesso all'account da applicazioni secondarie.  
Meccanismo invio mail: quando viene modificato il nome di un giocatore con mail registrata, gli arriverà una mail che comunica il cambio di nome.  
Partita: arriva la mail a tutti i giocatori con mail registrata al momento della creazione della partita.  
Finita la partita arriva la mail (a chi l'ha registrata) contenente le informazione e il vincitore della partita.  
Torneo: arriva la mail a tutti i partecipanti(a chi l'ha registrata) al momento della creazione del torneo. Finito il torneo arriva una mail a tutti i partecipanti, anche quelli eliminati, che mostra il vincitore del torneo.
#### Suoni e Audio
All'avvio dell'applicazione viene creata una variabile statica di tipo `Opzioni` che contiene un `MediaPlayer` per la musica e uno per gli effetti sonori. Dal menù impostazioni si possono pausare/riprendere questi due oggetti per disattivare e riattivare le traccie audio degli effetti sonori e della musica separatamente.
#### Cambio Lingua
Sempre nella classe statica `Opzioni` è memorizzata la lingua. `Opzioni` ha un metodo che data una stringa restituisce la traduzione corrispondente nella lingua selezionata usando una `ResourceBundle`.
#### CSS
Personalizzazione di pressoché ogni elemento dell'applicazione, dai `Button`, alle `Textfield`, alle `ComboBox`, al menu. In style.css vi è di fatto tutta la personalizzazione compresa di dinamismo al passare del mouse di determinati elementi.
#### Applicazione interamente utilizzabile da tastiera
È stato creato un metodo `KeyEvent` per spostare il focus tra i `Button` e `Label` dell'applicazione, affinaché possa essere utilizzata senza mouse. Per fare ciò abbiamo utilizzato una sequenza di `if` ed `else if` utilizzando il focus del nodo. Per esempio, se un bottone ha il focus e premo `invio`, è come se il bottone fosse stato cliccato. Si può spostare il focus tra varie label e bottoni con le frecce e il tasto `tab`.
#### Tabellone Torneo
Ogni torneo ha una variabile che memorizza il round corrente e quello massimo (`=log_2(num_giocatori_iniziali)`). Per  
esempio nel torneo da 4 giocatori, quando il numero round è uguale a 2 significa che si sta già giocando la finale, e quindi i bottoni per giocare le due semifinali non sono più cliccabili (dato che le due partite sono finite).
#### Interruzione e ripristino partite di un torneo
Nella classe torneo sono salvati i codici di tutte le partite giocate. Questo permette di caricare e salvare una partita come se fosse giocata singolarmente. Il tabellone inoltre permette facilmente di riaccedere a una partita iniziata ma non finita.
#### Cursore
Il cursore standard è stato sostituito da un'immagine personalizzata in linea con il design dell'applicazione, immagine che cambia al passaggio sopra elementi cliccabili tramite `setOnMouseEntered` e `setOnMouseExited`.