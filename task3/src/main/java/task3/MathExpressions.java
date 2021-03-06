package task3;

import task3.expressions.Expression;

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
                System.out.println("%d.  ".formatted(i + 1) + expressions.get(i).toString() + " Параметры: " + parameters);
                try {
                    double result = expressions.get(i).Execute(print);
                    System.out.println("    Результат: " + result);
                    results.add(result);
                } catch (Exception e) {
                    results.add(null);
                    System.out.println("Ошибка: " + e.getMessage());
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
