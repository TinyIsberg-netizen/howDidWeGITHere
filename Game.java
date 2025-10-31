import java.util.Scanner;

public class Game {
    private Scanner scan = new Scanner(System.in);
    private Player player;

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        // returns player object
        CharacterCreator creator = new CharacterCreator();
        player = creator.createCharacter();

        slowPrint("\n=== Entering The Tower of Tom ===", 1500);
        slowPrint("The ancient tower looms before you, shrouded in mystery...", 2000);
        slowPrint("Steel yourself, brave hero. Your quest begins now.\n", 1500);

        Enemy[] enemies = {
                new Enemy("Ravenous Rat", 6, 10),
                new Enemy("Shaman Goblin", 8, 20),
                new Enemy("Ancient Rambling Skeleton", 10, 30),
                new Enemy("Wilbur the Warlock", 12, 40),
                new Enemy("Demon Ogre", 20, 55)
        };

        for (int floor = 1; floor <= 5; floor++) {
            System.out.println("\n=== Floor " + floor + " ===");

            boolean survived;

            if (floor == 4) {
                survived = playWarlockRiddle(enemies[floor - 1]);

            } else {
                Enemy currentEnemy = enemies[floor - 1];
                slowPrint("You hear something moving in the darkness...", 1500);
                slowPrint("A wild " + currentEnemy.name + " appears!", 2000);
                survived = fight(player, currentEnemy);
            }
            if (!survived) {
                slowPrint("You have been defeated...", 2000);
                slowPrint("Your vision fades to black...", 2000);
                slowPrint("GAME OVER", 1500);
                return;
            }

            slowPrint("Floor " + floor + " cleared! Well done, " + player.getName() + "!", 1500);

            if (!postLevelMenu()) {
                slowPrint("You turn your back on the tower...", 1500);
                slowPrint("You are not worthy of conquering Tom.", 2000);
                return;
            }
        }

        slowPrint("\n" + "=".repeat(50), 500);
        slowPrint("🏆 VICTORY! 🏆", 2000);
        slowPrint("Congratulations, " + player.getName() + "!", 1500);
        slowPrint("You have conquered the Tower of Tom!", 2000);
        slowPrint("The princess is saved, and your name will be legend!", 2000);
        slowPrint("=".repeat(50) + "\n", 500);

    }

    private boolean fight(Player player, Enemy currentEnemy) { // fight current enemy on floor
        while (player.isAlive() && currentEnemy.isAlive()) {
            System.out.println(player.getStatus() + " | " + currentEnemy.getStatus());
            System.out.println("(A)ttack or (H)eal");
            String action = scan.nextLine().toLowerCase();

            if (action.equals("a")) {
                player.attack(currentEnemy);

            } else if (action.equals("h")) {
                player.heal();
                System.out.println("You healed");
                int enemyDamage = currentEnemy.rollDice();
                player.takeDamage(enemyDamage);
                continue;

            } else {
                System.out.println("Invalid choice. Try again");
                continue;
            }

            if (currentEnemy.isAlive()) {
                int enemyDamage = currentEnemy.rollDice();
                player.takeDamage(enemyDamage);

            }

        }
        if (player.isAlive()) { // checks if player is alive
            player.gainXp(20);
            return true;
        } else {
            return false;
        }

    }

    private boolean playWarlockRiddle(Enemy warlock) { // warlock riddle method
        slowPrint("The air grows cold as you enter the chamber...", 1500);
        slowPrint("You encounter a mysterious warlock!", 2000);
        slowPrint("He grins wickedly and speaks:", 1500);
        System.out.println("\nDo you wish to (1) fight or (2) answer my riddle?");

        int choice;
        try { // invalid input
            choice = Integer.parseInt(scan.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid input. Defaulting to riddle.");
            choice = 2;
        }
        if (choice == 1) { // fight
            slowPrint("You choose to fight me eh? Finally! I hope you put up a good fight!", 1500);
            return fight(player, warlock);

        } else { // riddle
            Riddle riddle = new Riddle("What goes up but never comes down?", "age");
            slowPrint("Here is my riddle:", 1000);
            System.out.println(riddle.getRiddle());
            System.out.print("Your answer: ");
            String answer = scan.nextLine().trim().toLowerCase();

            if (riddle.checkAnswer(answer)) {
                slowPrint("Hrmph! You are correct...", 1500);
                slowPrint("The warlock grudgingly steps aside.", 1500);
                slowPrint("You may pass to the final level.", 2000);
                player.gainXp(20);// reward for solving the riddle
                return true;
            } else {
                slowPrint("Hahahaha! Wrong!", 1500);
                slowPrint("OOOohh finally, I've been itching for a fight for many years!", 1500);
                slowPrint("Get ready!", 1500);
                return fight(player, warlock);
            }

        }

    }

    private boolean postLevelMenu() { // Post level menu
        while (true) {
            System.out.println("\n--- After Battle Menu ---");
            System.out.println("1. Continue climbing");
            System.out.println("2. View Character Sheet");
            System.out.println("3. Give up and leave the tower");
            System.out.print("Your choice: ");

            int choice = scan.nextInt();
            scan.nextLine(); // scans next line

            switch (choice) {
                case 1 -> {
                    return true; // continue to next level
                }
                case 2 -> {
                    System.out.println(player); // print player info
                }
                case 3 -> {
                    return false; // give up and leave tower

                }
                default -> {
                    System.out.println("Invalid choice, try again.");
                }
            }
        }

    }

    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void slowPrint(String text, int delayMillis) {
        System.out.println(text);
        pause(delayMillis);
    }
}