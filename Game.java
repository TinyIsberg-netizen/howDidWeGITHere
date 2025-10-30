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

        System.out.println("\n=== Entering The Tower of Tom ===\n");

        Enemy[] enemies = {
                new Enemy("Ravenous Rat", 6, 10),
                new Enemy("Shaman Goblin", 8, 20),
                new Enemy("Ancient Rambling Skeleton", 10, 30),
                new Enemy("Wilbur the Warlock", 12, 40),
                new Enemy("Demon Ogre", 20, 55)
        };

        for (int floor = 1; floor <= 5; floor++) {
            System.out.println("=== Floor " + floor + " ===");

            boolean survived;

            if (floor == 4) {
                survived = playWarlockRiddle(enemies[floor - 1]);

            } else {
                Enemy currentEnemy = enemies[floor - 1];
                System.out.println("A wild " + currentEnemy.name + " appears!");
                survived = fight(player, currentEnemy);
            }
            if (!survived) {
                System.out.println("You were defeated! Game Over!");
                return;
            }

            System.out.println("Floor " + floor + " cleared!\n");

            if (!postLevelMenu()) {
                System.out.println("You decided to leave the tower. Thanks for playin!");
                return;
            }
        }

        System.out.println("Congratulations, " + player.getName() + "! You conqured the Tower of Tom");

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
        System.out.println("You encounter a mysterius warlock");
        System.out.println("Do you wish to (1) fight or (2) answer my riddle");

        int choice;
        try { // invalid input
            choice = Integer.parseInt(scan.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid input. Defaulting to riddle.");
            choice = 2;
        }
        if (choice == 1) { // fight
            System.out.println("You choose to fight me eh? Finally! I hope you put up a good fight!");
            return fight(player, warlock);

        } else { // riddle
            Riddle riddle = new Riddle("What goes up but never comes down?", "age");
            System.out.println("Here is my riddle: " + riddle.getRiddle());
            System.out.println("Your answer: ");
            String answer = scan.nextLine().trim().toLowerCase();

            if (riddle.checkAnswer(answer)) {
                System.out.println("Hrmph! You are correct. You may pass on to the final level.");
                player.gainXp(20);// reward for solving the riddle
                return true;
            } else {
                System.out.println(
                        "Hahahaha 'Wrong! OOOohh finally, i've been itching for a fight for many many years\nGet ready!");
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
}
