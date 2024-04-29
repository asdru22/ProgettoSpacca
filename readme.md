# SPACCA
![](https://cdn.discordapp.com/attachments/1120361955866185867/1234611348407586816/image.png?ex=66315ceb&is=66300b6b&hm=fb56eecf4524fbe18c785ab19476900b5ddfeeaca6607ec3e6956c0c560076e8&)
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
![](https://cdn.discordapp.com/attachments/1120361955866185867/1234611982649262321/image.png?ex=66315d82&is=66300c02&hm=1614df8db3781268323f68543cac385f64d90e9eb1e3fb1732763f53c396a93d&)
6. Tornare alla home
7. Cliccare gioca partita
8. Ogni giocatore inseirsce il proprio nome e il codice della parita da giocare (Admin possono entrare in tutte le partite)
![](https://cdn.discordapp.com/attachments/1120361955866185867/1234612093337079899/image.png?ex=66315d9d&is=66300c1d&hm=3ee4fbbb65d65a4fd410dbb77e31d11554c534444085c6efe595cc661f1ced54&)
## Torneo
1. Cliccare crea torneo
2. Fare login con username `admin` e password `spacca`
3. L'admin sceglie il numero di giocatori per il torneo
4. L'admin inserisce i giocatori. Se il numero dei giocatori è inferiore a quello dei giocatori selezionati, verranno automaticamente creati bot per raggiungere il numero richiesto
![](https://cdn.discordapp.com/attachments/1120361955866185867/1234613244216479754/image.png?ex=66315eaf&is=66300d2f&hm=2640c3ed8963eb8c2dc38bb6ca68ee5eeed4bf4c595dfcd986959b5ab79fefc3&)
5. Tornare alla home
6. Cliccare gioca torneo e inserire il codice del torneo
7. Appare la schermata del tabellone con le varie partite da giocare. Certe partite saranno bloccate fino a quando non terminano tutte quelle del round precedente 
8. Cliccare la partita da giocare
9. Finita la finale, il torneo verrà eliminato
![](https://cdn.discordapp.com/attachments/1120361955866185867/1234613463884501013/image.png?ex=66315ee3&is=66300d63&hm=80fb1b2ca3367e6a846de890920ed1929fd7eace88867b49fb2917857499c1a6&)
## Privilegi Amministratore
Eseguendo l'accesso da admin, oltre che creare partite e tornei, è possibile
- modificare i nomi dei giocatori e le loro mail
- creare giocatori nuovi
![](https://media.discordapp.net/attachments/1120361955866185867/1234613548097867906/image.png?ex=66315ef7&is=66300d77&hm=2b7d2b1d60cf9fc048d703cb857d4e88c1f289245403b5b1686dc8a760febf08&=&format=webp&quality=lossless&width=798&height=577)
- cancellare partite e tornei in sospeso
- cambiare il numero dei turni delle partite
## Opzioni
Dal menù impostazioni si può
- cambiare lingua
- attivare o disattivare effetti sonori e musica
- uscire da una partita salvando i progressi
- visualizzare le regole
- visualizzare la classifica
![](https://cdn.discordapp.com/attachments/1120361955866185867/1234612745245036544/image.png?ex=66315e38&is=66300cb8&hm=6a9ea4dff6820c2216d40e3ab03780492345ff1ad7e22ea7f70772996d949d63&)
## Sviluppo del progetto
### Funzionalità obbligatorie
#### Partita
![](https://cdn.discordapp.com/attachments/1120361955866185867/1234612945749545030/image.png?ex=66315e68&is=66300ce8&hm=3b63218138cd9478b9471a8ea5f8b05b09135b4a09bf949d400ae6d00408364b&)
![](https://cdn.discordapp.com/attachments/1120361955866185867/1234613024300466186/image.png?ex=66315e7a&is=66300cfa&hm=fa1565f1edeec6874d8448e3cbe28bea72f18d1513a9ccd92d8723423b3c0795&)
#### Torneo
Quando viene creato un nuovo torneo, si crea un numero di partite uguale alla metà dei giocatori inseriti. A ogni coppia di giocatori viene assoicata una di queste partite i cui codici vengono salvati nel torneo. Quando tutte le partite salvate sono finite si passa al round successivo. I vincitori delle partite del round precedente diventano i giocatori di quelle del round successivo fino a quando rimane un solo vincitore.
#### Salvataggi
Le classi Partita, Giocatore e Torneo hanno una funzione `salva` e `carica`. Il metodo carica è statico è quindi può essere chiamato in qualsiasi momento per creare una nuova istanza di quella classe. Il metodo salva converte la classe in un file `.json`. Entrambi i metodi richiedono la creazione di un nuovo oggetto di tipo `Gson`.
#### Gestione bot
#### Layout
#### Modifica dati dei giocatori
Funzioni nella classe di utilità quali `elencaGiocatori`, `esisteGiocatore` e `eliminaGiocatore` hanno facilitato la gestione dei giocatori. Sono usati controlli per evitare che queste modifiche possano essere fatte a Bot o all'admin.
### Funzionalità facoltative
#### Invio Mail
#### Suoni e Audio
All'avvio dell'applicazione viene creata una variabile statica di tipo `Opzioni` che contiene un `MediaPlayer` per la musica e uno per gli effetti sonori. Dal menù impostazioni si possono pausare/riprendere questi due oggetti per disattivare e riattivare le traccie audio degli effetti sonori e della musica separatamente.
#### Cambio Lingua
Sempre nella classe statica `Opzioni` è memorizzata la lingua. `Opzioni` ha un metodo che data una stringa restituisce la traduzione corrispondente nella lingua selezionata usando una `ResourceBundle`.
#### CSS
Personalizzazione di pressoché ogni elemento dell'applicazione, dai bottoni, alle textfield, alle combobox, al menu. in style.css vi è di fatto tutta la personalizzazione compresa di dinamismo al passare del mouse di determinati elementi. 
#### Applicazione interamente utilizzabile da tastiera
#### Tabellone Torneo
Ogni torneo ha una variabile che memorizza il round corrente e quello massimo (`=log_2(num_giocatori_iniziali)`). Per esempio nel torneo da 4 giocatori, quando il numero round è uguale a 2 significa che si sta già giocando la finale, e quindi i bottoni per giocare le due semifinali non sono più cliccabili (dato che le due partite sono finite).
#### Interruzione e ripristino partite di un torneo
Nella classe torneo sono salvati i codici di tutte le partite giocate. Questo permette di caricare e salvare una partita come se fosse giocata singolarmente. Il tabellone inoltre permette facilmente di riaccedere a una partita iniziata ma non finita.
#### Cursore
Il cursore standard è stato sostituito da un'immagine personalizzata in linea con il design dell'applicazione, immagine che cambia al passaggio sopra elementi cliccabili tramite setOnMouseEntered e setOnMouseExited.
## Suddivisione del lavoro
- Nanni: Tutte le classi nella cartella [classi](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/classi) a eccezione di `MailThread.java`, `Partita.java` e `Giocatore.java`. Vari collegamenti con le partite, opzioni, salvataggi e metodi della classe `Utili.java` all'interno dei [controller](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller), in particolare quelli usati per i tornei e i privilegi admin.
- Manieri: Grafica e design (FXML), collegamenti tra le scene e classi [controller](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller) associate, creazione di tutte le immagini. Cursore personalizzato.
- Silvestri: [PartitaController.java](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/controller/PartitaController.java): Implementazione partita vera e propria (animazioni, bot, eventi e algoritmo principale di gioco). Gestione di tutti gli eventi dell'intera applicazione (eventi javafx: mouseEvent, clickEvent, keyEvent ecc...), metodi funzionali alla partita nelle classi `Partita.java` e `Giocatore.java` nella cartella [classi](https://github.com/asdru22/ProgettoSpacca/tree/main/src/main/java/gioco/progettospacca/classi). Meccanismo di invio mail.