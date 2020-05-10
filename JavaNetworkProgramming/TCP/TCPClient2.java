import java.net.Socket;
import java.io.InputStream;

public class TCPClient2 {
    public static void main(String[] args) {
        String hostname = "127.0.0.1";
        int port = 4321;
        // Create a connection to Server (hostname, port 4321)
        try (Socket socket = new Socket(hostname, port);) {
            InputStream in = socket.getInputStream();
            // Read data from Server
            byte read[] = new byte[1024];
            int len = in.read(read);
            // Convert data from byte[] to String
            String str = new String(read, 0, len);
            System.out.println(str);
        } catch (Exception ex) {
        }
    }
}