package com.oopproject.bookmanagementgui.book;

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
        return price - (price * sale/100);
    }
}
