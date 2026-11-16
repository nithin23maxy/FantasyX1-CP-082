
import java.util.ArrayList;

public class PlayerManager {

    private ArrayList<Player> players;

    // Constructor
    public PlayerManager() {

        players = new ArrayList<>();

        // Adding sample players
        players.add(new Player("Virat Kohli", "Batter", "RCB", 15.0));
        players.add(new Player("Rajat Patidar", "Batter", "RCB", 9.0));
        players.add(new Player("Rohit Sharma", "Batter", "MI", 10.0));
        players.add(new Player("Suryakumar Yadav", "Batter", "MI", 9.5));

        players.add(new Player("Jasprit Bumrah", "Bowler", "MI", 9.5));
        players.add(new Player("Mohammed Siraj", "Bowler", "GT", 8.5));
        players.add(new Player("Yuzvendra Chahal", "Bowler", "PBKS", 8.5));

        players.add(new Player("Hardik Pandya", "All-Rounder", "MI", 9.0));
        players.add(new Player("Ravindra Jadeja", "All-Rounder", "CSK", 9.5));
        players.add(new Player("Andre Russell", "All-Rounder", "KKR", 9.0));

        players.add(new Player("KL Rahul", "Wicketkeeper", "DC", 9.0));
        players.add(new Player("Rishabh Pant", "Wicketkeeper", "LSG", 9.5));
    }

    // Display all available players
    public void displayPlayers() {

        System.out.println("\n========== AVAILABLE PLAYERS ==========");

        for (int i = 0; i < players.size(); i++) {

            System.out.println("Player ID: " + (i+1));

            players.get(i).display();
        }
    }

    // Return the complete player list
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
