import java.io.*;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HoursSpentCoding {

    private double totalHours;
    private String userName;
    private File file;
    private ResourceBundle language;
    private Scanner sc;


    public HoursSpentCoding(String userName, Scanner sc, ResourceBundle language) {
        this.totalHours = 0.0;
        this.userName = userName;
        this.language = language;
        this.sc = sc;
        this.file = new File("./" + userName + ".txt");
        System.out.println("*******************************************");
        System.out.println(language.getString("hoursintro") + " v2.0");
        System.out.println();
        System.out.println(language.getString("login") + " " + userName);
    }

    public void menu() {

        int choice = -1;
        while (choice != 0) {
            System.out.println(language.getString("menu"));
            System.out.println(language.getString("addnew"));
            System.out.println(language.getString("showprofile"));
            System.out.println(language.getString("pos0"));

            if (!sc.hasNextInt()) {
                sc.nextLine();
            } else {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    addHours();
                    System.out.println();
                    break;
                case 2:
                    showInfo();
                    System.out.println();
                    break;
                case 0:
                    System.out.println(language.getString("exitprogram"));
                    System.out.println();
                    break;
                default:
                    System.out.println(language.getString("unrecognized"));
                    System.out.println();
                    break;
            }
        }
    }

    public void showInfo() {

        loadHours();
        System.out.println(language.getString("profile") + " " + userName + "\t" + language.getString("totalhours") + " " + totalHours);
        pressEnter();
    }

    public void addHours() {

        loadHours();
        double addHour;

        try (BufferedWriter bwr = new BufferedWriter(new FileWriter(file))) {
            while (true) {
                System.out.println(language.getString("howmanyhours"));
                if (sc.hasNextDouble()) {
                    addHour = Double.parseDouble(sc.next());
                    break;
                } else {
                    System.out.println(language.getString("unrecognized"));
                }
                sc.nextLine();
            }

            sc.nextLine();
            totalHours += addHour;
            bwr.write(Double.toString(totalHours));
            bwr.newLine();
            System.out.println(language.getString("added") + " " + addHour + " " + language.getString("hour"));
            System.out.println(language.getString("totalhours") + " " + totalHours + " " + language.getString("hour"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        pressEnter();
    }

    private void loadHours() {
        try (Scanner loader = new Scanner(new FileReader(file))) {
            totalHours = Double.parseDouble(loader.nextLine());
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (NoSuchElementException e) {
            System.out.println(language.getString("nodata"));
        }
    }

    private void pressEnter() {
        System.out.println(language.getString("pressenter"));
        sc.nextLine();
        sc.nextLine();
    }

}
