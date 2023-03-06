package model.Spreadsheet.src.model;

import model.Spreadsheet.src.controller.Spreadsheet;

/**
 * The ExpressionTree class has a few methods that interact with
 * ExpressionTreeNode for the purpose of evaluating the expression
 * tree itself.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class ExpressionTree {
	private boolean myContainsRoot;

	public void makeEmpty() {

	}

	public static void printTree(ExpressionTreeNode expTreeNode) {
		//System.out.println(expTreeNode.getToken()); // Pre-fix
		if (expTreeNode.myLeft != null)
			printTree(expTreeNode.myLeft);
		//System.out.println(expTreeNode.getToken()); // In-fix
		if (expTreeNode.myRight != null)
			printTree(expTreeNode.myRight);
		System.out.println(expTreeNode.getToken()); // Post-fix
	}

	@Override
	public String toString() {
		return "";
	}

	public static int evaluate(ExpressionTreeNode expTreeNode) {
		// Literals and Cell tokens are leaves so just return whatever value is associated with them
		// But Operators will have both a left and right child, so we need to evaluate those as well.

		Token token = expTreeNode.getToken();
		if (token instanceof LiteralToken) {
			return ((LiteralToken) token).getValue();
		} else if (token instanceof CellToken) {
			return ((CellToken) token).getValue();
		} else if (token instanceof OperatorToken) {
			// Continue finding tokens that will form the
			// right subtree and left subtree.
			ExpressionTreeNode rightSubtree = expTreeNode.myRight;
			ExpressionTreeNode leftSubtree  = expTreeNode.myLeft;

			char op = ((OperatorToken) token).getOperatorToken();
			if (op == '+') {
				return evaluate(rightSubtree) + evaluate(leftSubtree);
			} else if (op == '-') {
				return evaluate(leftSubtree) - evaluate(rightSubtree);
			} else if (op == '*') {
				return evaluate(rightSubtree) * evaluate(leftSubtree);
			} else if (op == '/') {
				return evaluate(leftSubtree) / evaluate(rightSubtree);
			}

		}

		return -1; // Code should never get here
	}

	public int evaluate(final Spreadsheet theSpreadsheet) {
		return 0;
	}
}
