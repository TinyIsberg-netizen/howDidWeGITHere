import java.util.Scanner;
import Recources.*;

public class Welcome {
    Orc myOrc = new Orc();
    Human myHuman = new Human();
    Elve myElve = new Elve();

    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);    
    System.out.println("Welcome brave hero to The Tower of Tom!\nPlease enter your characters name: ");


    String characterName = scan.nextLine();
     System.out.println("Welcome " + characterName + "\nWhat's your race?" + "\n1.Human - Average \n2.Elve - Fast \n3.Orc - Strong" );
    
     String race = scan.nextLine();
     System.out.println("Wow " + race + " you dont say!" + " Haven't seen alot of your kind here!\nAnyway, good luck not getting squished.");
        if (scan.nextLine() == race);
            System.out.println();
    }
    

    
}
