package com.example.arturkocuba;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GuessTheNumber {

    private String userName;
    private ResourceBundle language;
    private Scanner sc;

    public GuessTheNumber(String userName, Scanner sc, ResourceBundle language) {
        this.userName = userName;
        this.language = language;
        this.sc = sc;
        System.out.println("*******************************************");
        System.out.println(language.getString("guessintro") + " v1.0");
        System.out.println();
        System.out.println(language.getString("login") + " " + userName);
    }

    public void game(){

        System.out.println(language.getString("guessrules"));
        pressEnter();

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
                if(!sc.hasNextInt()){
                    System.out.println(language.getString("invalidnumber"));
                } else {
                    usersNumber = sc.nextInt();
                    attempts++;
                    if (usersNumber > randomNumber) {
                        System.out.println(language.getString("lower"));
                    }
                    if (usersNumber < randomNumber) {
                        System.out.println(language.getString("higher"));
                    }
                }
                sc.nextLine();
            }
            System.out.println(language.getString("correct") + " " + randomNumber + ".");
            System.out.println(language.getString("attempts1") + " " + attempts + " " + language.getString("attempts2"));
            System.out.println(language.getString("playagain1"));
            System.out.println(language.getString("playagain2"));
            choice = sc.nextInt();
        }
    }

    private void pressEnter() {
        System.out.println(language.getString("pressenter"));
        sc.nextLine();
        sc.nextLine();
    }

}