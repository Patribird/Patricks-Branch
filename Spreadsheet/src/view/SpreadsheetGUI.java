package model.Spreadsheet.src.view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SpreadsheetGUI extends JFrame implements PropertyChangeListener {
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    /**
     * The users screen size.
     */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(SCREEN_SIZE));
    }
}
