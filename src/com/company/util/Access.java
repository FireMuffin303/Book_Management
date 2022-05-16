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
    public boolean isLogin = false;

    Scanner scanner = new Scanner(System.in);

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean b){
        isLogin = b;
    }

    public void login(){
        System.out.print("Enter your Username : ");
        String user = scanner.next();
        System.out.print("Enter your Password : ");
        String ps = scanner.next();

        if(jsonHelper.readAccount(user) == null){
            System.out.println("Wrong Username!");
            setLogin(false);

        }else if(jsonHelper.readAccount(user) instanceof Guest){
            Guest guest = jsonHelper.readAccount(user);
            if(loginProcess(guest,user,ps)){
                this.guest = guest;
                setLogin(true);
            }
        }else if(jsonHelper.readAccount(user) instanceof LibraryUser){
            LibraryUser libraryUser = jsonHelper.readAccount(user);
            if(loginProcess(libraryUser,user,ps)){
                this.libraryUser = libraryUser;
                setLogin(true);
            }
        }else if(jsonHelper.readAccount(user) instanceof BookStoreUser){
            BookStoreUser bookStoreUser = jsonHelper.readAccount(user);
            if(loginProcess(bookStoreUser,user,ps)){
                this.bookStoreUser = bookStoreUser;
                setLogin(true);
            }
        }
    }

    public boolean loginProcess(Guest guest,String user,String ps){
        if(guest.getUsername().equals(user)){
            if(guest.getPassword().equals(ps)){
                System.out.println("Login Successfully!");
                return true;
            }
        }
        return false;
    }

    public boolean loginProcess(LibraryUser libraryUser,String user,String ps){
        if(libraryUser.getUsername().equals(user)){
            if(libraryUser.getPassword().equals(ps)){
                System.out.println("Login Successfully!");
                return true;
            }
        }
        return false;
    }

    public boolean loginProcess(BookStoreUser bookStoreUser,String user,String ps){
        if(bookStoreUser.getUsername().equals(user)){
            if(bookStoreUser.getPassword().equals(ps)){
                System.out.println("Login Successfully!");
                return true;
            }
        }
        return false;
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
