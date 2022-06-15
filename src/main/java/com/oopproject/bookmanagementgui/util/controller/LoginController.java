package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.util.JsonHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller {

    @FXML
    TextField userInput;
    @FXML
    TextField passwordInput;
    @FXML
    Label loginError = new Label();

    @FXML
    public void switchRegisterScene(ActionEvent event) throws IOException{
        switchScene(event,"Createaccount.fxml");
    }

    public void login(ActionEvent event) throws IOException {
        String user = userInput.getText();
        String ps = passwordInput.getText();
        if(new JsonHelper().readAccount(user) != null) {
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

}