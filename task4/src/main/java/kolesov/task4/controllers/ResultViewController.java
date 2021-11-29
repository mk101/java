package kolesov.task4.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import kolesov.task4.MyApplication;
import kolesov.task4.logic.ExpressionParseException;
import kolesov.task4.logic.Lexer;
import kolesov.task4.logic.MathExpressions;
import kolesov.task4.logic.Translator;
import kolesov.task4.logic.expressions.Expression;
import kolesov.task4.models.Parameter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResultViewController {

    @FXML
    private ListView<String> resultList;
    private Stage dialogStage;

    @FXML
    private void handleOK() {
        dialogStage.close();
    }

    public void setStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void execute(ObservableList<String> expressions, ObservableList<Parameter> parameters) throws ExpressionParseException {
//        List<Expression> expressionList = new ArrayList<>();
//        for (String expression : expressions) {
//            Lexer lexer = new Lexer(expression);
//            var tokens = lexer.translate();
//            Translator translator = new Translator(tokens);
//            var resExpression = translator.translate();
//            expressionList.add(resExpression);
//        }
//
//        MathExpressions me = new MathExpressions(
//                expressionList,
//                parameters.stream().collect(
//                        Collectors.toMap(Parameter::getName, Parameter::getValue
//                        )
//                )
//        );
//
//        var res = me.EvaluateExpressions(true);
//        String outData = MyApplication.getByteArrayOutputStream().toString(StandardCharsets.UTF_16);
//        var out = outData.split("\n\n");
////        resultList.getItems().addAll(res.stream().map(Object::toString).collect(Collectors.toList()));
//        resultList.getItems().addAll(out);

        List<Expression> expressionList = new ArrayList<>();
        List<String> output = new ArrayList<>();
        for (String expression : expressions) {
            try {
                expressionList.clear();
                Lexer lexer = new Lexer(expression);
                var tokens = lexer.translate();
                Translator translator = new Translator(tokens);
                var resExpression = translator.translate();
                expressionList.add(resExpression);

                MathExpressions me = new MathExpressions(
                        expressionList,
                        parameters.stream().collect(
                                Collectors.toMap(Parameter::getName, Parameter::getValue
                                )
                        )
                );
                me.EvaluateExpressions(true);
                String outData = MyApplication.getByteArrayOutputStream().toString(StandardCharsets.UTF_16);
                MyApplication.getByteArrayOutputStream().reset();
                output.add(outData);
            } catch (Exception e) {
                output.add("Ошибка: " + e.getMessage());
            }
        }
        resultList.getItems().addAll(output);
    }
}
