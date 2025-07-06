import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();

        while (true){
            System.out.println("1.Add player");
            System.out.println("2.display players");
            System.out.println("3.exit");
            System.out.println("4.update player");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice){
                case 1:
                System.out.print("Enter player name: ");
                String name = scanner.nextLine();
                System.out.print("Enter total matchesplayed: ");
                int matchesPlayed = scanner.nextInt();
                System.out.print("Enter total runs: ");
                int score = scanner.nextInt();
                players.add(new Player(name, score, matchesPlayed));
                break;

                case 2:
                if (players.isEmpty()) {
                    System.out.println("No players to display.");
                } else {
                    for (Player player : players) {
                        player.display();
                    }
                }
                break;
                case 3:
                System.out.println("Exiting the program.");
                break;

                case 4:
                System.out.print("Enter player name to update: ");
                String playerName = scanner.nextLine();
                boolean found = false;

                for (Player player : players) {
                    if (player.name.equalsIgnoreCase(playerName)) {
                        found = true;
                        System.out.print("Enter new total matches played: ");
                        player.matchesPlayed = scanner.nextInt();
                        System.out.print("Enter new total runs: ");
                        player.score = scanner.nextInt();
                        player.averageScore = player.calculateAverageScore(); // Recalculate average score
                        System.out.println("Player updated successfully.");
                        break;

                    
                    }
                    if (!found) {
                        System.out.println("Player not found.");
                    }
                    break;
                }

                default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
}}
