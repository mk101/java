package kolesov.task5.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {
    private final String source;
    private int pos;

    public Lexer(String source) {
        this.source = source;
        pos = 0;
    }

    public List<Token> translate() {
        List<Token> tokens = new ArrayList<>();

        while (cur() != '\0') {
            if (Character.isDigit(cur())) {
                tokens.add(getNumber());
            } else if (Character.isLetter(cur())) {
                tokens.add(getWord());
            } else if (cur() == ' ') {
                next();
            } else {
                tokens.add(getOperator());
            }
        }

        tokens.add(new Token(TokenType.END));

        return tokens;
    }

    private Token getOperator() {
        Map<Character, TokenType> operators = new HashMap<>();
        operators.put('+', TokenType.PLUS);
        operators.put('-', TokenType.MINUS);
        operators.put('*', TokenType.STAR);
        operators.put('/', TokenType.SLASH);
        operators.put('(', TokenType.BR_OPEN);
        operators.put(')', TokenType.BR_CLOSE);

        return new Token(operators.get(next()));
    }

    private Token getWord() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetter(cur())) {
            sb.append(next());
        }

        return new Token(TokenType.WORD, sb.toString());
    }

    private Token getNumber() {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(cur())) {
            sb.append(next());
        }

        return new Token(TokenType.NUMBER, sb.toString());
    }

    private char cur() {
        if (pos < source.length()) {
            return source.charAt(pos);
        } else {
            return '\0';
        }
    }

    private char next() {
        if (pos < source.length()) {
            return source.charAt(pos++);
        } else {
            return '\0';
        }
    }
}
