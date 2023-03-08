package model.Spreadsheet.src.view;

import java.awt.*;

public class ColorData {
    private static final Color DEFAULT_THEME_HIGHLIGHT_COLOR = new Color(243, 255, 171);
    private static final Color DEFAULT_THEME_NORMAL_COLOR = Color.white;

    private static final Color DEFAULT_THEME_BORDER_COLOR = Color.black;

    private static final Color DEFAULT_THEME_TEXT_COLOR = Color.black;

    private static final Color DEFAULT_THEME_ERROR_COLOR = new Color(255, 196, 196);

    private static final Color DARK_THEME_HIGHLIGHT_COLOR = new Color(100, 100, 100);
    private static final Color DARK_THEME_NORMAL_COLOR = new Color(20, 20, 20);

    private static final Color DARK_THEME_BORDER_COLOR = new Color(125, 125, 125);

    private static final Color DARK_THEME_TEXT_COLOR = new Color(240, 240, 240);

    private static final Color DARK_THEME_ERROR_COLOR = new Color(54, 38, 47);

    public static Color getColor(String theTheme, String theColor) {
        if (theTheme.equals("dark")) {
            if (theColor.equals("highlight")) {
                return DARK_THEME_HIGHLIGHT_COLOR;
            }
            if (theColor.equals("border")) {
                return DARK_THEME_BORDER_COLOR;
            }
            if (theColor.equals("text")) {
                return DARK_THEME_TEXT_COLOR;
            }
            if (theColor.equals("error")) {
                return DARK_THEME_ERROR_COLOR;
            }
            return DARK_THEME_NORMAL_COLOR;
        }
        if (theColor.equals("highlight")) {
            return DEFAULT_THEME_HIGHLIGHT_COLOR;
        }
        if (theColor.equals("border")) {
            return DEFAULT_THEME_BORDER_COLOR;
        }
        if (theColor.equals("text")) {
            return DEFAULT_THEME_TEXT_COLOR;
        }
        if (theColor.equals("error")) {
            return DEFAULT_THEME_ERROR_COLOR;
        }
        return DEFAULT_THEME_NORMAL_COLOR;
    }
}
