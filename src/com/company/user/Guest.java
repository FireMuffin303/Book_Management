package com.company.user;

import com.company.books.Book;

import java.util.ArrayList;
import java.util.List;

public class Guest extends AbstractUser{
    ArrayList<Book> book = new ArrayList<>();

    public Guest(String username, String password) {
        super(username, password, "guest");
    }


    public void setBook(Book book) {
        this.book.add(book) ;
    }


    public void setBook(ArrayList<Book> book) {
        this.book = book ;
    }

    public ArrayList<Book> getBook() {
        return book;
    }

    public boolean hasBook(){
        return book.isEmpty()? false:true;
    }

}
