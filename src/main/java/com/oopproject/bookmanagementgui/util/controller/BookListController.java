package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.MainApplication;
import com.oopproject.bookmanagementgui.book.Book;
import com.oopproject.bookmanagementgui.book.LibraryBook;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookListController extends Controller {
    @FXML
    TableView<Book> bookTableView;
    @FXML
    TableColumn<Book,String> bookName;
    @FXML
    TableColumn<Book,String> bookGenre;
    @FXML
    TableColumn<Book, LocalDate> bookDate;

    @FXML
    Button searchButton;
    @FXML
    ComboBox genreBox;
    @FXML
    TextField search;

    private ObservableList<Book> books;

    @FXML
    TableView<LibraryBook> libraryBookTableView;
    @FXML
    TableColumn<LibraryBook,String> libraryBookID;
    @FXML
    TableColumn<LibraryBook,String> libraryBookName;
    @FXML
    TableColumn<LibraryBook,String> libraryBookGenre;
    @FXML
    TableColumn<LibraryBook, String> libraryBookStorage;
    @FXML
    TableColumn<LibraryBook, String> libraryBookBorrows;
    private ObservableList<LibraryBook> libraryBooks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        switch (access.getAccount().getType()){
            case "guest":
                this.bookName.setCellValueFactory(new PropertyValueFactory<>("name"));
                this.bookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
                this.bookDate.setCellValueFactory(new PropertyValueFactory<>("addDate"));
                setTableBookArrayList();
                this.bookTableView.setItems(books);
                this.bookTableView.setRowFactory(tv -> {
                    TableRow<Book> row = new TableRow<>();
                    row.setOnMouseClicked(event -> {
                        if(event.getClickCount() == 2 && !row.isEmpty()){
                            Book book = row.getItem();
                            access.setBookIndex(access.getAccount().getBook().indexOf(book));
                            System.out.println(access.getBookIndex());
                            Stage stage;
                            Parent parent = null;
                            try {
                                parent = FXMLLoader.load(MainApplication.class.getResource("book.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = new Scene(parent);
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        }
                    });
                    return row;
                });
                break;
            case "library_user":
                this.libraryBookID.setCellValueFactory(new PropertyValueFactory<>("id"));
                this.libraryBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
                this.libraryBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
                this.libraryBookStorage.setCellValueFactory(new PropertyValueFactory<>("storage"));
                this.libraryBookBorrows.setCellValueFactory( data ->{
                    //System.out.println(data.getValue().isBorrow());
                   if(!data.getValue().isBorrow()){
                        return new SimpleStringProperty("ไม่ถูกยืม");
                   }else {
                       return new SimpleStringProperty("กำลังถูกยืม");
                   }
                });

                setLibraryBookTableView();
                this.libraryBookTableView.setItems(libraryBooks);
                this.libraryBookTableView.setRowFactory(tv ->{
                    TableRow<LibraryBook> libraryBookTableRow = new TableRow<>();
                    libraryBookTableRow.setOnMouseClicked(event -> {
                        if(event.getClickCount() == 2 && !libraryBookTableRow.isEmpty()) {
                            LibraryBook book = libraryBookTableRow.getItem();
                            access.setBookIndex(access.getAccount().getBook().indexOf(book));
                            System.out.println(access.getBookIndex());
                            Stage stage;
                            Parent parent = null;
                            try {
                                parent = FXMLLoader.load(MainApplication.class.getResource("book.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = new Scene(parent);
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        }
                    });
                    return libraryBookTableRow;
                });

        }

    }

    public void onClickSearch(){
        String search = this.search.getText();
        if(this.searchButton.getText().equalsIgnoreCase("search") && (!this.search.getText().isEmpty() || this.genreBox.getValue() != null)) {
            switch (access.getAccount().getType()) {
                case "guest" -> {
                    setTableBookArrayList(search);
                    this.bookTableView.setItems(books);
                }
                case "library_user" -> {
                    setLibraryBookTableView(search);
                    this.libraryBookTableView.setItems(libraryBooks);
                }
            }
            this.searchButton.setText("Clear");
        }else if(this.searchButton.getText().equalsIgnoreCase("clear")){
            switch (access.getAccount().getType()){
                case "guest" -> {
                    setTableBookArrayList();
                    this.bookTableView.setItems(books);
                }
                case "library_user" -> {
                    setLibraryBookTableView();
                    this.libraryBookTableView.setItems(libraryBooks);
                }
            }
            this.search.setText("");
            this.genreBox.setValue(null);
            this.searchButton.setText("Search");
            switch (access.getAccount().getType()){
                case "guest" ->this.search.setPromptText("Book Name");
                case "library_user" -> this.search.setPromptText("Book ID or Book Name");
            }
            this.genreBox.setPromptText("- Type -");
        }
    }

    private void setTableBookArrayList(String search){
        if(access.getAccount().hasBook()){
                ArrayList<Book> arrayList = new ArrayList<>();
                if(this.genreBox.getValue() == null){
                    for (int i = 0; i < access.getAccount().getBook().size(); i++) {
                        if (access.getAccount().getBook().get(i).getName().toLowerCase().contains(search)) {
                            arrayList.add(access.getAccount().getBook().get(i));
                        }
                    }
                    books = FXCollections.observableArrayList(arrayList);
                }else {
                    String genreBoxValue = this.genreBox.getValue().toString();
                    for (int i = 0; i < access.getAccount().getBook().size(); i++) {
                        if (access.getAccount().getBook().get(i).getName().toLowerCase().contains(search) && access.getAccount().getBook().get(i).getGenre().equalsIgnoreCase(genreBoxValue)) {
                            arrayList.add(access.getAccount().getBook().get(i));
                        }
                    }
                    books = FXCollections.observableArrayList(arrayList);
                }
            }
    }

    private void setTableBookArrayList(){
        if(access.getAccount().hasBook()) {
            books = FXCollections.observableArrayList(access.getAccount().getBook());
        }
    }

    private void setLibraryBookTableView(String search){
        if(access.getAccount().hasBook()){
            ArrayList<LibraryBook> arrayList = new ArrayList<>();

            if(this.genreBox.getValue() == null) {
                for (int i = 0; i < access.getAccount().getBook().size(); i++) {
                    LibraryBook book = (LibraryBook) access.getAccount().getBook().get(i);
                    if (book.getName().toLowerCase().contains(search.toLowerCase()) || book.getId().toLowerCase().contains(search.toLowerCase())) {
                        arrayList.add((LibraryBook) access.getAccount().getBook().get(i));
                    }
                }
                libraryBooks = FXCollections.observableArrayList(arrayList);
            }else {
                String genreBoxValue = this.genreBox.getValue().toString();
                for (int i = 0; i < access.getAccount().getBook().size(); i++) {
                    LibraryBook book = (LibraryBook) access.getAccount().getBook().get(i);
                    if (book.getName().toLowerCase().contains(search.toLowerCase()) && book.getGenre().equalsIgnoreCase(genreBoxValue)) {
                        arrayList.add((LibraryBook) access.getAccount().getBook().get(i));
                    }
                }
                libraryBooks = FXCollections.observableArrayList(arrayList);
            }
        }
    }


    private void setLibraryBookTableView(){
        if(access.getAccount().hasBook()) {
            libraryBooks = (ObservableList<LibraryBook>) FXCollections.observableArrayList(access.getAccount().getBook());
        }
    }
}
