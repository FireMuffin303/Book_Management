package com.oopproject.bookmanagementgui.user;

import com.oopproject.bookmanagementgui.book.Book;

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

    public abstract void removeBook(int i);

}
