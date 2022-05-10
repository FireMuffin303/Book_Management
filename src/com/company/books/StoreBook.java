package com.company.books;

import java.awt.desktop.AppReopenedEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StoreBook extends Book{
    double price,sale;

    public StoreBook(String name, String desc, String genre, String storage, int count, double price,double sale) {
        super(name, desc, genre, storage, count);
        this.price = price;
        this.sale = sale;

    }

    public double getPrice() {
        return price;
    }

    public double getSale() {
        return sale;
    }

    public double getSalePrice(){
        return price - (price * sale);
    }
}
