package com.company.util;

import com.company.books.Book;
import com.company.books.LibraryBook;
import com.company.books.StoreBook;
import com.company.user.AbstractUser;

import java.util.Scanner;

public class BookStorage {
    Book book;
    LibraryBook libraryBook;
    StoreBook storeBook;

    public void addBook(AbstractUser abstractUser){
        Book a;
        Scanner scanner = new Scanner(System.in);
        switch(abstractUser.getType()){
            case "guest":
                book = addBookBasic();
                break;
            case "library_user":
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
                break;
            case "book_store_user":
                a = addBookBasic();
                System.out.print("Enter Book's price: ");
                double bookPrice = scanner.nextDouble();
                System.out.print("Enter Book's sale: ");
                double booksale = scanner.nextDouble();
                storeBook = new StoreBook(a.getName(),a.getDesc(),a.getGenre(),a.getStorage(),a.getCount(),bookPrice,booksale);
                break;
        }
    }


    public void showBook(String bookName){

    }

    private Book addBookBasic(){
        String bookName,bookDesc = "",bookGenre,bookStorage ="";
        int bookCount;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book's Name: ");
        bookName = scanner.next();
        System.out.print("Enter Book's Description: ");
        bookDesc += scanner.nextLine();
        System.out.print("Enter Book's Genre: ");
        bookGenre = scanner.next();
        System.out.print("Enter Book's Storage: ");
        bookStorage += scanner.next();
        System.out.print("Enter Number of Books: ");
        bookCount = scanner.nextInt();

        return new Book(bookName,bookDesc,bookGenre,bookStorage,bookCount);
    }

    
}
