import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    // Evaluates a single round and returns the result
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        switch (playerMove.toLowerCase()) {
            case "rock":
                return computerMove.equalsIgnoreCase("scissors") ? "Player Wins" : "Computer Wins";
            case "paper":
                return computerMove.equalsIgnoreCase("rock") ? "Player Wins" : "Computer Wins";
            case "scissors":
                return computerMove.equalsIgnoreCase("paper") ? "Player Wins" : "Computer Wins";
            default:
                return "Invalid Move";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] moves = {"Rock", "Paper", "Scissors"};

        int totalRounds = 5;
        String[][] history = new String[totalRounds][4]; // Round, Player, Computer, Result

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("=== Welcome to the Coding Arcade: Rock-Paper-Scissors ===");

        for (int i = 0; i < totalRounds; i++) {
            System.out.print("\nRound " + (i + 1) + " - Enter move (Rock, Paper, Scissors): ");
            String playerMove = scanner.nextLine().trim();

            // Generate computer move
            String computerMove = moves[random.nextInt(3)];

            String result = playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else if (result.equals("Draw")) {
                draws++;
            } else {
                System.out.println("Invalid input! Defaulting to Computer Win for this round.");
                result = "Computer Wins";
                losses++;
            }

            System.out.println("Player: " + playerMove + ", Computer: " + computerMove);
            System.out.println("Result: " + result);

            // Record history
            history[i][0] = String.valueOf(i + 1);
            history[i][1] = playerMove;
            history[i][2] = computerMove;
            history[i][3] = result;
        }

        // Print Summary Table
        System.out.println("\n========================================================");
        System.out.printf("%-8s | %-12s | %-14s | %-12s\n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("%-8s | %-12s | %-14s | %-12s\n", 
                history[i][0], history[i][1], history[i][2], history[i][3]);
        }
        System.out.println("========================================================");

        // Calculate and display statistics
        double winPercentage = ((double) wins / totalRounds) * 100.0;
        System.out.printf("Final Summary: Wins: %d | Losses: %d | Draws: %d | Win %%=%.1f%%\n", 
            wins, losses, draws, winPercentage);

        scanner.close();
    }
}