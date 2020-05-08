package dev.sinhnx;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailUtil {
    public static void main(String[] args) {
        System.out.println("Hello Java Mail");
        final Scanner kb = new Scanner(System.in);
        System.out.print("Email: ");
        final String userName = kb.nextLine();
        System.out.print("Password: ");
        final String password = App.getPassword(kb);
        GmailUtil gmail = new GmailUtil();
        gmail.readPOP3(userName, password);
    }

    public boolean sendGmail(final String senderEmail, final String pass, final String toEmail,
            final String ccEmail, final String subject, final String content) {
        try {
            final Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", senderEmail);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.auth", "true"); // Use SSL
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.port", "465");
            final Session session = Session.getDefaultInstance(props);
            final MimeMessage message = new MimeMessage(session);
            message.setHeader("Importance", "high");
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(ccEmail));
            message.setSubject(subject, "utf-8");
            message.setText(content, "utf-8", "html");
            final Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", senderEmail, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (final Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void readPOP3(final String user, final String password) {
        try {
            // 1) get the session object
            final Properties props = new Properties();
            props.put("mail.pop3.host", "pop.gmail.com");
            props.put("mail.pop3.ssl.enable", "true"); // Use SSL
            props.put("mail.pop3.user", user);
            props.put("mail.pop3.socketFactory", 995);
            props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.pop3.port", 995);
            final Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);

                }
            });

            // 2) create the POP3 store object and connect with the pop server
            final Store store = (Store) session.getStore("pop3");
            store.connect("pop.gmail.com", 995, user, password);

            // 3) create the folder object and open it
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            // 4) retrieve the messages from the folder in an array and print it
            int messageCount = inbox.getMessageCount();
            System.out.println("Total Messages:- " + messageCount);
            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");
            for (int i = 0; i < messageCount; i++) {
                System.out.println("Mail Subject:- " + messages[i].getSubject());
            }

            // 5) close the store and folder objects
            inbox.close(false);
            store.close();
        } catch (final MessagingException e) {
            e.printStackTrace();
        }
    }

    public void readSMTP(final String user, final String password) {
        Properties props = new Properties();
        try {
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", user, password);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            int messageCount = inbox.getMessageCount();
            System.out.println("Total Messages:- " + messageCount);
            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");
            for (int i = messageCount - 1; i > messageCount - 11; i--) {
                System.out.println("Mail Subject:- " + messages[i].getSubject());
            }
            inbox.close(true);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}