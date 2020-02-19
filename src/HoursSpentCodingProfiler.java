import java.io.*;
import java.util.Scanner;

public class HoursSpentCodingProfiler {

    private double totalHours;
    private String userName;
    private File file;


    public double loadHours(Scanner sc) {
        try (Scanner inFile = new Scanner(new FileReader(file))) {

            totalHours = Double.parseDouble(inFile.nextLine());

            System.out.println("Total amount of hours spent coding: " + totalHours);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return totalHours;
    }

    public void addHours(Scanner sc) {
        try (BufferedWriter bwr = new BufferedWriter(new FileWriter(file))) {
            System.out.println("Enter the amount of hours:");
            double addHour = Double.parseDouble(sc.next());
            sc.nextLine();
            totalHours += addHour;

            bwr.write(Double.toString(totalHours));
            bwr.newLine();
            System.out.println("Added: " + addHour);
            System.out.println("Total hours: " + totalHours);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public HoursSpentCodingProfiler(String userName, Scanner sc) {
        this.totalHours = 0.0;
        this.userName = userName;
        this.file = new File("./" + userName + ".txt");
        System.out.println("Constructor went through");
    }

    public double getTotalHours() {
        return totalHours;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
