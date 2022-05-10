package com.company.user;

import com.company.books.Book;

import java.util.ArrayList;
import java.util.List;

public class Guest extends AbstractUser{
    ArrayList<Book> book;

    public Guest(String username, String password) {
        super(username, password, "guest");
    }

    public void setBook(Book book) {
        this.book.add(book) ;
    }

    public ArrayList<Book> getBook() {
        return book;
    }


}
