package model.Spreadsheet.src.model;

import java.util.Stack;

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

	public int evaluate() {
		return 0;
	}

	public String getFormula() {
		return myFormula;
	}
}
