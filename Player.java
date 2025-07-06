public class Player{
    String name;
    int runs;
    int matchesPlayed;
    double averageScore;
    public Player(String name, int runs, int matchesPlayed) {
        this.name = name;
        this.runs = runs;
        this.matchesPlayed = matchesPlayed;
        this.averageScore = calculateAverageScore();
    }
    public double calculateAverageScore() {
        if (matchesPlayed == 0) {
            return 0.0; // Avoid division by zero
        }
        return (double) runs / matchesPlayed;
    }
    public void display() {
        System.out.println("Player Name: " + name);
        System.out.println("Total Score: " + runs);
        System.out.println("Matches Played: " + matchesPlayed);
        System.out.println("Average Score: " + averageScore + '\n');
        
    }
    public String toFileString() {
        return name + "," + runs + "," + matchesPlayed + "," + averageScore;
    }
}