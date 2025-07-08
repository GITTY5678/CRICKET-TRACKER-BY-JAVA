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
                    if (parts.length == 3) {
                        String name = parts[0];
                        int runs = Integer.parseInt(parts[1]);
                        int matchesPlayed = Integer.parseInt(parts[2]);
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
            System.out.println("5. Delete Player");
            System.out.println("6. Sort by Average");
            System.out.println("7. Export to CSV");
            System.out.println("8. Show Team Stats");
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
                    scanner.nextLine();

                    players.add(new Player(name, runs, matchesPlayed));
                    System.out.println("✅ Player added.");
                    savePlayers(players);
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
                            System.out.print("Enter new matches played: ");
                            player.matchesPlayed = scanner.nextInt();
                            System.out.print("Enter new runs: ");
                            player.runs = scanner.nextInt();
                            scanner.nextLine();
                            player.averageScore = player.calculateAverageScore();
                            System.out.println("✅ Player updated.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("❌ Player not found.");
                    } else {
                        savePlayers(players);
                    }
                    break;

                case 5:
                    System.out.print("Enter player name to delete: ");
                    String deleteName = scanner.nextLine();
                    boolean deleted = false;
                    for (int i = 0; i < players.size(); i++) {
                        if (players.get(i).name.equalsIgnoreCase(deleteName)) {
                            players.remove(i);
                            deleted = true;
                            System.out.println("🗑 Player deleted.");
                            break;
                        }
                    }
                    if (!deleted) {
                        System.out.println("❌ Player not found.");
                    } else {
                        savePlayers(players);
                    }
                    break;

                case 6:
                    if (players.isEmpty()) {
                        System.out.println("⚠️ No players to sort.");
                    } else {
                        players.sort(new Comparator<Player>() {
                            public int compare(Player a, Player b) {
                                return Double.compare(b.averageScore, a.averageScore);
                            }
                        });
                        System.out.println("📊 Players sorted by average:");
                        for (Player p : players) {
                            p.display();
                        }
                        savePlayers(players);
                    }
                    break;

                case 7:
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("players.csv"));
                        writer.write("Name,Runs,Matches,Average");
                        writer.newLine();
                        for (Player p : players) {
                            writer.write(p.name + "," + p.runs + "," + p.matchesPlayed + "," + String.format("%.2f", p.averageScore));
                            writer.newLine();
                        }
                        writer.close();
                        System.out.println("✅ Exported to players.csv");
                    } catch (IOException e) {
                        System.out.println("⚠️ Error exporting to CSV: " + e.getMessage());
                    }
                    break;

                case 8:
                    if (players.isEmpty()) {
                        System.out.println("⚠️ No players to show stats.");
                    } else {
                        int totalRuns = 0;
                        int totalMatches = 0;
                        for (Player p : players) {
                            totalRuns += p.runs;
                            totalMatches += p.matchesPlayed;
                        }
                        double teamAvg = totalMatches == 0 ? 0 : (double) totalRuns / totalMatches;

                        System.out.println("🏏 Team Stats:");
                        System.out.println("Total Players: " + players.size());
                        System.out.println("Total Runs: " + totalRuns);
                        System.out.println("Total Matches: " + totalMatches);
                        System.out.println("Team Batting Average: " + String.format("%.2f", teamAvg));
                    }
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    // 💾 Helper method to save players
    public static void savePlayers(ArrayList<Player> players) {
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
    }
}

