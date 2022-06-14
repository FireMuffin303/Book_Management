package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.LibraryBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.Chronology;
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
                Book book = new Book(bookNameTextField.getText(), bookDescTextArea.getText(),comboBox.getValue(),bookDatepicker.getValue());
                access.getAccount().setBook(id,book);
            }
            case "library_user" ->{
                LibraryBook libraryBook = new LibraryBook(bookNameTextField.getText(), bookDescTextArea.getText(),comboBox.getValue(),bookDatepicker.getValue(),bookStorageTF.getText(),bookID.getText());
                access.getAccount().setLibraryBook(id,libraryBook);
            }
        }
        switchScene(event,"book.fxml");
    }

    @Override
    public void onClickBack(ActionEvent event) throws IOException {
        switchScene(event,"book.fxml");
    }
}
