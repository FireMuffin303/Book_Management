package com.oopproject.bookmanagementgui.util;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.LibraryBook;
import com.oopproject.bookmanagementgui.user.AbstractUser;
import com.oopproject.bookmanagementgui.user.Guest;
import com.oopproject.bookmanagementgui.user.LibraryUser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JsonHelper {
    String path = System.getenv("APPDATA")+"/bookManagement/users/";
    public void write(Guest guest){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd-MM-yyyy");
        Gson gson = gsonBuilder.create();
        try(Writer writer = new FileWriter(path+guest.getUsername()+".json")){
            gson.toJson(guest,writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void write(LibraryUser libraryUser){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd-MM-yyyy");
        Gson gson = gsonBuilder.create();
        try(Writer writer = new FileWriter(path+libraryUser.getUsername()+".json")){
            gson.toJson(libraryUser,writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public <T extends AbstractUser>T readAccount(String name) {
        File file = new File(path+name+".json");
        try{
            if(file.exists()){
                JsonReader reader = new JsonReader(new FileReader(file));
                JsonElement element = JsonParser.parseReader(reader);
                JsonObject object = element.getAsJsonObject();

                switch (object.get("type").getAsString()){
                    case "guest":
                        return (T) readGuest(object);

                    case "library_user":
                        return (T) readLibraryUser(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Guest readGuest(JsonObject jsonObject){
        Guest guest = new Guest(jsonObject.get("username").getAsString(),jsonObject.get("password").getAsString());
        if(!jsonObject.get("book").getAsJsonArray().isJsonNull()){
            ArrayList<Book> books =new ArrayList<>();
            for (int i = 0; i < jsonObject.get("book").getAsJsonArray().size();i++){
                JsonElement element1 = jsonObject.get("book").getAsJsonArray().get(i);
                String bookName1 =element1.getAsJsonObject().get("name").getAsString();
                String bookDecs1 =element1.getAsJsonObject().get("desc").getAsString();
                String bookGenre1 =element1.getAsJsonObject().get("genre").getAsString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(element1.getAsJsonObject().get("addDate").getAsString(),formatter);

                Book book1 = new Book(bookName1,bookDecs1,bookGenre1,date);
                books.add(book1);
            }
            guest.setBooks(books);
        }
        return guest;
    }

    private LibraryUser readLibraryUser(JsonObject jsonObject) {
        LibraryUser libraryUser = new LibraryUser(jsonObject.get("username").getAsString(),jsonObject.get("password").getAsString());
        if(!jsonObject.get("libraryBook").getAsJsonArray().isJsonNull()){
            ArrayList<LibraryBook> libraryBooks =new ArrayList<>();
            for (int i = 0; i < jsonObject.get("libraryBook").getAsJsonArray().size();i++){
                JsonElement element1 = jsonObject.get("libraryBook").getAsJsonArray().get(i);
                String bookName1 =element1.getAsJsonObject().get("name").getAsString();
                String bookDecs1 =element1.getAsJsonObject().get("desc").getAsString();
                String bookGenre1 =element1.getAsJsonObject().get("genre").getAsString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(element1.getAsJsonObject().get("addDate").getAsString(),formatter);
                String bookStorage1 =element1.getAsJsonObject().get("storage").getAsString();
                int bookCount1 =element1.getAsJsonObject().get("count").getAsInt();
                String bookId = element1.getAsJsonObject().get("id").getAsString();
                LibraryBook book1 = new LibraryBook(bookName1,bookDecs1,bookGenre1,date,bookStorage1,bookCount1,bookId);
                if (element1.getAsJsonObject().get("lendDate") != null) {
                    LocalDate date1 = LocalDate.parse(element1.getAsJsonObject().get("lendDate").getAsString(),formatter);
                    book1.setLendDate(date1);
                }
                if(element1.getAsJsonObject().get("returnDate") != null){
                    LocalDate date1 = LocalDate.parse(element1.getAsJsonObject().get("returnDate").getAsString(),formatter);
                    book1.setReturnDate(date1);
                }

                libraryBooks.add(book1);
            }
            libraryUser.setLibraryBooks(libraryBooks);
        }
        return libraryUser;
    }
}
