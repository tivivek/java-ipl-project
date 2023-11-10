import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Bowler{
    String bowlerName;
    double economy;
    Bowler(String bowlerName, double economy){
        this.bowlerName = bowlerName;
        this.economy = economy;
    }

    public double getEconomy() {
        return economy;
    }
}
class RunWicket{
    int run, wicket;
    RunWicket(int run,int wicket){
        this.run = run;
        this.wicket = wicket;
    }
}

public class Main {
    private static  final int ID=0;
    private static  final int SEASON=1;
    private static  final int CITY=2;
    private static  final int DATE=3;
    private static  final int TEAM1=4;
    private static  final int TEAM2=5;
    private static  final int TOSS_WINNER=6;
    private static  final int TOSS_DECISION=7;
    private static  final int RESULT=8;
    private static  final int DL_APPLIED=9;
    private static  final int WINNER=10;
    private static  final int WIN_BY_RUNS=11;
    private static  final int WIN_BY_WICKETS=12;
    private static  final int PLAYER_OF_MATCH=13;
    private static  final int VENUE=14;
    private static  final int UMPIRE1=15;
    private static  final int UMPIRE2=16;
    private static  final int UMPIRE3=17;
    private static  final int MATCH_ID = 0;
    private static  final int INNING = 1;
    private static  final int BATTING_TEAM = 2;
    private static  final int BOWLING_TEAM = 3;
    private static  final int OVER = 4;
    private static  final int BALL = 5;
    private static  final int BATSMAN = 6;
    private static  final int NON_STRIKER = 7;
    private static  final int BOWLER = 8;
    private static  final int IS_SUPER_OVER = 9;
    private static  final int WIDE_RUNS = 10;
    private static  final int BYE_RUNS = 11;
    private static  final int LEG_BYE_RUNS = 12;
    private static  final int NOBALL_RUNS = 13;
    private static  final int PENALTY_RUNS = 14;
    private static  final int BATSMAN_RUNS = 15;
    private static  final int EXTRA_RUNS = 16;
    private static  final int TOTAL_RUNS = 17;
    private static  final int PLAYER_DISMISSED = 18;
    private static  final int DISMISSAL_KIND = 19;
    private static  final int FIELDER = 20;

