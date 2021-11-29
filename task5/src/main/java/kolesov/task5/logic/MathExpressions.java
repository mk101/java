package kolesov.task5.logic;

import kolesov.task5.MyApplication;
import kolesov.task5.logic.expressions.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MathExpressions {
    private final List<Expression> expressions;
    private final Map<String, Integer> parameters;

    public MathExpressions(List<Expression> expressions, Map<String, Integer> parameters) {
        this.expressions = expressions;
        this.parameters = parameters;

        setParameters();
    }

    private void setParameters() {
        for (var parameter : parameters.keySet()) {
            for (var e : expressions) {
                e.SetParameter(parameter, parameters.get(parameter));
            }
        }
    }

    public List<Double> EvaluateExpressions(boolean print) {
        List<Double> results = new ArrayList<>();
        if (print) {
            for (int i = 0; i < expressions.size(); i++) {
                MyApplication.getOutputStream().println(expressions.get(i).toString() + " Параметры: " + parameters);
                try {
                    double result = expressions.get(i).Execute(print);
                    MyApplication.getOutputStream().println("    Результат: " + result);
                    results.add(result);
                } catch (Exception e) {
                    results.add(null);
                    MyApplication.getOutputStream().println("Ошибка: " + e.getMessage());
                }
            }
        } else {
            for (var e : expressions) {
                try {
                    results.add(e.Execute());
                } catch (Exception ex) {
                    results.add(null);
                }
            }
        }

        return results;
    }
}
