package model.Spreadsheet.src.view;

import model.Spreadsheet.src.controller.Spreadsheet;
import model.Spreadsheet.src.controller.SpreadsheetApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * CellGUI contains some basic information of a cell for
 * SpreadsheetGUI.java to use when constructing the spreadsheet.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 * @author Moon chang
 */
public class CellGUI extends JPanel {
    private int myRow;
    private int myCol;

    private JTextField myTextField;

    public CellGUI(final int theRow, final int theCol) {
        myRow = theRow;
        myCol = theCol;

        myTextField = new JTextField(10);
        myTextField.setHorizontalAlignment(SwingConstants.CENTER);
        myTextField.setBorder(BorderFactory.createEmptyBorder());
        myTextField.setForeground(ColorData.getColor(SpreadsheetGUI.theme, "text"));
        myTextField.setToolTipText("Row: " + Integer.toString(theRow) + ", Column: " + getColumnString(theCol + 1));

        myTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                highlightCell();
            }

            @Override
            public void focusLost(FocusEvent e) {
                setCellToNormal();
            }
        });

        setBorder(BorderFactory.createLineBorder(ColorData.getColor(SpreadsheetGUI.theme, "border")));
        setLayout(new BorderLayout());
        setSize(new Dimension(60, 20));
        add(myTextField, BorderLayout.CENTER);
        setCellToNormal();
        setVisible(true);
        myTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    myTextField.transferFocus();
                }
            }
        });
    }

    public void highlightCell() {
        setBackground(ColorData.getColor(SpreadsheetGUI.theme, "highlight"));
        myTextField.setBackground(ColorData.getColor(SpreadsheetGUI.theme, "highlight"));
        //System.out.println(myRow + ", " + myCol);
        myTextField.grabFocus();
        String formula = "";
        if (Spreadsheet.getCell(myRow, myCol) != null) {
            formula = Spreadsheet.getCell(myRow, myCol).getFormula();
        }
        myTextField.setText(formula);
        //System.out.println("focus gained");
    }

    /**
     *  Sets the background to the default color if there
     *  are no errors with the cell, or the error color
     *  if there are errors with the cell. Also changes
     *  the text in the cell to its value.
     */
    public void setCellToNormal() {
        setBackground(ColorData.getColor(SpreadsheetGUI.theme, "normal"));
        myTextField.setBackground(ColorData.getColor(SpreadsheetGUI.theme, "normal"));
        if (!myTextField.getText().equals("")) {
            SpreadsheetApp.GUIChangeCell(myRow, myCol, myTextField.getText());
        }
        String value = "";
        if (Spreadsheet.getCell(myRow, myCol) != null) {
            value = Integer.toString(Spreadsheet.getCell(myRow, myCol).getValue());
        }
        myTextField.setText(value);
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
        setCellToNormal();
        setBorder(BorderFactory.createLineBorder(ColorData.getColor(SpreadsheetGUI.theme, "border")));
        myTextField.setForeground(ColorData.getColor(SpreadsheetGUI.theme, "text"));
    }

    public void updateText(final String newText) {
        myTextField.setText(newText);
    }
}
