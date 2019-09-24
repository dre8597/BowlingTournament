import java.io.Serializable;

class Results implements Serializable {
    private int teamNum;
    private double teamHandicap;
    private double teamTotal;

    Results(int teamNum, double teamHandicap, double teamTotal) {
        this.teamNum = teamNum;
        this.teamHandicap = teamHandicap;
        this.teamTotal = teamTotal;
    }

    int getTeamNum() {
        return teamNum;
    }

    double getTeamHandicap() {
        return teamHandicap;
    }

    double getTeamTotal() {
        return teamTotal;
    }

}
