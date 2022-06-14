package com.oopproject.bookmanagementgui.util.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.Chronology;
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
    @FXML
    Spinner<Integer> spinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        datePicker.setValue(LocalDate.now());
    }

    public void addBook(){
        String bookName = this.bookNameTextField.getText();
        String bookGenre = this.comboBox.getValue();
        String bookDesc = this.bookDescTextArea.getText();
        LocalDate addedDate = this.datePicker.getValue();
        switch (access.getAccount().getType()){
            case "guest" -> access.getAccount().addBook(bookName,bookDesc,bookGenre,addedDate);
            case "library_user" -> {
                String storage = this.bookStorageTF.getText();
                String id = this.bookID.getText();
                access.getAccount().addBook(bookName,bookDesc,bookGenre,addedDate,storage,id);
                this.bookStorageTF.setText("");
                this.bookID.setText("");
            }
        }


        this.bookNameTextField.setText("");
        this.bookDescTextArea.setText("");

    }
}
