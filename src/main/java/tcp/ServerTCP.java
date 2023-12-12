package tcp;

import java.io.*;
import java.net.*;

class ServerTCP {

    private static final int SERVER_SOCKET_PORT = 4568;

    public static void main(String argv[]) throws Exception {
        System.out.println("Server started!");

        try (ServerSocket serverSocket = new ServerSocket(SERVER_SOCKET_PORT)) {
            while (true) {
                // accept connection from client
                Socket socketConnection = serverSocket.accept();

                // get phrase from client via socket
                InputStream clientInputStream = socketConnection.getInputStream();
                BufferedReader receivedDataFromClient = new BufferedReader(new InputStreamReader(clientInputStream));
                String receivedPhrase = receivedDataFromClient.readLine();

                System.out.println("Received phrase:" + receivedPhrase + " from client " + socketConnection.getInetAddress() + ":" + socketConnection.getPort());

                // prepare response message and send it to the client
                String messageToClient = receivedPhrase.toUpperCase();
                PrintWriter writer = new PrintWriter(socketConnection.getOutputStream(), true);
                writer.println(messageToClient);
            }
        }
    }
}

