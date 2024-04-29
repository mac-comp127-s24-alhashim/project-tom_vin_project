
package Connect4;

public class Player {
    private String name;
    private Token token; 

    public Player(String name, Token token) {
        this.name = name;
        this.token = token; 
    }

    public String getName() {
        return name;
    }

    public Token getToken() {
        return new Token(60, 60, token.getColor());
    }
}
