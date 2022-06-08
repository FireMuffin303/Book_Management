module com.oopproject.bookmanagementgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.base;

    exports com.oopproject.bookmanagementgui;
    exports com.oopproject.bookmanagementgui.util;
    opens com.oopproject.bookmanagementgui.util to javafx.fxml;
    exports com.oopproject.bookmanagementgui.util.controller;
    opens com.oopproject.bookmanagementgui.util.controller to javafx.fxml;
    opens com.oopproject.bookmanagementgui.book to com.google.gson, javafx.base;
    opens com.oopproject.bookmanagementgui.user to com.google.gson;

}