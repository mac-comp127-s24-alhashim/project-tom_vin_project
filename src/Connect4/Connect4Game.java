package Connect4;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.*;


public class Connect4Game {
    private Player p1;
    private Player p2;
    private GameBoard gameBoard;
    private boolean gameOver = false;
    private Player currentPlayer;
    private CanvasWindow canvas;
    private Token token1;

    private Token[][] grid;
    private int ROWS = 6;
    private int COLUMNS = 7;
    private int BOARD_WIDTH = 700;
    private int BOARD_HEIGHT = 600;

    public static void main(String[] args) {
        new Connect4Game();
    }

    public Connect4Game() {
        p1 = new Player("Player 1", new Token("R"));
        p2 = new Player("Player 2", new Token("Y"));
        
        canvas = new CanvasWindow("Connect 4", 800, 800);
        
        gameBoard = new GameBoard();
        canvas.add(gameBoard);

        token1 = new Token("Y");
        token1.setPosition(70,620);

        canvas.add(token1);

        currentPlayer = p1; // Player 1 starts

        dropToken();
    }

    public void startGame() {        
        // Game loop implementation will go here.
    }

    public void dropToken() {
        canvas.onClick(event -> {
            for (int i = 70; i<=670; i+=100){
                if (event.getPosition().getX() >= i && event.getPosition().getX() <= i+100){
                    Token token = new Token("Y");
                    token.setPosition(i, 620);
                    canvas.add(token);
                }
            }
        });
    }

    // Methods for makeMove, changeTurn, checkWin, checkTie will be implemented here.


    public boolean playMove(int column, Token token) {
        if (column < 0 || column >= COLUMNS) {
            return false; // Invalid column
        }
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][column] == null) {
                grid[row][column] = token;
                return true;
            }
        }
        return false; // Column is full
    }

    public boolean checkWin(Token token) {
        return checkHorizontalWin(token) || checkVerticalWin(token) || checkDiagonalWin(token);
    }

    // Check for horizontal win
    private boolean checkHorizontalWin(Token token) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
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
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid[row][col] == token &&
                    grid[row + 1][col + 1] == token &&
                    grid[row + 2][col + 2] == token &&
                    grid[row + 3][col + 3] == token) {
                    return true; // downwards diagonal met
                }
            }
        }
        // upwards diagonal
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
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
        for (int col = 0; col < COLUMNS; col++) {
            if (grid[0][col] == null) {
                return false; // if empty space in top row, not full
            }
        }
        return true; // no empty spaces, board full
    }
}
// Add additional methods for isFull, isEmpty, checkWin, checkTie later
