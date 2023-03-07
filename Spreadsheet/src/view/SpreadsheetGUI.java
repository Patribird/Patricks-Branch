package model.Spreadsheet.src.view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * SpreadsheetGUI contains the GUI components for the Spreadsheet
 * and displays the GUI to the user.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class SpreadsheetGUI extends JFrame implements PropertyChangeListener {
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    /**
     * The users screen size.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    private static final Dimension PROGRAM_DEFAULT_SIZE = new Dimension(SCREEN_SIZE.width * 9 / 10, SCREEN_SIZE.height * 9 / 10);

    private static final Dimension CELL_PANEL_SIZE = new Dimension(PROGRAM_DEFAULT_SIZE.width * 6, PROGRAM_DEFAULT_SIZE.height * 2);

    private static final int ROWS = 57; // The top row is for labels

    private static final int COLUMNS = 57; // The left column is for labels
    private static final LayoutManager CELL_LAYOUT = new GridLayout(ROWS, COLUMNS);

    private static CellGUI[][] myCells = new CellGUI[ROWS][COLUMNS];

    private static JPanel mainPanel = new JPanel();
    private static JPanel cellPanel = new JPanel();

    private JScrollPane scrollPane;

    public SpreadsheetGUI() {
        setupGUI();
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private void setupGUI() {
        setTitle("Spreadsheet");
        scrollPane = new JScrollPane(cellPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(new Dimension(PROGRAM_DEFAULT_SIZE.width * 9 / 10, PROGRAM_DEFAULT_SIZE.height * 9 / 10));
        add(scrollPane, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setUpCells();
        setSize(PROGRAM_DEFAULT_SIZE);
        setLocationRelativeTo(null);
    }

    private void setUpCells() {
        cellPanel.setLayout(CELL_LAYOUT);
        cellPanel.setPreferredSize(CELL_PANEL_SIZE);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (row == 0 && col == 0) {
                  cellPanel.add(new JLabel(" "));
                } else if (row == 0) {
                    cellPanel.add(new JLabel(getColumnString(col), SwingConstants.CENTER));
                } else if (col == 0) {
                    cellPanel.add(new JLabel(Integer.toString(row - 1), SwingConstants.CENTER));
                } else {
                    CellGUI cellAdding = new CellGUI(row - 1, col - 1);
                    myCells[row][col] = cellAdding;
                    cellPanel.add(cellAdding);
                }
            }
        }
        cellPanel.revalidate();
        cellPanel.setVisible(true);
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
}
