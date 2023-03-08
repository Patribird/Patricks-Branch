package model.Spreadsheet.src.model;

/**
 * CellToken are for the cell tokens with a row and column.
 * An example is row: A
 *               col: 2
 *               Or:  A2
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class CellToken extends Token {
    /** The column of the Cell. */
    private int myColumn;   // column A = 0, B = 1, ...
    /** The row of the Cell. */
    private int myRow;      // row        0, 1, 2, ...

    /**
     * Public constructor for CellToken that sets the Row
     * and Column to a default value of 0.
     */
    public CellToken() {
        myColumn = 0;
        myRow = 0;
    }

    /**
     * Getter for the row of the CellToken.
     * @return Returns an integer of the Cell's row.
     */
    public int getRow() {
        return myRow;
    }

    /**
     * Getter for the column of the CellToken.
     * @return Returns an integer of the Cell's column.
     */
    public int getColumn() {
        return myColumn;
    }

    /**
     * Setter for the row of the Cell.
     * @param theNewRow The new row to be set to.
     */
    public void setRow(final int theNewRow) {
        myRow = theNewRow;
    }

    /**
     * Setter for the column of the Cell.
     * @param theNewColumn The new column to be set to.
     */
    public void setColumn(final int theNewColumn) {
        myColumn = theNewColumn;
    }

    /**
     * toString method that calls getColumnCharacters method to get
     * the columns character and myRow to get the row number of
     * the cell.
     * @return Returns the ColumnRow (I.E. A0, F10)
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getColumnCharacters());
        result.append(myRow);
        return result.toString();
    }

    /**
     * Takes the column and converts it into a char from A to B ... AA
     * and so on. Uses myRow to get the column since 2D arrays are
     * stored strange internally.
     * @return Returns a String of the columns letter representation.
     */
    private String getColumnCharacters() {
        StringBuilder result = new StringBuilder();
        int currentRow = myRow;

        do {
            int letterNumber = (myColumn % 26) + 'A';
            result.append((char) letterNumber);
            currentRow = currentRow / 26;
        } while (currentRow > 0);

        return result.toString();
    }

}
