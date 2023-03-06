package model.Spreadsheet.src.model;

import model.Spreadsheet.src.controller.Spreadsheet;

import java.util.Stack;

/**
 * Cell contains all relevant information pertaining to a cell
 * including its formula as well as its value. Also has some
 * setters and an expression tree.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class Cell {
	private String myFormula;

	private Integer myValue;

	private ExpressionTree myExpressionTree;
	private String myInOrderFormula;

	public void setFormula (final String theFormula) {
		myFormula = theFormula;
	}

	public void setFormula (final Stack<Token> expressionTreeTokens) {
		StringBuilder newFormula = new StringBuilder();
		while (!expressionTreeTokens.isEmpty()) {
			newFormula.append(expressionTreeTokens.pop().toString());
		}
		myFormula = newFormula.toString();
		System.out.println("My Formula : " + myFormula);
	}

	public void setFormulaInOrder(final String theFormula) {
		myInOrderFormula = theFormula;
	}

	public void evaluate(Spreadsheet theSpreadsheet) {
		Stack expTreeTokenStack = SpreadSheetUtility.getFormula(myFormula);
		ExpressionTreeNode root = ExpressionTreeNode.GetExpressionTree(expTreeTokenStack);
		System.out.println("Test eval in cell");
		ExpressionTree.printTree(root);
		System.out.println("Test eval in cell end");
		myValue = ExpressionTree.evaluate(root, theSpreadsheet);
		System.out.println(myValue);
	}

	public String getFormula() {
		return myFormula;
	}

	public String getInOrderFormula() {
		return myInOrderFormula;
	}

	public Integer getValue() {
		return myValue;
	}
}
