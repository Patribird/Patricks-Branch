package model.Spreadsheet.src.model;

import static model.Spreadsheet.src.model.OperatorToken.*;

/**
 * ExpressionTreeNode holds the root as well as the left and
 * right children of the root. It also includes the tokens
 * in each Node.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class ExpressionTreeNode {
	public ExpressionTreeNode myRoot;
	public ExpressionTreeNode myLeft;

	public ExpressionTreeNode myRight;

	private Token myToken;

	public Token getToken() {
		return myToken;
	}

	public void setToken(final Token theToken) {
		myToken = theToken;
	}

	/**
	 * Return true if the char ch is an operator of a formula.
	 * Current operators are: +, -, *, /, (.
	 * @param ch  a char
	 * @return  whether ch is an operator
	 */
	boolean isOperator (char ch) {
		return ((ch == Plus) ||
				(ch == Minus) ||
				(ch == Mult) ||
				(ch == Div) ||
				(ch == LeftParen) );
	}
}
