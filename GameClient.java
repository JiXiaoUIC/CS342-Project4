package battleShip;

import java.net.*;
import java.io.*;

public class GameClient {
    public static void main(String args[]) throws IOException {
        Socket echoSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            echoSocket = new Socket("127.0.0.1", 10007);

            out = new ObjectOutputStream(echoSocket.getOutputStream());
            in = new ObjectInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: trans.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: trains.");
            System.exit(1);
        }

        int cliSend = 2222;
        int cliReceive = 0;

        System.out.println("Sending integer: " + cliSend + " (to server)");

        out.writeInt(cliSend);
        out.flush();

        System.out.println("Send integer, waiting for return ralue");

        try {
            cliReceive = in.readInt();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Got integer: " + cliReceive + " (from server)");

        out.close();
        in.close();
        echoSocket.close();

    }
}
