package com.oopproject.bookmanagementgui.book;

import com.oopproject.bookmanagementgui.util.JsonHelper;

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

    public void setLendDate(LocalDate date){
        this.lendDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void setReturnDate(LocalDate date){
        this.returnDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

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


    public void setBorrowName(String borrowName){
        this.borrowName = borrowName;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrow(boolean borrow){
        this.borrow = borrow;
    }

    public boolean isBorrow() {
        return borrow;
    }
}
