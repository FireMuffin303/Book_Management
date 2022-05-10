package com.company.user;

import com.company.books.LibraryBook;

import java.util.ArrayList;

public class LibraryUser extends AbstractUser{
    ArrayList<LibraryBook> libraryBook;
    public LibraryUser(String username, String password) {
        super(username, password, "library_user");

    }

    public void setLibraryBook(LibraryBook libraryBook) {
        this.libraryBook.add(libraryBook);
    }

    public ArrayList<LibraryBook> getLibraryBook() {
        return libraryBook;
    }


}
