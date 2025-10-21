import java.util.Scanner;

public class Welcome {
    
    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);    
    System.out.println("Welcome brave hero!\nPlease enter your characters name: ");


    String characterName = scan.nextLine();
        System.out.println("Welcome " + characterName + "\nWhat's your race?" + "\n1.Human \n2.Elve \n3.Orc - Strong" );
        
    }
    

    
}
