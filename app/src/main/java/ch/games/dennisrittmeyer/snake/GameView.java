package ch.games.dennisrittmeyer.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
        paint.setColor(Color.WHITE);

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
            int x1 = calculateBorderSize(canvas.getWidth());
            int y1 = calculateBorderSize(canvas.getHeight());
            int x2 = canvas.getWidth() - x1;
            int y2 = canvas.getHeight() - y1;
            canvas.drawRect(x1, y1, x2, y2, paint);
            snake.draw(canvas, x1, y1);
        }

    }

    private int calculateBorderSize(int canvasSize) {
        if(canvasSize % 50 != 0) {
           int borderSize = (canvasSize - roundToFifty(canvasSize)) / 2;
           return borderSize;
        } else {
            return 0;
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
