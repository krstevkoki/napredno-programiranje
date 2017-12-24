package mk.ukim.finki.kol2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FootballTable {
    private Map<String, Team> teams;

    public FootballTable() {
        this.teams = new HashMap<>();
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        Team home = this.teams.computeIfAbsent(homeTeam, key -> new Team(homeTeam));
        Team away = this.teams.computeIfAbsent(awayTeam, key -> new Team(awayTeam));

        // setting goals
        home.setGoalsScored(home.getGoalsScored() + homeGoals);
        home.setGoalsConceded(home.getGoalsConceded() + awayGoals);
        away.setGoalsScored(away.getGoalsScored() + awayGoals);
        away.setGoalsConceded(away.getGoalsConceded() + homeGoals);

        // setting wins, losses, draws
        if (homeGoals > awayGoals) {  // homeTeam wins
            home.setWins(home.getWins() + 1);
            away.setLosses(away.getLosses() + 1);
        } else if (awayGoals > homeGoals) {  // awayTeam wins
            away.setWins(away.getWins() + 1);
            home.setLosses(home.getLosses() + 1);
        } else {
            home.setDraws(home.getDraws() + 1);
            away.setDraws(away.getDraws() + 1);
        }

        // setting games played
        home.setNumGames(home.getNumGames() + 1);
        away.setNumGames(away.getNumGames() + 1);
    }

    public void printTable() {
        List<Team> teams = this.teams.values()
                .stream()
                .sorted(Comparator
                        .comparing(Team::getPoints)
                        .thenComparing(Team::goalDifference).reversed()
                        .thenComparing(Team::getTeamName))
                .collect(Collectors.toList());
        IntStream.range(0, teams.size())
                .forEach(i -> System.out.printf("%2d. %s\n", i + 1, teams.get(i)));
    }
}
