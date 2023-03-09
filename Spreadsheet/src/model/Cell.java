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
	/** The row this cell is in. */
	private final int myRow;
	/** The column this cell is in. */
	private final int myCol;
	/** The formula of the cell. */
	private String myFormula;
	/** The value of the cell. */
	private Integer myValue;
	/** The ExpressionTree of the Cell. */
	private ExpressionTree myExpressionTree;
	/** The formula in order of how it was typed. */
	private String myInOrderFormula;
	/** The root node of the tree that holds the formula for this cell. */
	private ExpressionTreeNode myTreeNodeRoot;
	/** The list of cells used inside this cells` formula. */
	private final ArrayList<Cell> cellsInMyFormula = new ArrayList<Cell>();
	/** The list of cells that use this cell in their formula. */
	private final ArrayList<Cell> cellsThatContainMeInFormula = new ArrayList<Cell>();

	/** Create a cell using an int for row and column.
	 *
	 * @param theRow The row this cell is in.
	 * @param theCol The column this cell is in.
	 */
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
	 */
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

	/** Gets all the cells in the formula.
	 *
	 * @return A list of the cells in this formula.
	 */
	public ArrayList<Cell> getCellsInMyFormula() {
		return cellsInMyFormula;
	}

	/** Gets the cells that contain this formula.
	 *
	 * @return A list of the cells that contain this formula.
	 */
	public ArrayList<Cell> getCellsThatContainThisInFormula() {
		return cellsThatContainMeInFormula;
	}

	/** Creates a list of Prerequisites for this cell and adds it to the cellsInMyFormula list
	 *
	 * @param formula The formula of this list.
	 * @param theSpreadsheet The main spreadsheet.
	 */
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

	/**
	 *  Clears the lists of cells that contain this cell in their formula,
	 *  and the cells that are contained in this cells' formula.
	 */
	public void clearLists() {
		cellsThatContainMeInFormula.clear();
		cellsInMyFormula.clear();
	}

	/**
	 * Adds a cell to the list of cells that contain this formula.
	 * @param cell The cell to add to the list.
	 */
	private void addToCellsThatContainThisInFormula(Cell cell) {
		cellsThatContainMeInFormula.add(cell);
	}

	/** Remove cell from the dependency list.
	 *
	 * @param cellRemoving The cell to remove from the dependency list.
	 */
	private void removeCellFromDependencyList(Cell cellRemoving) {
		for (int i = 0; i < cellsInMyFormula.size(); i++) {
			if (cellsInMyFormula.get(i).equals(cellRemoving)) {
				cellsInMyFormula.remove(i);
			}
		}
	}

	/**
	 * Look over all the cells that have this cell in their dependency list and remove this cell.
	 * @param theSpreadsheet The main spreadsheet.
	 */
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

	/**
	 * Returns the integer representing the row this cell is in.
	 * @return An int representing the row this cell is in.
	 */
	public int getRow() {
		return myRow;
	}

	/**
	 * Returns the integer representing the column this cell is in.
	 * @return An int representing the column this cell is in.
	 */
	public int getColumn() {
		return myCol;
	}
}
