package Connect4;

import java.awt.Color;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class GameBoard extends GraphicsGroup {
    private final int ROWS = 6;
    private final int COLUMNS = 7;
    private final int BOARD_WIDTH = 700;
    private final int BOARD_HEIGHT = 600;

    public GameBoard() {
        Rectangle rect = new Rectangle(50, 100, BOARD_WIDTH, BOARD_HEIGHT);
        rect.setFillColor(Color.BLUE);
        this.add(rect);
        drawGrid();
    }

    private void drawGrid() {
        int cellWidth = BOARD_WIDTH / COLUMNS;
        int cellHeight = BOARD_HEIGHT / ROWS;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                double x = 50 + col * cellWidth + cellWidth / 2.0 - 30;
                double y = 100 + row * cellHeight + cellHeight / 2.0 - 30;
                Ellipse hole = new Ellipse(x, y, 60, 60);
                hole.setFillColor(Color.WHITE);
                this.add(hole);
            }
        }
    }
}
