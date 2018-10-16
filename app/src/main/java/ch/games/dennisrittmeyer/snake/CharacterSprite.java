package ch.games.dennisrittmeyer.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;

public class CharacterSprite extends MyGestureListener {
    private Paint paint = new Paint();
    private Rectangle rect;
    private int x, y;

    public CharacterSprite(Rectangle rect) {
        this.rect = rect;
        paint.setColor(Color.rgb(0,0,0));
        x = 500;
        y = 500;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x-50, y-50,x,y, paint);
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
}
