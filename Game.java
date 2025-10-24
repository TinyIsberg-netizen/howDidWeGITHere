import java.util.Random;
import java.util.Scanner;

public class Game {

    // Start the game after the player has chosen a name/race
    public void start(Scanner scan, String playerName) {
        Random rand = new Random();

        System.out.println("\n== Dice Tower ==");
        System.out.println("Hello, " + playerName + "!");
        System.out.println("Rules:");
        System.out.println("• Clear 4 floors in a row to rescue the target.");
        System.out.println("• You always roll a 12-sided die (1–12).");
        System.out.println("• Enemies roll: 4, 6, 8, 20 sides on floors 1–4.");
        System.out.println("• If you lose OR tie on any floor, you restart from Floor 1.\n");

        int yourDiceSides = 12;
        int[] enemyDiceSides = {4, 6, 8, 20};

        // Keep attempting until all four floors are cleared in one run
        while (true) {
            boolean clearedAll = true;

            for (int floor = 0; floor < enemyDiceSides.length; floor++) {
                int enemySides = enemyDiceSides[floor];

                System.out.println("=== Floor " + (floor + 1) + " ===");
                System.out.print("Press ENTER to roll your 12-sided die...");
                scan.nextLine(); // wait for ENTER

                int yourRoll = rand.nextInt(yourDiceSides) + 1; // 1..12
                int enemyRoll = rand.nextInt(enemySides) + 1;   // 1..enemySides

                System.out.println("You rolled:   " + yourRoll);
                System.out.println("Enemy rolled: " + enemyRoll);

                if (yourRoll > enemyRoll) {
                    System.out.println("✅ You win this floor! Proceed to the next.\n");
                } else if (yourRoll == enemyRoll) {
                    System.out.println("🤝 It's a tie. Tie counts as a loss. Back to Floor 1!\n");
                    clearedAll = false;
                    break;
                } else {
                    System.out.println("❌ You lost this floor. Back to Floor 1!\n");
                    clearedAll = false;
                    break;
                }
            }

            if (clearedAll) {
                System.out.println("🏆 Congratulations, " + playerName + "! You cleared all 4 floors and rescued the target!");
                break; // done
            } else {
                System.out.println("🔁 Restarting from Floor 1. Be brave, " + playerName + "!\n");
            }
        }
    }
}

