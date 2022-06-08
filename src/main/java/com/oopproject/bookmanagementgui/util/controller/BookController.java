package com.oopproject.bookmanagementgui.util.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookController extends Controller {

    int id;
    @FXML
    Label bookName = new Label();
    @FXML
    TextArea bookDesc = new TextArea();
    @FXML
    Label bookGenre = new Label();
    @FXML
    Label bookStorage = new Label();
    @FXML
    Label bookCount = new Label();
    @FXML
    Label bookDate = new Label();
    @FXML
    Button borrowBook = new Button();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        id = access.getBookIndex();
        bookName.setText(access.getAccount().getBook().get(id).getName());
        bookDesc.setText(access.getAccount().getBook().get(id).getDesc());
        bookDesc.setEditable(false);
        bookGenre.setText(access.getAccount().getBook().get(id).getGenre());
        bookDate.setText(access.getAccount().getBook().get(id).getAddDate().toString());

        borrowBook.setVisible(!access.getAccount().getType().equals("guest"));
    }


    public void onClickRemove(ActionEvent event) throws IOException{
        access.getAccount().removeBook(id);
        this.onClickBack(event);
    }

    public void onClickEdit(ActionEvent event) throws IOException{
        switch (access.getAccount().getType()){
            case "guest" -> switchScene(event,"guest/editBook_Guest.fxml");
            case "library_user" -> switchScene(event,"library/edit1Book_Library.fxml");
        }

    }


    @Override
    public void onClickBack(ActionEvent event) throws IOException {
        switch (access.getAccount().getType()){
            case "guest" -> switchScene(event,"guest/mybook.fxml");
            case "library_user" -> switchScene(event,"library/list_book.fxml");
        }
    }
}
