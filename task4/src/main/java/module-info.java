module kolesov.task {
    requires javafx.controls;
    requires javafx.fxml;

    exports kolesov.task4.models;
    exports kolesov.task4.controllers;
    exports kolesov.task4.logic;
    exports kolesov.task4.logic.expressions;
    exports kolesov.task4;
    opens kolesov.task4.controllers to javafx.fxml;
}