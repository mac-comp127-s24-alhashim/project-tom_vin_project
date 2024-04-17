package Connect4;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

import java.awt.Color;


public class Token {
    private String color;

    public Token(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
