package com.oopproject.bookmanagementgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Book Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        File path = new File(System.getenv("APPDATA")+"/bookManagement");
        File a = new File(System.getenv("APPDATA")+"/bookManagement/users");
        if(!path.exists()){
            path.mkdirs();
        }
        if(!a.exists()){
            a.mkdirs();
        }
        launch();
    }
}