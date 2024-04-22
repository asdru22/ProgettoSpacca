package gioco.progettospacca.classi;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/*
Ho deciso di utilizzare un thread separato per l'invio delle mail proprio per non far bloccare
l'applicazione in attesa del completamento dell'operazione. Questo grazie alle lezioni di sistemi operativi del secondo anno(anno corrente)
per ultilzzare la libreria ho aggiunto le dipendeze maven nel file pom.xml
 */
public class MailThread extends Thread{
    private String host = "smtp.gmail.com";
    private String port = "587";
    private String username = "spacca000@gmail.com";
    private String password = "xzsq bbdr xghx xljh";    //password della mail per applicazioni fornita dall'account google
    private Properties proprieta;
    private String destinatario;
    private String oggetto;
    private String corpo;
    private Session sessione;

    public MailThread(String destinatario, String oggetto, String corpo){
        this.destinatario = destinatario;
        this.oggetto = oggetto;
        this.corpo = corpo;

        proprieta = new Properties();
        proprieta.put("mail.smtp.auth", "true");
        proprieta.put("mail.smtp.starttls.enable", "true");
        proprieta.put("mail.smtp.host", host);
        proprieta.put("mail.smtp.port", port);

        sessione = Session.getInstance(proprieta, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void run(){
        try {
            // Creazione del messaggio
            Message mess = new MimeMessage(sessione);
            mess.setFrom(new InternetAddress(username)); // Indirizzo email mittente
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario)); // Indirizzo email destinatario
            mess.setSubject(oggetto);
            mess.setText(corpo);

            // Invio del messaggio
            Transport.send(mess);

            System.out.println("Messaggio inviato con successo!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
