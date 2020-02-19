import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("*******************************************");
        System.out.println("Wersja testowa");
        System.out.println("[1] - Polski; [2] - English");
        int choice = sc.nextInt();
        sc.nextLine();

        ResourceBundle language;
        if (choice == 1) {
            language = ResourceBundle.getBundle("text", Locale.forLanguageTag("pl-PL"));
        } else {
            language = ResourceBundle.getBundle("text", Locale.UK);
        }

        System.out.println(language.getString("welcome"));

        menu(sc, language);

        sc.close();
    }

    public static void menu(Scanner sc, ResourceBundle language) {

        int choice = -1;
        while (choice != 0) {
            System.out.println(language.getString("menu"));
            System.out.println("[1] - Hours Spent Coding");
            System.out.println("[2] - P2P Interest Counter");
            System.out.println("[3] - Guess the Number Game");
            System.out.println(language.getString("pos0"));
            if (!sc.hasNextInt()) {
                sc.nextLine();
            } else {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter user name:");
                    sc.nextLine();
                    String userName = sc.nextLine();
                    HoursSpentCoding profile1 = new HoursSpentCoding(userName, sc);
                    profile1.loadHours(sc);
                    profile1.addHours(sc);
                    break;
                case 2:
                    P2PInterestCounter counter = new P2PInterestCounter();
                    counter.menu(sc);
                    break;
                case 3:
                    GuessTheNumber.game(sc, language);
                    break;
                case 0:
                    System.out.println(language.getString("exitmsg1"));
                    System.out.println(language.getString("exitmsg2"));
                    break;
                default:
                    System.out.println(language.getString("unreckognized"));
                    break;
            }
        }
    }

}
