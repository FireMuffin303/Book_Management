package com.company.util;

import com.company.books.Book;
import com.company.books.LibraryBook;
import com.company.books.StoreBook;
import com.company.user.AbstractUser;
import com.company.user.BookStoreUser;
import com.company.user.Guest;
import com.company.user.LibraryUser;

import java.util.Scanner;

public class BookStorage {
    Book book;
    LibraryBook libraryBook;
    StoreBook storeBook;
    Book a;
    Scanner scanner = new Scanner(System.in);

    public void addBook(Guest guest) {

        book = addBookBasic();
        guest.setBook(book);
        new JsonHelper().write(guest);
    }

    public void addBook(LibraryUser libraryUser) {
        a = addBookBasic();
        System.out.println("Enter lend date");
        System.out.println("Example (02/02/2022 13:25)");
        System.out.print("Enter : ");
        String lendDate = "";
        lendDate = scanner.nextLine();

        System.out.println("Enter return date");
        System.out.println("Example (02/02/2022 13:25)");
        System.out.print("Enter : ");
        String returnDate = "";
        returnDate = scanner.nextLine();
        libraryBook = new LibraryBook(a.getName(),a.getDesc(),a.getGenre(),a.getStorage(),a.getCount(),lendDate,returnDate);
        libraryUser.setLibraryBook(libraryBook);
        new JsonHelper().write(libraryUser);
    }

    public void addBook(BookStoreUser bookStoreUser) {
        a = addBookBasic();
        System.out.print("Enter Book's price: ");
        double bookPrice = scanner.nextDouble();
        System.out.print("Enter Book's sale: ");
        double booksale = scanner.nextDouble();
        storeBook = new StoreBook(a.getName(),a.getDesc(),a.getGenre(),a.getStorage(),a.getCount(),bookPrice,booksale);
        bookStoreUser.setStoreBook(storeBook);
        new JsonHelper().write(bookStoreUser);

    }

    public void showBook(String bookName){

    }

    public void showBooks(Guest guest){
        if(!guest.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else{
            int bookSize = guest.getBook().size();
            System.out.println("----------");
            for (int i = 0; i< bookSize;i++){
                System.out.printf("No %d : %s\n",i+1,guest.getBook().get(i).getName());
            }
            System.out.println("----------");
        }

    }

    public void showBooks(LibraryUser libraryUser){
        if(!libraryUser.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else{
            int bookSize = libraryUser.getLibraryBook().size();
            System.out.println("----------");
            for (int i = 0; i< bookSize;i++){
                System.out.printf("No %d : %s\n",i+1,libraryUser.getLibraryBook().get(i).getName());
            }
            System.out.println("----------");
        }

    }

    public void showBooks(BookStoreUser bookStoreUser){
        if(!bookStoreUser.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else{
            int bookSize = bookStoreUser.getStoreBook().size();
            System.out.println("----------");
            for (int i = 0; i< bookSize;i++){
                System.out.printf("No %d : %s\n",i+1,bookStoreUser.getStoreBook().get(i).getName());
            }
            System.out.println("----------");
        }


    }
    private Book addBookBasic(){
        String bookName,bookDesc = "",bookGenre,bookStorage ="";
        int bookCount;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book's Name: ");
        bookName = scanner.next();
        System.out.print("Enter Book's Description: ");
        bookDesc += scanner.next();
        System.out.print("Enter Book's Genre: ");
        bookGenre = scanner.next();
        System.out.print("Enter Book's Storage: ");
        bookStorage += scanner.next();
        System.out.print("Enter Number of Books: ");
        bookCount = scanner.nextInt();

        return new Book(bookName,bookDesc,bookGenre,bookStorage,bookCount);
    }

    
}
