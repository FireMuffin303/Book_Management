package com.oopproject.bookmanagementgui.user;

import com.oopproject.bookmanagementgui.book.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class AbstractUser {
    String username, password,type;

    public AbstractUser(String username,String password,String type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public abstract ArrayList<? extends Book> getBook();

    public abstract boolean hasBook();

    public abstract void showBook(String s);

    public void showBooks(String s){
        int bookSize = getBook().size();
        if(!hasBook()){
            System.out.println("You don't have any book yet, please add at least a book first.");
        }else if(!s.equals("@")){
            System.out.println("----------");
            int a = 0;
            for (int i = 0; i< bookSize;i++){
                if (s.startsWith("#")){
                    String string1 = s.substring(1);
                    if(string1.equalsIgnoreCase(getBook().get(i).getGenre())){
                        ++a;
                        System.out.printf("No %d : %s\n",a,getBook().get(i).getName());
                    }
                }else{
                    if (s.equalsIgnoreCase(getBook().get(i).getName())){
                        ++a;
                        System.out.printf("No %d : %s\n",a,getBook().get(i).getName());
                    }
                }
            }
            System.out.println("----------");
        }
        else{
            System.out.println("----------");
            for (int i = 0; i< bookSize;i++){
                System.out.printf("No %d : %s\n",i+1,getBook().get(i).getName());
            }
            System.out.println("----------");
        }
    }

    public void addBook(String a, String b, String c, String d, int i) {

    }

    public void addBook(String name, String genre, String desc, String storage, int count, double price, double discount){

    }

    public void setBook(int id,Book book){

    }

    public abstract void remove(int i);

    public Book addBookBasic(){
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
