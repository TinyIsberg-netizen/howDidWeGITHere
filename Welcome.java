import java.util.Scanner;
import Recources.*;

public class Welcome {
    

    Orc myOrc = new Orc();
    Human myHuman = new Human();
    Elve myElve = new Elve();
    
    public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);    
    System.out.println("Welcome brave hero to The Tower of Tom!\nPlease enter your characters name: ");
        
    boolean validRace = false;
    String characterName = scan.nextLine();
    while(!characterName.matches("[a-zA-ZåäöÅÄÖ ]+")){
        System.out.println("Nice try Tom, try again :)");
        characterName = scan.nextLine();
        
    }
    while (!validRace) {
    
    System.out.println("Welcome " + characterName + "\nPlease enter one of the races below" + "\n1.Human - Average \n2.Elve - Fast \n3.Orc - Strong" );
        String race = scan.nextLine();
     
        if (race.equalsIgnoreCase("orc")){
            System.out.println("You chose Orc" + "\nWow! Orc, you dont say!" + " Haven't seen alot of your kind here!\nAnyway, good luck not getting squished.");
            validRace = true;
            
        }else if(race.equalsIgnoreCase("human")){
            System.out.println("You chose Human" + "\nWow! Human, you dont say!" + " Haven't seen alot of your kind here!\nAnyway, good luck not getting squished.");
            validRace = true;

        }else if(race.equalsIgnoreCase("elve")){
            System.out.println("You chose Elve" + "\nWow! Elve, you dont say!" + " Haven't seen alot of your kind here!\nAnyway, good luck not getting squished.");
            validRace = true; 

        }else{System.out.println("Thats not a race, you silly goose! Try Again.");
            
        }

        }

        
        
    }
    
}
    

