package task3;

import task3.expressions.ExpressionFactory;
import task3.expressions.ExpressionSign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws UndefinedParameterException, ZeroDivException {
        List<Expression> expressions = new ArrayList<>();
        expressions.add(
                ExpressionFactory.Create(ExpressionSign.PLUS,
                        ExpressionFactory.Create(10),
                        ExpressionFactory.Create(ExpressionSign.MULTIPLY,
                                ExpressionFactory.Create("a"),
                                ExpressionFactory.Create(10)
                        )
                )
        );

        expressions.add(
                ExpressionFactory.Create(ExpressionSign.DIVIDE,
                        ExpressionFactory.Create(
                                ExpressionSign.PLUS,
                                ExpressionFactory.Create("a"),
                                ExpressionFactory.Create("b")
                        ),
                        ExpressionFactory.Create(ExpressionSign.MULTIPLY,
                                ExpressionFactory.Create("a"),
                                ExpressionFactory.Create(10)
                        )
                )
        );

        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("a", 42);
        parameters.put("b", 24);

        MathExpressions mathExpressions = new MathExpressions(expressions, parameters);

        for (int i = 0; i < expressions.size(); i++) {
            System.out.println("%d.  ".formatted(i+1) + expressions.get(i).toString() + " Параметры: " + parameters);
            double result = expressions.get(i).Execute(true);
            System.out.println("    Результат: " + result);
        }
    }
}
