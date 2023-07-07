package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {
    public static void main(String[] args) {
        // CSV file path

        String csvFile = "C:/Users/joeyz/Downloads/emails - Sheet1.csv";

        // Email credentials
        final String senderEmail = "joeyzheng13@gmail.com";
        final String senderPassword = "undgbcyeolhwrpva";

        // Email details
        String subject = "Say Yes Buffalo: Please respond to this survey";
        String message = "https://docs.google.com/forms/d/e/1FAIpQLSc-t60jNI4eD2ITgvFDQ25rDReWdVAZYjcUQKqbDstNIIZA5w/viewform?usp=sf_link Contact me";

        // Read CSV file and send emails
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String recipientEmail = line.trim();

                // Send email
                sendEmail(senderEmail, senderPassword, recipientEmail, subject, message);
                System.out.println("Email sent to: " + recipientEmail);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendEmail(String senderEmail, String senderPassword, String recipientEmail, String subject, String message) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(senderEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}