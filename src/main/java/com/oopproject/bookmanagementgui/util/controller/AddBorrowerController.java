package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.book.LibraryBook;
import com.oopproject.bookmanagementgui.user.LibraryUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBorrowerController extends Controller{
    @FXML
    Label bookName = new Label();
    @FXML
    Label bookID = new Label();
    @FXML
    TextField borrowerName = new TextField();
    @FXML
    DatePicker lendDate = new DatePicker();
    @FXML
    DatePicker returnDate = new DatePicker();
    @FXML
    CheckBox checkBox = new CheckBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LibraryUser libraryUser = access.getAccount();
        this.bookName.setText(libraryUser.getBook().get(id).getName());
        this.bookID.setText(libraryUser.getBook().get(id).getId());

        LibraryBook libraryBook = libraryUser.getBook().get(id);
        if(libraryBook.getBorrowName() != null) {
            this.borrowerName.setText(libraryBook.getBorrowName());
        }
        if(libraryBook.getLendDate() != null){
            this.lendDate.setValue(libraryBook.getLendDate());
        }
        if(libraryBook.getReturnDate() != null){
            this.returnDate.setValue(libraryBook.getReturnDate());
        }
        this.checkBox.setSelected(libraryBook.isBorrow());
    }

    public void onClickConfirm(ActionEvent event) throws IOException {
        LibraryUser libraryUser = access.getAccount();
        LibraryBook libraryBook = libraryUser.getBook().get(id);
        libraryBook.setBorrowName(this.borrowerName.getText());
        libraryBook.setLendDate(this.lendDate.getValue());
        libraryBook.setBorrow(this.checkBox.isSelected());
        if (this.returnDate.getValue() != null) {
            libraryBook.setReturnDate(this.returnDate.getValue());
        }
        libraryUser.setLibraryBook(id, libraryBook);
        onClickBack(event);
    }

    @Override
    public void onClickBack(ActionEvent event) throws IOException {
        switchScene(event,"book.fxml");
    }
}
