package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.user.Guest;
import com.oopproject.bookmanagementgui.user.LibraryUser;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddBookController extends Controller{
    @FXML
    TextField bookNameTextField;
    @FXML
    ComboBox<String> comboBox;
    @FXML
    TextArea bookDescTextArea = new TextArea();
    @FXML
    DatePicker datePicker;
    @FXML
    TextField bookStorageTF;
    @FXML
    TextField bookID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        datePicker.setValue(LocalDate.now());
    }

    public void onClickAddBook(){
        String bookName = this.bookNameTextField.getText();
        String bookGenre = this.comboBox.getValue();
        String bookDesc = this.bookDescTextArea.getText();
        LocalDate addedDate = this.datePicker.getValue();
        switch (access.getAccount().getType()){
            case "guest" -> {
                Guest guest = access.getAccount();
                guest.addBook(bookName,bookDesc,bookGenre,addedDate);
            }
            case "library_user" -> {
                LibraryUser libraryUser = access.getAccount();
                String storage = this.bookStorageTF.getText();
                String id = this.bookID.getText();
                libraryUser.addBook(bookName,bookDesc,bookGenre,addedDate,storage,id);
                this.bookStorageTF.setText("");
                this.bookID.setText("");
            }
        }
        this.bookNameTextField.setText("");
        this.bookDescTextArea.setText("");
    }
}
