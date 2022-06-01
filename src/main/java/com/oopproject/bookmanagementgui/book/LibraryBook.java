package com.oopproject.bookmanagementgui.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryBook extends Book{
    Date lendDate = null ,returnDate = null;

    public LibraryBook(String name, String desc, String genre, String storage, int count) {
        super(name, desc, genre, storage, count);
    }

    public void setLendDate(Date date){
        this.lendDate = date;
    }

    public void setReturnDate(Date date){
        this.returnDate = date;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
