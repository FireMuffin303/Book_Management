package com.oopproject.bookmanagementgui.book;

public class Book {
    String name,desc,genre,storage;
    int count;

    public Book(String name,String desc, String genre, String storage,int count){
        this.name = name;
        this.desc = desc;
        this.genre = genre;
        this.storage = storage;
        this.count = count;
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

    public String getStorage() {
        return storage;
    }

    public int getCount() {
        return count;
    }
}
