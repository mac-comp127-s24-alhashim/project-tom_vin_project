package Connect4;

import java.awt.Color;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;

public class Token extends GraphicsGroup {
    private String color;
    private Ellipse token;

    public Token(String color) {
        this.color = color;
        token = new Ellipse(0, 0, 60, 60);
        setColor(color);
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

    public String getColor() {
        return color;
    }

    public void setTokenPosition(double x, double y) {
        this.setPosition(x, y);
    }
}
