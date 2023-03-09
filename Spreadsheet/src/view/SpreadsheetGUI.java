package model.Spreadsheet.src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * SpreadsheetGUI contains the GUI components for the Spreadsheet
 * and displays the GUI to the user.
 * @author Patrick Hern
 * @author Nathameion Montgomery
 */
public class SpreadsheetGUI extends JFrame implements PropertyChangeListener {
    /** Default toolkit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    /**  The users screen size. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** Sets the Dimensions of the program window to a 90% of the screen space. */
    private static final Dimension PROGRAM_DEFAULT_SIZE = new Dimension(SCREEN_SIZE.width * 9 / 10, SCREEN_SIZE.height * 9 / 10);
    /** Sets the Dimensions of the cells to be decently large in the program. */
    private static final Dimension CELL_PANEL_SIZE = new Dimension(PROGRAM_DEFAULT_SIZE.width * 6, PROGRAM_DEFAULT_SIZE.height * 2);
    /** The number of rows for the side label. */
    private static int rows = 57; // The top row is for labels (57 is the default number of rows)
    /** The number of columns for the top label. */
    private static int columns = 57; // The left column is for labels (57 is the default number of columns)
    /** The defualt theme for the spreadsheet. */
    public static String theme = "light";
    /** Contain for the borderComponents. */
    public JComponent[] borderComponents;
    /** The layout of the cells. */
    private static LayoutManager CELL_LAYOUT;
    /** The GUI element for the spreadsheet cells. */
    private static CellGUI[][] myCells;
    /** The file menu. */
    private static JMenu fileMenu;
    /** The exit button menu option */
    private static JMenuItem exitButton;
    /** The options' menu. */
    private static JMenu optionsMenu;
    /** The themes' submenu. */
    private static JMenu themesMenu;
    /** The radio button for the light theme. */
    private static JRadioButtonMenuItem lightThemeButton;
    /** The radio button for the dark theme. */
    private static JRadioButtonMenuItem darkThemeButton;
    /** The radio button for the pink theme. */
    private static JRadioButtonMenuItem pinkThemeButton;
    /** The radio button for the nature theme. */
    private static JRadioButtonMenuItem natureThemeButton;
    /** The radio button for the fun theme. */
    private static JRadioButtonMenuItem funThemeButton;
    /** The main panel.. */
    private static JPanel mainPanel = new JPanel();
    /** The cell panel. */
    private static JPanel cellPanel = new JPanel();
    /** The scroll pane. */
    private JScrollPane scrollPane;

