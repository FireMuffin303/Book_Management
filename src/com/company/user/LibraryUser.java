package com.company.user;

import com.company.books.Book;
import com.company.books.LibraryBook;
import com.company.util.JsonHelper;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryUser extends AbstractUser{
    ArrayList<LibraryBook> libraryBook = new ArrayList<>();
    public LibraryUser(String username, String password) {
        super(username, password, "library_user");

    }

    public void setLibraryBook(LibraryBook libraryBook) {
        this.libraryBook.add(libraryBook);
    }

    public void setLibraryBook(ArrayList<LibraryBook> libraryBook) {
        this.libraryBook = libraryBook;
    }

    public boolean hasBook(){
        return !libraryBook.isEmpty();
    }

    @Override
    public void showBook(String s) {
        int bookSize = getBook().size();
        int i;
        LibraryBook book;
        if(!hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else {
            System.out.println("----------");
            String a,b;
            for (i = 0; i< bookSize;i++){
                book = getBook().get(i);
                if(s.equalsIgnoreCase(book.getName())){
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

    @Override
    public void addBook() {
        Book a;
        Scanner scanner = new Scanner(System.in);
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
        LibraryBook libraryBook;
        libraryBook = new LibraryBook(a.getName(),a.getDesc(),a.getGenre(),a.getStorage(),a.getCount(),lendDate,returnDate);
        setLibraryBook(libraryBook);
        new JsonHelper().write(this);
    }

    @Override
    public ArrayList<LibraryBook> getBook() {
        return libraryBook;
    }

}
