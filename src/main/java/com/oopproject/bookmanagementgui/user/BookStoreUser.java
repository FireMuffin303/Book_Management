package com.oopproject.bookmanagementgui.user;

import com.oopproject.bookmanagementgui.util.JsonHelper;
import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.StoreBook;

import java.util.ArrayList;
import java.util.Scanner;

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


    public boolean hasBook(){
        return storeBook.isEmpty()? false:true;
    }

    @Override
    public void showBook(String s) {
        int bookSize = getBook().size();
        int i;

        StoreBook book;
        if(!hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else {
            System.out.println("----------");
            for (i = 0; i< bookSize;i++){
                book = getBook().get(i);
                if(s.equalsIgnoreCase(book.getName())){
                    System.out.printf("Name : %s\n",book.getName());
                    System.out.printf("Description : %s\n",book.getDesc());
                    System.out.printf("Genre : %s\n",book.getGenre());
                    System.out.printf("Storage : %s\n",book.getStorage());
                    System.out.printf("Count: %d\n",book.getCount());
                    System.out.printf("Price: %.2f\n",book.getPrice());
                    System.out.printf("Sale: %.2f\n",book.getSale());
                    System.out.printf("Discounted: %.2f\n",book.getSalePrice());
                    break;
                }else if(i == bookSize-1){
                    System.out.println("Book not found!");
                }
            }
            System.out.println("----------");
        }
    }

    public void addBook(String name,String genre, String desc, String storage, int i,double price,double discount) {
        StoreBook storeBook = new StoreBook(name,genre,desc,storage,i,price,discount);
        setStoreBook(storeBook);
        new JsonHelper().write(this);
    }

    @Override
    public void remove(int i) {
        if(i >=0 &&i < storeBook.size()){
            storeBook.remove(i);
        }else{
            System.out.println("Book's number out of length!");
        }


        new JsonHelper().write(this);
    }

    @Override
    public ArrayList<StoreBook> getBook() {
        return storeBook;
    }

}
