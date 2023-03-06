package model.Spreadsheet.src.model;

/**
 * LiteralToken is a type of token that holds a literal value/integer.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class LiteralToken extends Token {
    private int myLiteral;

    public LiteralToken(final int theLiteral) {
        myLiteral = theLiteral;
    }

    public int getValue() {
        return myLiteral;
    }

    @Override
    public String toString() {
        return Integer.toString(myLiteral);
    }
}
