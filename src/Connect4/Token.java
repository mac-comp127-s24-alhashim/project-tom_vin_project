package Connect4;

import java.awt.Color;
import java.awt.Graphics;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;

public class Token extends GraphicsGroup {
    private String color;
    private Ellipse token;

    public Token(String color) {

        Ellipse token = new Ellipse(0, 0, 60, 60);
        
        System.out.println(Connect4Game.tokens%2);
        if (Connect4Game.tokens %2 == 0){
            color = "R";
        } else color ="Y";

        if (color == "R"){
            token.setFillColor(Color.RED);
        }
        else if (color == "Y"){
            token.setFillColor(Color.YELLOW);
        }
        
        this.add(token);
    }

    public void setColor(String color) {
        this.color = color;
        if ("R".equals(color)) {
            token.setFillColor(Color.RED);
        } else if ("Y".equals(color)) {
            token.setFillColor(Color.YELLOW);
        } else {
            token.setFillColor(Color.GRAY); 
        }
    }

    public Paint getColor() {
        return token.getFillColor();
    }

    public void setTokenPosition(double x, double y) {
        this.setPosition(x, y);
    }
}
