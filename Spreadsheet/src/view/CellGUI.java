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
    private int myRow;
    private int myCol;

    private JTextField myFormulaField;

    private boolean myFormulaFieldHoveredOver = false;

    public CellGUI(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;

        myFormulaField = new JTextField(10);
        myFormulaField.setHorizontalAlignment(SwingConstants.CENTER);
        myFormulaField.setBorder(BorderFactory.createEmptyBorder());
        myFormulaField.setForeground(ColorData.getColor(SpreadsheetGUI.theme, "text"));
        myFormulaField.setToolTipText("Row: " + Integer.toString(theRow) + ", Column: " + getColumnString(theCol + 1));

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

        setBorder(BorderFactory.createLineBorder(ColorData.getColor(SpreadsheetGUI.theme, "border")));
        setLayout(new BorderLayout());
        setSize(new Dimension(60, 20));
        add(myFormulaField, BorderLayout.CENTER);
        setBackgroundToNormal();
        setVisible(true);
    }

    public void highlightCell() {
        setBackground(ColorData.getColor(SpreadsheetGUI.theme, "highlight"));
        myFormulaField.setBackground(ColorData.getColor(SpreadsheetGUI.theme, "highlight"));
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
        setBackground(ColorData.getColor(SpreadsheetGUI.theme, "normal"));
        myFormulaField.setBackground(ColorData.getColor(SpreadsheetGUI.theme, "normal"));
        // Right now there is no way to see if there are errors in a cell.
    }

    private String getColumnString(int theColumn) {
        StringBuilder result = new StringBuilder();
        int currentCol = theColumn;
        while (currentCol / 26 > 0) {
            result.append((char) (currentCol % 26 + 'A'));
            currentCol = currentCol / 26;
        }
        result.append((char) (currentCol % 26 + 'A' - 1));
        return result.reverse().toString();
    }

    public void changeTheme() {
        setBackgroundToNormal();
        myFormulaField.setForeground(ColorData.getColor(SpreadsheetGUI.theme, "text"));
    }
}
