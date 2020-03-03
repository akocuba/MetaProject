package com.example.arturkocuba;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class OLDP2PInterestCounter {

    public static void menu(Scanner sc) {

        System.out.println("P2P Lending Interest Counter v1.0");
        System.out.println("What would you like to do:");

        int choice = -1;
        while (choice != 0) {
            System.out.println("Options available:");
            System.out.println("[1] - Calculate interest for 2 entries");
            System.out.println("[0] - Return to the Hub");

            if (!sc.hasNextInt()) {
                sc.nextLine();
            } else {
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1:
                    calculateInterest(sc);
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

    public static void calculateInterest(Scanner sc) {
        System.out.println("Enter first deposit amount:");
        double deposit1 = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter first deposit date: (dd MM yyyy)");
        String date1 = sc.nextLine();
        System.out.println("Deposited: " + deposit1 + "\ton date: " + date1);

        System.out.println("Enter second deposit amount:");
        double deposit2 = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter second deposit date: (dd MM yyyy)");
        String date2 = sc.nextLine();
        System.out.println("Deposited: " + deposit1 + "\ton date: " + date1);

        System.out.println("Enter total profit earned:");
        double totalInterest = Double.parseDouble(sc.nextLine());

        double time1 = calculateTime(date1, date2);
        double time2 = calculateTime(date2);

        deposit2 = deposit1 + deposit2;

        double jointInterestRate = (totalInterest * 100d) / ((deposit1 * time1) + (deposit2 * time2));
        System.out.println("Interest rate = " + String.format("%.2f", jointInterestRate) + "%");


        System.out.println("[Press Enter to return to Menu]");
        sc.nextLine();
        sc.nextLine();
    }

    public static double calculateTime(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate firstDate = LocalDate.parse(date1, formatter);
        LocalDate secondDate = LocalDate.parse(date2, formatter);
        long daysPassed = ChronoUnit.DAYS.between(firstDate, secondDate);
        return daysPassed / 365d;
    }

    public static double calculateTime(String date1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate firstDate = LocalDate.parse(date1, formatter);
        long daysPassed = ChronoUnit.DAYS.between(firstDate, LocalDate.now());
        return daysPassed / 365d;
    }

}
