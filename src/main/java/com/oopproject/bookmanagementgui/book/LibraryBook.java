package com.oopproject.bookmanagementgui.book;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LibraryBook extends Book{

    String id,storage;
    boolean borrow = false;
    String borrowName;
    Date lendDate = null ,returnDate = null;

    public LibraryBook(String name, String desc, String genre, LocalDate date , String storage,String id) {
        super(name, desc, genre, date);
        this.id = id;
        this.storage = storage;
    }

    //Set
    public void setBorrow(boolean borrow){
        this.borrow = borrow;
    }

    public void setBorrowName(String borrowName){
        this.borrowName = borrowName;
    }

    public void setLendDate(LocalDate date){
        this.lendDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void setReturnDate(LocalDate date){
        this.returnDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void setId(String bookId){
        this.id =bookId;
    }

    public void setStorage(String bookStorage){
        this.storage = bookStorage;
    }

    //Get
    public LocalDate getLendDate() {
        if(this.lendDate != null) {
            return lendDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

    public LocalDate getReturnDate() {
        if(this.returnDate != null){
            return returnDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

    public String getId(){return id;}

    public String getStorage(){return storage;}

    public String getBorrowName() {
        return borrowName;
    }

    public boolean isBorrow() {
        return borrow;
    }
}
