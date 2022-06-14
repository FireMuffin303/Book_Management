package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.LibraryBook;
import com.oopproject.bookmanagementgui.user.LibraryUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BorrowersListController extends Controller {
    @FXML
    TableView<LibraryBook> bookTableView;
    @FXML
    TableColumn<Book, String> bookName;
    @FXML
    TableColumn<Book, String> bookId;
    @FXML
    TableColumn<Book, String> borrowerName;
    @FXML
    TableColumn<Book, LocalDate> lendDate;

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
            for (int i = 0 ; i < bookList.size();i++){
                if(bookList.get(i).isBorrow()){
                    LibraryBook libraryBook = bookList.get(i);
                    libraryBooksList.add(libraryBook);
                }
            }
            observableList  = FXCollections.observableArrayList(libraryBooksList);
        }
    }
}