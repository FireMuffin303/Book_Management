package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.LibraryBook;
import com.oopproject.bookmanagementgui.user.LibraryUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BorrowersListController extends Controller {
    @FXML
    TableView<LibraryBook> bookTableView;
    @FXML
    TableColumn<LibraryBook, String> bookName;
    @FXML
    TableColumn<LibraryBook, String> bookId;
    @FXML
    TableColumn<LibraryBook, String> borrowerName;
    @FXML
    TableColumn<LibraryBook, LocalDate> lendDate;
    @FXML
    TextField search;
    @FXML
    ComboBox<String> comboBox;
    @FXML
    Button button;
    private boolean isSearch = false;
    private ObservableList<LibraryBook> observableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        this.bookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.bookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.borrowerName.setCellValueFactory(new PropertyValueFactory<>("borrowName"));
        this.lendDate.setCellValueFactory(new PropertyValueFactory<>("lendDate"));
        setTable();
        this.bookTableView.setItems(observableList);
    }

    public void setTable() {
        LibraryUser libraryUser = access.getAccount();
        ArrayList<LibraryBook> libraryBooksList = new ArrayList<>();
        if (libraryUser.hasBook()) {
            ArrayList<LibraryBook> bookList = libraryUser.getBook();
            for (LibraryBook book : bookList) {
                if (book.isBorrow()) {
                    libraryBooksList.add(book);
                }
            }
            observableList  = FXCollections.observableArrayList(libraryBooksList);
        }
    }

    public void onClickSearch(){
        LibraryUser libraryUser = access.getAccount();
        ArrayList<LibraryBook> libraryBookArrayList = new ArrayList<>();
        String search = this.search.getText().toLowerCase();
        if(!isSearch){
            if(libraryUser.hasBook()){
                ArrayList<LibraryBook> libraryBooks = libraryUser.getBook();
                for (LibraryBook libraryBook : libraryBooks) {
                    if (libraryBook.isBorrow()) {
                        System.out.println(comboBox.getValue().toLowerCase());
                        switch (comboBox.getValue().toLowerCase()) {
                            case "book name" -> {
                                if (libraryBook.getName().toLowerCase().contains(search)) {
                                    libraryBookArrayList.add(libraryBook);
                                }
                            }
                            case "borrower name" -> {
                                if (libraryBook.getBorrowName().toLowerCase().contains(search)) {
                                    libraryBookArrayList.add(libraryBook);
                                }
                            }
                            case "book id" -> {
                                if (libraryBook.getId().toLowerCase().contains(search)) {
                                    libraryBookArrayList.add(libraryBook);
                                }
                            }
                            case "loan date" -> {
                                if (libraryBook.getLendDate().toString().toLowerCase().contains(search)) {
                                    libraryBookArrayList.add(libraryBook);
                                }
                            }
                        }
                    }
                }
                this.observableList =FXCollections.observableArrayList(libraryBookArrayList);
            }
            this.isSearch = true;
            this.button.setText("Clear");
            this.bookTableView.setItems(this.observableList);
        }else{
            setTable();
            this.isSearch = false;
            this.button.setText("Search");
            this.bookTableView.setItems(this.observableList);
            this.search.setText("");
        }


    }
}