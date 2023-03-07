package model.Spreadsheet.src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * CellGUI contains some basic information of a cell for
 * SpreadsheetGUI.java to use when constructing the spreadsheet.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class CellGUI extends JPanel {
    private static final Color HIGHLIGHT_COLOR = new Color(243, 255, 171);
    private  static final Color DEFAULT_COLOR = Color.white;
    private int myRow;
    private int myCol;

    private JTextField myFormulaField;

    private boolean myFormulaFieldHoveredOver = false;

    public CellGUI(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;
        myFormulaField = new JTextField(10);
        myFormulaField.setBorder(BorderFactory.createEmptyBorder());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(new Dimension(60, 20));
        add(myFormulaField);
        myFormulaField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                highlightCell();
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackgroundToNormal();
            }
        });
        setVisible(true);
    }

    public void highlightCell() {
        setBackground(HIGHLIGHT_COLOR);
        myFormulaField.setBackground(HIGHLIGHT_COLOR);
        //System.out.println(myRow + ", " + myCol);
        myFormulaField.grabFocus();
        //System.out.println("focus gained");
    }

    /**
     *  Sets the background to the default color if there
     *  are no errors with the cell, or the error color
     *  if there are errors with the cell.
     */
    public void setBackgroundToNormal() {
        setBackground(DEFAULT_COLOR);
        myFormulaField.setBackground(DEFAULT_COLOR);
        // Right now there is no way to see if there are errors in a cell.
    }
}
