package com.oopproject.bookmanagementgui.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.oopproject.bookmanagementgui.MainApplication;
import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.LibraryBook;
import com.oopproject.bookmanagementgui.book.StoreBook;
import com.oopproject.bookmanagementgui.user.AbstractUser;
import com.oopproject.bookmanagementgui.user.BookStoreUser;
import com.oopproject.bookmanagementgui.user.Guest;
import com.oopproject.bookmanagementgui.user.LibraryUser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class JsonHelper {
    String path = "D:/Programming/university/BookManagementGUI/src/main/resources/com/oopproject/user/";
    public void write(Guest guest){
        Gson gson = new Gson();
        try(Writer writer = new FileWriter(path+guest.getUsername()+".json")){
            gson.toJson(guest,writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void write(LibraryUser libraryUser){
        Gson gson = new Gson();
        try(Writer writer = new FileWriter(path+libraryUser.getUsername()+".json")){
            gson.toJson(libraryUser,writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void write(BookStoreUser bookStoreUser){
        Gson gson = new Gson();
        try(Writer writer = new FileWriter(path+bookStoreUser.getUsername()+".json")){
            gson.toJson(bookStoreUser,writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Guest readAccount(Guest guest) {
        Gson gson = new Gson();
        Guest guest1 = null;
        try (JsonReader reader = new JsonReader(new FileReader(path + guest.getUsername() + ".json"))) {
            guest1 = gson.fromJson(reader, Guest.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return guest1;
    }

    public LibraryUser readAccount(LibraryUser libraryUser) {
        Gson gson = new Gson();
        LibraryUser libraryUser1 = null;
        try (JsonReader reader = new JsonReader(new FileReader(path + libraryUser.getUsername() + ".json"))) {
            libraryUser1 = gson.fromJson(reader, Guest.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return libraryUser1;
    }

    public BookStoreUser readAccount(BookStoreUser bookStoreUser) {
        Gson gson = new Gson();
        BookStoreUser bookStoreUser1 = null;
        try (JsonReader reader = new JsonReader(new FileReader(path + bookStoreUser.getUsername() + ".json"))) {
            bookStoreUser1 = gson.fromJson(reader, Guest.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookStoreUser1;
    }



    public <T extends AbstractUser>T readAccount(String name) {
        Gson gson = new Gson();
        File file = new File(path+name+".json");
        try{
            if(file.exists()){
                JsonReader reader = new JsonReader(new FileReader(file));
                JsonElement element = JsonParser.parseReader(reader);
                JsonObject object = element.getAsJsonObject();

                switch (object.get("type").getAsString()){
                    case "guest":
                        Guest guest = new Guest(object.get("username").getAsString(),object.get("password").getAsString());
                        if(!object.get("book").getAsJsonArray().isJsonNull()){
                            ArrayList<Book> books =new ArrayList<>();
                            for (int i = 0; i < object.get("book").getAsJsonArray().size();i++){
                                JsonElement element1 = object.get("book").getAsJsonArray().get(i);
                                String bookName1 =element1.getAsJsonObject().get("name").getAsString();
                                String bookDecs1 =element1.getAsJsonObject().get("desc").getAsString();
                                String bookGenre1 =element1.getAsJsonObject().get("genre").getAsString();
                                String bookStorage1 =element1.getAsJsonObject().get("storage").getAsString();
                                int bookCount1 =element1.getAsJsonObject().get("count").getAsInt();
                                Book book1 = new Book(bookName1,bookDecs1,bookGenre1,bookStorage1,bookCount1);
                                books.add(book1);
                            }
                            guest.setBook(books);
                        }
                        return (T) guest;
                    case "library_user":
                        LibraryUser libraryUser = new LibraryUser(object.get("username").getAsString(),object.get("password").getAsString());
                        if(!object.get("libraryBook").getAsJsonArray().isJsonNull()){
                            ArrayList<LibraryBook> libraryBooks =new ArrayList<>();
                            for (int i = 0; i < object.get("libraryBook").getAsJsonArray().size();i++){
                                JsonElement element1 = object.get("libraryBook").getAsJsonArray().get(i);
                                String bookName1 =element1.getAsJsonObject().get("name").getAsString();
                                String bookDecs1 =element1.getAsJsonObject().get("desc").getAsString();
                                String bookGenre1 =element1.getAsJsonObject().get("genre").getAsString();
                                String bookStorage1 =element1.getAsJsonObject().get("storage").getAsString();
                                int bookCount1 =element1.getAsJsonObject().get("count").getAsInt();
                                LibraryBook book1 = new LibraryBook(bookName1,bookDecs1,bookGenre1,bookStorage1,bookCount1);
                                if (element1.getAsJsonObject().get("lendDate") != null) {
                                    String lendDate = element1.getAsJsonObject().get("lendDate").getAsString();
                                    book1.setLendDate(new SimpleDateFormat("dd/MM/yyyy H:mm").parse(lendDate));
                                }
                                if(element1.getAsJsonObject().get("returnDate") != null){
                                    String returnDate = element1.getAsJsonObject().get("returnDate").getAsString();
                                    book1.setReturnDate(new SimpleDateFormat("dd/MM/yyyy H:mm").parse(returnDate));
                                }

                                libraryBooks.add(book1);
                            }
                            libraryUser.setLibraryBook(libraryBooks);
                        }

                        return (T) libraryUser;
                    case "book_store_user":
                        BookStoreUser bookStoreUser = new BookStoreUser(object.get("username").getAsString(),object.get("password").getAsString());
                        if(!object.get("storeBook").getAsJsonArray().isJsonNull()){
                            ArrayList<StoreBook> storeBooks =new ArrayList<>();
                            for (int i = 0; i < object.get("storeBook").getAsJsonArray().size();i++){
                                JsonElement element1 = object.get("storeBook").getAsJsonArray().get(i);
                                String bookName1 =element1.getAsJsonObject().get("name").getAsString();
                                String bookDecs1 =element1.getAsJsonObject().get("desc").getAsString();
                                String bookGenre1 =element1.getAsJsonObject().get("genre").getAsString();
                                String bookStorage1 =element1.getAsJsonObject().get("storage").getAsString();
                                int bookCount1 =element1.getAsJsonObject().get("count").getAsInt();
                                double price = element1.getAsJsonObject().get("price").getAsDouble();
                                double sale = element1.getAsJsonObject().get("sale").getAsDouble();
                                StoreBook book1 = new StoreBook(bookName1,bookDecs1,bookGenre1,bookStorage1,bookCount1,price,sale);
                                storeBooks.add(book1);
                            }
                            bookStoreUser.setStoreBook(storeBooks);
                        }
                        return (T) bookStoreUser;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
