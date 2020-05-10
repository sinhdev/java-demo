import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class UDPClient2 {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            for (int i = 0; i < 10; i++) {
                byte[] buf = new byte[256];
                InetAddress address = InetAddress.getByName("localhost");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 1313);
                socket.send(packet);
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Current server time: " + received);
                Thread.sleep(1500);
            }
        } catch (Exception ex) {
        }
    }
}