package Connect4;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.*;


public class Connect4Game {
    private Player p1;
    private Player p2;
    private GameBoard gameBoard;
    private boolean gameOver = false;
    private Player currentPlayer;
    private CanvasWindow canvas;

    private Token[][] grid;
    private final int ROWS = 6;
    private final int COLUMNS = 7;
    private final int BOARD_WIDTH = 700;
    private final int BOARD_HEIGHT = 600;
    public static int tokens; // I think we need to change the type to Token or sth to be able to do tokens.size()
    private GraphicsText turnText;

    public static void main(String[] args) {
        new Connect4Game();
    }

    public Connect4Game() {
        p1 = new Player("Player 1", new Token("R"));
        p2 = new Player("Player 2", new Token("Y"));
        
        canvas = new CanvasWindow("Connect 4", 800, 800);
        
        gameBoard = new GameBoard();
        canvas.add(gameBoard);

        grid = new Token[ROWS][COLUMNS];

        currentPlayer = p1; // Player 1 starts

        // turnText = new GraphicsText("Player 1's Turn", 10, 30);
        // turnText.setFont(FontStyle.BOLD,20);

        // if (tokens %2 == 0){
        //     canvas.add(turnText);
        // }

        // else {
        //     canvas.remove(turnText);
        //     turnText.setText("Player 2's Turn");
        //     canvas.add(turnText);
        // }

        dropToken();
    }

    public void startGame() {        
        // Game loop implementation will go here.
    }

    public void dropToken() {
        // need to change row and column when we have proper storage
        int row = 0;
        int column = 0;
        canvas.onClick(event -> {
            for (int i = 70; i<=670; i+=100){
                if (event.getPosition().getX() >= i && event.getPosition().getX() <= i+100){
                    int c = i/100; // column
                    int r = 5; // row
                    int yCounter = 620;
                    
                    Token token = new Token("");

                    while (grid[r][c]!=null){
                        r-=1;
                        yCounter-=100;
                    }
                    
                    token.setPosition(i, yCounter);
                    canvas.add(token);
                    grid[r][c] = token;

                    tokens+=1; // delete this later, for now this is what switches the colors
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
