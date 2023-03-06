package model.Spreadsheet.src.model;

/**
 * Abstract class for the Tokens included in each cell.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public abstract class Token {
    public Token myToken;

    public Token getToken() {
        return myToken;
    }

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
}
