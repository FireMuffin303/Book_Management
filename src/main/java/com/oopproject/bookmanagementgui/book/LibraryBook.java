package com.oopproject.bookmanagementgui.book;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LibraryBook extends Book{

    String id,storage;
    int count;
    boolean borrow = false;
    Date lendDate = null ,returnDate = null;

    public LibraryBook(String name, String desc, String genre, LocalDate date , String storage, int count,String id) {
        super(name, desc, genre, date);
        this.id = id;
        this.storage = storage;
        this.count = count;
    }

    public void setLendDate(LocalDate date){
        this.lendDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void setReturnDate(LocalDate date){
        this.returnDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getLendDate() {
        return lendDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getId(){return id;}

    public String getStorage(){return storage;}

    public int getCount() {
        return count;
    }
}
