package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.MainApplication;
import com.oopproject.bookmanagementgui.util.Access;
import com.oopproject.bookmanagementgui.util.JsonHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller {

    @FXML
    TextField userInput;

    @FXML
    TextField passwordInput;

    @FXML
    Label accountName = new Label();
    @FXML
    Label loginError = new Label();

    JsonHelper jsonHelper = new JsonHelper();

    @FXML
    public void switchRegisterScene(ActionEvent event) throws IOException{
        switchScene(event,"Createaccount.fxml");
    }

    public void login(ActionEvent event) throws IOException {
        String user = userInput.getText();
        String ps = passwordInput.getText();
        if(jsonHelper.readAccount(user) != null) {
            access.login(user, ps);
            if(access.getAccount() != null){
                 switchScene(event,"Menu.fxml");
            }
            loginError.setText("Wrong Password");
        }else{
            loginError.setText("Wrong Username");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}


    public void setAccountName(String s){
        accountName.setText(s);
    }
}