/*
 *This client calls the ClientThread passing it the socket needed to connect to the server
 * You must add the number of teams to be created, the ip address of the server and port of the server
 * as a command line argument
 */

import java.io.*;
import java.net.Socket;

public class ParentClient {
    private ParentClient(int numTeams, String ip, int port) {
        for (int i = 0; i < numTeams; i++) {
            try {
                Thread clientThread = new ClientThread(new Socket(ip, port));
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        int numTeams = 0;
        String ip = "";
        int port = 0;
        if (args.length > 0) {
            numTeams = Integer.parseInt(args[0]);
            ip = args[1];
            port = Integer.parseInt(args[2]);
        }
        new ParentClient(numTeams, ip, port);
    }
}


