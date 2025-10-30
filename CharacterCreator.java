import java.util.Scanner;

public class CharacterCreator {

    public Player createCharacter() {

        Scanner scan = new Scanner(System.in);

        slowPrint(" ...... You feel someone shaking you carefully....", 2000);
        slowPrint("...you open your eyes and see a strange figure looking down at you...", 2000);
        slowPrint("\u001B[1;32m Who are you?...", 1500);
        slowPrint("...What are you doing here?..", 2000);
        slowPrint("Why do you look like you do not know who you are?\u001B[0m ", 2000);
        slowPrint("So many questions, .... i do not really know who I am, maybe you can help me?.. ", 2000);
        slowPrint("I just know that I am on a quest to rescue a princess from the Tower of Tom..", 2500);
        slowPrint("\u001B[1;32m .... Well then you have come to the right place my dear.. This is the Tower of Tom..",
                2000);
        slowPrint("But let us first figure out who you are..\u001B[0m ", 2000);

        slowPrint("\u001B[1;32m Can you tell me your name: \u001B[0m", 1700);

        String characterName = scan.nextLine();
        while (!characterName.matches("[a-zA-ZåäöÅÄÖ ]+")
                || characterName.replaceAll("[^a-zA-ZåäöÅÄÖ]", "").length() < 2) {
            System.out.println("\u001B[31mHmmm that's not a valid name. Try again!\u001B[0m");
            characterName = scan.nextLine();
        }
        String playRace = "";
        boolean validRace = false;
        while (!validRace) {

            slowPrint("\u001B[1;32mNice to meet you \u001B[0m" + characterName + "\u001B[1;32m.\u001B[0m", 1500);
            slowPrint("\u001B[1;32mCan you please tell me what heritage you stem from?\u001B[0m", 2000);
            slowPrint("Human - Average \nElve - Fast \nOrc - Strong", 1500);
            String race = scan.nextLine();

            if (race.equalsIgnoreCase("orc")) {
                slowPrint("\u001B[1;32mOrc you say!\nWell that seems right, you do look like an Orc!"
                        + " Haven't seen alot of your kind here!\nAnyway, good luck conquering the Tower.\u001B[0m",
                        1500);
                validRace = true;

            } else if (race.equalsIgnoreCase("human")) {
                slowPrint("\u001B[1;32mHuman you say!\nWell that seems right, you do look like a Human!"
                        + " Haven't seen alot of your kind here!\nAnyway, good luck conquering the Tower.\u001B[0m",
                        1500);
                validRace = true;

            } else if (race.equalsIgnoreCase("elve")) {
                slowPrint("\u001B[1;32mElve you say!\nWell that seems right, you do look like an Elve!"
                        + " Haven't seen alot of your kind here!\nAnyway, good luck conquering the Tower.\u001B[0m",
                        1500);
                validRace = true;

            } else {
                System.out.println("\u001B[31mThats not a race, you silly goose! Try Again.\u001B[0m\n ");
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

        // Create and return player
        return new Player(characterName, playRace);
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
