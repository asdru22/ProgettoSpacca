package gioco.progettospacca.classi;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class test{
    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "spacca000@gmail.com";
        String password = "xzsq bbdr xghx xljh";

        // Proprietà per la sessione di posta
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Creazione dell'oggetto Session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        for(int i = 0; i<1 ; i++) {
            try {
                // Creazione del messaggio
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username)); // Indirizzo email mittente
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("asdru22corp@gmail.com")); // Indirizzo email destinatario
                message.setSubject("oggetto");
                message.setText("corpo");

                // Invio del messaggio
                Transport.send(message);

                System.out.println("Messaggio inviato con successo!");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}


