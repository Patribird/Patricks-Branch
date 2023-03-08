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

    private static final Color PINK_THEME_HIGHLIGHT_COLOR = new Color(246, 161, 253);
    private static final Color PINK_THEME_NORMAL_COLOR = new Color(248, 67, 244);

    private static final Color PINK_THEME_BORDER_COLOR = new Color(131, 0, 130);

    private static final Color PINK_THEME_TEXT_COLOR = new Color(54, 0, 33);

    private static final Color PINK_THEME_ERROR_COLOR = new Color(192, 27, 51);

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
        if (theTheme.equals("pink")) {
            if (theColor.equals("highlight")) {
                return PINK_THEME_HIGHLIGHT_COLOR;
            }
            if (theColor.equals("border")) {
                return PINK_THEME_BORDER_COLOR;
            }
            if (theColor.equals("text")) {
                return PINK_THEME_TEXT_COLOR;
            }
            if (theColor.equals("error")) {
                return PINK_THEME_ERROR_COLOR;
            }
            return PINK_THEME_NORMAL_COLOR;
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
