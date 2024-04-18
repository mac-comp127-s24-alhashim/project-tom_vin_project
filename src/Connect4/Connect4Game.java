package Connect4;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.*;


public class Connect4Game {
    private Player p1;
    private Player p2;
    private GameBoard gameBoard;
    private boolean gameOver;
    private Player currentPlayer;
    private CanvasWindow canvas;
    private Token token1;

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
        canvas.add(token1);

        currentPlayer = p1; // Player 1 starts
        gameOver = false;
    }

    public void startGame() {
        // Game loop implementation will go here.
    }

    // Methods for makeMove, changeTurn, checkWin, checkTie will be implemented here.
}
