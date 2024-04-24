package Connect4;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;

public class Connect4Game {
    private Player p1;
    private Player p2;
    private boolean gameOver = false;
    private Player currentPlayer;
    private CanvasWindow canvas;

    private Token[][] grid;
    private Token token;
    private final int ROWS = 6;
    private final int COLUMNS = 7;
    private GraphicsText turnText;

    public static void main(String[] args) {
        new Connect4Game();
    }

    public Connect4Game() {
        canvas = new CanvasWindow("Connect 4", 800, 800);
        setupStartScreen();
    }

    private void setupStartScreen() {
        GraphicsText titleText = new GraphicsText("Connect 4", 275, 350);
        titleText.setFontSize(50);
        titleText.setFontStyle(FontStyle.BOLD);
        canvas.add(titleText);

        Button playButton = new Button("Play Now!");
        playButton.setPosition(350, 375);
        playButton.onClick(() -> startGame());
        canvas.add(playButton);
    }

    private void startGame() {
        canvas.removeAll();

        p1 = new Player("Player 1", new Token("R"));
        p2 = new Player("Player 2", new Token("Y"));
        
        canvas = new CanvasWindow("Connect 4", 800, 800);
        GameBoard gameBoard = new GameBoard();
        canvas.add(gameBoard);

        grid = new Token[ROWS][COLUMNS];
        currentPlayer = p1; 
        turnText = new GraphicsText(currentPlayer.getName() + " 's Turn", 10, 30);
        turnText.setFontStyle(FontStyle.BOLD);
        canvas.add(turnText);

        canvas.onClick(event -> {
            if (gameOver) return;
            int columnClicked = getColumnFromX(event.getPosition().getX());
            if (columnClicked != -1) {
                dropToken(columnClicked);
            }
        });

        // setupGame();
    }
    
    private void setupGame() {
        // should be in the constructor
    }

    private int getColumnFromX(double x) {
        int leftMargin = 50;
        int columnWidth = 100;
        if (x < leftMargin || x > leftMargin + COLUMNS * columnWidth) return -1;
        return (int) ((x - leftMargin) / columnWidth);
    }

    private void dropToken(int column) {
        int row = findEmptyRow(column);
        if (row != -1) {
            token = new Token(currentPlayer.getToken().getColor());
            grid[row][column] = token;
            canvas.add(token);
            // token.setTokenPosition(70 + column * 100, 100 + row * 100 + 20);
            // grid[row][column] = token;

            // token.setPosition(70 + column * 100, 120 + row * 100);
            token.setPosition(70 + column * 100, 20);
            
            while (token.getY()<120 + row * 100){
                // token.
                token.moveBy(0, 10);
                canvas.pause(1);
                canvas.draw();
            }
            

            if (checkWin()) {
                endGame(currentPlayer.getName() + " wins!");
            } else if (checkTie()) {
                endGame("It's a tie!");
            } else {
                switchPlayer();
            }
        }
    }

    private int findEmptyRow(int column) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][column] == null) {
                return row;
            }
        }
        return -1; 
    }

    private boolean checkVerticalWin() {
        // Vertical direction
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (grid[row][col] != null && grid[row + 1][col] != null && grid[row + 2][col] != null && grid[row + 3][col] != null &&
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
        // Horizontal direction
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid[row][col] != null && grid[row][col + 1] != null && grid[row][col + 2] != null && grid[row][col + 3] != null &&
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
        // Downward diagonal (\ direction)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid[row][col] != null && grid[row + 1][col + 1] != null && grid[row + 2][col + 2] != null && grid[row + 3][col + 3] != null &&
                    grid[row][col].getColor().equals(grid[row + 1][col + 1].getColor()) &&
                    grid[row][col].getColor().equals(grid[row + 2][col + 2].getColor()) &&
                    grid[row][col].getColor().equals(grid[row + 3][col + 3].getColor())) {
                    return true;
                }
            }
        }
        // Upward diagonal (/ direction)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid[row][col] != null && grid[row - 1][col + 1] != null && grid[row - 2][col + 2] != null && grid[row - 3][col + 3] != null &&
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

    public void switchPlayer() {
        currentPlayer = (currentPlayer == p1) ? p2 : p1;
        turnText.setText(currentPlayer.getName() + "'s Turn");
    }

    public void undo(){
        // few things we need to do for this: button
        // remove token (now that's instance var we can, it's just the one that's still selected)
        // update grid[][] back
        // change to back to the other player
    }

    public void endGame(String message) {
        gameOver = true;
        turnText.setText(message);
        System.out.println("Game ended: " + message);
    }
}