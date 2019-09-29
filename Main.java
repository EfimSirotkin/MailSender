package sample;

import javafx.application.Application;

import javafx.stage.Stage;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        String to = "ilyagoldburt@gmail.com";
        String from = "efim.sirotkin@gmail.com";
        String host = "localhost";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myPassword = "MyPassword";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, myPassword);
            }
        });

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test message");
            message.setText("Здарова, Ильюха. Надеюсь, ты получил это сообщение");

            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
