package ch.games.dennisrittmeyer.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;

public class CharacterSprite extends MyGestureListener {
    private Paint paint = new Paint();
    private Rectangle rect;
    private int x;



    private int y;

    public CharacterSprite(Rectangle rect) {
        this.rect = rect;
        paint.setColor(Color.DKGRAY);
        x = 500;
        y = 500;
    }

    public void draw(Canvas canvas, int widthBorder, int heightBorder) {
        canvas.drawRect(x-50+widthBorder, y-50+heightBorder,x+widthBorder,y+heightBorder, paint);
    }

    public void update() {
        if(MyGestureListener.getDirection() == Direction.down) {
            y = y + 50;
        } else if(MyGestureListener.getDirection() == Direction.up) {
            y = y - 50;
        } else if(MyGestureListener.getDirection() == Direction.right) {
            x = x + 50;
        } else {
            x = x - 50;
        }
    }

    public void grow() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
