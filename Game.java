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

        System.out.println("\u001B[1;34m=== Entering The Tower of Tom ===\u001B[0m");

        Enemy[] enemies = {
                new Enemy("Ravenous Rat", 6, 10),
                new Enemy("Shaman Goblin", 8, 20),
                new Enemy("Ancient Rambling Skeleton", 10, 30),
                new Enemy("Wilbur the Warlock", 12, 40),
                new Enemy("Demon Ogre", 20, 55)
        };

        for (int floor = 1; floor <= 5; floor++) {
            System.out.println("\u001B[34m=== Floor " + floor + " ===\u001B[0m");

            boolean survived;

            if (floor == 4) {
                survived = playWarlockRiddle(enemies[floor - 1]);

            } else {
                Enemy currentEnemy = enemies[floor - 1];
                System.out.println("\u001B[1;35mA wild " + currentEnemy.name + " appears!\u001B[0m");
                survived = fight(player, currentEnemy);
            }
            if (!survived) {
                System.out.println("\u001B[1;31mYou were defeated! Game Over!\u001B[0m");
                return;
            }

                System.out.println("\u001B[33mFloor " + floor + " cleared!\n\u001B[0m");

            if (!postLevelMenu()) {
                System.out.println("\u001B[32mYou decided to leave the tower. You are a coward and not worthy of conquering Tom!\u001B[0m");
                return;
            }
        }

        System.out.println("\u001B[1;32mCongratulations, " + player.getName() + "! You conqured the Tower of Tom\u001B[0m");

    }

    private boolean fight(Player player, Enemy currentEnemy) { // fight current enemy on floor
        while (player.isAlive() && currentEnemy.isAlive()) {
            System.out.println(player.getStatus() + " | " + currentEnemy.getStatus());
            System.out.println("(\u001B[1;33mA\u001B[0m)ttack or (\u001B[1;33mH\u001B[0m)eal");
            String action = scan.nextLine().toLowerCase();

            if (action.equals("a")) {
                player.attack(currentEnemy);

            } else if (action.equals("h")) {
                player.heal();
                System.out.println("\u001B[1;32mYou healed!\u001B[0m");
                int enemyDamage = currentEnemy.rollDice();
                player.takeDamage(enemyDamage);
                continue;

            } else {
                System.out.println("\u001B[1;31mInvalid choice. Try again\u001B[0m");
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
        System.out.println("\u001B[1;35mYou encounter a mysterius warlock\u001B[0m");
        System.out.println("Do you wish to (1) fight or (2) answer my riddle");

        int choice;
        try { // invalid input
            choice = Integer.parseInt(scan.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("\u001B[1;31minvalid input. Defaulting to riddle.\u001B[0m");
            choice = 2;
        }
        if (choice == 1) { // fight
            System.out.println("You choose to fight me eh? Finally! I hope you put up a good fight!");
            return fight(player, warlock);

        } else { // riddle
            Riddle riddle = new Riddle("\u001B[1;35mWhat goes up but never comes down?", "age");
            System.out.println("\u001B[35mHere is my riddle: " + riddle.getRiddle() + "\u001B[0m");
            System.out.println("Your answer: ");
            String answer = scan.nextLine().trim().toLowerCase();

            if (riddle.checkAnswer(answer)) {
                System.out.println("\u001B[1;32mHrmph! You are correct. You may pass!\u001B[0m");
                player.gainXp(20);// reward for solving the riddle
                return true;
            } else {
                System.out.println(
                        "\\u001B[1;31mHahahaha 'Wrong! OOOohh finally, i've been itching for a fight for many many years\nGet ready!\u001B[0m");
                return fight(player, warlock);
            }

        }

    }

    private boolean postLevelMenu() { // Post level menu
        while (true) {
            System.out.println("\u001B[1;36m--- After Battle Menu ---\u001B[0m");
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
                    System.out.println("\u001B[33m***Character Sheet***\u001B[0m");
                    System.out.println(player); // print player info
                }
                case 3 -> {
                    return false; // give up and leave tower

                }
                default -> {
                    System.out.println("\u001B[1;31mInvalid choice, try again.\u001B[0m");
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
