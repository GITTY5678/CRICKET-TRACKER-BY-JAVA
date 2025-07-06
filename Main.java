import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

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

                try{
                    File file =new File("C:\\Users\\HARIHARA SUTHAN\\Documents\\GitHub\\CRICKET-TRACKER-BY-JAVA\\play.txt");
                    BufferedWriter writer=new BufferedWriter(new FileWriter("C:\\Users\\HARIHARA SUTHAN\\Documents\\GitHub\\CRICKET-TRACKER-BY-JAVA\\play.txt", true));
                    for (Player player : players) {
                        writer.write(player.toFileString());
                        writer.newLine();
                    }
                    writer.close();
                    System.out.println("Player added successfully.");
                }
                catch (IOException e) {
                    System.out.println("Error writing player data: " + e.getMessage());
                }
                break;

                case 2:
                if (players.isEmpty()) {
                    System.out.println("No players to display.");
                } else {
                    try{
            File file = new File("C:\\Users\\HARIHARA SUTHAN\\Documents\\GitHub\\CRICKET-TRACKER-BY-JAVA\\play.txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String name = parts[0];
                        int runs = Integer.parseInt(parts[1]);
                        int matchesPlayed = Integer.parseInt(parts[2]);
                        players.add(new Player(name, runs, matchesPlayed));
                    }
                }
                reader.close();
            } 
        }
        catch (IOException e) {
            System.out.println("Error reading player data: " + e.getMessage());
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
                        player.runs = scanner.nextInt();
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
