package kolesov.task5.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import kolesov.task5.models.Expression;

import java.util.function.UnaryOperator;

public class AddExpressionViewController {
    @FXML
    public Button okButton;
    @FXML
    private TextField expressionField;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Expression resultRef;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setExpression(Expression expression) {
        resultRef = expression;
    }

    @FXML
    private void initialize() {
        expressionField.textProperty().addListener( (o, old, txt) -> okButton.setDisable(txt.equals("")));
        okButton.setDisable(true);

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[- 0-9a-zA-Z+*/()]*")) {
                return change;
            }

            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        expressionField.setTextFormatter(textFormatter);
    }

    @FXML
    private void handleOk() {
        resultRef.value = expressionField.getText();

        okClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
