package com.company.user;

import com.company.books.StoreBook;

import java.util.ArrayList;

public class BookStoreUser extends AbstractUser{
    ArrayList<StoreBook> storeBook;
    public BookStoreUser(String username, String password) {
        super(username, password, "book_store_user");
    }

    public void setStoreBook(StoreBook storeBook) {
        this.storeBook.add(storeBook);
    }

    public ArrayList<StoreBook> getStoreBook() {
        return storeBook;
    }


}
