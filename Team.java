
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Team {

    private ArrayList<Player> myTeam;

    private Player captain;
    private Player viceCaptain;

    private HashMap<Player, Double> playerPoints;

    public Team() {

        myTeam = new ArrayList<>();

        captain = null;
        viceCaptain = null;

        playerPoints = new HashMap<>();
    }

    // Safe integer input method
    private int getIntegerInput(Scanner sc) {

        while (!sc.hasNextInt()) {

            System.out.print("Invalid input! Enter a number: ");

            sc.next();
        }

        return sc.nextInt();
    }

    // Add player
    public boolean addPlayer(Player player) {

        if (myTeam.contains(player)) {

            System.out.println("Player already selected!");

            return false;
        }

        if (myTeam.size() >= 11) {

            System.out.println("Your team already has 11 players!");

            return false;
        }

        myTeam.add(player);

        playerPoints.clear();

        System.out.println(player.getName() + " added successfully!");

        return true;
    }

    // Display team
    public void displayTeam() {

        if (myTeam.isEmpty()) {

            System.out.println("Your team is empty!");

            return;
        }

        System.out.println("\n========== MY FANTASY TEAM ==========");

        for (int i = 0; i < myTeam.size(); i++) {

            Player player = myTeam.get(i);

            System.out.println("\nPlayer ID: " + (i + 1));

            player.display();

            if (player == captain) {
                System.out.println("CAPTAIN (C)");
            }

            if (player == viceCaptain) {
                System.out.println("VICE-CAPTAIN (VC)");
            }
        }

        System.out.println("Total Players: " + myTeam.size());
    }

    // Remove player
    public void removePlayer(int index) {

        if (index < 1 || index > myTeam.size()) {

            System.out.println("Invalid player ID!");

            return;
        }

        Player removed = myTeam.remove(index - 1);

        if (removed == captain) {
            captain = null;
        }

        if (removed == viceCaptain) {
            viceCaptain = null;
        }

        playerPoints.clear();

        System.out.println(removed.getName() + " removed successfully!");
    }

    // Validate team
    public void validateTeam() {

        boolean valid = true;

        double totalCredits = 0;

        int batters = 0;
        int bowlers = 0;
        int allRounders = 0;
        int wicketkeepers = 0;

        HashMap<String, Integer> teamCount = new HashMap<>();

        System.out.println("\n========== TEAM VALIDATION ==========");

        for (Player player : myTeam) {

            totalCredits += player.getCredits();

            String role = player.getRole();

            if (role.equalsIgnoreCase("Batter")) {
                batters++;
            }
            else if (role.equalsIgnoreCase("Bowler")) {
                bowlers++;
            }
            else if (role.equalsIgnoreCase("All-Rounder")) {
                allRounders++;
            }
            else if (role.equalsIgnoreCase("Wicketkeeper")) {
                wicketkeepers++;
            }

            String realTeam = player.getTeam();

            teamCount.put(
                realTeam,
                teamCount.getOrDefault(realTeam, 0) + 1
            );
        }

        // Player count
        if (myTeam.size() == 11) {

            System.out.println("Player count: Valid");

        } else {

            System.out.println("Player count: Invalid - Need exactly 11 players");

            valid = false;
        }

        // Credit validation
        System.out.println("Total credits: " + totalCredits);

        if (totalCredits <= 100) {

            System.out.println("Credit limit: Valid");

        } else {

            System.out.println("Credit limit: Invalid - Maximum 100 credits");

            valid = false;
        }

        // Role validation
        System.out.println("\nRole Details:");

        System.out.println("Batters: " + batters);
        System.out.println("Bowlers: " + bowlers);
        System.out.println("All-Rounders: " + allRounders);
        System.out.println("Wicketkeepers: " + wicketkeepers);

        if (batters < 3) {

            System.out.println("Need at least 3 batters!");

            valid = false;
        }

        if (bowlers < 3) {

            System.out.println("Need at least 3 bowlers!");

            valid = false;
        }

        if (allRounders < 1) {

            System.out.println("Need at least 1 all-rounder!");

            valid = false;
        }

        if (wicketkeepers < 1) {

            System.out.println("Need at least 1 wicketkeeper!");

            valid = false;
        }

        // Real team limit
        for (String realTeam : teamCount.keySet()) {

            if (teamCount.get(realTeam) > 7) {

                System.out.println("Too many players from " + realTeam);

                valid = false;
            }
        }

        // Captain validation
        if (captain == null) {

            System.out.println("Captain not selected!");

            valid = false;
        }

        if (viceCaptain == null) {

            System.out.println("Vice-Captain not selected!");

            valid = false;
        }

        if (valid) {

            System.out.println("\nYour FantasyXI team is VALID!");

        } else {

            System.out.println("\nYour FantasyXI team is INVALID!");
        }
    }

    // Select captain
    public void setCaptain(int index) {

        if (index < 1 || index > myTeam.size()) {

            System.out.println("Invalid player ID!");

            return;
        }

        Player selected = myTeam.get(index - 1);

        if (selected == viceCaptain) {

            System.out.println("Vice-Captain cannot be Captain!");

            return;
        }

        captain = selected;

        playerPoints.clear();

        System.out.println(captain.getName() + " selected as Captain!");
    }

    // Select vice-captain
    public void setViceCaptain(int index) {

        if (index < 1 || index > myTeam.size()) {

            System.out.println("Invalid player ID!");

            return;
        }

        Player selected = myTeam.get(index - 1);

        if (selected == captain) {

            System.out.println("Captain cannot be Vice-Captain!");

            return;
        }

        viceCaptain = selected;

        playerPoints.clear();

        System.out.println(viceCaptain.getName() + " selected as Vice-Captain!");
    }

    // Display captain details
    public void displayCaptainDetails() {

        System.out.println("\n========== TEAM LEADERS ==========");

        if (captain == null) {

            System.out.println("Captain not selected!");

        } else {

            System.out.println("Captain: " + captain.getName());
        }

        if (viceCaptain == null) {

            System.out.println("Vice-Captain not selected!");

        } else {

            System.out.println("Vice-Captain: " + viceCaptain.getName());
        }
    }

    // Calculate fantasy points
    public void calculatePoints(Scanner sc) {

        if (myTeam.isEmpty()) {

            System.out.println("Your team is empty!");

            return;
        }

        if (captain == null || viceCaptain == null) {

            System.out.println("Select Captain and Vice-Captain first!");

            return;
        }

        HashMap<Player, Double> newPoints = new HashMap<>();

        double totalPoints = 0;

        System.out.println("\n========== FANTASY POINTS CALCULATION ==========");

        for (Player player : myTeam) {

            System.out.println("\nPlayer: " + player.getName());

            System.out.print("Enter runs scored: ");

            int runs = getIntegerInput(sc);

            System.out.print("Enter wickets taken: ");

            int wickets = getIntegerInput(sc);

            System.out.print("Enter catches taken: ");

            int catches = getIntegerInput(sc);

            if (runs < 0 || wickets < 0 || catches < 0) {

                System.out.println("Invalid statistics! Values cannot be negative.");

                System.out.println("Please calculate points again.");

                playerPoints.clear();

                return;
            }

            double points = runs + (wickets * 25) + (catches * 8);

            if (player == captain) {

                points = points * 2;

            }
            else if (player == viceCaptain) {

                points = points * 1.5;
            }

            newPoints.put(player, points);

            totalPoints += points;

            System.out.println("Fantasy Points: " + points);
        }

        playerPoints.clear();

        playerPoints.putAll(newPoints);

        System.out.println("\n========== FINAL SCORE ==========");

        System.out.println("Total Fantasy Points: " + totalPoints);
    }

    // Display leaderboard
    public void displayLeaderboard() {

        if (myTeam.isEmpty()) {

            System.out.println("Your team is empty!");

            return;
        }

        for (Player player : myTeam) {

            if (!playerPoints.containsKey(player)) {

                System.out.println("Please calculate points for all players first!");

                return;
            }
        }

        ArrayList<Player> sortedTeam = new ArrayList<>(myTeam);

        sortedTeam.sort(
            (p1, p2) -> Double.compare(
                playerPoints.get(p2),
                playerPoints.get(p1)
            )
        );

        System.out.println("\n========== FANTASYXI LEADERBOARD ==========");

        System.out.printf("%-10s %-22s %-15s%n",
                "RANK", "PLAYER", "POINTS");

        System.out.println("------------------------------------------------");

        for (int i = 0; i < sortedTeam.size(); i++) {

            Player player = sortedTeam.get(i);

            double points = playerPoints.get(player);

            String name = player.getName();

            if (player == captain) {
                name += " (C)";
            }
            else if (player == viceCaptain) {
                name += " (VC)";
            }

            System.out.printf("%-10d %-22s %-15.2f%n",
                    (i + 1),
                    name,
                    points);
        }

        System.out.println("------------------------------------------------");
    }

    // Return player points
    public HashMap<Player, Double> getPlayerPoints() {

        return playerPoints;
    }

    // Return selected players
    public ArrayList<Player> getMyTeam() {

        return myTeam;
    }

    // Clear existing team before loading
    public void clearTeam() {

        myTeam.clear();

        captain = null;
        viceCaptain = null;

        playerPoints.clear();

        System.out.println("Existing team cleared!");
    }

    // Return captain
    public Player getCaptain() {

        return captain;
    }

    // Return vice-captain
    public Player getViceCaptain() {

        return viceCaptain;
    }

}
