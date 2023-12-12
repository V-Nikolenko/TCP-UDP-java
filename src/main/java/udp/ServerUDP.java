package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class ServerUDP {

    private static final int SERVER_SOCKET_PORT = 4567;

    public static void main(String[] args) throws Exception {
        System.out.println("Server started!");

        try (DatagramSocket socket = new DatagramSocket(SERVER_SOCKET_PORT)) {
            while (true) {
                // receive phrase from client
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String receivedPhrase = new String(receiveBuffer);

                // get client address and port to send response message
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received phrase:" + receivedPhrase + " from client " + clientAddress + ":" + clientPort);

                // prepare response message and send it to the client
                String messageToClient = receivedPhrase.toUpperCase();
                byte[] messageBytes = messageToClient.getBytes();
                DatagramPacket requestPacket = new DatagramPacket(messageBytes, messageBytes.length, clientAddress, clientPort);
                socket.send(requestPacket);
            }
        }
    }
}

