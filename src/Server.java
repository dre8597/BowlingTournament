
/*
 *This multi-threaded server takes in it's port number and max number of teams to accept
 * While the number hasn't been met the server will continue to accept new connection
 * This will send each socket to the teamHandler where handicap and tournament position is assigned
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ArrayList<TeamScore> finalTotals = new ArrayList<>();

    // constructor with port and max # of teams
    private Server(int port, int maxTeams) {
        int numTeams = 0;
        // starts server and waits for a connection
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (numTeams < maxTeams) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new TeamHandler(socket, numTeams).start();
                numTeams++;
            }
            getFinalTotals();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void getFinalTotals() {
        finalTotals.sort(TeamScore.teamScoreComparator);
        for (int i = 0; i < finalTotals.size(); i++) {
            try {
                finalTotals.get(i).getSocketOutputStream().write(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void addTeam(Socket teamSocket, double teamTotal) {
        finalTotals.add(new TeamScore(teamSocket, teamTotal));
    }

    public static void main(String[] args) {
        new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
} 