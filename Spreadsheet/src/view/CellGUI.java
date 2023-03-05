package model.Spreadsheet.src.view;

import javax.swing.*;
import java.awt.*;

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
