package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.MainApplication;
import com.oopproject.bookmanagementgui.user.Guest;
import com.oopproject.bookmanagementgui.user.LibraryUser;
import com.oopproject.bookmanagementgui.util.JsonHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    ComboBox<String> comboBox;
    @FXML
    TextField username,password,cPassword;
    @FXML
    Label error;

    JsonHelper jsonHelper = new JsonHelper();
    @FXML
    public void switchLoginScene(ActionEvent event) throws IOException {
        Stage stage;
        Parent parent = FXMLLoader.load(MainApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(parent);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void register(){
        String username = this.username.getText();
        String password = this.password.getText();
        String confirmPassword = this.cPassword.getText();
        String type = this.comboBox.getValue() ;
        if(password.equals(confirmPassword)){
            if(type != null) {
                switch (type) {
                    case "Guest" -> {
                        Guest guest = new Guest(username, password);
                        jsonHelper.write(guest);
                    }
                    case "Library User" -> {
                        LibraryUser libraryUser = new LibraryUser(username, password);
                        jsonHelper.write(libraryUser);
                    }
                }
                this.username.setText("");
                this.password.setText("");
                this.cPassword.setText("");
            }else {
                this.error.setText("Please choose account type.");
            }
        }else {
            this.error.setText("Password and Confirm Password are not the same.");
        }

    }
}
