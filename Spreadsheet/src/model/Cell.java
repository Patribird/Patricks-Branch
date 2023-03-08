package model.Spreadsheet.src.model;

import model.Spreadsheet.src.controller.Spreadsheet;

import java.util.Stack;

/**
 * Cell contains all relevant information pertaining to a cell
 * including its formula as well as its value. Also has some
 * setters and an expression tree.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 * @author Tim Ratsko
 * @author Moon Chang
 */
public class Cell {
	/** The formula of the cell. */
	private String myFormula;
	/** The value of the cell. */
	private Integer myValue;
	/** The ExpressionTree of the Cell. */
	private ExpressionTree myExpressionTree;
	/** The formula in order of how it wa typed. */
	private String myInOrderFormula;

	/**
	 * Sets the formula of the Cell to the formula provided.
	 * @param theFormula The formula for the cell to be set to.
	 */
	public void setFormula (final String theFormula) {
		myFormula = theFormula;
	}

	/**
	 * Sets the formula of the Cell by using a Token Stack.
	 * Simply pops off the stack and appends it to the formula.
	 * @param expressionTreeTokens The Stack of expression tree tokens.
	 */
	public void setFormula (final Stack<Token> expressionTreeTokens) {
		StringBuilder newFormula = new StringBuilder();
		while (!expressionTreeTokens.isEmpty()) {
			newFormula.append(expressionTreeTokens.pop().toString());
		}
		myFormula = newFormula.toString();
		System.out.println("My Formula : " + myFormula);
	}

	/**
	 * Sets the in order formula for proper displaying of the
	 * formula the user input.
	 * @param theFormula The formula in normal order/in order.
	 */
	public void setFormulaInOrder(final String theFormula) {
		myInOrderFormula = theFormula;
	}

	/**
	 * Evaluates the Spreadsheet using the Token using the spreadsheet's
	 * formulas. Most of the work is done in the ExpressionTree classes
	 * evaluate methods.
	 * @param theSpreadsheet The spreadsheet to be evaluated.
	 */
	public void evaluate(Spreadsheet theSpreadsheet) {
		Stack expTreeTokenStack = SpreadSheetUtility.getFormula(myFormula);
		ExpressionTreeNode root = ExpressionTreeNode.GetExpressionTree(expTreeTokenStack);
		System.out.println("Test eval in cell");
		ExpressionTree.printTree(root);
		System.out.println("");
		System.out.println("Test eval in cell end");
		myValue = ExpressionTree.evaluate(root, theSpreadsheet);
		System.out.println(myValue);
	}

	/**
	 * Getter for the formula.
	 * @return Returns the formula.
	 */
	public String getFormula() {
		return myFormula;
	}

	/**
	 * Getter for the in order formula.
	 * @return Returns the formula in normal reading order.
	 */
	public String getInOrderFormula() {
		return myInOrderFormula;
	}

	/**
	 * Getter for the value in the cell.
	 * @return Returns the value in the cell.
	 */
	public Integer getValue() {
		return myValue;
	}
}
