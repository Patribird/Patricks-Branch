package model.Spreadsheet.src.model;

import model.Spreadsheet.src.controller.Spreadsheet;

import java.util.ArrayList;
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
	private final int myRow;

	private final int myCol;
	/** The formula of the cell. */
	private String myFormula;
	/** The value of the cell. */
	private Integer myValue;
	/** The ExpressionTree of the Cell. */
	private ExpressionTree myExpressionTree;
	/** The formula in order of how it was typed. */
	private String myInOrderFormula;

	private ExpressionTreeNode myTreeNodeRoot;

	private final ArrayList<Cell> cellsInMyFormula = new ArrayList<Cell>();

	private final ArrayList<Cell> cellsThatContainMeInFormula = new ArrayList<Cell>();

	public Cell(int theRow, int theCol) {
		myRow = theRow;
		myCol = theCol;
	}
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

	/** Sets the tree node root as the formula that should be evaluated.
	 *
	 * @param theExpTreeNode The tree node that has the entire expression.
	 */ // This is here because there were issues with the formula.
	public void setMyExpressionTree(ExpressionTreeNode theExpTreeNode) {
		myTreeNodeRoot = theExpTreeNode;
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
		myValue = ExpressionTree.evaluate(myTreeNodeRoot, theSpreadsheet);
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

	public ArrayList<Cell> getCellsInMyFormula() {
		return cellsInMyFormula;
	}

	public ArrayList<Cell> getCellsThatContainThisInFormula() {
		return cellsThatContainMeInFormula;
	}

	public void createListOfPrerequisites(String formula, Spreadsheet theSpreadsheet) {
		char ch = ' ';
		CellToken cellToken;
		int index = 0;
		while (index < formula.length() ) {
			// get rid of leading whitespace characters
			while (index < formula.length() ) {
				ch = formula.charAt(index);
				if (!Character.isWhitespace(ch)) {
					break;
				}
				index++;
			}
			if (index == formula.length() ) {
				break;
			}
			if (Character.isUpperCase(ch)) {
				// We found a cell reference token
				//CellToken cellToken = new CellToken();
				cellToken = new CellToken();
				index = SpreadSheetUtility.getCellToken(formula, index, cellToken);
				cellsInMyFormula.add(theSpreadsheet.getCell(cellToken.getRow(), cellToken.getColumn()));
				try {
					theSpreadsheet.getCell(cellToken.getRow(), cellToken.getColumn()).addToCellsThatContainThisInFormula(this);
				} catch (Exception e) {
					//cellsInMyFormula.add(null);
				}
				if (cellToken.getRow() == -1) {
					break;
				}

			} else {
				break;
			}
		}
	}

	public void clearLists() {
		cellsThatContainMeInFormula.clear();
		cellsInMyFormula.clear();
	}

	private void addToCellsThatContainThisInFormula(Cell cell) {
		cellsThatContainMeInFormula.add(cell);
	}

	private void removeCellFromDependencyList(Cell cellRemoving) {
		for (int i = 0; i < cellsInMyFormula.size(); i++) {
			if (cellsInMyFormula.get(i).equals(cellRemoving)) {
				cellsInMyFormula.remove(i);
			}
		}
	}

	public void removeSelfFromOtherCellsDependencyList(Spreadsheet theSpreadsheet) {
		for (int i = 0; i < cellsThatContainMeInFormula.size(); i++) {
			Cell cellThatContainsMeInFormula = cellsThatContainMeInFormula.get(i);
			cellThatContainsMeInFormula.removeCellFromDependencyList(this);
		}
	}

	@Override
	public String toString() {
		return myFormula;
	}

	public int getRow() {
		return myRow;
	}

	public int getColumn() {
		return myCol;
	}
}
