package tcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class ClientTCP {

    private static final int SERVER_SOCKET_PORT = 4568;

    public static void main(String[] args) throws Exception {
        System.out.println("Client started!");
        InetAddress serverAddress = InetAddress.getByName("localhost");

        try (Scanner scanner = new Scanner(System.in);
            Socket socketClient = new Socket(serverAddress, SERVER_SOCKET_PORT)
        ) {
            //get phrase from user that should be sent to the server
            System.out.println("Enter phrase that will be sent to the service:");
            String phrase = scanner.nextLine();

            // send phrase to the server
            PrintWriter writer = new PrintWriter(socketClient.getOutputStream(), true);
            writer.println(phrase);

            // receive response message from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            String receivedMessage = reader.readLine();

            System.out.println("Received message from server: " + receivedMessage);
        }
	}
}
