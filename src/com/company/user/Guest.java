package com.company.user;

import com.company.books.Book;
import com.company.util.JsonHelper;

import java.util.ArrayList;

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

    @Override
    public ArrayList<Book> getBook() {
        return book;
    }


    public boolean hasBook(){
        return book.isEmpty()? false:true;
    }

    @Override
    public void showBook(String s) {
        int bookSize = getBook().size();
        int i;
        Book book;
        if(!hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else{
            System.out.println("----------");
            for (i = 0; i< bookSize;i++){
                book = getBook().get(i);
                if(s.equalsIgnoreCase(book.getName())){
                    System.out.printf("Name : %s\n",book.getName());
                    System.out.printf("Description : %s\n",book.getDesc());
                    System.out.printf("Genre : %s\n",book.getGenre());
                    System.out.printf("Storage : %s\n",book.getStorage());
                    System.out.printf("Count: %d\n",book.getCount());
                    break;
                }else if(i == bookSize-1){
                    System.out.println("Book not found!");
                }
            }
            System.out.println("----------");
        }
    }

    @Override
    public void addBook() {
        Book book;
        book = addBookBasic();
        setBook(book);
        new JsonHelper().write(this);
    }

}
