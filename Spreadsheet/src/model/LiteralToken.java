package model.Spreadsheet.src.model;

/**
 * LiteralToken is a type of token that holds a literal value/integer.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class LiteralToken extends Token {
    /** The literal value of the Token. */
    private int myLiteral;

    /**
     * Basic constructor that constructs a new literal passed in by the
     * user.
     * @param theLiteral The literal value passed in.
     */
    public LiteralToken(final int theLiteral) {
        myLiteral = theLiteral;
    }

    /**
     * Returns the value of the literal to the user.
     * @return
     */
    public int getValue() {
        return myLiteral;
    }

    /**
     * Basic toString that returns a String of the literal to the
     * calling program.
     * @return Returns a String of the literal.
     */
    @Override
    public String toString() {
        return Integer.toString(myLiteral);
    }
}
