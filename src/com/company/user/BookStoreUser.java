package com.company.user;

import com.company.books.StoreBook;

import java.util.ArrayList;

public class BookStoreUser extends AbstractUser{
    ArrayList<StoreBook> storeBook = new ArrayList<>();
    public BookStoreUser(String username, String password) {
        super(username, password, "book_store_user");
    }

    public void setStoreBook(StoreBook storeBook) {
        this.storeBook.add(storeBook);
    }

    public void setStoreBook(ArrayList<StoreBook> storeBook) {
        this.storeBook = storeBook;
    }

    public ArrayList<StoreBook> getStoreBook() {
        return storeBook;
    }

    public boolean hasBook(){
        return storeBook.isEmpty()? false:true;
    }
}
