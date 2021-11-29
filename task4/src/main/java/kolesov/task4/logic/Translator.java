package kolesov.task4.logic;

import kolesov.task4.logic.expressions.Expression;
import kolesov.task4.logic.expressions.ExpressionFactory;
import kolesov.task4.logic.expressions.ExpressionSign;

import java.util.List;

public class Translator {
    private final List<Token> tokens;
    private int pos;

    public Translator(List<Token> tokens) {
        this.tokens = tokens;
        pos = 0;
    }

    public Expression translate() throws ExpressionParseException {
        Expression res = multDiv();

        while (true) {
            if (eq(TokenType.PLUS)) {
                next();
                res = ExpressionFactory.Create(ExpressionSign.PLUS, res, multDiv());
                continue;
            } else if (eq(TokenType.MINUS)) {
                next();
                res = ExpressionFactory.Create(ExpressionSign.MINUS, res, multDiv());
                continue;
            }
            break;
        }

        return res;
    }

    private Expression multDiv() throws ExpressionParseException {
        Expression res = unar();

        while (true) {
            if (eq(TokenType.STAR)) {
                next();
                res = ExpressionFactory.Create(ExpressionSign.MULTIPLY, res, multDiv());
                continue;
            } else if (eq(TokenType.SLASH)) {
                next();
                res = ExpressionFactory.Create(ExpressionSign.DIVIDE, res, multDiv());
                continue;
            }
            break;
        }

        return res;
    }

    private Expression unar() throws ExpressionParseException {
        if (eq(TokenType.MINUS)) {
            next();
            return ExpressionFactory.Create(-Integer.parseInt(next().getValue()));
        } else if (eq(TokenType.NUMBER)) {
            return ExpressionFactory.Create(Integer.parseInt(next().getValue()));
        } else if (eq(TokenType.WORD)) {
            return ExpressionFactory.Create(next().getValue());
        } else if (eq(TokenType.BR_OPEN)) {
            next();
            var res = translate();
            if (!expect(TokenType.BR_CLOSE)) {
                throw new ExpressionParseException();
            }
            return res;
        }

        throw new ExpressionParseException();
    }

    private Token cur() {
        return tokens.get(pos);
    }

    private Token next() {
        return tokens.get(pos++);
    }

    private boolean eq(TokenType type) {
        return type == tokens.get(pos).getType();
    }

    private boolean expect(TokenType type) {
        if (eq(type)) {
            next();
            return true;
        }
        return false;
    }
}
