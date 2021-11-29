package kolesov.task5.logic;

public class Token {
    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type) {
        this.type = type;
        value = "";
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
