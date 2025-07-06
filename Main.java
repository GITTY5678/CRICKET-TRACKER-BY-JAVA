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

                default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
}}
