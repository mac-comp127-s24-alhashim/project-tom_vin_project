package Connect4;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsObject;
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
    
    private static int lastR; private static int lastC; // lastR = row of last token, lastC = column of last token;
    private static Boolean buttonClicked = false;
    
    private GraphicsText turnText;
    private Token turnToken;

    public static void main(String[] args) {
        new Connect4Game();
    }

    public Connect4Game() {
        canvas = new CanvasWindow("Connect 4", 800, 800);
        setupStartScreen();
    }

    private void setupStartScreen() {
        // start screen text
        GraphicsText titleText = new GraphicsText("Connect 4", 275, 350);
        titleText.setFontSize(50);
        titleText.setFontStyle(FontStyle.BOLD);
        canvas.add(titleText);

        // start screen play button
        Button playButton = new Button("Play Now!");
        playButton.setPosition(350, 375);
        playButton.onClick(() -> startGame());
        canvas.add(playButton);
    }

    private void startGame() {
        canvas.removeAll();

        p1 = new Player("Player 1", new Token(60, 60, "R"));
        p2 = new Player("Player 2", new Token(60, 60, "Y"));
        
        GameBoard gameBoard = new GameBoard();
        canvas.add(gameBoard);

        grid = new Token[ROWS][COLUMNS];
        
        currentPlayer = p1; 
        // text at the top left showing player's turn
        turnText = new GraphicsText(currentPlayer.getName() + "'s Turn", 10, 30);
        turnText.setFontStyle(FontStyle.BOLD);
        canvas.add(turnText);

        // color of token on top left
        turnToken = new Token(20, 20, currentPlayer.getToken().getColor());
        turnToken.setPosition(125, 15);
        canvas.add(turnToken);

        
        undo(); // undo button
        exitGame(); // exit game button

        canvas.onClick(event -> {
            if (gameOver) return;
            int columnClicked = getColumnFromX(event.getPosition().getX());
            if (columnClicked != -1) {
                dropToken(columnClicked);
            }
        });
    }

    private int getColumnFromX(double x) {
        int leftMargin = 50;
        int columnWidth = 100;
        if (x < leftMargin || x > leftMargin + COLUMNS * columnWidth) return -1;
        return (int) ((x - leftMargin) / columnWidth);
    }

    private void dropToken(int column) {
        // drop token after onclick
        
        int row = findEmptyRow(column);
        if (row != -1) {
            token = new Token(60, 60, currentPlayer.getToken().getColor());
            grid[row][column] = token;
            lastR = row; lastC = column;
            canvas.add(token);
            buttonClicked = false;

            // ToDo: get the token behind the board

            
            // starting drop position
            token.setPosition(70 + column * 100, 20);
            
            // token falling speed
            while (token.getY()<120 + row * 100){
                token.moveBy(0, 10);
                canvas.pause(0.8);
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

    // private boolean checkVerticalWin() {
    //     // Vertical direction
    //     for (int col = 0; col < COLUMNS; col++) {
    //         for (int row = 0; row < ROWS - 3; row++) {
    //             if (grid[row][col] != null && grid[row + 1][col] != null && grid[row + 2][col] != null && grid[row + 3][col] != null &&
    //                 grid[row][col].getColor().equals(grid[row + 1][col].getColor()) &&
    //                 grid[row][col].getColor().equals(grid[row + 2][col].getColor()) &&
    //                 grid[row][col].getColor().equals(grid[row + 3][col].getColor())) {
    //                     return true;
    //             }
    //         }
    //     }
    //     return false;
    // }

    // updated checkWin, hopefully faster
    private boolean checkVerticalWin() {
        int vCount = 0;  // Count consecutive tokens of the same color
    
        for (int row = 0; row < ROWS; row++) {
            if (grid[row][lastC] != null && grid[row][lastC].getColor().equals(token.getColor())) {
                vCount++;
                if (vCount == 4) {
                    return true; 
                }
            }
            else vCount = 0; 
        }
    
        return false;
    }
    

    // private boolean checkHorizontalWin() {
    //     // Horizontal direction
    //     for (int row = 0; row < ROWS; row++) {
    //         for (int col = 0; col < COLUMNS - 3; col++) {
    //             if (grid[row][col] != null && grid[row][col + 1] != null && grid[row][col + 2] != null && grid[row][col + 3] != null &&
    //                 grid[row][col].getColor().equals(grid[row][col + 1].getColor()) &&
    //                 grid[row][col].getColor().equals(grid[row][col + 2].getColor()) &&
    //                 grid[row][col].getColor().equals(grid[row][col + 3].getColor())) {
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }

    private boolean checkHorizontalWin() {
        int hCount = 0;  // Count consecutive tokens of the same color
    
        for (int col = 0; col < COLUMNS; col++) {
            if (grid[lastR][col] != null && grid[lastR][col].getColor().equals(token.getColor())) {
                hCount++;
                if (hCount == 4) {
                    return true;
                }
            }
            else hCount = 0;
        }
        return false;
    }

    // private boolean checkDiagonalWin() {
    //     // Downward diagonal (\ direction)
    //     for (int row = 0; row < ROWS - 3; row++) {
    //         for (int col = 0; col < COLUMNS - 3; col++) {
    //             if (grid[row][col] != null && grid[row + 1][col + 1] != null && grid[row + 2][col + 2] != null && grid[row + 3][col + 3] != null &&
    //                 grid[row][col].getColor().equals(grid[row + 1][col + 1].getColor()) &&
    //                 grid[row][col].getColor().equals(grid[row + 2][col + 2].getColor()) &&
    //                 grid[row][col].getColor().equals(grid[row + 3][col + 3].getColor())) {
    //                 return true;
    //             }
    //         }
    //     }
    //     // Upward diagonal (/ direction)
    //     for (int row = 3; row < ROWS; row++) {
    //         for (int col = 0; col < COLUMNS - 3; col++) {
    //             if (grid[row][col] != null && grid[row - 1][col + 1] != null && grid[row - 2][col + 2] != null && grid[row - 3][col + 3] != null &&
    //                 grid[row][col].getColor().equals(grid[row - 1][col + 1].getColor()) &&
    //                 grid[row][col].getColor().equals(grid[row - 2][col + 2].getColor()) &&
    //                 grid[row][col].getColor().equals(grid[row - 3][col + 3].getColor())) {
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    // }


    // for i < 3; i ++;
    // find out where lastR + 1, lastC + 1 = lastR, lastC; then do lastR+2, lastC+2


    private boolean checkDiagonalWin() {

        // Check down-right diagonal (\ direction)
        int row = lastR;
        int col = lastC;
        int dCount = 0;
    
        // Move upward-left to find the start of the downward diagonal
        while (row >= 0 && col >= 0) {
            if (grid[row][col] != null && grid[row][col].getColor().equals(token.getColor())) {
                row--;
                col--;
            }
            else break;
        }
    
        // Count down-right from the start point
        row++;
        col++;
        while (row < ROWS && col < COLUMNS) {
            if (grid[row][col] != null && grid[row][col].getColor().equals(token.getColor())) {
                dCount++;
                if (dCount == 4) {
                    return true;  // Four in a row found
                }
                row++;
                col++;
            }
            else break;
        }
    
        // Check up-right diagonal (/ direction)
        row = lastR;
        col = lastC;
        dCount = 0;
    
        // Move down-left to find the start of the upward diagonal
        while (row < ROWS && col >= 0) {
            if (grid[row][col] != null && grid[row][col].getColor().equals(token.getColor())) {
                row++;
                col--;
            }
            else break;
        }
    
        // Count up-right along the diagonal
        row--;
        col++;
        while (row >= 0 && col < COLUMNS) {
            if (grid[row][col] != null && grid[row][col].getColor().equals(token.getColor())) {
                dCount++;
                if (dCount == 4) {
                    return true;  // Four in a row found
                }
                row--;
                col++;
            } else {
                break;
            }
        }
    
        return false;
    }

    // private Boolean checkDiagonalWin(){
    //     // Up-right diagonal (/)
    //     int dCount = 0;
    //     for (int i = 0; i < 4; i++){
    //         if (lastR-i >= 0 && lastC+i <= 6 && grid[lastR-i][lastC+i]!=null && grid[lastR-i][lastC+i].getColor().equals(grid[lastR][lastC].getColor())){
    //             dCount+=1;
    //             if (dCount==4){
    //                 return true;
    //             }
    //         }
    //         else dCount = 0;
    //     }
    //     return false;
    // }
    

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
        turnToken.setColor(currentPlayer.getToken().getColor());
    }

    public void undo(){
        Button undoButton = new Button("Undo");
        
        undoButton.setPosition(610, 15);

        undoButton.onClick(() -> {
            if (buttonClicked == true || gameOver == true){
                return;
            }
            canvas.remove(this.token);
            grid[lastR][lastC] = null;
            switchPlayer();
            buttonClicked = true;
        });
        canvas.add(undoButton);
    }

    public void exitGame(){
        Button exitButton = new Button("Exit");
        exitButton.setPosition(680,15);
        exitButton.onClick(() -> {
            canvas.closeWindow();
        });
        canvas.add(exitButton);
    }

    public void endGame(String message) {
        gameOver = true;
        turnText.setText(message);
        turnToken.setPosition(120,15);
        System.out.println("Game ended: " + message);

        Button restartButton = new Button("Restart?");
        restartButton.setPosition(5, 40);
        canvas.add(restartButton);
        restartButton.onClick(() -> {
            canvas.closeWindow();
            new Connect4Game();
        });
    }
}