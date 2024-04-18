package Connect4;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.*;

public class GameBoard extends GraphicsGroup {
    private Token[][] grid;
    private int rows = 6;
    private int columns = 7;
    private int BOARD_WIDTH = 700;
    private int BOARD_HEIGHT = 600;

    private int cellWidth = BOARD_WIDTH / columns;
    private int cellHeight = BOARD_HEIGHT / rows;

    // draws the gameboard
    public GameBoard() {

        Rectangle rect = new Rectangle(50, 100, BOARD_WIDTH, BOARD_HEIGHT);
        rect.setFillColor(Color.blue);
        this.add(rect);

        grid = new Token[rows][columns];
        drawGrid();

    }
    // draws the holes in the grid
    private void drawGrid(){
        for (int row = 0; row < rows; row++) { // loops through each row and column until row/column is less than rows/columns
            for (int col = 0; col <columns; col++) {
                double x = 50 + col * cellWidth + cellWidth / 2.0 - 30; // sets placement of the grid
                double y = 100 + row * cellHeight + cellHeight / 2.0 - 30;
                Ellipse hole = new Ellipse(x, y, 60, 60); // sets the size of the holes
                hole.setFillColor(Color.WHITE);
                this.add(hole);

            }
        }
        Ellipse gridEllipse = new Ellipse(0,0,100,100);
        gridEllipse.setFillColor(Color.WHITE);
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

    public boolean checkWin(Token token){
        return checkHorizontalWin(token) || checkVerticalWin(token) || checkDiagonalWin(token);
     }

     // Check for horizontal win
     private boolean checkHorizontalWin(Token token) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (grid[row][col] == token && grid[row][col + 1] == token && 
                    grid[row][col + 2] == token && grid[row][col + 3] == token) {
                        return true; // horizontal win met
                    }
            }
        }
        return false;
     }

     // Check for vertical win
     private boolean checkVerticalWin(Token token) {
        for (int col = 0; col < col; col++) {
            for (int row = 0; row < row - 3; row++) {
                if (grid[row][col] == token && grid[row + 1][col] == token &&
                    grid[row + 2][col] == token && grid[row + 3][col] == token) {
                        return true; // vertical win met
                    }
            }
        }
        return false;
     }
     private boolean checkDiagonalWin(Token token) {
        // downwards diagonal
        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (grid[row][col] == token && 
                    grid[row + 1][col + 1] == token &&
                    grid[row + 2][col + 2] == token &&
                    grid[row + 3][col + 3] == token) {
                        return true; // downwards diagonal met
            }
        }
     }
     // upwards diagonal
        for (int row = 3; row < rows; row++) {
            for (int col = 0; col < columns - 3; col++) {
                if (grid[row][col] == token &&
                    grid[row - 1][col + 1] == token &&
                    grid[row - 2][col + 2] == token &&
                    grid[row - 3][col + 3] == token) {
                        return true;
                }
            }
        }
        return false;
    }
    // check to see if board is full
    public boolean isFull() {
        for (int col = 0; col < columns; col++) {
            if (grid[0][col] == null) {
                return false; // if empty space in top row, not full
            }
        }
        return true; // no empty spaces, board full
    }
}
    // Add additional methods for isFull, isEmpty, checkWin, checkTie later