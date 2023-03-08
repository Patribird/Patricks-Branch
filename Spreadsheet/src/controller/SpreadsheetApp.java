package model.Spreadsheet.src.controller;

import model.Spreadsheet.src.model.*;
import model.Spreadsheet.src.view.SpreadsheetGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static model.Spreadsheet.src.model.OperatorToken.*;

/**
 * SpreadsheetApp is the main driver for the Spreadsheet program and is
 * written with a text based version of the Spreadsheet.
 * @author Donald Chinn
 * @author Patrick Hern
 * @author Nathameion Montgomery
 * @author Tim Ratsko
 * @author Moon Chang
 */
public class SpreadsheetApp {
    static Spreadsheet theSpreadsheet;
    private static final int ROWS_AND_COLUMNS = 57;

    /**
     * Read a string from standard input.
     * All characters up to the first carriage return are read.
     * The return string does not include the carriage return.
     * @return  a line of input from standard input
     */
    public static String readString() {
        BufferedReader inputReader;
        String returnString = "";
        char ch;

        inputReader = new BufferedReader (new InputStreamReader(System.in));


        // read all characters up to a carriage return and append them
        // to the return String
        try {
             returnString = inputReader.readLine();
        }
        catch (IOException e) {
            System.out.println("Error in reading characters in readString.");
        }
        return returnString;
    }

    /**
     * Prints the values stored in the cells.
     * @param theSpreadsheet The spreadsheet with all of its cells.
     */
    private static void menuPrintValues(Spreadsheet theSpreadsheet) {
        theSpreadsheet.printValues();
    }

    /**
     * Prints the cell's formula out to the console as a part of the
     * print cell menu option.
     * @param theSpreadsheet The spreadsheet with all the cells.
     */
    private static void menuPrintCellFormula(Spreadsheet theSpreadsheet) {
        CellToken cellToken = new CellToken();
        String inputString;

        System.out.println("Enter the cell: ");
        inputString = readString();
        theSpreadsheet.getCellToken(inputString, 0, cellToken);

        System.out.print(theSpreadsheet.printCellToken(cellToken));
        System.out.print(": ");

        if ((cellToken.getRow() < 0) ||
            (cellToken.getRow() >= theSpreadsheet.getNumRows()) ||
            (cellToken.getColumn() < 0) ||
            (cellToken.getColumn() >= theSpreadsheet.getNumColumns())) {

            System.out.println("Bad cell.");
            return;
        }

        //theSpreadsheet.printCellFormula(cellToken);

        theSpreadsheet.printCellFormula(theSpreadsheet.getCell(cellToken.getRow(), cellToken.getColumn()));

        System.out.println();
    }

    /**
     * Prints out all the formulas stored in the Spreadsheet.
     * @param theSpreadsheet The spreadsheet with all Cells and formulas.
     */
    private static void menuPrintAllFormulas(Spreadsheet theSpreadsheet) {
        theSpreadsheet.printAllFormulas();
        System.out.println();
    }

    /**
     * Prints out to the console a prompt to change a Cell in the spreadsheet.
     * The String is parsed and then the formula is updated for the
     * respective cell.
     * @param theSpreadsheet The spreadsheet where a Cell will be changed.
     */
    private static void menuChangeCellFormula(Spreadsheet theSpreadsheet) {
        String inputCell;
        String inputFormula;
        CellToken cellToken = new CellToken();
        Stack expTreeTokenStack;
//        ExpressionTreeToken expTreeToken;
        Token expTreeToken;


        System.out.println("Enter the cell to change: ");
        inputCell = readString();
        theSpreadsheet.getCellToken(inputCell, 0, cellToken);

        // error check to make sure the row and column
        // are within spreadsheet array bounds.
        if ((cellToken.getRow() < 0) ||
            (cellToken.getRow() >= theSpreadsheet.getNumRows()) ||
            (cellToken.getColumn() < 0) ||
            (cellToken.getColumn() >= theSpreadsheet.getNumColumns()) ) {

            System.out.println("Bad cell.");
            return;
        }

        System.out.println("Enter the cell's new formula: ");
        inputFormula = readString();
        expTreeTokenStack = SpreadSheetUtility.getFormula(inputFormula);

        ExpressionTreeNode root = ExpressionTreeNode.GetExpressionTree(expTreeTokenStack);
        // DEBUG CODE
        ExpressionTree.printTree(root);
        System.out.println("");
        System.out.println("The answer should be " + ExpressionTree.evaluate(root, theSpreadsheet));
        String postFix = ""; // Temp Code
        // This code prints out the expression stack from
        // top to bottom (that is, reverse of postfix).
        while (!expTreeTokenStack.isEmpty()) {
            expTreeToken = (Token) expTreeTokenStack.pop();
            printExpressionTreeToken(expTreeToken);

            postFix += expTreeToken + " ";
        }
        System.out.println(postFix);


        theSpreadsheet.changeCellFormulaAndRecalculate(cellToken, ExpressionTree.stringTree(root), inputFormula, root);
        System.out.println();
    }

