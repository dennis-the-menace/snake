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
    private Food food;
    private Paint paint = new Paint();
    private static int borderSizeLeftRight;
    private static int borderSizeTopBottom;
    private static int canvasWidth;
    private static int canvasHeight;
    private boolean isFoodEaten = false;
    private boolean doDrawFood = false;

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
        food = new Food(rect);
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
        if(isFoodEaten) {
            food.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            canvas.drawColor(Color.BLACK);
            borderSizeLeftRight = calculateBorderSize(canvas.getWidth());
            borderSizeTopBottom = calculateBorderSize(canvas.getHeight());
            canvasWidth = canvas.getWidth() - borderSizeLeftRight;
            canvasHeight = canvas.getHeight() - borderSizeTopBottom;
            canvas.drawRect(borderSizeLeftRight, borderSizeTopBottom, canvasWidth, canvasHeight, paint);
            if(snake.getX() == food.getX() && snake.getY() == food.getY()) {
                System.out.println("consoleOutput: eat food");
                snake.draw(canvas, borderSizeLeftRight, borderSizeTopBottom);
                isFoodEaten = true;
            } else {
                if(doDrawFood) {
                    food.draw(canvas);
                } else {
                    doDrawFood = true;
                    food.update();
                }
                snake.draw(canvas, borderSizeLeftRight, borderSizeTopBottom);
                isFoodEaten = false;
            }
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

    public static int roundToFifty(int number) {
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



    public static int getCanvasWidth() {
        return canvasWidth;
    }

    public static int getCanvasHeight() {
        return canvasHeight;
    }

    public static int getBorderSizeLeftRight() {
        return borderSizeLeftRight;
    }

    public static int getBorderSizeTopBottom() {
        return borderSizeTopBottom;
    }


}
