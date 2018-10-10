package ch.games.dennisrittmeyer.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;

public class CharacterSprite {
    private Paint paint = new Paint();
    private Rectangle rect;
    private int x, y;

    public CharacterSprite(Rectangle rect) {
        this.rect = rect;
        paint.setColor(Color.rgb(255,0,0));
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x-50, y-50,x,y, paint);
    }

    public void update() {
        y = y + 50;
    }
}
