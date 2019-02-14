package ch.games.dennisrittmeyer.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;

public class Food {

    private Paint paint = new Paint();
    private Rectangle rect;
    private int x, y;
    int randomNumber ;

    public Food(Rectangle rect) {
        this.rect = rect;
        paint.setColor(Color.RED);
        generateRandomFoodPos();
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x-50+GameView.getBorderSizeLeftRight(), y-50+GameView.getBorderSizeTopBottom(),x+GameView.getBorderSizeLeftRight(),y+GameView.getBorderSizeTopBottom(), paint);
    }

    public void update() {
        generateRandomFoodPos();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void generateRandomFoodPos() {
        randomNumber = (int)(Math.random() * GameView.getCanvasWidth()-GameView.getBorderSizeLeftRight()) + 50;
        x = GameView.roundToFifty(randomNumber);
        randomNumber = (int)(Math.random() * GameView.getCanvasHeight()-GameView.getBorderSizeTopBottom()) + 50;
        y = GameView.roundToFifty(randomNumber);
    }

}
