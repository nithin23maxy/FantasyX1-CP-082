
public class Player {

    // Player details
    private String name;
    private String role;
    private String team;
    private double credits;

    // Constructor
    public Player(String name, String role, String team, double credits) {
        this.name = name;
        this.role = role;
        this.team = team;
        this.credits = credits;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getTeam() {
        return team;
    }

    public double getCredits() {
        return credits;
    }

    // Display player details
    public void display() {
        System.out.println("------------------------------");
        System.out.println("Player Name : " + name);
        System.out.println("Role        : " + role);
        System.out.println("Team        : " + team);
        System.out.println("Credits     : " + credits);
        System.out.println("------------------------------");
    }
}
