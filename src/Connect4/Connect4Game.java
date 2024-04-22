package Connect4;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.*;
import edu.macalester.graphics.ui.Button;


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

        currentPlayer = p1; 
        turnText = new GraphicsText(currentPlayer.getName() + " 's Turn", 10, 30);
        turnText.setFontStyle(FontStyle.BOLD);
        canvas.add(turnText);
        
        Button testButton = new Button("Switch Player");
        testButton.onClick(() -> switchPlayer());
        testButton.setPosition(125, 10);
        canvas.add(testButton);

        dropToken();
    }
    

    public void dropToken() {
        canvas.onClick(event -> {
            if (gameOver) return; 
    
            for (int i = 70; i <= 670; i += 100) {
                if (event.getPosition().getX() >= i && event.getPosition().getX() < i + 100) {
                    int c = (int) ((event.getPosition().getX() - 50) / 100);
                    int r = ROWS - 1;
    
                    while (r >= 0 && grid[r][c] != null) {
                        r--;
                    }
    
                    if (r >= 0) {
                        Token token = new Token(currentPlayer.getToken().getColor());
                        token.setTokenPosition(50 + c * 100 + 20, 100 + r * 100 + 20);
                        canvas.add(token);
                        grid[r][c] = token;
    
                        if (checkWin()) {
                            endGame(currentPlayer.getName() + " wins!");
                        } else if (checkTie()) {
                            endGame("It's a tie!");
                        } else {
                            switchPlayer();
                        


                        // this one correctly gets the check win but doesnt correctly change turns
                        // if (checkWin()) {
                        //     endGame(currentPlayer.getName() + " wins!");
                        // } else if (checkTie()) {
                        //     endGame("It's a tie!");
                        // } else {
                        // switchPlayer();
                        }
                    }
                }
            }
        });
    }

    
    public void switchPlayer() {
        currentPlayer = (currentPlayer == p1) ? p2 : p1;
        turnText.setText(currentPlayer.getName() + "'s Turn");
        canvas.draw(); 
    }
    
    
    

    private boolean checkVerticalWin() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (grid[row][col] != null &&
                    grid[row][col].getColor().equals(grid[row + 1][col].getColor()) &&
                    grid[row][col].getColor().equals(grid[row + 2][col].getColor()) &&
                    grid[row][col].getColor().equals(grid[row + 3][col].getColor())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontalWin() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid[row][col] != null &&
                    grid[row][col].getColor().equals(grid[row][col + 1].getColor()) &&
                    grid[row][col].getColor().equals(grid[row][col + 2].getColor()) &&
                    grid[row][col].getColor().equals(grid[row][col + 3].getColor())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        // Check for downwrads diagonal win
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid[row][col] != null &&
                    grid[row][col].getColor().equals(grid[row + 1][col + 1].getColor()) &&
                    grid[row][col].getColor().equals(grid[row + 2][col + 2].getColor()) &&
                    grid[row][col].getColor().equals(grid[row + 3][col + 3].getColor())) {
                    return true;
                }
            }
        }
        // Check for upwards diagonal win
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid[row][col] != null &&
                    grid[row][col].getColor().equals(grid[row - 1][col + 1].getColor()) &&
                    grid[row][col].getColor().equals(grid[row - 2][col + 2].getColor()) &&
                    grid[row][col].getColor().equals(grid[row - 3][col + 3].getColor())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (grid[row][col] == null) {
                    return false; 
                }
            }
        }
        return true; 
    }

    private boolean checkWin() {
        return checkVerticalWin() || checkHorizontalWin() || checkDiagonalWin();
    }

    public void endGame(String message) {
        gameOver = true;
        turnText.setText(message);
        System.out.println("Game ended: " + message);
    }
    
}