import java.util.ArrayList;
import java.util.Random;

//Randomly generates three player averages and returns them as a string separated by a space
//Simulates as if only one game is played per team due to issue with handicap calculation
class BowlingTeam {
    String teamScores() {
        StringBuilder scores = new StringBuilder();

        for (int j = 0; j < 3; j++) {
            Random random = new Random();
            scores.append((int) random.nextGaussian() * 15 + 210);
            if (j != 2) {
                scores.append(" ");
            }
        }
        return (scores.toString());
    }
}
