
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.Console;
public class UDPClient {
    public static void main(String[] args)
    {
        
        try{
            String hostname = "127.0.0.1";
            int port = 3000;
            InetAddress inetAddress = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
           
            Console console = System.console();
            String Sendtext = console.readLine("Enter text: ");
            byte[] buffer = Sendtext.getBytes();
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            socket.send(request);
            buffer = new byte[512];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            String ReceiveText = new String(response.getData(),0,response.getLength());
            System.out.println("Message of Server : " + ReceiveText);
            socket.close();

        }
        catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
        }
    }

}