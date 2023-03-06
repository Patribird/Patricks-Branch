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

	public void setFormula (final String theFormula) {
		myFormula = theFormula;
	}

	public void setFormula (final Stack<Token> expressionTreeTokens) {
		StringBuilder newFormula = new StringBuilder();
		while (!expressionTreeTokens.isEmpty()) {
			newFormula.append(expressionTreeTokens.pop().toString());
		}
		myFormula = newFormula.toString();
	}

	public void evaluate(Spreadsheet theSpreadsheet) {

	}

	public String getFormula() {
		return myFormula;
	}
}
