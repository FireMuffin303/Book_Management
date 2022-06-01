package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.user.BookStoreUser;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BookStoreAddBookController extends AddBookController {
    @FXML
    TextField bookPriceTextField;
    @FXML
    TextField bookDiscountTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    public void addBook() {
        String bookName = this.bookNameTextField.getText();
        double price = Double.parseDouble(this.bookPriceTextField.getText());
        double discount = Double.parseDouble(this.bookDiscountTextField.getText());
        String bookGenre = this.comboBox.getValue();
        String bookDesc = this.bookDescTextField.getText();
        String bookStorage = this.bookStorageTextField.getText();
        int bookCount = this.spinner.getValue();
        if (access.getAccount() instanceof BookStoreUser) {
            access.getAccount().addBook(bookName, bookGenre, bookDesc, bookStorage, bookCount, price, discount);
        }
    }
}