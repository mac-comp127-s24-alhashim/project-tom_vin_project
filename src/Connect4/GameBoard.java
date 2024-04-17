package Connect4;

public class GameBoard {
    private Token[][] grid;
    private int rows;
    private int columns;

    public GameBoard(int rows, int columns) {
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

    public boolean checkWin(Token token
    }


    asdipasdaks;d

    // Add additional methods for isFull, isEmpty, checkWin, checkTie later.
}