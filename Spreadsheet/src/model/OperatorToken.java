package model.Spreadsheet.src.model;

/**
 * OperatorToken is a type of Token that stores a char of the
 * basic operations that can be performed. Also includes
 * their priority (order of operations).
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class OperatorToken extends Token {
    /** A constant char that represents addition. */
    public static final char Plus = '+';
    /** A constant char that represents subtraction. */
    public static final char Minus = '-';
    /** A constant char that represents multiplication. */
    public static final char Mult = '*';
    /** A constant char that represents division. */
    public static final char Div = '/';
    /** A constant char that represents precedence with a parenthesis. */
    public static final char LeftParen = '(';
    public static final char Exponent = '^';
    /** A constant char that represents the operator. */
    private final char myOperator;

    /**
     * Basic constructor to set operator tokens value.
     * @param theOperator The operator token to set myOperator to.
     */
    public OperatorToken(final char theOperator) {
        myOperator = theOperator;
    }

    /**
     * Returns the operator token to the user.
     * @return Returns a char of the operator.
     */
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
            case Exponent -> {
                return 2;
            }
            case LeftParen -> {
                return 3;
            }
            default -> {
                // This case should NEVER happen
                System.out.println("Error in priority.");
                System.exit(0);
                return 0;
            }
        }
    }

    /**
     * Basic toString that returns a String of the operator to the
     * calling program.
     * @return Returns a String of the operator.
     */
    @Override
    public String toString() {
        return Character.toString(myOperator);
    }
}

