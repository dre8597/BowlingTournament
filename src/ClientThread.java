import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

class ClientThread extends Thread {
    ClientThread(Socket socket) {
        DataOutputStream outputStream = null;
        ObjectInputStream objectInputStream = null;
        DataInputStream dataInputStream = null;

        try {
            System.out.println("Connected");

            //sends output to the socket
            outputStream = new DataOutputStream(socket.getOutputStream());

            //takes in results object
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            //Take in its standing in tournament
            dataInputStream = new DataInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //send the equation to server
            assert outputStream != null;
            BowlingTeam bowlingTeam = new BowlingTeam();
            outputStream.writeUTF(bowlingTeam.teamScores());

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            assert objectInputStream != null;
            Results results = (Results) objectInputStream.readObject();
            System.out.println("Team Number: " + results.getTeamNum());
            System.out.println("Team Handicap: " + results.getTeamHandicap());
            System.out.println("Team Final Score: " + results.getTeamTotal());
            //TODO add sorting to results arrayList to find place of each team e.g. Team 0: First Place!!!
            assert dataInputStream != null;
            System.out.println("Team Place: " + dataInputStream.read());
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
