import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UnsecureDNSServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(53);
            byte[] receiveData = new byte[512];
            byte[] sendData = new byte[512];

            System.out.println("DNS server is running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramSocket forwardSocket = new DatagramSocket();
                DatagramPacket forwardPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(), InetAddress.getByName("8.8.8.8"), 53);
                forwardSocket.send(forwardPacket);

                DatagramPacket responsePacket = new DatagramPacket(sendData, sendData.length);
                forwardSocket.receive(responsePacket);

                DatagramPacket sendPacket = new DatagramPacket(responsePacket.getData(), responsePacket.getLength(), clientAddress, clientPort);
                serverSocket.send(sendPacket);

                forwardSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
