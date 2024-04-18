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
    private int cellSize = BOARD_HEIGHT / rows;


    public GameBoard() {

        Rectangle rect = new Rectangle(50, 100, BOARD_WIDTH, BOARD_HEIGHT);
        rect.setFillColor(Color.blue);
        this.add(rect);

        grid = new Token[rows][columns];

    }

    private void drawGrid(){
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

    // public boolean checkWin(Token token){ }
    // Add additional methods for isFull, isEmpty, checkWin, checkTie later.
}