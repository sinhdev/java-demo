package dev.sinhnx;

import java.io.Console;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello Java Mail");
        final Scanner kb = new Scanner(System.in);
        System.out.print("Email: ");
        final String senderEmail = kb.nextLine();
        System.out.print("Password: ");
        final String pass = getPassword(kb);
        System.out.print("Subject: ");
        final String subject = kb.nextLine();
        System.out.print("Content: ");
        final String content = kb.nextLine();
        System.out.print("To: ");
        final String toEmail = kb.nextLine();
        System.out.print("Cc: ");
        final String ccEmail = kb.nextLine();
        kb.close();
        GmailUtil gmail = new GmailUtil();
        if (gmail.sendGmail(senderEmail, pass, toEmail, ccEmail, subject, content)) {
            System.out.println("Send email complete!");
        } else {
            System.err.println("Send email error!");
        }
    }

    public static String getPassword(Scanner kb) {
        final Console console = System.console();
        if (console != null) {
            return String.valueOf(console.readPassword());
        }
        return kb.nextLine();
    }
}
