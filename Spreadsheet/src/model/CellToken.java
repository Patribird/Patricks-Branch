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
    private int myColumn;   // column A = 0, B = 1, ...
    private int myRow;

    public CellToken() {
        myColumn = 0;
        myRow = 0;
    }

    public int getRow() {
        return myRow;
    }

    public int getColumn() {
        return myColumn;
    }

    public void setRow(final int theNewRow) {
        myRow = theNewRow;
    }

    public void setColumn(final int theNewColumn) {
        myColumn = theNewColumn;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(getColumnCharacters());
        result.append(myRow);
        return result.toString();
    }

    private String getColumnCharacters() {
        StringBuilder result = new StringBuilder();
        int currentRow = myRow;
        while (currentRow > 0) {
            int letterNumber = (myColumn % 26) + 'A';
            result.append((char) letterNumber);
            currentRow = currentRow / 26;
        }
        return result.toString();
    }

}
