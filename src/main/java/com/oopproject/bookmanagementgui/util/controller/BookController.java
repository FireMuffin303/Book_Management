package com.oopproject.bookmanagementgui.util.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookController extends Controller {

    int id;
    @FXML
    Label bookName = new Label();
    @FXML
    Label bookDesc = new Label();
    @FXML
    Label bookGenre = new Label();
    @FXML
    Label bookStorage = new Label();
    @FXML
    Label bookCount = new Label();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        id = access.getBookIndex();
        bookName.setText(access.getAccount().getBook().get(id).getName());
        bookDesc.setText(access.getAccount().getBook().get(id).getDesc());
        bookGenre.setText(access.getAccount().getBook().get(id).getGenre());
        bookStorage.setText(access.getAccount().getBook().get(id).getStorage());
        bookCount.setText(String.valueOf(access.getAccount().getBook().get(id).getCount()));
    }

    public void onClickBack(ActionEvent event) throws IOException {
        switchScene(event,"Main.fxml");
    }

    public void onClickRemove(ActionEvent event) throws IOException{
        access.getAccount().remove(id);
        onClickBack(event);
    }

    public void onClickEdit(ActionEvent event) throws IOException{
        switchScene(event,"editBookGuest.fxml");
    }

}
