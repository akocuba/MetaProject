import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class P2PInterestCounter {

    private double deposit1;
    private double deposit2;
    private double deposit3;
    private double deposit4;
    private LocalDate date1;
    private LocalDate date2;
    private LocalDate date3;
    private LocalDate date4;
    private double totalProfit;
    private double interestRate;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yy");



    public P2PInterestCounter() {
    }

    public void menu(Scanner sc) {
        System.out.println("P2P Lending Interest Counter v2.0");
        System.out.println("What would you like to do:");

        int choice = -1;
        while (choice != 0) {
            System.out.println("Options available:");
            System.out.println("[1] - Add entry");
            System.out.println("[2] - Display entries");
            System.out.println("[3] - Add total profit");
            System.out.println("[4] - Calculate interest rate (up to three entries)");
            System.out.println("[0] - Return to the Hub");

            if (!sc.hasNextInt()) {
                sc.nextLine();
            } else {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    addEntry(sc);
                    System.out.println();
                    break;
                case 2:
                    displayEntries(sc);
                    System.out.println();
                    break;
                case 3:
                    setProfit(sc);
                    System.out.println();
                    break;
                case 4:
                    calculateInterestRate(sc);
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

    public void addEntry(Scanner sc) {
        System.out.println("Which entry would you like to add?");
        sc.nextLine();
        int entryNumber = Integer.parseInt(sc.nextLine());
        System.out.println("When was that entry done? (dd MM yy format)");
        String dateString = sc.nextLine();
        System.out.println("How much deposit did you do?");
        double deposit = Double.parseDouble(sc.nextLine());
        System.out.println("Entry no: " + entryNumber + ";\tDate: " + dateString + ";\t Deposit amount: " + deposit);
        setDate(dateString, entryNumber);
        setDeposit(deposit, entryNumber);
    }

    public void displayEntries(Scanner sc) {
        System.out.println("Date 1 : " + date1 + "; Deposit 1: " + deposit1);
        System.out.println("Date 2 : " + date2 + "; Deposit 2: " + deposit2);
        System.out.println("Date 3 : " + date3 + "; Deposit 3: " + deposit3);
        System.out.println("Date 4 : " + date4 + "; Deposit 4: " + deposit4);
        System.out.println("Total profit: " + totalProfit);
        System.out.println("Interest rate: " + interestRate);
    }

    public void setProfit(Scanner sc) {
        System.out.println("How much profit did you make until now?");
        sc.nextLine();
        this.totalProfit = Double.parseDouble(sc.nextLine());
        System.out.println("Total profit set to " + totalProfit);
    }

    public void calculateInterestRate(Scanner sc) {

        double time1 = calculateTime(date1, date2);
        double time2 = calculateTime(date2, date3);
        double time3 = calculateTime(date3, date4);
//        double time4 = calculateTime(date4);

        double entry1 = deposit1 * time1;
        double entry2 = (deposit1 + deposit2) * time2;
        double entry3 = (deposit2 + deposit3) * time3;
//        double entry4 = (deposit3 + deposit4) * time4;


        interestRate = (totalProfit * 100d) / (entry1 + entry2 + entry3);
        System.out.println("Interest rate = " + String.format("%.2f", interestRate) + "%");


        System.out.println("[Press Enter to return to Menu]");
        sc.nextLine();
        sc.nextLine();
    }

    private static double calculateTime(LocalDate date1, LocalDate date2) {
        double timePassed;

        if (date1 == null) {
            timePassed = 0;
        } else if (date2 == null){
            timePassed = calculateTime(date1);
        } else {
            timePassed = (ChronoUnit.DAYS.between(date1, date2)) / 365d;
        }
        return timePassed;
    }

    private static double calculateTime(LocalDate date1) {
        return (ChronoUnit.DAYS.between(date1, LocalDate.now()) / 365d);
    }

    private void setDeposit(double deposit, int depositNumber) {
        if (depositNumber == 1) {
            deposit1 = deposit;
        } else if (depositNumber == 2) {
            deposit2 = deposit;
        } else if (depositNumber == 3) {
            deposit3 = deposit;
        } else if (depositNumber == 4) {
            deposit4 = deposit;
        } else {
            System.out.println("Invalid deposit number.");
        }
        System.out.println("Successfully set deposit of " + deposit + " to slot " + depositNumber);
    }

    private void setDate(String dateString, int dateNumber) {
        LocalDate date = LocalDate.parse(dateString, dateTimeFormatter);
        if (dateNumber == 1) {
            date1 = date;
        } else if (dateNumber == 2) {
            date2 = date;
        } else if (dateNumber == 3) {
            date3 = date;
        } else if (dateNumber == 4) {
            date4 = date;
        } else {
            System.out.println("Invalid date number.");
        }
        System.out.println("Successfully set date of " + dateString + " to slot " + dateNumber);
    }

}
