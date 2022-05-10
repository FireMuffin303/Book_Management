package com.company;

import com.company.user.AbstractUser;
import com.company.util.Access;
import com.company.util.BookStorage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String command;
        Access access = new Access();
        BookStorage bookStorage = new BookStorage();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Login or Register");
            System.out.print("Enter: ");
            command = scanner.next();
            switch (command.toLowerCase()) {
                case "login":
                    access.login();
                    break;
                case "register":
                    access.register();
                    break;
            }
        }while (command.equalsIgnoreCase("register"));


        do {
            System.out.print("Enter command: ");
            command = scanner.next();
            switch (command.toLowerCase()){
                case "account":
                    System.out.printf("Username : %s\n",access.getAccount().getUsername());
                    System.out.printf("AccountType : %s\n",access.getAccount().getType());
                    break;
                case "addbook":
                    bookStorage.addBook(access.getAccount());

                    break;

            }
        }while (!command.equals("exit"));
        System.exit(0);
    }
}
