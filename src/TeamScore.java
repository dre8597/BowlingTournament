import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Comparator;

public class TeamScore {
    private Socket socket;
    private double teamTotal;

    TeamScore(Socket socket, double teamTotal) {
        this.socket = socket;
        this.teamTotal = teamTotal;
    }

    Socket getSocket() {
        return socket;
    }

    OutputStream getSocketOutputStream() {
        try {
            return socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private double getTeamTotal() {
        return teamTotal;
    }

    public void setTeamTotal(double teamTotal) {
        this.teamTotal = teamTotal;
    }

    /*Comparator for sorting the list by roll no*/
    static Comparator<TeamScore> teamScoreComparator = (s1, s2) -> {

        double rollno1 = s1.getTeamTotal();
        double rollno2 = s2.getTeamTotal();

//        /*For ascending order*/
//        return (int) (rollno1 - rollno2);

        /*For descending order*/
        return (int) (rollno2 - rollno1);
    };
}
