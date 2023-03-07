package model.Spreadsheet.src.model;

/**
 * Abstract class for the Tokens included in each cell.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public abstract class Token {
    /** The String that represents the token. */
    public String myToken;
    /** A public method that returns the token to the user. */
    public String getToken() {
        return myToken;
    }

    /**
     * The printExpressionTreeToken method prints out a token from
     * the expression tree. It'll either be a cell, literal,
     * or operator.
     * @param expTreeToken The expression token to be printed.
     * @return Returns a String of the token.
     */
    String printExpressionTreeToken (Token expTreeToken) {
        String returnString = "";
        if (expTreeToken instanceof OperatorToken) {
            returnString = ((OperatorToken) expTreeToken).getOperatorToken() + " ";
        } else if (expTreeToken instanceof CellToken) {
            returnString = SpreadSheetUtility.printCellToken((CellToken) expTreeToken) + " ";
        } else if (expTreeToken instanceof LiteralToken) {
            returnString = ((LiteralToken) expTreeToken).getValue() + " ";
        } else {
            // This case should NEVER happen
            System.out.println("Error in printExpressionTreeToken.");
            System.exit(0);
        }
        return returnString;
    }

    @Override
    public String toString() {
        return myToken;
    }
}