    // Converting deliveries.csv into list form
    private static List<Deliveries> getDeliveriesData(String pathDeliveries) {
        List<Deliveries> deliveries = new ArrayList<>();
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathDeliveries));
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Deliveries delivery= new Deliveries();
                delivery.setMatch_id(data[MATCH_ID]);
                delivery.setInning(data[INNING]);
                delivery.setBatting_team(data[BATTING_TEAM]);
                delivery.setBowling_team(data[BOWLING_TEAM]);
                delivery.setOver(data[OVER]);
                delivery.setBall(data[BALL]);
                delivery.setBatsman(data[BATSMAN]);
                delivery.setNon_striker(data[NON_STRIKER]);
                delivery.setBowler(data[BOWLER]);
                delivery.setIs_supper_over(data[IS_SUPER_OVER]);
                delivery.setWide_runs(data[WIDE_RUNS]);
                delivery.setBye_runs(data[BYE_RUNS]);
                delivery.setLeg_by_run(data[LEG_BYE_RUNS]);
                delivery.setNoBall_runs(data[NOBALL_RUNS]);
                delivery.setPenalty_runs(data[PENALTY_RUNS]);
                delivery.setBatsman_run(data[BATSMAN_RUNS]);
                delivery.setExtra_runs(data[EXTRA_RUNS]);
                delivery.setTotal_runs(data[TOTAL_RUNS]);
                if(data.length>=19)
                    delivery.setPlayer_dismissed(data[PLAYER_DISMISSED]);
                if(data.length>=20)
                    delivery.setDismissed_kind(data[DISMISSAL_KIND]);
                if(data.length>=21)
                    delivery.setFielder(data[FIELDER]);
                deliveries.add(delivery);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    // Converting matches.csv into list form
    private static List<Matches> getMatchesData(String pathMatches) {
        List<Matches> matches= new ArrayList<>();
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathMatches));
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Matches match = new Matches();
                match.setId(data[ID]);
                match.setSeason(data[SEASON]);
                match.setCity(data[CITY]);
                match.setDate(data[DATE]);
                match.setTeam1(data[TEAM1]);
                match.setTeam2(data[TEAM2]);
                match.setToss_winner(data[TOSS_WINNER]);
                match.setToss_decision(data[TOSS_DECISION]);
                match.setResult(data[RESULT]);
                match.setDl_applied(data[DL_APPLIED]);
                match.setWinner(data[WINNER]);
                match.setWin_by_run(data[WIN_BY_RUNS]);
                match.setWin_by_wicket(data[WIN_BY_WICKETS]);
                match.setPlayer_of_match(data[PLAYER_OF_MATCH]);
                match.setVenue(data[VENUE]);
                if(data.length>=16) {
                    match.setUmpire1(data[UMPIRE1]);
                }
                if(data.length>=17) {
                    match.setUmpire2(data[UMPIRE2]);
                }
                if(data.length==18) {
                    match.setUmpire3(data[UMPIRE3]);
                }
                matches.add(match);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    // Question-1
    private void findMatchesPlayedPerYear(List<Matches> matches) {
        Map<String, Integer> matchesPerYear = new HashMap<>();
        for (Matches match : matches) {
            if (!match.getSeason().equals("season")) {
                matchesPerYear.put(match.getSeason(), matchesPerYear.getOrDefault(match.getSeason(), 0) +1);
            }
        }
        System.out.println(matchesPerYear);
    }

    // Question-2
    private void findNumberOfMatchesWonByAllTeams(List<Matches> matches) {
        HashMap<String, Integer> matchesWonByAllTeams = new HashMap<>();

        for (Matches match: matches) {
            String winner = match.getWinner();
            if(!match.getId().equals("id") && !winner.isEmpty()) {
                matchesWonByAllTeams.put(winner, matchesWonByAllTeams.getOrDefault(winner, 0) +1);
            }
        }
        System.out.println(matchesWonByAllTeams);
    }

    // Question-3
    private void findExtraRunsConcededPerTeamInYear2016(List<Matches> matches, List<Deliveries> deliveries) {
        HashMap<String, Integer> extraRunsConcededPerTeam = new HashMap<>();
        List<String> matchId = new ArrayList<>();
        for (Matches match: matches) {
            if (match.getSeason().equals("2016")) {
                matchId.add((match.getId()));
            }
        }

        for (Deliveries delivery: deliveries) {
            if (matchId.contains(delivery.getMatch_id())) {
                String teamName = delivery.getBatting_team();
                int extraRuns = Integer.parseInt((delivery.getExtra_runs()));
                extraRunsConcededPerTeam.put(teamName, extraRunsConcededPerTeam.getOrDefault(teamName, 0) + extraRuns);
            }
        }

        System.out.println(extraRunsConcededPerTeam);
    }

    // Question-4
    private void findMostEconomicBowlersIn2015(List<Matches> matches, List<Deliveries> deliveries) {
        Map<String, Integer> totalRun = new HashMap<>();
        Map<String, Integer> totalBall = new HashMap<>();
        List<Bowler> economicalBowlers = new ArrayList<>();
        List<String> matchIds2015 = new ArrayList<>();

        for (Matches match: matches) {
            if(match.getSeason().equals("2015")) {
                matchIds2015.add(match.getId());
            }
        }

        for (Deliveries delivery: deliveries) {
            if (matchIds2015.contains(delivery.getMatch_id())) {
                int totalRuns = 0;
                int totalBalls = 0;

                if (!delivery.getWide_runs().equals("0") || !delivery.getNoBall_runs().equals("0")) {
                    totalRuns += Integer.parseInt(delivery.getWide_runs()) + Integer.parseInt(delivery.getNoBall_runs());

                } else if (!delivery.getLeg_by_run().equals("0") || !delivery.getBye_runs().equals("0")) {
                    totalBalls += 1;
                } else {
                    totalBalls += 1;
                    totalRuns += Integer.parseInt(delivery.getBatsman_run());
                }
                totalRun.put(delivery.getBowler(), totalRun.getOrDefault(delivery.getBowler(), 0) + totalRuns);
                totalBall.put(delivery.getBowler(), totalBall.getOrDefault(delivery.getBowler(), 0) + totalBalls);
            }
        }

        for (String bowler : totalRun.keySet()) {
            if (totalBall.containsKey(bowler)) {
                double over = totalBall.get(bowler) / 6.0;
                double economy = totalRun.get(bowler)/ over;
                economicalBowlers.add(new Bowler(bowler, economy));
            }
        }

        economicalBowlers.sort(Comparator.comparingDouble(Bowler::getEconomy));

        for (Bowler bowler : economicalBowlers) {
            System.out.println(bowler.bowlerName + " " +  bowler.economy + ", ");
        }
    }

    // Question-5
    // Display the venue where the team batting first won the highest number of matches.
    private void findVenueWhereTeamBattingFirstWonHighestNumberOfMatches(List<Matches> matches) {
        Map<String, Integer> venueWinsCount = new HashMap<>();

        for (Matches match: matches) {
            if (match.getToss_decision().equals("bat") && match.getWinner().equals(match.getTeam1())) {
                String venue = match.getVenue();
                venueWinsCount.put(venue, venueWinsCount.getOrDefault(venue, 0) +1);
            }
        }

        String venueWithMostWins = Collections.max(venueWinsCount.entrySet(), Map.Entry.comparingByValue()).getKey();

        System.out.println(venueWithMostWins);
    }

    // Question-6
    // Find the number of times each team won the toss and also won the match
    private void findNoOfTimesTeamsWonTossAndWonMatch(List<Matches> matches) {
        Map<String, Integer> teamWonTossWonMatch = new HashMap<>();

        for (Matches match : matches) {
            if (match.getToss_winner().equals(match.getWinner())) {
                String team = match.getToss_winner();
                teamWonTossWonMatch.put(team, teamWonTossWonMatch.getOrDefault(team, 0) +1);
            }
        }

        System.out.println(teamWonTossWonMatch);
    }

    // Question-7
    // Find the players who have won the highest number of Player of the Match awards for each season
    private void findPlayersWithMostMOMAwardsBySeason(List<Matches> matches) {
        Map<String, Map<String, Integer>> playersWhoWonMOMAwards = new HashMap<>();

        for (Matches match : matches) {
           String season = match.getSeason();
           String playerOfTheMatch = match.getPlayer_of_match();
           playersWhoWonMOMAwards.putIfAbsent(season, new HashMap<>());
           playersWhoWonMOMAwards.get(season).put(playerOfTheMatch ,playersWhoWonMOMAwards.get(season).getOrDefault(playerOfTheMatch, 0) +1);
        }

        Map<String, String> seasonTopPlayers = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry :playersWhoWonMOMAwards.entrySet()) {
            String season = entry.getKey();
            Map<String, Integer> playerAwards = entry.getValue();

            String topPlayer = Collections.max(playerAwards.entrySet(), Map.Entry.comparingByValue()).getKey();
            seasonTopPlayers.put(season, topPlayer);
        }
        System.out.println(seasonTopPlayers);
    }

    // Question-8
    // Display the names of batsman who scored highest runs against 'sunrisers hyd' for each season
    private void findBatsmenWithHighestRunsAgainstHYD(List<Matches> matches, List<Deliveries> deliveries) {
        Map<String, String> seasonTopBatsmen = new HashMap<>();
        String team = "Sunrisers Hyderabad";
        String opponentTeam = " ";

        for (Matches match : matches) {
            if(match.getTeam1().equals(team)) {
                opponentTeam = match.getTeam2();
            } else if(match.getTeam2().equals(team)) {
                opponentTeam = match.getTeam1();
            }
            String season = match.getSeason();
            String matchId = match.getId();
            int maxRuns = 0;
            String topBatsman = "";

            for (Deliveries delivery : deliveries) {
                if (delivery.getMatch_id().equals(matchId)) {
                    String batsman = delivery.getBatsman();
                    int runs = Integer.parseInt(delivery.getBatsman_run());

                    if (runs > maxRuns) {
                        maxRuns = runs;
                        topBatsman = batsman;
                    }
                }
            }

            if (!topBatsman.isEmpty()) {
                seasonTopBatsmen.put(season, topBatsman);
            }
        }
        System.out.println("Batsmen who scored the highest runs against 'Sunrisers Hyderabad' for each season:");
        for (Map.Entry<String, String> entry : seasonTopBatsmen.entrySet()) {
            System.out.println("Season: " + entry.getKey() + ", Batsman: " + entry.getValue());
        }
    }

    // Question-9
    private void findBowlerWithHighestWicketsAgainstHYD(List<Matches> matches, List<Deliveries> deliveries) {
        String team = "Sunrisers Hyderabad";
        Map<String, Integer> bowlerWickets = new HashMap<>();

        for (Matches match : matches) {
            String matchId = match.getId();

            for (Deliveries delivery : deliveries) {
                if (delivery.getMatch_id().equals(matchId) && delivery.getBowling_team().equals(team)) {
                    String bowler = delivery.getBowler();
                    String dismissalKind = delivery.getDismissal_kind();

                    if (dismissalKind != null && !dismissalKind.isEmpty() &&
                            (dismissalKind.equals("caught") || dismissalKind.equals("bowled") ||
                                    dismissalKind.equals("lbw") || dismissalKind.equals("caught and bowled") ||
                                    dismissalKind.equals("stumped"))) {
                        bowlerWickets.put(bowler, bowlerWickets.getOrDefault(bowler, 0) + 1);
                    }
                }
            }
        }

        if (!bowlerWickets.isEmpty()) {
            Map.Entry<String, Integer> entry = Collections.max(bowlerWickets.entrySet(), Map.Entry.comparingByValue());
            String topBowler = entry.getKey();
            int wickets = entry.getValue();

            System.out.println("Bowler who took the highest wickets against 'Sunrisers Hyderabad':");
            System.out.println("Bowler: " + topBowler);
            System.out.println("Wickets: " + wickets);
        } else {
            System.out.println("No bowlers took wickets against 'Sunrisers Hyderabad'.");
        }
    }

    public static void main(String[] args) {
        Main mainProcessor = new Main();
        String pathDeliveries = "/home/vivek/AutomationTrack/IPLProject/src/resources/deliveries.csv";
        String pathMatches = "/home/vivek/AutomationTrack/IPLProject/src/resources/matches.csv";

        List<Matches> matchesData = getMatchesData(pathMatches);
        List<Deliveries> deliveriesData = getDeliveriesData(pathDeliveries);

//            mainProcessor.findMatchesPlayedPerYear(matchesData);

//        mainProcessor.findNumberOfMatchesWonByAllTeams(matchesData);

//        mainProcessor.findExtraRunsConcededPerTeamInYear2016(matchesData,deliveriesData);

//        mainProcessor.findMostEconomicBowlersIn2015(matchesData,deliveriesData);

//        mainProcessor. findVenueWhereTeamBattingFirstWonHighestNumberOfMatches(matchesData);
//        mainProcessor.findNoOfTimesTeamsWonTossAndWonMatch(matchesData);
//        mainProcessor.findPlayersWithMostMOMAwardsBySeason(matchesData);
//        mainProcessor.findBatsmenWithHighestRunsAgainstHYD(matchesData, deliveriesData);
            mainProcessor.findBowlerWithHighestWicketsAgainstHYD(matchesData, deliveriesData);

    }

}