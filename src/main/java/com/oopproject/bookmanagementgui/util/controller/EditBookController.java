package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.user.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditBookController extends Controller {
    int id;
    @FXML
    TextField bookNameTextField;
    @FXML
    ComboBox<String> comboBox;
    @FXML
    TextField bookDescTextField;
    @FXML
    TextField bookStorageTextField;
    @FXML
    Spinner<Integer> spinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        id = access.getBookIndex();
        this.bookNameTextField.setText(access.getAccount().getBook().get(id).getName());
        this.comboBox.setValue(access.getAccount().getBook().get(id).getGenre());
        this.bookDescTextField.setText(access.getAccount().getBook().get(id).getDesc());
        this.bookStorageTextField.setText(access.getAccount().getBook().get(id).getStorage());
        this.spinner.getValueFactory().setValue(access.getAccount().getBook().get(id).getCount());
    }

    public void onClickDone(ActionEvent event) throws IOException {
        Book book = new Book(bookNameTextField.getText(),bookDescTextField.getText(),comboBox.getValue(),bookStorageTextField.getText(),spinner.getValue());
        access.getAccount().setBook(id,book);
        switchScene(event,"book.fxml");
    }

    @Override
    public void onClickBack(ActionEvent event) throws IOException {
        switchScene(event,"book.fxml");
    }
}
