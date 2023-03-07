package model.Spreadsheet.src.model;

/**
 * The ExpressionTreeToken is a type of token found in an
 * expression tree.
 * @author Patrick Hern
 */
public class ExpressionTreeToken extends Token {
    /** The expression tree token. */
    public Token myExpTreeToken;

    /**
     * Returns a String of the expression tree token.
     * @return Returns a String of the expression tree token.
     */
    @Override
    public String toString() {
        return myExpTreeToken.getToken();
    }
}
