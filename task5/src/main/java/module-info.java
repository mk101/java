module kolesov.task {
    requires javafx.controls;
    requires javafx.fxml;

    exports kolesov.task5.models;
    exports kolesov.task5.controllers;
    exports kolesov.task5.logic;
    exports kolesov.task5.logic.expressions;
    exports kolesov.task5;
    opens kolesov.task5.controllers to javafx.fxml;
}