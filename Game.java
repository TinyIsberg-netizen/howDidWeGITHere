import java.util.Scanner;

public class Game {
    Scanner scan = new Scanner(System.in);
    Player player;

    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    public void start() {
        CharacterCreator creator = new CharacterCreator();
        String Player = creator.createCharacter();

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

            if (floor == 4) {
                if (!playLevelFour()) {
                    System.out.println("You were defeated! Game over");
                    return;
                }
            } else {
                Enemy currentEnemy = enemies[floor - 1];
                System.out.println("A wild " + currentEnemy.name + " appears!");
                boolean wonFight = fight(player, currentEnemy);

                if (!wonFight) {
                    System.out.println("You were defeated! Game Over!");
                    return;
                }
            }
            System.out.println("Floor " + floor + " cleared!\n");

        }

        System.out.println("Congratulations, " + player.getName() + "! You conqured the Tower of Tom");

    }

    private boolean fight(Player player2, Enemy currentEnemy) {
        while(player.isAlive() && enemy.isAlive()){
            System.out.println(player.getStatus + " | " + enemy.getStatus());
            System.out.println("(A)ttack or (H)eal");
            String action = scan.nextLine().toLowerCase();

            if(action.equals("a")){
                player.attack(enemy);
                
            }else if (action.equals("h")){
                player.heal();
            }

            if (enemy.isAlive()){
                int enemyDamage = enemy.rollDice();
                player.takeDamage(enemyDamage);

            }
            if (player.isAlive()){
                player.gainXp(20);
                return true;
            } else {
                return false;
            }
    }

    private boolean playWarlockRiddle(int level) {
        System.out.println("You encounter a mysterius warlock");
        System.out.println("Do you wish to (1) fight or (2) answer my riddle");
        int choice = scan.nextInt();

        if (choice == 1) {
            System.out.println("You have choosen poorly, get ready for a fight!");
            return fight(player, warlock);
        } else {
            System.out.println("\nWilbur cackles: 'Heh Heh... Let's see if you can answer this!\n");
            Riddle riddle = new Riddle("What goes up but never comes down?", "age");
            System.out.println("Here is my riddle: " + riddle.getQuestion());
            System.out.println("Think long and hard, you only get one try!");
            String answer = scan.nextLine().toLowerCase();
        }
        if (riddle.checkAnswer(answer)) {
            System.out.println("Hrmph! You are correct. You may pass on to the final level.");
            return true;
        } else {
            System.out.println(
                    "Hahahaha 'Wrong! OOOohh finally, i've been itching for a fight for many many years\nGet ready!");
            return fight(warlock);
        }

    }

}}

/*
 * Enemy rat = new Enemy("Ravenous Rat", 6, 10);
 * Enemy goblin = new Enemy("Shaman Goblin", 8, 20);
 * Enemy skeleton = new Enemy("Ancient Rambling Skeleton", 10, 30);
 * Enemy warlock = new Enemy("Wilbur the Warlock", 12, 40);
 * Enemy ogre = new Enemy("Demon Ogre", 20, 55);
 */