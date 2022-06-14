package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.LibraryBook;
import com.oopproject.bookmanagementgui.user.Guest;
import com.oopproject.bookmanagementgui.user.LibraryUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditBookController extends Controller {
    //int id;
    @FXML
    TextField bookNameTextField;
    @FXML
    ComboBox<String> comboBox;
    @FXML
    TextArea bookDescTextArea;
    @FXML
    DatePicker bookDatepicker = new DatePicker();

    //Library
    @FXML
    TextField bookID;
    @FXML
    TextField bookStorageTF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        id = access.getBookIndex();
        this.bookNameTextField.setText(access.getAccount().getBook().get(id).getName());
        this.comboBox.setValue(access.getAccount().getBook().get(id).getGenre());
        this.bookDescTextArea.setText(access.getAccount().getBook().get(id).getDesc());
        this.bookDatepicker.setValue(access.getAccount().getBook().get(id).getAddDate());
        if(access.getAccount().getType().equals("library_user")){
            LibraryBook libraryBook = (LibraryBook) access.getAccount().getBook().get(id);
            this.bookID.setText(libraryBook.getId());
            this.bookStorageTF.setText(libraryBook.getStorage());
        }
    }

    public void onClickDone(ActionEvent event) throws IOException {
        switch (access.getAccount().getType()){
            case "guest" ->{
                Guest guest = access.getAccount();
                Book book = guest.getBook().get(id);
                book.setName(this.bookNameTextField.getText());
                book.setGenre(this.comboBox.getValue());
                book.setDesc(this.bookDescTextArea.getText());
                book.setAddDate(this.bookDatepicker.getValue());
                guest.setBook(id,book);
            }
            case "library_user" ->{
                LibraryUser libraryUser = access.getAccount();
                LibraryBook libraryBook = libraryUser.getBook().get(id);
                libraryBook.setName(this.bookNameTextField.getText());
                libraryBook.setDesc(this.bookDescTextArea.getText());
                libraryBook.setGenre(this.comboBox.getValue());
                libraryBook.setAddDate(this.bookDatepicker.getValue());
                libraryBook.setId(this.bookID.getText());
                libraryBook.setStorage(this.bookStorageTF.getText());
                libraryUser.setLibraryBook(id,libraryBook);
            }
        }
        switchScene(event,"book.fxml");
    }

    @Override
    public void onClickBack(ActionEvent event) throws IOException {
        switchScene(event,"book.fxml");
    }
}
