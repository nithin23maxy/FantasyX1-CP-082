import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class FileManager {

    // Save team to a text file
    public void saveTeam(Team team) {

        ArrayList<Player> myTeam = team.getMyTeam();

        if (myTeam.isEmpty()) {
            System.out.println("Cannot save an empty team!");
            return;
        }

        if (myTeam.size() > 11) {
            System.out.println("Invalid team! Maximum 11 players allowed.");
            return;
        }

        if (team.getCaptain() == null ||
            team.getViceCaptain() == null ||
            team.getCaptain() == team.getViceCaptain() ||
            !myTeam.contains(team.getCaptain()) ||
            !myTeam.contains(team.getViceCaptain())) {

            System.out.println("Select a valid Captain and Vice-Captain first!");
            return;
        }

        double totalCredits = 0;
        HashSet<String> names = new HashSet<>();
        HashSet<String> realTeams = new HashSet<>();

        for (Player player : myTeam) {

            totalCredits += player.getCredits();

            if (!names.add(player.getName().toLowerCase())) {
                System.out.println("Duplicate player found! Team not saved.");
                return;
            }

            realTeams.add(player.getTeam());
        }

        if (totalCredits > 100) {
            System.out.println("Credit limit exceeded! Team not saved.");
            return;
        }

        for (String realTeam : realTeams) {

            int count = 0;

            for (Player player : myTeam) {

                if (player.getTeam().equalsIgnoreCase(realTeam)) {
                    count++;
                }
            }

            if (count > 7) {
                System.out.println("Maximum 7 players allowed from one team!");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("fantasy_team.txt"))) {

            for (Player player : myTeam) {

                String leader = "PLAYER";

                if (player == team.getCaptain()) {
                    leader = "CAPTAIN";
                }
                else if (player == team.getViceCaptain()) {
                    leader = "VICE";
                }

                writer.write(
                    player.getName() + "|" +
                    player.getRole() + "|" +
                    player.getTeam() + "|" +
                    player.getCredits() + "|" +
                    leader
                );

                writer.newLine();
            }

            System.out.println("Team saved successfully!");

        } catch (IOException e) {

            System.out.println("Error while saving team!");
        }
    }

    // Load team from a text file
    public void loadTeam(
            Team team,
            ArrayList<Player> availablePlayers) {

        ArrayList<Player> loadedPlayers = new ArrayList<>();

        HashSet<String> names = new HashSet<>();

        Player loadedCaptain = null;
        Player loadedViceCaptain = null;

        double totalCredits = 0;

        int captainCount = 0;
        int viceCaptainCount = 0;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("fantasy_team.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                // Check file format
                if (data.length != 5) {
                    System.out.println("Invalid saved file format!");
                    return;
                }

                String name = data[0].trim();
                String role = data[1].trim();
                String realTeam = data[2].trim();
                String leader = data[4].trim();

                double credits;

                try {
                    credits = Double.parseDouble(data[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid credits in saved file!");
                    return;
                }

                if (name.isEmpty() || role.isEmpty() ||
                    realTeam.isEmpty() || !Double.isFinite(credits) ||
                    credits <= 0) {

                    System.out.println("Invalid player information in saved file!");
                    return;
                }

                // Check duplicate players
                if (!names.add(name.toLowerCase())) {
                    System.out.println("Duplicate player found in saved file!");
                    return;
                }

                // Find player in available player list
                Player matchedPlayer = null;

                for (Player player : availablePlayers) {

                    if (player.getName().equalsIgnoreCase(name)) {
                        matchedPlayer = player;
                        break;
                    }
                }

                if (matchedPlayer == null) {
                    System.out.println("Player not found: " + name);
                    return;
                }

                // Check saved details against original player details
                if (!matchedPlayer.getRole().equalsIgnoreCase(role) ||
                    !matchedPlayer.getTeam().equalsIgnoreCase(realTeam) ||
                    Math.abs(matchedPlayer.getCredits() - credits) > 0.000001) {

                    System.out.println("Player details do not match: " + name);
                    return;
                }

                // Check leadership information
                if (leader.equalsIgnoreCase("CAPTAIN")) {

                    captainCount++;
                    loadedCaptain = matchedPlayer;

                }
                else if (leader.equalsIgnoreCase("VICE")) {

                    viceCaptainCount++;
                    loadedViceCaptain = matchedPlayer;

                }
                else if (!leader.equalsIgnoreCase("PLAYER")) {

                    System.out.println("Invalid leadership information!");
                    return;
                }

                loadedPlayers.add(matchedPlayer);

                totalCredits += matchedPlayer.getCredits();

                // Maximum 11 players
                if (loadedPlayers.size() > 11) {
                    System.out.println("Saved team has more than 11 players!");
                    return;
                }

                // Maximum 100 credits
                if (totalCredits > 100) {
                    System.out.println("Saved team exceeds 100 credits!");
                    return;
                }
            }

            // Check empty file
            if (loadedPlayers.isEmpty()) {
                System.out.println("Saved file contains no players!");
                return;
            }

            // Check captain and vice-captain
            if (captainCount != 1 || viceCaptainCount != 1) {
                System.out.println("Saved team must have one Captain and one Vice-Captain!");
                return;
            }

            if (loadedCaptain == loadedViceCaptain) {
                System.out.println("Captain and Vice-Captain must be different!");
                return;
            }

            // Check maximum 7 players from one real team
            for (String realTeam : names) {

               int count = 0;

                for (Player player : loadedPlayers) {

                    if (player.getTeam().equalsIgnoreCase(realTeam)) {
                        count++;
                    }
                }
            }

            for (Player player : loadedPlayers) {

                int count = 0;

                for (Player other : loadedPlayers) {

                    if (player.getTeam().equalsIgnoreCase(other.getTeam())) {
                        count++;
                    }
                }

                if (count > 7) {
                    System.out.println("More than 7 players from one team!");
                    return;
                }
            }

            // Only replace the existing team after all checks pass
            team.clearTeam();

            for (Player player : loadedPlayers) {
                team.addPlayer(player);
            }

            team.setCaptain(loadedPlayers.indexOf(loadedCaptain) + 1);

            team.setViceCaptain(loadedPlayers.indexOf(loadedViceCaptain) + 1);

            System.out.println("Team loaded successfully!");

        } catch (FileNotFoundException e) {

            System.out.println("No saved team found!");

        } catch (IOException e) {

            System.out.println("Error while loading team!");
        }
    }
}
