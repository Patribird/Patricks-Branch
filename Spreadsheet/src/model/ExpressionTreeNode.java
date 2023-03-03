package model.Spreadsheet.src.model;

public class ExpressionTreeNode {
	private final static char Plus = '+';
	private final static char Minus = '-';
	private final static char Mult = '*';
	private final static char Div = '/';
	private final static char LeftParen = '(';
	public ExpressionTreeNode left;

	public ExpressionTreeNode right;

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
