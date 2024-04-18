package Connect4;

import java.awt.Color;
import java.awt.Graphics;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

import java.awt.Color;


public class Token extends GraphicsGroup {
    private String color;
    private Ellipse token;

    public Token(String color) {

        Ellipse token = new Ellipse(0, 0, 20, 20);
        token.setFillColor(Color.RED);
        this.add(token);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
