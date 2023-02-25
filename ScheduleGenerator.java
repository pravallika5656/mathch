import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleGenerator {

    public static void main(String[] args) {

        // Define the teams
        String[] teams = { "Team A", "Team B", "Team C", "Team D", "Team E" };

        // Create a list of matches
        List<Match> matches = new ArrayList<>();

        // Generate the matches for each round
        for (int round = 0; round < teams.length - 1; round++) {
            for (int i = 0; i < teams.length / 2; i++) {
                int j = teams.length - 1 - i;
                matches.add(new Match(teams[i], teams[j]));
            }
            Collections.rotate(teams, 1);
        }

        // Shuffle the matches to ensure randomness
        Collections.shuffle(matches);

        // Ensure no team plays back-to-back matches
        for (int i = 1; i < matches.size(); i++) {
            Match previousMatch = matches.get(i - 1);
            Match currentMatch = matches.get(i);
            if (previousMatch.getHomeTeam().equals(currentMatch.getHomeTeam()) ||
                    previousMatch.getHomeTeam().equals(currentMatch.getAwayTeam()) ||
                    previousMatch.getAwayTeam().equals(currentMatch.getHomeTeam()) ||
                    previousMatch.getAwayTeam().equals(currentMatch.getAwayTeam())) {
                // Swap the current match with a random match that doesn't break the rule
                int j = i + 1;
                while (j < matches.size()) {
                    Match tempMatch = matches.get(j);
                    if (previousMatch.getHomeTeam().equals(tempMatch.getHomeTeam()) ||
                            previousMatch.getHomeTeam().equals(tempMatch.getAwayTeam()) ||
                            previousMatch.getAwayTeam().equals(tempMatch.getHomeTeam()) ||
                            previousMatch.getAwayTeam().equals(tempMatch.getAwayTeam())) {
                        j++;
                    } else {
                        matches.set(i, tempMatch);
                        matches.set(j, currentMatch);
                        break;
                    }
                }
            }
        }

        // Print the schedule
        System.out.println("Schedule:");
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            System.out.println("Match " + (i + 1) + ": " + match.getHomeTeam() + " vs " + match.getAwayTeam());
        }
    }

    // A class to represent a match between two teams
    private static class Match {
        private String homeTeam;
        private String awayTeam;

        public Match(String homeTeam, String awayTeam) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
        }

        public String getHomeTeam() {
            return homeTeam;
        }

        public String getAwayTeam() {
            return awayTeam;
        }
    }

}
