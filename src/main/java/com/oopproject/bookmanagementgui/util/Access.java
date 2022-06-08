package com.oopproject.bookmanagementgui.util;

import com.oopproject.bookmanagementgui.user.AbstractUser;
import com.oopproject.bookmanagementgui.user.Guest;
import com.oopproject.bookmanagementgui.user.LibraryUser;

public class Access {
    public Guest guest;
    public LibraryUser libraryUser;
    JsonHelper jsonHelper = new JsonHelper();
    public boolean isLogin = false;
    int bookIndex;


    public void setLogin(boolean b){
        isLogin = b;
    }

    public void login(String user,String ps){
            if(jsonHelper.readAccount(user) instanceof Guest){
                Guest guest = jsonHelper.readAccount(user);
                if(loginProcess(guest,user,ps)) {
                    this.libraryUser = null;
                    this.guest = guest;
                    setLogin(true);
                }
            }else if(jsonHelper.readAccount(user) instanceof LibraryUser){
                LibraryUser libraryUser = jsonHelper.readAccount(user);
                if(loginProcess(libraryUser,user,ps)){
                    this.guest = null;
                    this.libraryUser = libraryUser;
                    setLogin(true);
                }
            }
    }

    public boolean loginProcess(Guest guest,String user,String ps){
        return guest.getUsername().equals(user) && guest.getPassword().equals(ps);
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

    public void logout(){
        this.guest = null;
        this.libraryUser = null;
        setLogin(false);
    }

    public void setBookIndex(int a){
        this.bookIndex = a;
    }

    public int getBookIndex() {
        return this.bookIndex;
    }

    public < T extends AbstractUser> T getAccount() {
        if(guest != null && libraryUser == null ){
            return (T) guest;
        } else if (guest == null && libraryUser != null ){
            return (T) libraryUser;
        }
        return (T) guest;
    }
}
