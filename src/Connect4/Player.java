package Connect4;

public class Player {
    private String name;
    private Token tokenTest; 

    public Player(String name, Token token) {
        this.name = name;
        this.tokenTest = token; 
    }

    public String getName() {
        return name;
    }

    public Token getToken() {
        return new Token(tokenTest.getColor());
    }
}
