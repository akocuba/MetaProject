import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class HoursSpentCodingCounter {

    private static File file = new File("./hours.txt");

    public static void menu(Scanner sc) {
        System.out.println("Hours Spent Coding Counter v1.0");
        System.out.println("What would you like to do:");
        int choice = -1;
        while (choice != 0) {
            System.out.println("Options available:");
            System.out.println("[1] - Add new entry");
            System.out.println("[2] - Display all entries");
            System.out.println("[3] - Calculate total number of hours spent coding");
            System.out.println("[4] - Delete all data");
            System.out.println("[0] - Return to the Hub");
            if (!sc.hasNextInt()) {
                sc.nextLine();
            } else {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    addHours(sc);
                    System.out.println();
                    break;
                case 2:
                    displayAllEntries(sc);
                    System.out.println();
                    break;
                case 3:
                    displaySum(sc);
                    System.out.println();
                    break;
                case 4:
                    deleteData(sc);
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Closing the Program.");
                    System.out.println();
                    break;
                default:
                    System.out.println("Unrecognized command.");
                    System.out.println();
                    break;
            }
        }
    }

    public static void addHours(Scanner sc) {
        try (BufferedWriter bwr = new BufferedWriter(new FileWriter(file, true))) {
            System.out.println("Enter the amount of hours:");
            int addHour = parseInt(sc.next());
            sc.nextLine();
            System.out.println("Enter the date (dd mm yy)");
            String dataFormat = sc.nextLine();
            //String dataFormat = (LocalDate.now()).format(DateTimeFormatter.ofPattern("yy MM dd"));

            bwr.write(addHour + "\t" + dataFormat);
            bwr.newLine();
            System.out.println("Added: +" + addHour + " h. \t" + dataFormat);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        System.out.println("[Press Enter to return to Menu]");
        sc.nextLine();
        sc.nextLine();
    }

    public static void displayAllEntries(Scanner sc) {
        System.out.println();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        System.out.println("[Press Enter to return to Menu]");
        sc.nextLine();
        sc.nextLine();
    }

    public static void displaySum(Scanner sc) {
        System.out.println();
        try (Scanner inFile = new Scanner(new FileReader(file))) {
            int val;
            int sum = 0;
            while (inFile.hasNextLine()) {
                val = inFile.nextInt();
                inFile.nextLine();
                sum += val;
            }
            System.out.println("Total amount of hours spent coding: " + sum);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        System.out.println("[Press Enter to return to Menu]");
        sc.nextLine();
        sc.nextLine();
    }

    public static void deleteData(Scanner sc) {
        System.out.println("This function is going to delete all the entries.");
        System.out.println("Do you want to continue?");
        System.out.println("[1] - Continue; [0] - Cancel");
        sc.nextLine();
        int wybranyNr = parseInt(sc.nextLine());
        if (wybranyNr == 1) {
            try (BufferedWriter bwr = new BufferedWriter(new FileWriter(file))) {
                bwr.flush();
                System.out.println("All entries have been removed.");
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("Operation has been cancelled.");
        }
        System.out.println("[Press Enter to return to Menu]");
        sc.nextLine();
        sc.nextLine();
    }
}
