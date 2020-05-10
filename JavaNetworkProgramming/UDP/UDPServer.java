
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;
public class UDPServer {
    public static void main(String[] args) {
        try
        {
            DatagramSocket socket = new DatagramSocket(3000);
 
            byte[] buffer = new byte[512];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            String receiveText = new String(request.getData(),0,request.getLength());
            System.out.println("receive Data " + receiveText + " from " + request.getAddress());
            String sendText = "Hello " + receiveText;
            buffer = sendText.getBytes();
            System.out.println(buffer.length);
            DatagramPacket response = new DatagramPacket(buffer, buffer.length,request.getAddress(),request.getPort());
            socket.send(response);
            socket.close();
        }
        catch (IOException ex)
        {
            System.out.println("I/O Error :" + ex.getMessage());
        }
    }
}
