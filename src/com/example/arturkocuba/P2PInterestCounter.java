package com.example.arturkocuba;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class P2PInterestCounter {

    private ArrayList<Double> deposits = new ArrayList<>();
    private ArrayList<LocalDate> dates = new ArrayList<>();
    private ArrayList<Double> timePassed = new ArrayList<>();
    private ArrayList<Double> timePlusMoney = new ArrayList<>();
    private ArrayList<Double> balance = new ArrayList<>();

    private double totalProfit;
    private Scanner sc;
    private String userName;
    private ResourceBundle language;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yy");


    public P2PInterestCounter(String userName, Scanner sc, ResourceBundle language) {
        this.sc = sc;
        this.userName = userName;
        this.language = language;
        System.out.println("*******************************************");
        System.out.println(language.getString("p2pintro") + " v2.1");
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
            System.out.println("[5] - Load Mintos");
            System.out.println("[6] - Load WiseFund");
            System.out.println(language.getString("pos0"));

            if (!sc.hasNextInt()) {
                sc.nextLine();
            } else {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    addEntryArray();
                    System.out.println();
                    break;
                case 2:
                    setProfit();
                    System.out.println();
                    break;
                case 3:
                    displayEntriesArray();
                    System.out.println();
                    break;
                case 4:
                    calculateInterestRateArray();
                    System.out.println();
                    break;
                case 5:
                    loadMintos();
                    System.out.println();
                    break;
                case 6:
                    loadWiseFund();
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

    public void loadMintos() {
        deposits.clear();
        dates.clear();
        deposits.add(100.0);
        dates.add(LocalDate.parse("07 10 19", dateTimeFormatter));
        deposits.add(-50.0);
        dates.add(LocalDate.parse("18 10 19", dateTimeFormatter));
        System.out.println("Mintos dates loaded.");

        pressEnter();
    }

    public void loadWiseFund() {
        deposits.clear();
        dates.clear();
        deposits.add(50.0);
        dates.add(LocalDate.parse("10 10 19", dateTimeFormatter));
        deposits.add(50.0);
        dates.add(LocalDate.parse("22 10 19", dateTimeFormatter));
        System.out.println("WiseFund dates loaded.");

        pressEnter();
    }

    public void addEntryArray() {

        sc.nextLine();

        int entryNumber;
        while (true) {
            System.out.println("Which entry do you want to add?");
            if (sc.hasNextInt()) {
                entryNumber = Integer.parseInt(sc.nextLine());
                break;
            } else {
                System.out.println(language.getString("unrecognized"));
            }
            sc.nextLine();
        }

        sc.nextLine();
        System.out.println("Enter date: (dd MM yy format)");
        dates.add(entryNumber, LocalDate.parse(sc.nextLine(), dateTimeFormatter));
        while (true) {
            System.out.println("Enter deposit:");
            if (sc.hasNextDouble()) {
                deposits.add(Double.parseDouble(sc.next()));
                break;
            } else {
                System.out.println(language.getString("unrecognized"));
            }
        }

        System.out.println("Date:\t" + dates.get(entryNumber) + "; Deposit:\t" + deposits.get(entryNumber));

        pressEnter();
    }

    public void calculateTimePassedArray() {

        for (int i = 0; i < dates.size() - 1; i++) {
            timePassed.add(calculateTimeArray(dates.get(i), dates.get(i + 1)));
            System.out.println("Time passed " + i + " = " + timePassed.get(i));
        }
        for (int i = dates.size() - 1; i < dates.size(); i++) {
            timePassed.add(calculateTimeArray(dates.get(i)));
            System.out.println("Time passed " + i + " = " + timePassed.get(i));
        }

    }

    public void calculateBalanceArray() {
        balance.clear();
        balance.add(0, deposits.get(0));
        System.out.println("Balance 0 = " + balance.get(0));
        for (int i = 1; i < deposits.size(); i++) {
            balance.add(i,balance.get(i-1)+ deposits.get(i));
            System.out.println("Balance " + i + " = " + balance.get(i));
        }
    }

    public void calculateInterestRateArray() {

        calculateTimePassedArray();
        calculateBalanceArray();

        timePlusMoney.clear();

        for (int i = 0; i < deposits.size(); i++) {
            timePlusMoney.add(i,balance.get(i) * timePassed.get(i));
            System.out.println("Time plus money " + i + " = " + timePlusMoney.get(i));
        }
        double entrySum = 0;
        for (double v : timePlusMoney) {
            entrySum += v;
            System.out.println("Entrysum = " + entrySum);
        }

        double interestRate = (totalProfit * 100d) / entrySum;
        System.out.println(language.getString("interestrate") + ": " + String.format("%.2f", interestRate) + "%");
        pressEnter();
    }

    private static double calculateTimeArray(LocalDate date1, LocalDate date2) {
        System.out.println("Days passed: " + ChronoUnit.DAYS.between(date1, date2));
        return (ChronoUnit.DAYS.between(date1, date2)) / 365d;
    }

    private static double calculateTimeArray(LocalDate date1) {
        System.out.println("Days passed till now: " + ChronoUnit.DAYS.between(date1, LocalDate.now()));
        return (ChronoUnit.DAYS.between(date1, LocalDate.now()) / 365d);
    }

    public void displayEntriesArray() {

        if (deposits == null) {
            System.out.println("No data to show");
        } else {
            for (int i = 0; i < deposits.size(); i++) {
                System.out.println("Date " + i + ":\t" + dates.get(i) + "; Deposit " + i + ":\t" + deposits.get(i));
            }
        }
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

    private void pressEnter() {
        System.out.println(language.getString("pressenter"));
        sc.nextLine();
        sc.nextLine();
    }

}
