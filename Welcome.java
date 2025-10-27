import Recources.*;
import java.util.Scanner;

public class Welcome {

    Orc myOrc = new Orc();
    Human myHuman = new Human();
    Elve myElve = new Elve();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome brave hero to The Tower of Tom!\nPlease enter your characters name: ");

        boolean validRace = false;
        String characterName = scan.nextLine();
        while (!characterName.matches("[a-zA-ZåäöÅÄÖ ]+")
                || characterName.replaceAll("[^a-zA-ZåäöÅÄÖ]", "").length() < 2) {
            System.out.println("Hmmm that's not a valid name. Try again!");
            characterName = scan.nextLine();
        }

        while (!validRace) {

            System.out.println("Welcome " + characterName + "\nPlease enter one of the races below"
                    + "\nHuman - Average \nElve - Fast \nOrc - Strong");
            String race = scan.nextLine();

            if (race.equalsIgnoreCase("orc")) {
                System.out.println("You chose Orc" + "\nWow! Orc, you dont say!"
                        + " Haven't seen alot of your kind here!\nAnyway, good luck not getting squished.");
                validRace = true;

            } else if (race.equalsIgnoreCase("human")) {
                System.out.println("You chose Human" + "\nWow! Human, you dont say!"
                        + " Haven't seen alot of your kind here!\nAnyway, good luck not getting squished.");
                validRace = true;

            } else if (race.equalsIgnoreCase("elve")) {
                System.out.println("You chose Elve" + "\nWow! Elve, you dont say!"
                        + " Haven't seen alot of your kind here!\nAnyway, good luck not getting squished.");
                validRace = true;

            } else {
                System.out.println("Thats not a race, you silly goose! Try Again." + "\n ");
                try {
                    Thread.sleep(1250);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        Enemy rat = new Enemy("Ravenous Rat", 6, 10);
        Enemy goblin = new Enemy("Shaman Goblin", 8, 20);
        Enemy skeleton = new Enemy("Ancient Rambling Skeleton", 10, 30);
        Enemy warlock = new Enemy("Wilbur the Warlock", 12, 40);
        Enemy ogre = new Enemy("Demon Ogre", 20, 55);

    }

}
