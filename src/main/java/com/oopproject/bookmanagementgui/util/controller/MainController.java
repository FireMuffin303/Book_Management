package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.user.BookStoreUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller {


    @FXML
    VBox vBox = new VBox();
    @FXML
    TextField searchBox = new TextField();

    Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        if (access.getAccount().hasBook()) {
            search();
        }
    }

    public void onClickLogout(ActionEvent event) throws IOException {
        LoginController.access.logout();
        switchScene(event, "Login.fxml");
    }

    public void onClickAddBook(ActionEvent event) throws IOException {
        if (access.getAccount() instanceof BookStoreUser) {
            switchScene(event, "bookStoreAddBook.fxml");
        } else {
            switchScene(event, "addBookGuest.fxml");
        }
    }

    public void onClickSearch(ActionEvent event) throws IOException {
        String searchItem = this.searchBox.getText();
        if (access.getAccount().hasBook()) {
            vBox.getChildren().remove(0,vBox.getChildren().size());
            if (!searchItem.equalsIgnoreCase("")) {
                if (searchItem.startsWith("#")) {
                    String a = searchItem.substring(1).toLowerCase();
                    searchGenre(a);
                }else{
                    searchName(searchItem.toLowerCase());
                }

            }else {
                search();
            }
            this.searchBox.setText("");
        }
    }

    public void search() {
        for (int i = 0; i < access.getAccount().getBook().size(); i++) {
            button = new Button(access.getAccount().getBook().get(i).getName());
            button.prefHeight(79.0D);
            button.prefWidth(600.0D);
            button.setId(String.valueOf(i));
            button.setTextFill(Paint.valueOf("#0085FF"));
            button.setAlignment(Pos.CENTER);
            button.setPadding(new Insets(5));
            button.setCursor(Cursor.HAND);
            button.setUnderline(true);
            button.setStyle("-fx-background-color: #FFFFFF00;");
            button.setOnAction(event -> {
                try {
                    access.setBookIndex(Integer.parseInt(((Button) event.getSource()).getId()));
                    switchScene(event, "book.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            vBox.getChildren().add(button);
        }
    }

    public void searchGenre(String a){
        for (int i = 0; i < access.getAccount().getBook().size(); i++) {
            if(a.equalsIgnoreCase(access.getAccount().getBook().get(i).getGenre())) {
                button = new Button(access.getAccount().getBook().get(i).getName());
                button.prefHeight(79.0D);
                button.prefWidth(600.0D);
                button.setId(String.valueOf(i));
                button.setTextFill(Paint.valueOf("#0085FF"));
                button.setAlignment(Pos.CENTER);
                button.setPadding(new Insets(5));
                button.setCursor(Cursor.HAND);
                button.setUnderline(true);
                button.setStyle("-fx-background-color: #FFFFFF00;");
                button.setOnAction(event -> {
                    try {
                        access.setBookIndex(Integer.parseInt(((Button) event.getSource()).getId()));
                        switchScene(event, "book.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                vBox.getChildren().add(button);
            }
        }
    }

    public void searchName(String a){
        for (int i = 0; i < access.getAccount().getBook().size(); i++) {
            if(access.getAccount().getBook().get(i).getName().toLowerCase().contains(a)) {
                button = new Button(access.getAccount().getBook().get(i).getName());
                button.prefHeight(79.0D);
                button.prefWidth(600.0D);
                button.setId(String.valueOf(i));
                button.setTextFill(Paint.valueOf("#0085FF"));
                button.setAlignment(Pos.CENTER);
                button.setPadding(new Insets(5));
                button.setCursor(Cursor.HAND);
                button.setUnderline(true);
                button.setStyle("-fx-background-color: #FFFFFF00;");
                button.setOnAction(event -> {
                    try {
                        access.setBookIndex(Integer.parseInt(((Button) event.getSource()).getId()));
                        switchScene(event, "book.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                vBox.getChildren().add(button);
            }
        }
    }
}

