package model.Spreadsheet.src.model;

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
}
