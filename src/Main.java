import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    private static ResourceBundle language;
    private static Scanner sc = new Scanner(System.in);
    private static String userName;

    public static void main(String[] args) {

        System.out.println("*******************************************");
        System.out.println("MetaProject Version 1.0 - 20.02.2020");

        chooseLanguage();

        enterName();

        menu();

        sc.close();
    }

    public static void menu() {

        int choice = -1;
        while (choice != 0) {
            System.out.println(language.getString("menu"));
            System.out.println(language.getString("hourcounter"));
            System.out.println(language.getString("interestcounter"));
            System.out.println(language.getString("guessthenumber"));
            System.out.println(language.getString("pos0"));
            if (!sc.hasNextInt()) {
                sc.nextLine();
            } else {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    HoursSpentCoding hoursSpentCoding = new HoursSpentCoding(userName, sc, language);
                    hoursSpentCoding.menu();
                    break;
                case 2:
                    P2PInterestCounter p2PInterestCounter = new P2PInterestCounter(userName, sc, language);
                    p2PInterestCounter.menu();
                    break;
                case 3:
                    GuessTheNumber guessTheNumber = new GuessTheNumber(userName, sc, language);
                    guessTheNumber.game();
                    break;
                case 0:
                    System.out.println(language.getString("exitmsg1") + " " + userName + ".");
                    System.out.println(language.getString("exitmsg2"));
                    break;
                default:
                    System.out.println(language.getString("unrecognized"));
                    break;
            }
        }
    }

    public static void chooseLanguage(){
        int choice = -1;
        while(true) {
            System.out.println("[1] - Polski; [2] - English");
            if(sc.hasNextInt()) {
                choice = sc.nextInt();
            }
            if (choice == 1) {
                language = ResourceBundle.getBundle("text", Locale.forLanguageTag("pl-PL"));
                break;
            } else if (choice == 2) {
                language = ResourceBundle.getBundle("text", Locale.UK);
                break;
            }
            sc.nextLine();
        }
        sc.nextLine();
    }

    public static void enterName(){
        System.out.println(language.getString("username"));
        userName = sc.nextLine();
        System.out.println(language.getString("welcome") + " " + userName + ".");

    }

}
