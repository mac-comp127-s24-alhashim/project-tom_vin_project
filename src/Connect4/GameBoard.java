package Connect4;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.*;

public class GameBoard extends GraphicsGroup {
    private Token[][] grid;
    private int rows;
    private int columns;

    public GameBoard(int rows, int columns) {
        Ellipse ellipse = new Ellipse(0, 0, 500, 500);
        ellipse.setFillColor(Color.BLACK);
        this.add(ellipse);

        
        
        this.rows = rows;
        this.columns = columns;
        grid = new Token[rows][columns];
    }

    public boolean playMove(int column, Token token) {
        if (column < 0 || column >= columns) {
            return false; // Invalid column
        }
        for (int row = rows - 1; row >= 0; row--) {
            if (grid[row][column] == null) {
                grid[row][column] = token;
                return true;
            }
        }
        return false; // Column is full
    }

    // public boolean checkWin(Token token){ }
    // Add additional methods for isFull, isEmpty, checkWin, checkTie later.
}