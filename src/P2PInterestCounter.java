import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
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
    private Scanner sc;
    private String userName;
    private ResourceBundle language;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yy");


    public P2PInterestCounter(String userName, Scanner sc, ResourceBundle language) {
        this.sc = sc;
        this.userName = userName;
        this.language = language;
        System.out.println("*******************************************");
        System.out.println(language.getString("p2pintro")+ " v2.0");
        System.out.println();
        System.out.println(language.getString("login") + " " + userName);
    }

    public void menu() {

        int choice = -1;
        while (choice != 0) {
            System.out.println(language.getString("menu"));
            System.out.println(language.getString("adddata"));
            System.out.println(language.getString("addprofit"));
            System.out.println(language.getString("showdata"));
            System.out.println(language.getString("calculateinterest"));
            System.out.println(language.getString("pos0"));

            if (!sc.hasNextInt()) {
                sc.nextLine();
            } else {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    addEntry();
                    System.out.println();
                    break;
                case 2:
                    setProfit();
                    System.out.println();
                    break;
                case 3:
                    displayEntries();
                    System.out.println();
                    break;
                case 4:
                    calculateInterestRate();
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

    public void addEntry() {

        int entryNumber;
        double deposit;
        sc.nextLine();

        while (true) {
            System.out.println(language.getString("whichentry"));
            if (sc.hasNextInt()) {
                entryNumber = Integer.parseInt(sc.nextLine());
                break;
            } else {
                System.out.println(language.getString("unrecognized"));
            }
            sc.nextLine();
        }

        System.out.println(language.getString("enterdate")+ " (dd MM yy format)");
        String dateString = sc.nextLine();

        while (true) {
            System.out.println(language.getString("enterdepo"));
            if (sc.hasNextDouble()) {
                deposit = Double.parseDouble(sc.next());
                break;
            } else {
                System.out.println(language.getString("unrecognized"));
            }
            sc.nextLine();
        }

        setDate(dateString, entryNumber);
        setDeposit(deposit, entryNumber);
        System.out.println(language.getString("entryno")+ ": " + entryNumber + ";\t" + language.getString("dateno") + ": " + dateString + ";\t" + language.getString("depono") + ": " + deposit);
        pressEnter();
    }

    public void displayEntries() {
        System.out.println(language.getString("dateno") + " 1 : " + date1 + ";\t" + language.getString("depono") + " 1: " + deposit1);
        System.out.println(language.getString("dateno") + " 2 : " + date2 + ";\t" + language.getString("depono") + " 2: " + deposit2);
        System.out.println(language.getString("dateno") + " 3 : " + date3 + ";\t" + language.getString("depono") + " 3: " + deposit3);
        System.out.println(language.getString("dateno") + " 4 : " + date4 + ";\t" + language.getString("depono") + " 4: " + deposit4);
        System.out.println(language.getString("totalprofit") + ": " + totalProfit);
        System.out.println(language.getString("interestrate") + ": " + interestRate);
        pressEnter();
    }

    public void setProfit() {

        sc.nextLine();

        while (true) {
            System.out.println(language.getString("setprofit"));
            if (sc.hasNextDouble()) {
                totalProfit = Double.parseDouble(sc.nextLine());
                break;
            } else {
                System.out.println(language.getString("unrecognized"));
            }
            sc.nextLine();
        }

        System.out.println(language.getString("setprofit2") + ": " + totalProfit);
        pressEnter();
    }

    public void calculateInterestRate() {

        double time1 = calculateTime(date1, date2);
        double time2 = calculateTime(date2, date3);
        double time3 = calculateTime(date3, date4);
//        double time4 = calculateTime(date4);

        double entry1 = deposit1 * time1;
        double entry2 = (deposit1 + deposit2) * time2;
        double entry3 = (deposit2 + deposit3) * time3;
//        double entry4 = (deposit3 + deposit4) * time4;

        interestRate = (totalProfit * 100d) / (entry1 + entry2 + entry3);
        System.out.println(language.getString("interestrate") + ": " + String.format("%.2f", interestRate) + "%");
        pressEnter();
    }

    private static double calculateTime(LocalDate date1, LocalDate date2) {
        double timePassed;

        if (date1 == null) {
            timePassed = 0;
        } else if (date2 == null) {
            timePassed = calculateTime(date1);
        } else {
            timePassed = (ChronoUnit.DAYS.between(date1, date2)) / 365d;
        }
        return timePassed;
    }

    private static double calculateTime(LocalDate date1) {
        return (ChronoUnit.DAYS.between(date1, LocalDate.now()) / 365d);
    }

    private void setDeposit(double deposit, int entryNumber) {
        if (entryNumber == 1) {
            deposit1 = deposit;
        } else if (entryNumber == 2) {
            deposit2 = deposit;
        } else if (entryNumber == 3) {
            deposit3 = deposit;
        } else if (entryNumber == 4) {
            deposit4 = deposit;
        } else {
            System.out.println(language.getString("invalidentryno"));
        }
    }

    private void setDate(String dateString, int entryNumber) {
        LocalDate date = LocalDate.parse(dateString, dateTimeFormatter);
        if (entryNumber == 1) {
            date1 = date;
        } else if (entryNumber == 2) {
            date2 = date;
        } else if (entryNumber == 3) {
            date3 = date;
        } else if (entryNumber == 4) {
            date4 = date;
        } else {
            System.out.println(language.getString("invalidentryno"));
        }
    }

    private void pressEnter() {
        System.out.println(language.getString("pressenter"));
        sc.nextLine();
        sc.nextLine();
    }

}
