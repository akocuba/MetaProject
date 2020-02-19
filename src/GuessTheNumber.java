import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GuessTheNumber {

    public static void game(Scanner sc, ResourceBundle language){
        Random random = new Random();

        int randomNumber = random.nextInt(101);
        System.out.println(language.getString("guess"));
        int usersNumber = -1;
        int attempts = 0;
        boolean isFirst = true;

        int choice = -1;
        while (choice != 0) {
            if(isFirst){
                isFirst = false;
            } else {
                usersNumber = -1;
                attempts = 0;
                randomNumber = random.nextInt(101);
                System.out.println(language.getString("guess"));
            }

            while (usersNumber != randomNumber) {
                usersNumber = sc.nextInt();
                attempts++;
                if (usersNumber > randomNumber) {
                    System.out.println(language.getString("lower"));
                }
                if (usersNumber < randomNumber) {
                    System.out.println(language.getString("higher"));
                }
            }
            System.out.println(language.getString("correct") + " " + randomNumber + ".");
            System.out.println(language.getString("attempts1") + " " + attempts + " " + language.getString("attempts2"));
            System.out.println(language.getString("playagain1"));
            System.out.println(language.getString("playagain2"));
            choice = sc.nextInt();
        }
    }
}