
import java.util.Scanner;

public class Main {

    // Safe integer input method
    public static int getIntegerInput(Scanner sc) {

        while (!sc.hasNextInt()) {

            System.out.print("Invalid input! Enter a number: ");

            sc.next();
        }

        return sc.nextInt();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PlayerManager manager = new PlayerManager();

        Team team = new Team();
        FileManager fileManager = new FileManager();

        int choice;

        while (true) {

            System.out.println("\n========== FANTASYXI ==========");
            System.out.println("1. View Available Players");
            System.out.println("2. Add Player");
            System.out.println("3. View My Team");
            System.out.println("4. Calculate Points");
            System.out.println("5. View Leaderboard");
            System.out.println("6. Remove Player");
            System.out.println("7. Validate My Team");
            System.out.println("8. Select Captain");
            System.out.println("9. Select Vice-Captain");
            System.out.println("10. View Captain Details");
            System.out.println("0. Exit");
            System.out.println("11. Save Team");
System.out.println("12. Load Team");

            System.out.print("\nEnter your choice: ");

            choice = getIntegerInput(sc);

            switch (choice) {

                case 1:
                    manager.displayPlayers();
                    break;

                case 2:
                    manager.displayPlayers();

                    System.out.print("\nEnter Player ID to add: ");

                    int id = getIntegerInput(sc);

                    if (id >= 1 && id <= manager.getPlayers().size()) {

                        Player selected = manager.getPlayers().get(id - 1);

                        team.addPlayer(selected);

                    } else {

                        System.out.println("Invalid Player ID!");
                    }

                    break;

                case 3:
                    team.displayTeam();
                    break;

                case 4:
                    team.calculatePoints(sc);
                    break;

                case 5:
                    team.displayLeaderboard();
                    break;

                case 6:
                    team.displayTeam();

                    if (!team.getMyTeam().isEmpty()) {

                        System.out.print("\nEnter Player ID to remove: ");

                        int removeId = getIntegerInput(sc);

                        team.removePlayer(removeId);
                    }

                    break;

                case 7:
                    team.validateTeam();
                    break;

                case 8:
                    team.displayTeam();

                    if (!team.getMyTeam().isEmpty()) {

                        System.out.print("\nEnter Captain Player ID: ");

                        int captainId = getIntegerInput(sc);

                        team.setCaptain(captainId);
                    }

                    break;

                case 9:
                    team.displayTeam();

                    if (!team.getMyTeam().isEmpty()) {

                        System.out.print("\nEnter Vice-Captain Player ID: ");

                        int viceCaptainId = getIntegerInput(sc);

                        team.setViceCaptain(viceCaptainId);
                    }

                    break;

                case 10:
                    team.displayCaptainDetails();
                    break;
                    case 11:
    fileManager.saveTeam(team);
    break;

case 12:
    fileManager.loadTeam(team, manager.getPlayers());
    break;

                case 0:
                    System.out.println("\nThank you for using FantasyXI!");

                    sc.close();

                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
