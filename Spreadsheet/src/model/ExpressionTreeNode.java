package model.Spreadsheet.src.model;

import java.util.Stack;

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

	public ExpressionTreeNode(Token theToken, ExpressionTreeNode theLeftHS, ExpressionTreeNode theRightHS) {
		myToken = theToken;
		myLeft = theLeftHS;
		myRight = theRightHS;
	}

	public void setMyRoot(ExpressionTreeNode theRoot) {
		myRoot = theRoot;
	}

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

	// Build an expression tree from a stack of ExpressionTreeTokens
	void BuildExpressionTree (Stack<Token> s) {
		myRoot = GetExpressionTree(s);
		if (!s.isEmpty()) {
			System.out.println("Error in BuildExpressionTree.");
		}
	}

	public static ExpressionTreeNode GetExpressionTree(Stack<Token> s) {
		ExpressionTreeNode returnTree;
		Token token;

		if (s.isEmpty())
			return null;

		token = (Token) s.pop();  // need to handle stack underflow
		if ((token instanceof LiteralToken) ||
				(token instanceof CellToken) ) {

			// Literals and Cells are leaves in the expression tree
			returnTree = new ExpressionTreeNode(token, null, null);
			return returnTree;
		} else if (token instanceof OperatorToken) {
			// Continue finding tokens that will form the
			// right subtree and left subtree.
			ExpressionTreeNode rightSubtree = GetExpressionTree (s);
			ExpressionTreeNode leftSubtree  = GetExpressionTree (s);
			returnTree =
					new ExpressionTreeNode(token, leftSubtree, rightSubtree);
			return returnTree;
		}
		return null;
	}
}
