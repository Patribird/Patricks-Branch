package model.Spreadsheet.src.view;

import java.awt.*;

/**
 * The color data for the themes.
 * @author Nathameion Montgomery
 */
public class ColorData {
    /** The color for the default highlight. */
    private static final Color DEFAULT_THEME_HIGHLIGHT_COLOR = new Color(243, 255, 171);
    /** The color for the default background. */
    private static final Color DEFAULT_THEME_NORMAL_COLOR = Color.white;
    /** The color for the default border. */
    private static final Color DEFAULT_THEME_BORDER_COLOR = Color.black;
    /** The color for the default text. */
    private static final Color DEFAULT_THEME_TEXT_COLOR = Color.black;
    /** The color for the default error. */
    private static final Color DEFAULT_THEME_ERROR_COLOR = new Color(255, 196, 196);
    /** The color for the default highlight. */
    private static final Color DARK_THEME_HIGHLIGHT_COLOR = new Color(100, 100, 100);
    /** The color for the dark background. */
    private static final Color DARK_THEME_NORMAL_COLOR = new Color(20, 20, 20);
    /** The color for the dark border. */
    private static final Color DARK_THEME_BORDER_COLOR = new Color(125, 125, 125);
    /** The color for the dark text. */
    private static final Color DARK_THEME_TEXT_COLOR = new Color(240, 240, 240);
    /** The color for the dark error. */
    private static final Color DARK_THEME_ERROR_COLOR = new Color(54, 38, 47);
    /** The color for the pink highlight. */
    private static final Color PINK_THEME_HIGHLIGHT_COLOR = new Color(248, 67, 244);
    /** The color for the pink background. */
    private static final Color PINK_THEME_NORMAL_COLOR = new Color(246, 161, 253);
    /** The color for the pink border. */
    private static final Color PINK_THEME_BORDER_COLOR = new Color(131, 0, 130);
    /** The color for the pink text. */
    private static final Color PINK_THEME_TEXT_COLOR = new Color(54, 0, 33);
    /** The color for the pink error. */
    private static final Color PINK_THEME_ERROR_COLOR = new Color(192, 27, 51);
    /** The color for the nature highlight. */
    private static final Color NATURE_THEME_HIGHLIGHT_COLOR = new Color(215, 202, 112);
    /** The color for the nature background. */
    private static final Color NATURE_THEME_NORMAL_COLOR = new Color(138, 178, 76);
    /** The color for the nature border. */
    private static final Color NATURE_THEME_BORDER_COLOR = new Color(87, 52, 23);
    /** The color for the nature text color. */
    private static final Color NATURE_THEME_TEXT_COLOR = new Color(10, 65, 0);
    /** The color for the nature error. */
    private static final Color NATURE_THEME_ERROR_COLOR = new Color(252, 82, 82);
    /** The color for the fun highlight. */
    private static final Color FUN_THEME_HIGHLIGHT_COLOR = new Color(58, 134, 255);
    /** The color for the fun background. */
    private static final Color FUN_THEME_NORMAL_COLOR = new Color(246, 255, 0);
    /** The color for the fun border. */
    private static final Color FUN_THEME_BORDER_COLOR = new Color(255, 0, 110);
    /** The color for the fun text. */
    private static final Color FUN_THEME_TEXT_COLOR = new Color(108, 1, 106);
    /** The color for the fun error. */
    private static final Color FUN_THEME_ERROR_COLOR = new Color(252, 82, 82);

    /**
     * Gets the color by passing in the theme and the color.
     * @param theTheme The theme passed in.
     * @param theColor The color name.
     * @return Returns the color.
     */
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
        if (theTheme.equals("nature")) {
            if (theColor.equals("highlight")) {
                return NATURE_THEME_HIGHLIGHT_COLOR;
            }
            if (theColor.equals("border")) {
                return NATURE_THEME_BORDER_COLOR;
            }
            if (theColor.equals("text")) {
                return NATURE_THEME_TEXT_COLOR;
            }
            if (theColor.equals("error")) {
                return NATURE_THEME_ERROR_COLOR;
            }
            return NATURE_THEME_NORMAL_COLOR;
        }

        if (theTheme.equals("fun")) {
            if (theColor.equals("highlight")) {
                return FUN_THEME_HIGHLIGHT_COLOR;
            }
            if (theColor.equals("border")) {
                return FUN_THEME_BORDER_COLOR;
            }
            if (theColor.equals("text")) {
                return FUN_THEME_TEXT_COLOR;
            }
            if (theColor.equals("error")) {
                return FUN_THEME_ERROR_COLOR;
            }
            return FUN_THEME_NORMAL_COLOR;
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
