package model.Spreadsheet.src.model;

import java.util.Stack;

import static model.Spreadsheet.src.model.OperatorToken.*;

/**
 * ExpressionTreeNode holds the root as well as the left and
 * right children of the root. It also includes the tokens
 * in each Node.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 * @author Moon Chang
 */
public class ExpressionTreeNode {
	/** The root of the expression tree. */
	public ExpressionTreeNode myRoot;
	/** The left node of the root. */
	public ExpressionTreeNode myLeft;
	/** The right node of the root. */
	public ExpressionTreeNode myRight;
	/** The token stored inside the expression tree node. */
	private Token myToken;

	/**
	 * Expression tree node constructor that takes in the
	 * token, left subtree, and the right subtree.
	 * @param theToken The token/value stored in the node.
	 * @param theLeftHS The left child of the root.
	 * @param theRightHS The right child of the root.
	 */
	public ExpressionTreeNode(Token theToken, ExpressionTreeNode theLeftHS, ExpressionTreeNode theRightHS) {
		myToken = theToken;
		myLeft = theLeftHS;
		myRight = theRightHS;
	}

	/**
	 * Sets the root of the expression tree node.
	 * @param theRoot The new root of the expression tree.
	 */
	public void setMyRoot(ExpressionTreeNode theRoot) {
		myRoot = theRoot;
	}

	/**
	 * Getter that returns the token to the user.
	 * @return Returns the Token to the user.
	 */
	public Token getToken() {
		return myToken;
	}

	/**
	 * Setter method for the Token.
	 * @param theToken The Token to be set to.
	 */
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
				(ch == Exponent) ||
				(ch == LeftParen) );
	}

	// Build an expression tree from a stack of ExpressionTreeTokens
	void BuildExpressionTree (Stack<Token> s) {
		myRoot = GetExpressionTree(s);
		if (!s.isEmpty()) {
			System.out.println("Error in BuildExpressionTree.");
		}
	}

	/**
	 * Takes in a stack and pops them off and into an expression
	 * tree. Then the root node of the expression tree is returned.
	 * @param theStack The stack of Tokens representing the cells.
	 * @return Returns the root of the ExpressionTree.
	 */
	public static ExpressionTreeNode GetExpressionTree(Stack<Token> theStack) {
		ExpressionTreeNode returnTree;
		Token token;

		if (theStack.isEmpty())
			return null;

		token = (Token) theStack.pop();  // need to handle stack underflow
		if ((token instanceof LiteralToken) ||
				(token instanceof CellToken) ) {

			// Literals and Cells are leaves in the expression tree
			returnTree = new ExpressionTreeNode(token, null, null);
			return returnTree;
		} else if (token instanceof OperatorToken) {
			// Continue finding tokens that will form the
			// right subtree and left subtree.
			ExpressionTreeNode rightSubtree = GetExpressionTree (theStack);
			ExpressionTreeNode leftSubtree  = GetExpressionTree (theStack);
			returnTree =
					new ExpressionTreeNode(token, leftSubtree, rightSubtree);
			return returnTree;
		}
		return null;
	}
}
