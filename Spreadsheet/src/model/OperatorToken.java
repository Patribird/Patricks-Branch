package model.Spreadsheet.src.model;

public class OperatorToken extends Token {
    public static final char Plus = '+';
    public static final char Minus = '-';
    public static final char Mult = '*';
    public static final char Div = '/';
    public static final char LeftParen = '(';
    private final char myOperator;

    public OperatorToken(final char theOperator) {
        myOperator = theOperator;
    }

    public char getOperatorToken() {
        return myOperator;
    }


    /*
     * Return the priority of this OperatorToken.
     *
     * priorities:
     *   +, - : 0
     *   *, / : 1
     *   (    : 2
     *
     * @return  the priority of operatorToken
     */
    public int priority() {
        switch (this.myOperator) {
            case Plus, Minus -> {
                return 0;
            }
            case Mult, Div -> {
                return 1;
            }
            case LeftParen -> {
                return 2;
            }
            default -> {
                // This case should NEVER happen
                System.out.println("Error in priority.");
                System.exit(0);
                return 0;
            }
        }
    }
}

