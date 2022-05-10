package com.company.user;

import com.company.books.Book;

public class AbstractUser {
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
}
