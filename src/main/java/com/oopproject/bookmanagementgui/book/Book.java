package com.oopproject.bookmanagementgui.book;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Book {
    String name,desc,genre;
    Date addDate;

    public Book(String name,String desc, String genre, LocalDate date){
        this.name = name;
        this.desc = desc;
        this.genre = genre;
        this.addDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getAddDate() {return addDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();}
}
