package mk.ukim.finki.kol2;

class Team {
    private String teamName;
    private int numGames;
    private int goalsScored;
    private int goalsConceded;
    private int wins;
    private int draws;
    private int losses;

    public Team(String teamName) {
        this.teamName = teamName;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.numGames = 0;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getNumGames() {
        return numGames;
    }

    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getPoints() {
        return wins * 3 + draws;
    }

    public int goalDifference() {
        return goalsScored - goalsConceded;
    }

    @Override
    public String toString() {
        return String.format("%-15s%5d%5d%5d%5d%5d", teamName, numGames, wins, draws, losses, getPoints());
    }
}
