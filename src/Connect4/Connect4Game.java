package Connect4;

public class Connect4Game {
    private Player p1;
    private Player p2;
    private GameBoard gameBoard;
    private boolean gameOver;
    private Player currentPlayer;

    public Connect4Game(int rows, int columns) {
        p1 = new Player("Player 1", new Token("R"));
        p2 = new Player("Player 2", new Token("Y"));
        gameBoard = new GameBoard(rows, columns);
        currentPlayer = p1; // Player 1 starts
        gameOver = false;
    }

    public void startGame() {
        // Game loop implementation will go here.
    }

    public boolean makeMove(int)
    // Methods for makeMove, changeTurn, checkWin, checkTie will be implemented here.
}
