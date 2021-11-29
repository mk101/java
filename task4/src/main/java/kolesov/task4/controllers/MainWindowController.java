package kolesov.task4.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import kolesov.task4.MyApplication;
import kolesov.task4.logic.ExpressionParseException;
import kolesov.task4.models.Expression;
import kolesov.task4.models.Parameter;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private TableColumn<Parameter, Integer> valueColumn;
    @FXML
    private TableColumn<Parameter, String> nameColumn;
    @FXML
    private Button executeButton;
    @FXML
    private Button deleteExpressionButton;
    @FXML
    private Button deleteParameterButton;
    @FXML
    private ListView<String> expressionsList;
    @FXML
    private TableView<Parameter> parametersTable;

//    private Parameter selectedParameter;
//    private String selectedExpression;

    private MyApplication app;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        valueColumn.setCellValueFactory(data -> data.getValue().valueProperty().asObject());

        expressionsList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldVal, newVal) -> {
//                    selectedExpression = newVal;
                    deleteExpressionButton.setDisable(newVal == null);
                }
        );
        parametersTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldVal, newVal) -> {
//                    selectedParameter = newVal;
                    deleteParameterButton.setDisable(newVal == null);
                }
        );

        deleteExpressionButton.setDisable(true);
        deleteParameterButton.setDisable(true);
        executeButton.setDisable(true);
    }

    public void setApplication(MyApplication app) {
        expressionsList.setItems(app.getExpressionsData());
        parametersTable.setItems(app.getParametersData());

        this.app = app;
    }

    @FXML
    private void deleteParameter() {
        var index = parametersTable.getSelectionModel().getFocusedIndex();
        parametersTable.getItems().remove(index);
    }

    @FXML
    private void deleteExpression() {
        var indexes = expressionsList.getSelectionModel().getSelectedIndices();
        for (int i : indexes) {
            expressionsList.getItems().remove(i);
        }

        if (expressionsList.getItems().isEmpty()) {
            executeButton.setDisable(true);
        }
    }

    @FXML
    private void addExpression() throws IOException {
        Expression e = new Expression("");
        if (app.showAddExpressionDialog(e)) {
            expressionsList.getItems().add(e.value);
            executeButton.setDisable(false);
        }
    }

    @FXML
    private void addParameter() throws IOException {
        Parameter p = new Parameter("", 0);
        if (app.showAddParameterDialog(p)) {
            for (Parameter parameter : parametersTable.getItems()) {
                if (parameter.getName().equals(p.getName())) {
                    return;
                }
            }
            parametersTable.getItems().add(p);
        }
    }

    @FXML
    private void execute() throws IOException, ExpressionParseException {
        app.showResultDialog();
    }
}