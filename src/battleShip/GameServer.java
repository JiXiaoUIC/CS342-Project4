package battleShip;

import java.net.*;
import java.io.*;

public class GameServer {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10007);
        } catch (IOException e) {
            System.err.println("Could not listen to port: 10007");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            System.out.println("Waiting for Client");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }

        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream((clientSocket.getInputStream()));

        int serReceive = 0;
        int serSend = 1111;

        try {
            serReceive = in.readInt();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("The serReceive is: " + serReceive + " (from client)");

        System.out.println("Sever sending integer: " + serSend + " (to client)");
        out.writeInt(serSend);
        out.flush();

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }
}
