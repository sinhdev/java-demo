import java.net.Socket;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Console;

public class TCPClient {
    public static void main(String[] args) {
        try {
            // 1.Create and Connect
            String hostname = "127.0.0.1";
            int port = 3000;
            Socket socket = new Socket(hostname, port);
            // 2.GetStream
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            // 3.Send
            Console console = System.console();
            String Sendtext = console.readLine("Enter text: ");
            writer.println(Sendtext);
            // 4.Receive
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String Receivetext = reader.readLine();
            System.out.println(Receivetext);
            // 5.close
            input.close();
            output.close();
            reader.close();
            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }

}