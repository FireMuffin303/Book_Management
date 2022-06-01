package com.oopproject.bookmanagementgui.util.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController extends Controller{
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
    }

    public void addBook(){
        String bookName = this.bookNameTextField.getText();
        String bookGenre = this.comboBox.getValue();
        String bookDesc = this.bookDescTextField.getText();
        String bookStorage = this.bookStorageTextField.getText();
        int bookCount = this.spinner.getValue();
        access.getAccount().addBook(bookName,bookDesc,bookGenre,bookStorage,bookCount);


        this.bookNameTextField.setText("");
        this.bookDescTextField.setText("");
        this.bookStorageTextField.setText("");

    }
}