    public static void GUIChangeCell(int row, int col, String inputFormula) {
        Stack expTreeTokenStack = SpreadSheetUtility.getFormula(inputFormula);
        Token expTreeToken;
        CellToken cellToken = new CellToken();
        cellToken.setRow(row);
        cellToken.setColumn(col);
        ExpressionTreeNode root = ExpressionTreeNode.GetExpressionTree(expTreeTokenStack);
        // DEBUG CODE
        ExpressionTree.printTree(root);
        System.out.println("The answer should be " + ExpressionTree.evaluate(root, theSpreadsheet));
        String postFix = ""; // Temp Code
        // This code prints out the expression stack from
        // top to bottom (that is, reverse of postfix).
        while (!expTreeTokenStack.isEmpty()) {
            expTreeToken = (Token) expTreeTokenStack.pop();
            printExpressionTreeToken(expTreeToken);

            postFix += expTreeToken + " ";
        }
        theSpreadsheet.changeCellFormulaAndRecalculate(cellToken, ExpressionTree.stringTree(root), inputFormula, root);
    }

    /**
     * Prints the expression tree's token out to the console.
     * @param theExpTreeToken The expression tree token to be printed.
     */
    private static void printExpressionTreeToken(Token theExpTreeToken) {
        System.out.println(theExpTreeToken);
    }

    /**
     * The main method of the driver program that controls the console
     * interface.
     * @param args The arguments passed into main.
     */
    public static void main(String[] args) {
        //Spreadsheet theSpreadsheet = new Spreadsheet(8);
        SpreadsheetGUI theGUI = new SpreadsheetGUI(ROWS_AND_COLUMNS, ROWS_AND_COLUMNS);
        theSpreadsheet = new Spreadsheet(ROWS_AND_COLUMNS, theGUI);

        boolean done = false;
        String command = "";

        System.out.println(">>> Welcome to the TCSS 342 Spreadsheet <<<");
        System.out.println();
        System.out.println();

        while (!done) {
            System.out.println("Choose from the following commands:");
            System.out.println();
            System.out.println("p: print out the values in the spreadsheet");
            System.out.println("f: print out a cell's formula");
            System.out.println("a: print all cell formulas");
            System.out.println("c: change the formula of a cell");
    /* BONUS
            System.out.println("r: read in a spreadsheet from a text file");
            System.out.println("s: save the spreadsheet to a text file");
     */
            System.out.println();
            System.out.println("q: quit");

            System.out.println();
            System.out.println("Enter your command: ");
            command = readString();

            // We care only about the first character of the string
            switch (command.charAt(0)) {
                case 'p':
                    menuPrintValues(theSpreadsheet);
                    break;

                case 'f':
                    menuPrintCellFormula(theSpreadsheet);
                    break;

                case 'a':
                    menuPrintAllFormulas(theSpreadsheet);
                    break;

                case 'c':
                    menuChangeCellFormula(theSpreadsheet);
                    break;

                    /* BONUS:
                case 'r':
                    menuReadSpreadsheet(theSpreadsheet);
                    break;

                case 's':
                    menuSaveSpreadsheet(theSpreadsheet);
                    break;
                    */

                case 'q':
                    done = true;
                    break;

                default:
                    System.out.println(command.charAt(0) + ": Bad command.");
                    break;
            }
            System.out.println();

        }

        System.out.println("Thank you for using our spreadsheet.");
    }
}