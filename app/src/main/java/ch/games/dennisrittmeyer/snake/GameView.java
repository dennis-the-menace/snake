package ch.games.dennisrittmeyer.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.content.ContentValues.TAG;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Rectangle rect;
    private Rectangle playGround;
    private CharacterSprite snake;
    private Paint paint = new Paint();

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        snake = new CharacterSprite(rect);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        snake.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);
            canvas.drawRect(50, 50, roundToFifty(canvas.getWidth()), roundToFifty(canvas.getHeight()), paint);
            Log.d(TAG,canvas.getWidth() + "/ hei: " + canvas.getHeight());
            snake.draw(canvas);
        }

    }

    private int roundToFifty(int number) {
        if(number % 50 != 0) {
            int roundedToHundred = number / 100 * 100;
            int diff = number - roundedToHundred;
            if(diff > 50) {
                return number - (diff - 50);
            } else {
                return number - diff;
            }
        } else {
            return number;
        }
    }

}
