package Connect4;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.*;

public class GameBoard extends GraphicsGroup {
    public Token[][] grid;

    private int ROWS = 6;
    private final int COLUMNS = 7;
    private final int BOARD_WIDTH = 700;
    private final int BOARD_HEIGHT = 600;

    private int cellWidth = BOARD_WIDTH / COLUMNS;
    private int cellHeight = BOARD_HEIGHT / ROWS;

    // draws the gameboard
    public GameBoard() {

        Rectangle rect = new Rectangle(50, 100, BOARD_WIDTH, BOARD_HEIGHT);
        rect.setFillColor(Color.blue);
        this.add(rect);

        grid = new Token[ROWS][COLUMNS];
        
        drawGrid();

    }

    // draws the holes in the grid
    private void drawGrid() {
        for (int row = 0; row < ROWS; row++) { // loops through each row and column until row/column is less than
                                               // rows/columns
            for (int col = 0; col < COLUMNS; col++) {
                double x = 50 + col * cellWidth + cellWidth / 2.0 - 30; // sets placement of the grid
                double y = 100 + row * cellHeight + cellHeight / 2.0 - 30;
                Ellipse hole = new Ellipse(x, y, 60, 60); // sets the size of the holes
                hole.setFillColor(Color.WHITE);
                this.add(hole);
                // grid[row][col] = null;

                System.out.println(hole.getPosition());
            }
        }
        // Ellipse gridEllipse = new Ellipse(0, 0, 100, 100);
        // gridEllipse.setFillColor(Color.WHITE);
    }
}
