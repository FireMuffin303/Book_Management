package com.company;

import com.company.user.AbstractUser;
import com.company.user.BookStoreUser;
import com.company.user.Guest;
import com.company.user.LibraryUser;
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
            while (access.isLogin() == false){
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
            }
            System.out.print("Enter command: ");
            command = scanner.next();
            switch (command.toLowerCase()){
                case "account":
                    System.out.printf("Username : %s\n",access.getAccount().getUsername());
                    System.out.printf("AccountType : %s\n",access.getAccount().getType());
                    break;
                case "addbook":
                    switch (access.getAccount().getType()){
                        case "guest":
                            bookStorage.addBook((Guest) access.getAccount());
                            break;
                        case "library_user":
                            bookStorage.addBook((LibraryUser) access.getAccount());
                            break;
                        case "book_store_user":
                            bookStorage.addBook((BookStoreUser) access.getAccount());
                            break;
                    }
                    break;
                case "showbooks":
                    switch (access.getAccount().getType()){
                        case "guest":
                            bookStorage.showBooks((Guest) access.getAccount());
                            break;
                        case "library_user":
                            bookStorage.showBooks((LibraryUser) access.getAccount());
                            break;
                        case "book_store_user":
                            bookStorage.showBooks((BookStoreUser) access.getAccount());
                            break;
                    }
                    break;
                case "logout":
                    access.setLogin(false);
                    break;

            }
        }while (!command.equals("exit"));
        System.exit(0);
    }
}
