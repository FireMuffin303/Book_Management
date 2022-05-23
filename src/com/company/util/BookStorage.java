package com.company.util;

import com.company.books.Book;
import com.company.books.LibraryBook;
import com.company.books.StoreBook;
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
        String lendDate;
        lendDate = scanner.nextLine();

        System.out.println("Enter return date");
        System.out.println("Example (02/02/2022 13:25)");
        System.out.print("Enter : ");
        String returnDate;
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

    public void showBook(String search,Guest guest){
        int bookSize = guest.getBook().size();
        int i;
        Book book;
        if(!guest.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else{
            System.out.println("----------");
            for (i = 0; i< bookSize;i++){
                book = guest.getBook().get(i);
                if(search.equalsIgnoreCase(book.getName())){
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

    public void showBook(String search,LibraryUser libraryUser){
        int bookSize = libraryUser.getBook().size();
        int i;
        LibraryBook book;
        if(!libraryUser.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else {
            System.out.println("----------");
            String a,b;
            for (i = 0; i< bookSize;i++){
                book = libraryUser.getBook().get(i);
                if(search.equalsIgnoreCase(book.getName())){
                    System.out.printf("Name : %s\n",book.getName());
                    System.out.printf("Description : %s\n",book.getDesc());
                    System.out.printf("Genre : %s\n",book.getGenre());
                    System.out.printf("Storage : %s\n",book.getStorage());
                    System.out.printf("Count: %d\n",book.getCount());
                    a = book.getLendDate() == null ? "N/A" : book.getLendDate().toString();
                    System.out.printf("Lend Date: %s\n",a);
                    b = book.getReturnDate() == null ? "N/A" : book.getReturnDate().toString();
                    System.out.printf("Return Date: %s\n",b);
                    break;
                }else if(i == bookSize-1){
                    System.out.println("Book not found!");
                }
            }
            System.out.println("----------");
        }
    }

    public void showBook(String search,BookStoreUser bookStoreUser){
        int bookSize = bookStoreUser.getBook().size();
        int i;

        StoreBook book;
        if(!bookStoreUser.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else {
            System.out.println("----------");
            for (i = 0; i< bookSize;i++){
                book = bookStoreUser.getBook().get(i);
                if(search.equalsIgnoreCase(book.getName())){
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
  /*
    public void showBooks(Guest guest,String strings){
        int bookSize = guest.getBook().size();
        if(!guest.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else if(!strings.equals("@")){
            System.out.println("----------");
            int a = 0;
            for (int i = 0; i< bookSize;i++){
                if (strings.startsWith("#")){
                    String string1 = strings.substring(1);
                    if(string1.equalsIgnoreCase(guest.getBook().get(i).getGenre())){
                        ++a;
                        System.out.printf("No %d : %s\n",a,guest.getBook().get(i).getName());
                    }
                }else{
                    if (strings.equalsIgnoreCase(guest.getBook().get(i).getName())){
                        ++a;
                        System.out.printf("No %d : %s\n",a,guest.getBook().get(i).getName());
                    }
                }
            }
            System.out.println("----------");
        }else{
            System.out.println("----------");
            for (int i = 0; i< bookSize;i++){
                System.out.printf("No %d : %s\n",i+1,guest.getBook().get(i).getName());
            }
            System.out.println("----------");
        }
    }

    public void showBooks(LibraryUser libraryUser, String string){
        int bookSize = libraryUser.getBook().size();
        if(!libraryUser.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else if(!string.equals("@")){
            System.out.println("----------");
            int a = 0;
            for (int i = 0; i< bookSize;i++){
                if (string.startsWith("#")){
                    String string1 = string.substring(1);
                    if(string1.equalsIgnoreCase(libraryUser.getBook().get(i).getGenre())){
                        ++a;
                        System.out.printf("No %d : %s\n",a,libraryUser.getBook().get(i).getName());
                    }
                }else{
                    if (string.equalsIgnoreCase(libraryUser.getBook().get(i).getName())){
                        ++a;
                        System.out.printf("No %d : %s\n",a,libraryUser.getBook().get(i).getName());
                    }
                }
            }
            System.out.println("----------");
        }
        else{
            System.out.println("----------");
            for (int i = 0; i< bookSize;i++){
                System.out.printf("No %d : %s\n",i+1,libraryUser.getBook().get(i).getName());
            }
            System.out.println("----------");
        }
    }

    public void showBooks(BookStoreUser bookStoreUser,String string){
        int bookSize = bookStoreUser.getStoreBook().size();
        if(!bookStoreUser.hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else if(!string.equals("@")) {
            System.out.println("----------");
            int a = 0;
            for (int i = 0; i < bookSize; i++) {
                if (string.startsWith("#")) {
                    String string1 = string.substring(1);
                    if (string1.equalsIgnoreCase(bookStoreUser.getBook().get(i).getGenre())) {
                        ++a;
                        System.out.printf("No %d : %s\n", a, bookStoreUser.getBook().get(i).getName());
                    }
                } else {
                    if (string.equalsIgnoreCase(bookStoreUser.getBook().get(i).getName())) {
                        ++a;
                        System.out.printf("No %d : %s\n", a, bookStoreUser.getBook().get(i).getName());
                    }
                }
            }
        }else {
            System.out.println("----------");
            for (int i = 0; i < bookSize; i++) {
                System.out.printf("No %d : %s\n", i + 1, bookStoreUser.getBook().get(i).getName());
            }
            System.out.println("----------");
        }
    }

    */
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
