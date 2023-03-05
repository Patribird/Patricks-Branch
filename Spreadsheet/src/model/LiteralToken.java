package model.Spreadsheet.src.model;

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
