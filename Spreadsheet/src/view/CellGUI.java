package model.Spreadsheet.src.view;

import javax.swing.*;
import java.awt.*;

/**
 * CellGUI contains some basic information of a cell for
 * SpreadsheetGUI.java to use when constructing the spreadsheet.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class CellGUI extends JPanel {
    private int myRow;
    private int myCol;

    public CellGUI(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);
    }
}
