package com.company.util;

import com.company.user.AbstractUser;
import com.company.user.BookStoreUser;
import com.company.user.Guest;
import com.company.user.LibraryUser;

import java.util.Scanner;

public class Access{
    Guest guest;
    LibraryUser libraryUser;
    BookStoreUser bookStoreUser;
    JsonHelper jsonHelper = new JsonHelper();

    Scanner scanner = new Scanner(System.in);

    public void login(){
        System.out.print("Enter your Username : ");
        String user = scanner.next();
        System.out.print("Enter your Password : ");
        String ps = scanner.next();
        AbstractUser abstractUser = jsonHelper.readAccount(user);

        if(abstractUser.getUsername().equals(user)){
            if(abstractUser.getPassword().equals(ps)){
                switch (abstractUser.getType()){
                    case "guest":
                        guest = new Guest(abstractUser.getUsername(),abstractUser.getPassword());
                        break;
                    case "library_user":
                        libraryUser = new LibraryUser(abstractUser.getUsername(),abstractUser.getPassword());
                        break;
                    case "book_store_user":
                        bookStoreUser = new BookStoreUser(abstractUser.getUsername(),abstractUser.getPassword());
                        break;
                }
                    System.out.println("Login Successfully!");

            }else{
                System.out.println("Wrong Password.");
            }
        }
    }

    public void register() {
        System.out.print("Enter your Username(No Space) : ");
        String username = scanner.next();
        System.out.print("Enter your Password(No Space) : ");
        String password = scanner.next();
        System.out.println("Guest = 0");
        System.out.println("Library User = 1");
        System.out.println("Book Store Manager = 2");
        System.out.print("Select account type : ");
        int type = scanner.nextInt();
            switch (type){
                case 0:
                    guest = new Guest(username,password);
                    jsonHelper.write(guest);
                    break;
                case 1:
                    libraryUser = new LibraryUser(username,password);
                    jsonHelper.write(libraryUser);
                    break;
                case 2:
                    bookStoreUser = new BookStoreUser(username,password);
                    jsonHelper.write(bookStoreUser);
                    break;
            }
    }

    public < T extends AbstractUser> T getAccount() {
        if(guest != null && libraryUser == null && bookStoreUser == null){
            return (T) guest;
        } else if (guest == null && libraryUser != null && bookStoreUser == null){
            return (T) libraryUser;
        }else if (guest == null && libraryUser == null && bookStoreUser != null){
            return (T) bookStoreUser;
        }
        return (T) guest;
    }
}