    /**
     * Public constructor for the GUI that calls the other
     * methods to set up the GUI.
     * @param theRows The rows of the spreadsheet.
     * @param theColumns The columns of the spreadsheet.
     */
    public SpreadsheetGUI(int theRows, int theColumns) {
        rows = theRows;
        columns = theColumns;
        CELL_LAYOUT = new GridLayout(rows, columns);
        borderComponents = new JComponent[rows + columns - 1]; // This array keeps track of the border components so their themes can be changed later
        myCells = new CellGUI[rows][columns];
        setupGUI(theRows, theColumns);
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

    /**
     * Sets up the general GUI, the cells, and the menus.
     * @param theRows The rows in the spreadsheet.
     * @param theColumns The columns in the spreadsheet.
     */
    private void setupGUI(int theRows, int theColumns) {
        setTitle("Spreadsheet");
        scrollPane = new JScrollPane(cellPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        cellPanel.setSize(PROGRAM_DEFAULT_SIZE);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpCells();
        setUpMenu();
        setSize(PROGRAM_DEFAULT_SIZE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);
        setVisible(true);
    }

    /**
     * Set the dimensions and attributes of the Cells in the GUI.
     */
    private void setUpCells() {
        cellPanel.setLayout(CELL_LAYOUT);
        cellPanel.setPreferredSize(CELL_PANEL_SIZE);
        int borderComponentIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (row == 0 && col == 0) {
                    JLabel adding = new JLabel(" ");
                    adding.setOpaque(true);
                    adding.setBackground(ColorData.getColor(theme, "normal"));
                    borderComponents[borderComponentIndex] = adding;
                    borderComponentIndex++;
                    cellPanel.add(adding);
                } else if (row == 0) {
                    JLabel adding = new JLabel(getColumnString(col), SwingConstants.CENTER);
                    adding.setOpaque(true);
                    adding.setBackground(ColorData.getColor(theme, "normal"));
                    adding.setForeground(ColorData.getColor(theme, "text"));
                    borderComponents[borderComponentIndex] = adding;
                    borderComponentIndex++;
                    cellPanel.add(adding);
                } else if (col == 0) {
                    JLabel adding = new JLabel(Integer.toString(row - 1), SwingConstants.CENTER);
                    adding.setOpaque(true);
                    adding.setBackground(ColorData.getColor(theme, "normal"));
                    adding.setForeground(ColorData.getColor(theme, "text"));
                    borderComponents[borderComponentIndex] = adding;
                    borderComponentIndex++;
                    cellPanel.add(adding);
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

    /**
     * Sets up the menu and the menu options found in the
     * GUI.
     */
    private void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setAlignmentX(SwingConstants.LEFT);
        fileMenu = new JMenu("File");
        exitButton = new JMenuItem("Exit");
        fileMenu.add(exitButton);
        optionsMenu = new JMenu("Options");
        themesMenu = new JMenu("Themes");
        optionsMenu.add(themesMenu);
        lightThemeButton = new JRadioButtonMenuItem("Light");
        darkThemeButton = new JRadioButtonMenuItem("Dark");
        pinkThemeButton = new JRadioButtonMenuItem("Pink");
        natureThemeButton = new JRadioButtonMenuItem("Nature");
        funThemeButton = new JRadioButtonMenuItem("Fun");
        ButtonGroup group = new ButtonGroup();
        group.add(lightThemeButton);
        group.add(darkThemeButton);
        group.add(pinkThemeButton);
        group.add(natureThemeButton);
        group.add(funThemeButton);
        lightThemeButton.setSelected(true);
        themesMenu.add(lightThemeButton);
        themesMenu.add(darkThemeButton);
        themesMenu.add(pinkThemeButton);
        themesMenu.add(natureThemeButton);
        themesMenu.add(funThemeButton);
        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);

        lightThemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme("light");
            }
        });
        darkThemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme("dark");
            }
        });
        pinkThemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme("pink");
            }
        });
        natureThemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme("nature");
            }
        });

        funThemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme("fun");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Takes an integer that represents the column and then converts it
     * into a character (like excel and other spreadsheets).
     * @param theColumn The integer representation of the
     * @return Returns a String of the column. A = 0, B, C, ...,  AA
     */
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

    /**
     * Changes the color theme of the spreadsheet.
     * @param newTheme The name of the new theme.
     */
    private void changeTheme(String newTheme) {
        if (newTheme.equals(theme)) {
            return;
        }
        theme = newTheme;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (myCells[row][col] != null) {
                    //System.out.println(Integer.toString(row) + "," + Integer.toString(col));
                    myCells[row][col].changeTheme();
                } else {
                    //System.out.println("null cell");
                }
            }
        }
        for (JComponent component : borderComponents) {
            component.setOpaque(true);
            component.setBackground(ColorData.getColor(theme, "normal"));
            component.setForeground(ColorData.getColor(theme, "text"));
        }
    }

    /**
     * Sets the cells text.
     * @param theRow The integer value for the row.
     * @param theCol The integer value for the column.
     * @param theText The cell text to be displayed.
     */
    public void setCellText(int theRow, int theCol, String theText) {
        if (myCells[theRow][theCol] == null) {
            return;
        }
        myCells[theRow][theCol].updateText(theText);
    }

    /**
     * If there is an error in a cell then set the cell to contain
     * an error.
     * @param row The integer value for the row.
     * @param col The integer value for the column.
     * @param setting The setting of the error.
     * @param message The message to be displayed.
     */
    public void setErrorInCell(int row, int col, boolean setting, String message) {
        try {
            myCells[row + 1][col + 1].setErrorInCell(setting, message);
        } catch (Exception e) {

        }
    }

}
