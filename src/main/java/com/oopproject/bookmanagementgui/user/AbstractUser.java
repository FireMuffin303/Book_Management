package com.oopproject.bookmanagementgui.user;

import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.LibraryBook;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class AbstractUser {
    String username, password,type;

    public AbstractUser(String username,String password,String type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public abstract ArrayList<? extends Book> getBook();

    public abstract boolean hasBook();

    public  void addBook(String name, String desc, String genre, LocalDate date){

    }

    public void addBook(String name, String desc, String genre, LocalDate date,String storage,int count,String id){

    }

    public void setBook(int id,Book book){

    }

    public void setLibraryBook(int id, LibraryBook libraryBook){}

    public abstract void removeBook(int i);

}
