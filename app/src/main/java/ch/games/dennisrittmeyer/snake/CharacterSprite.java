package ch.games.dennisrittmeyer.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;

public class CharacterSprite {

    private Rectangle rect;
    private Paint paint = new Paint();

    public CharacterSprite(Rectangle rect) {
        this.rect = rect;
        paint.setColor(Color.rgb(255,0,0));
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(50, 50,100,100, paint);
    }
}
