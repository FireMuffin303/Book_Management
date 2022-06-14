package com.oopproject.bookmanagementgui.util.controller;

import com.oopproject.bookmanagementgui.MainApplication;
import com.oopproject.bookmanagementgui.util.Access;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static Access access = new Access();

    int id = access.getBookIndex();
    @FXML
    Label accountName = new Label();
    @FXML
    Label accountType = new Label();

    public void switchScene(ActionEvent event, String path) throws IOException {
        Stage stage;
        Parent parent = FXMLLoader.load(MainApplication.class.getResource(path));
        Scene scene = new Scene(parent);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountName.setText(access.getAccount().getUsername());
        switch (access.getAccount().getType()) {
            case "guest" -> accountType.setText("Guest");
            case "library_user" -> accountType.setText("Library User");
        }

    }

    public void onClickBack(ActionEvent event) throws IOException {
            switchScene(event,"Menu.fxml");
    }

}
