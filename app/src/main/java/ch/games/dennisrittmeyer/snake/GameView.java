package ch.games.dennisrittmeyer.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Rectangle rect;
    private CharacterSprite snake;

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
            canvas.drawColor(Color.WHITE);
            snake.draw(canvas);
        }

    }

}
