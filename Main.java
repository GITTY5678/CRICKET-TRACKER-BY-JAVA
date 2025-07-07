import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();

        // 🔄 Load players from file
        try {
            File file = new File("play.txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String name = parts[0];
                        int runs = Integer.parseInt(parts[1]);
                        int matchesPlayed = Integer.parseInt(parts[2]);
                        double averageScore = Double.parseDouble(parts[3]);
                        players.add(new Player(name, runs, matchesPlayed));
                    }
                }
                reader.close();
                System.out.println("📂 Players loaded from file.");
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error reading player data: " + e.getMessage());
        }

        // 🔁 Menu loop
        while (true) {
            System.out.println("\n--- Cricket Tracker Menu ---");
            System.out.println("1. Add Player");
            System.out.println("2. Display Players");
            System.out.println("3. Exit");
            System.out.println("4. Update Player");
            System.out.println("5. delete Player");
            System.out.println("6.sort by average ");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter player name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter total matches played: ");
                    int matchesPlayed = scanner.nextInt();
                    System.out.print("Enter total runs: ");
                    int runs = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    Player newPlayer = new Player(name, runs, matchesPlayed);
                    players.add(newPlayer);
                    System.out.println("✅ Player added.");

                    // 💾 Save all players after adding
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("play.txt"));
                        for (Player p : players) {
                            writer.write(p.toFileString());
                            writer.newLine();
                        }
                        writer.close();
                        System.out.println("💾 Data saved.");
                    } catch (IOException e) {
                        System.out.println("⚠️ Error saving player data: " + e.getMessage());
                    }
                    break;

                case 2:
                    if (players.isEmpty()) {
                        System.out.println("⚠️ No players to display.");
                    } else {
                        for (Player p : players) {
                            p.display();
                        }
                    }
                    break;

                case 3:
                    System.out.println("👋 Exiting the program. Goodbye!");
                    System.exit(0);
                    break;

                case 4:
                    System.out.print("Enter player name to update: ");
                    String playerName = scanner.nextLine();
                    boolean found = false;

                    for (Player player : players) {
                        if (player.name.equalsIgnoreCase(playerName)) {
                            System.out.print("Enter new total matches played: ");
                            player.matchesPlayed = scanner.nextInt();
                            System.out.print("Enter new total runs: ");
                            player.runs = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            player.averageScore = player.calculateAverageScore();
                            System.out.println("✅ Player updated.");
                            found = true;

                            // 💾 Save updated list to file
                            try {
                                BufferedWriter writer = new BufferedWriter(new FileWriter("play.txt"));
                                for (Player p : players) {
                                    writer.write(p.toFileString());
                                    writer.newLine();
                                }
                                writer.close();
                                System.out.println("💾 Data saved.");
                            } catch (IOException e) {
                                System.out.println("⚠️ Error saving player data: " + e.getMessage());
                            }
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("❌ Player not found.");
                    }
                    break;
                    case 5:
                    System.out.print("Enter player name to delete: ");
                    String deletename = scanner.nextLine();
                    boolean delete = false;
                    for (int i=0;i< players.size(); i++) {
                        if (players.get(i).name.equalsIgnoreCase(deletename)) {
                            players.remove(i);
                            System.out.println("✅ Player deleted.");
                            delete = true;

                            // 💾 Save updated list to file
                            try {
                                BufferedWriter writer = new BufferedWriter(new FileWriter("play.txt"));
                                for (Player p : players) {
                                    writer.write(p.toFileString());
                                    writer.newLine();
                                }
                                writer.close();
                                System.out.println("💾 Data saved.");
                            } catch (IOException e) {
                                System.out.println("⚠️ Error saving player data: " + e.getMessage());
                            }
                            break;
                        }
                    }   
                    case 6:
                    if (players.isEmpty()) {
                        System.out.println("No players to sort");
                    }else{
                        players.sort(new Comparator<Player>() {
                            public int compare(Player a,Player b){
                                return Double.compare(b.averageScore, a.averageScore);
                            }
                        });
                        System.out.println("Players sorted by average score in descending order:");
                        for (Player p : players) {
                            p.display();
                        }
                    }
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("play.txt"));
                        for (Player p : players) {
                            writer.write(p.toFileString());
                            writer.newLine();
                        }
                        writer.close();
                        System.out.println("💾 Data saved.");
                    } catch (IOException e) {
                        System.out.println("⚠️ Error saving player data: " + e.getMessage());
                    }
                    break;
                    


                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
}
