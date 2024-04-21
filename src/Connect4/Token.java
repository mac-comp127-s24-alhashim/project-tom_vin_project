package Connect4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

import java.awt.Color;


public class Token extends GraphicsGroup {
    private String color;
    private Ellipse token;

    public Token(String color) {

        Ellipse token = new Ellipse(0, 0, 60, 60);
        
        if (Connect4Game.tokens %2 == 0){
            color = "R";
        } else color ="Y";

        if (color == "R"){
            token.setFillColor(Color.RED);
        }
        else if (color == "Y"){
            token.setFillColor(Color.YELLOW);
            System.out.println(token.getFillColor());
        }
        
        this.add(token);
    }

    public void setColor(String color) {
        // maybe for player to set the token color / p1 = "R", p2 = "Y"
        if (color == "R"){
            token.setFillColor(Color.RED);
        }
        else if (color == "Y"){
            token.setFillColor(Color.YELLOW);
        }
    }

    public Paint getColor() {
        return token.getFillColor();
    }
}
