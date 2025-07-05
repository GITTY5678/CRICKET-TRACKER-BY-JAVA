public class Player{
    String name;
    int score;
    int matchesPlayed;
    double averageScore;
    public Player(String name, int score, int matchesPlayed) {
        this.name = name;
        this.score = score;
        this.matchesPlayed = matchesPlayed;
        this.averageScore = calculateAverageScore();
    }
    public double calculateAverageScore() {
        if (matchesPlayed == 0) {
            return 0.0; // Avoid division by zero
        }
        return (double) score / matchesPlayed;
    }
    public void display() {
        System.out.println("Player Name: " + name);
        System.out.println("Total Score: " + score);
        System.out.println("Matches Played: " + matchesPlayed);
        System.out.println("Average Score: " + averageScore + '\n');
        
    }
}