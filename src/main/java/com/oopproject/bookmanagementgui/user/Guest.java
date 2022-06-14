package com.oopproject.bookmanagementgui.user;

import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.util.JsonHelper;

import java.time.LocalDate;
import java.util.ArrayList;

public class Guest extends AbstractUser{
    ArrayList<Book> book = new ArrayList<>();

    public Guest(String username, String password) {
        super(username, password, "guest");
    }

    public void setBooks(ArrayList<Book> book) {
        this.book = book ;
    }

    public void setBook(int id,Book book) {
         this.book.set(id,book);
         new JsonHelper().write(this);
    }

    @Override
    public ArrayList<Book> getBook() {
        return book;
    }

    public boolean hasBook(){
        return !book.isEmpty();
    }

    //@Override
    public void addBook(String name, String desc, String genre , LocalDate date) {
        Book book = new Book(name,desc,genre,date);
        this.book.add(book);
        new JsonHelper().write(this);
    }

    @Override
    public void removeBook(int i) {
        if(i >=0 &&i < book.size()){
            book.remove(i);
        }else{
            System.out.println("Book's number out of length!");
        }
        new JsonHelper().write(this);
    }

}
