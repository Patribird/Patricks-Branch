package model.Spreadsheet.src.model;

import model.Spreadsheet.src.controller.Spreadsheet;

/**
 * The ExpressionTree class has a few methods that interact with
 * ExpressionTreeNode for the purpose of evaluating the expression
 * tree itself.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 * @author Tim Ratsko
 */
public class ExpressionTree {
	/** Boolean for if the ExpressionTree contains the root. */
	private boolean myContainsRoot;
	/** Method to make the expression tree empty. */
	public void makeEmpty() {

	}

	/**
	 * Recursive method to print out the tree nodes in pre, in,
	 * and post fixes. Though pre and in are commented out.
	 * @param expTreeNode The expression tree node to print.
	 */
	public static void printTree(ExpressionTreeNode expTreeNode) {
		//System.out.println(expTreeNode.getToken()); // Pre-fix
		if (expTreeNode.myLeft != null)
			printTree(expTreeNode.myLeft);
		//System.out.println(expTreeNode.getToken()); // In-fix
		if (expTreeNode.myRight != null)
			printTree(expTreeNode.myRight);
		System.out.println(expTreeNode.getToken()); // Post-fix
	}

	/**
	 * Generates a string of the ExpressionTree. Also, recursive and
	 * in post-fix order.
	 * @param expTreeNode The expression tree node to print.
	 * @return Returns a String of the ExpressionTree.
	 */
	public static String stringTree(ExpressionTreeNode expTreeNode) {
		String out = "";
		if (expTreeNode.myLeft != null)
			out += stringTree(expTreeNode.myLeft) + " ";
		if (expTreeNode.myRight != null)
			out += stringTree(expTreeNode.myRight) + " ";
		return out +=expTreeNode.getToken().toString(); // Post-fix
	}

	/**
	 * Evaluate parses the ExpressionTree and evaluates all expression.
	 * It checks for the different types of tokens and then
	 * @param expTreeNode The expression tree node and Token to parse.
	 * @param s The spreadsheet that contains the needed cells.
	 * @return Returns the integer the ExpressionTree evaluates to.
	 */
	public static int evaluate(ExpressionTreeNode expTreeNode, Spreadsheet s) {
		// Literals and Cell tokens are leaves so just return whatever value is associated with them
		// But Operators will have both a left and right child, so we need to evaluate those as well.
		Token token;
		if (expTreeNode == null) {
			token = new LiteralToken(0);
		} else {
			token = expTreeNode.getToken();
		}
		if (token instanceof LiteralToken) {
			return ((LiteralToken) token).getValue();
		} else if (token instanceof CellToken) {
			Cell c = s.getCell(((CellToken) token).getRow(), ((CellToken) token).getColumn());
			if (c == null) { // Check if the cell actually exists.
				return 0;
			} else { // If it does then grab the value
				return c.getValue();
			}
		} else if (token instanceof OperatorToken) {
			// Continue finding tokens that will form the
			// right subtree and left subtree.
			ExpressionTreeNode rightSubtree = expTreeNode.myRight;
			ExpressionTreeNode leftSubtree  = expTreeNode.myLeft;

			char op = ((OperatorToken) token).getOperatorToken();
			if (op == '+') {
				return evaluate(rightSubtree, s) + evaluate(leftSubtree, s);
			} else if (op == '-') {
				return evaluate(leftSubtree, s) - evaluate(rightSubtree, s);
			} else if (op == '*') {
				return evaluate(rightSubtree, s) * evaluate(leftSubtree, s);
			} else if (op == '/') {
				return evaluate(leftSubtree, s) / evaluate(rightSubtree, s);
			}

		}

		return -1; // Code should never get here
	}
}
