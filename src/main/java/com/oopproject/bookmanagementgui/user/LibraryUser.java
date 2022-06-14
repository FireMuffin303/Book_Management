package com.oopproject.bookmanagementgui.user;

import com.oopproject.bookmanagementgui.book.LibraryBook;
import com.oopproject.bookmanagementgui.util.JsonHelper;

import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryUser extends AbstractUser{
    ArrayList<LibraryBook> libraryBook = new ArrayList<>();
    public LibraryUser(String username, String password) {
        super(username, password, "library_user");

    }

    public void setLibraryBooks(ArrayList<LibraryBook> libraryBook) {
        this.libraryBook = libraryBook;
    }


    //--Abstract--
    public void setLibraryBook(int id, LibraryBook libraryBook) {
        this.libraryBook.set(id,libraryBook);
        new JsonHelper().write(this);
    }

    public boolean hasBook(){
        return !libraryBook.isEmpty();
    }

    @Override
    public void addBook(String name, String desc, String genre, LocalDate date, String storage,String id) {
        LibraryBook libraryBook = new LibraryBook(name,desc,genre,date,storage,id);
        this.libraryBook.add(libraryBook);
        new JsonHelper().write(this);
    }

    @Override
    public void removeBook(int i) {
        if(i >=0 &&i < libraryBook.size()){
            libraryBook.remove(i);
        }else{
            System.out.println("Book's number out of length!");
        }
        new JsonHelper().write(this);
    }

    @Override
    public ArrayList<LibraryBook> getBook() {
        return libraryBook;
    }

}
