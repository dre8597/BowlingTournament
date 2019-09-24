import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * This class handles the calculation of the teams scores and adjust based on handicap per player
 * Once calculations are complete this class will send the results to each client
 */
public class TeamHandler extends Thread {
    private Socket socket;
    private int teamNumber;

    TeamHandler(Socket socket, int teamNumber) {
        this.socket = socket;
        this.teamNumber = teamNumber;

    }

    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            String teamAverages = inputStream.readUTF();
            double totalHandicap = 0;
            double teamTotal = 0;
            String[] scores = teamAverages.split(" ", 5);

            for (String score : scores) {
                totalHandicap += (250 - Integer.parseInt(score)) * .9;
                teamTotal += Math.abs(totalHandicap) + Integer.parseInt(score);
            }
            Server.addTeam(socket, teamTotal);
            Results results = new Results(teamNumber, totalHandicap, teamTotal);
            objectOutputStream.writeObject(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
