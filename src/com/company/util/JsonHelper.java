package com.company.util;

import com.company.user.AbstractUser;
import com.company.user.BookStoreUser;
import com.company.user.Guest;
import com.company.user.LibraryUser;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class JsonHelper {
    String path = "D:/Programming/university/Book Management/src/resource/user/";
    public void write(AbstractUser abstractUser){
        Gson gson = new Gson();
        try(Writer writer = new FileWriter(path+abstractUser.getUsername()+".json")){
            gson.toJson(abstractUser,writer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public AbstractUser readAccount(AbstractUser abstractUser) {
        Gson gson = new Gson();
        AbstractUser abstractUser1 = null;
        try (JsonReader reader = new JsonReader(new FileReader(path + abstractUser.getUsername() + ".json"))) {
            abstractUser1 = gson.fromJson(reader, AbstractUser.class);
            switch (abstractUser1.getType()){
                case "guest":
                    Guest guest = gson.fromJson(reader,Guest.class);
                    return guest;
                case "library_user":
                    LibraryUser libraryUser = gson.fromJson(reader,LibraryUser.class);
                    return libraryUser;
                case "book_store_user":
                    BookStoreUser bookStoreUser = gson.fromJson(reader,BookStoreUser.class);
                    return bookStoreUser;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return abstractUser1;
    }

    public AbstractUser readAccount(String name) {
        Gson gson = new Gson();
        AbstractUser abstractUser1 = null;
        File file = new File(path+name+".json");
        try{
            if(file.exists()){
                JsonReader reader = new JsonReader(new FileReader(path + name + ".json"));
                abstractUser1 = gson.fromJson(reader, AbstractUser.class);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return abstractUser1;
    }
}
