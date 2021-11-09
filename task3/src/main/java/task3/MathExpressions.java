package task3;

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

    public List<Double> EvaluateExpressions() throws ZeroDivException, UndefinedParameterException {
        List<Double> results = new ArrayList<>();
        for (var e : expressions) {
            results.add(e.Execute());
        }

        return results;
    }
}
