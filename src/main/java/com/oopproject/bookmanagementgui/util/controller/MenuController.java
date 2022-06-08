package com.oopproject.bookmanagementgui.util.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller {
    @FXML
    Button bookList = new Button();
    @FXML
    Button addBook = new Button();
    @FXML
    Button button2 = new Button();

    @FXML
    VBox vBox = new VBox();
    @FXML
    TextField searchBox = new TextField();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        //If account is guest.
        if ("guest".equals(access.getAccount().getType())) {
            button2.setVisible(false);
        }
    }

    public void onClickLogout(ActionEvent event) throws IOException {
        LoginController.access.logout();
        switchScene(event, "Login.fxml");
    }

    public void onClickAddBook(ActionEvent event) throws IOException {
        switch (access.getAccount().getType()){
            case "guest" -> switchScene(event, "guest/addBook_Guest.fxml");
            case "library_user" -> switchScene(event, "library/addBook_Library.fxml");
        }

    }

    public void onClickBookList(ActionEvent event) throws IOException{
        switch (access.getAccount().getType()){
            case "guest" -> switchScene(event,"guest/myBook.fxml");
            case "library_user" -> switchScene(event,"library/list_book.fxml");
        }
    }


}

