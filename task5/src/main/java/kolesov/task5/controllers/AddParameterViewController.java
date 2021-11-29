package kolesov.task5.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import kolesov.task5.models.Parameter;

import java.util.function.UnaryOperator;

public class AddParameterViewController {
    @FXML
    private TextField valueField;
    @FXML
    private TextField nameField;
    @FXML
    private Button okButton;


    private Stage dialogStage;
    private boolean okClicked = false;
    private Parameter resultRef;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setExpression(Parameter expression) {
        resultRef = expression;
    }

    @FXML
    private void initialize() {
        nameField.textProperty().addListener( (o, old, txt) -> okButton.setDisable(txt.equals("") || valueField.getText().equals("")));
        valueField.textProperty().addListener( (o, old, txt) -> okButton.setDisable(txt.equals("") || nameField.getText().equals("")));
        okButton.setDisable(true);

        UnaryOperator<TextFormatter.Change> numFilter = change -> {
            String text = change.getText();

            //^[-]?[0-9]*
            if (text.matches("^-?[0-9]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> numTextFormatter = new TextFormatter<>(numFilter);
        valueField.setTextFormatter(numTextFormatter);

        UnaryOperator<TextFormatter.Change> textFilter = change -> {
            String text = change.getText();

            if (text.matches("[a-z A-Z]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(textFilter);
        nameField.setTextFormatter(textFormatter);
    }

    @FXML
    private void handleOk() {
        int parameter = 0;
        try {
            parameter = Integer.parseInt(valueField.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка заполнения");
            alert.setContentText("Введено некорректное значение параметра");

            alert.showAndWait();
            return;
        }

        resultRef.setName(nameField.getText());
        resultRef.setValue(parameter);

        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
