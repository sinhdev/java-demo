import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;

public class TCPServer2 {
    public static void main(String[] args) {
        int port = 4321;
        // Create a ServerSocket
        try (ServerSocket ss = new ServerSocket(port);) {
            System.out.println("Server is running!");
            while (true) {
                // when accept from client
                try (Socket s = ss.accept()) {
                    // Get IP and Port of client
                    System.out.println(s.getInetAddress() + " is connect.");
                    String response = "Hello " + s.getInetAddress() + " on port " + s.getPort() + "\r\n";
                    response += "This is " + s.getLocalAddress() + " on port " + s.getLocalPort() + "\r\n";
                    // Response to client
                    OutputStream out = s.getOutputStream();
                    out.write(response.getBytes("US-ASCII"));
                    out.flush();
                    out.close();
                } catch (IOException ex) {
                }
            }
        } catch (IOException e) {
        }
    }
}