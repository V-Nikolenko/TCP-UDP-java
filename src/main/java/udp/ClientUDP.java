package udp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class ClientUDP {

    private static final int SERVER_SOCKET_PORT = 4567;

	public static void main(String[] args) throws Exception {
        System.out.println("Client started!");
        InetAddress serverAddress = InetAddress.getByName("localhost");

        try (Scanner scanner = new Scanner(System.in);
             DatagramSocket socketClient = new DatagramSocket()
        ) {
            //get phrase from user that should be sent to the server
            System.out.println("Enter phrase that will be sent to the service:");
            String phrase = scanner.nextLine();
            byte[] phraseBytes = phrase.getBytes();

            // send phrase to the server
            DatagramPacket sendPacket = new DatagramPacket(phraseBytes, phraseBytes.length, serverAddress, SERVER_SOCKET_PORT);
            socketClient.send(sendPacket);

            // receive response message from the server
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socketClient.receive(receivePacket);
            String receivedMessage = new String(receiveBuffer);
            System.out.println("Received message from server: " + receivedMessage);
        }
	}
}
