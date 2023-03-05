package model.Spreadsheet.src.view;

import javax.swing.*;

public class CellGUI extends JPanel {
    private int myRow;
    private int myCol;

    public CellGUI(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;
    }
}
