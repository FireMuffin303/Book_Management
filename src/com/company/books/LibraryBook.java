package com.company.books;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryBook extends Book{
    Date lendDate = null ,returnDate = null;

    public LibraryBook(String name, String desc, String genre, String storage, int count,String lendDate,String returnDate) {
        super(name, desc, genre, storage, count);
        try{
            this.lendDate = new SimpleDateFormat("dd/MM/yyyy H:mm").parse(lendDate);
            this.returnDate = new SimpleDateFormat("dd/MM/yyyy H:mm").parse(returnDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public Date getLendDate() {
        return lendDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
