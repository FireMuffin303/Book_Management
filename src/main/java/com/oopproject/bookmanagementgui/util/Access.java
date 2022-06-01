package com.oopproject.bookmanagementgui.util;

import com.oopproject.bookmanagementgui.user.AbstractUser;
import com.oopproject.bookmanagementgui.user.BookStoreUser;
import com.oopproject.bookmanagementgui.user.Guest;
import com.oopproject.bookmanagementgui.user.LibraryUser;

import java.util.Scanner;

public class Access {
    public Guest guest;
    public LibraryUser libraryUser;
    public BookStoreUser bookStoreUser;
    JsonHelper jsonHelper = new JsonHelper();
    public boolean isLogin = false;
    int bookIndex;

    Scanner scanner = new Scanner(System.in);

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean b){
        isLogin = b;
    }

    public void login(String user,String ps){
            if(jsonHelper.readAccount(user) instanceof Guest){
                Guest guest = jsonHelper.readAccount(user);
                if(loginProcess(guest,user,ps)) {
                    this.libraryUser = null;
                    this.bookStoreUser = null;
                    this.guest = guest;
                    setLogin(true);
                }
            }else if(jsonHelper.readAccount(user) instanceof LibraryUser){
                LibraryUser libraryUser = jsonHelper.readAccount(user);
                if(loginProcess(libraryUser,user,ps)){
                    this.bookStoreUser = null;
                    this.guest = null;
                    this.libraryUser = libraryUser;
                    setLogin(true);
                }
            }else if(jsonHelper.readAccount(user) instanceof BookStoreUser){
                BookStoreUser bookStoreUser = jsonHelper.readAccount(user);
                if(loginProcess(bookStoreUser,user,ps)){
                    this.guest = null;
                    this.libraryUser = null;
                    this.bookStoreUser = bookStoreUser;
                    setLogin(true);
                }
            }
    }

    public boolean loginProcess(Guest guest,String user,String ps){
        if(guest.getUsername().equals(user)&&guest.getPassword().equals(ps)){
            return true;
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

    public void logout(){
        this.guest = null;
        this.libraryUser = null;
        this.bookStoreUser = null;
    }

    public void setBookIndex(int a){
        this.bookIndex = a;
    }

    public int getBookIndex() {
        return this.bookIndex;
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
