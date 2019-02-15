package ch.games.dennisrittmeyer.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class CharacterSprite extends MyGestureListener {
    private Paint paint = new Paint();
    private Rectangle rect;
    private List<SnakeRectangle> rectPosList = new ArrayList<>();

    public CharacterSprite(Rectangle rect) {
        this.rect = rect;
        paint.setColor(Color.DKGRAY);
        rectPosList.add(new SnakeRectangle(500, 500));
//        rectPosList.add(new SnakeRectangle(450, 500));
//        rectPosList.add(new SnakeRectangle(400, 500));
//        rectPosList.add(new SnakeRectangle(400, 450));
    }

    public void draw(Canvas canvas, int widthBorder, int heightBorder) {
        for (SnakeRectangle rect : rectPosList) {
            int x = rect.getX();
            int y = rect.getY();
            canvas.drawRect(x-50+widthBorder, y-50+heightBorder,x+widthBorder,y+heightBorder, paint);
        }
    }

    public void update() {
        List<SnakeRectangle> oldPositioningList = duplicateList(rectPosList);

        if(MyGestureListener.getDirection() == Direction.down) {
            int oldY = rectPosList.get(0).getY();
            rectPosList.get(0).setY(oldY + 50);
            int counter = 1;
            for (SnakeRectangle rect : oldPositioningList) {
                if(oldPositioningList.size() > counter) {
                    rectPosList.set(counter, rect);
                }
                counter++;
            }
        } else if(MyGestureListener.getDirection() == Direction.up) {
            int oldY = rectPosList.get(0).getY();
            rectPosList.get(0).setY(oldY - 50);
            int counter = 1;
            for (SnakeRectangle rect : oldPositioningList) {
                if(oldPositioningList.size() > counter) {
                    rectPosList.set(counter, rect);
                }
                counter++;
            }
        } else if(MyGestureListener.getDirection() == Direction.right) {
            int oldX = rectPosList.get(0).getX();
            rectPosList.get(0).setX(oldX + 50);
            int counter = 1;
            for (SnakeRectangle rect : oldPositioningList) {
                if(oldPositioningList.size() > counter) {
                    rectPosList.set(counter, rect);
                }
                counter++;
            }
        } else {
            int oldX = rectPosList.get(0).getX();
            rectPosList.get(0).setX(oldX - 50);
            int counter = 1;
            for (SnakeRectangle rect : oldPositioningList) {
                if(oldPositioningList.size() > counter) {
                    rectPosList.set(counter, rect);
                }
                counter++;
            }
        }
    }

    public void grow(Food food) {
        SnakeRectangle lastRect = rectPosList.get(rectPosList.size()-1);
        if(rectPosList.size() == 1) {
            if(MyGestureListener.getDirection() == Direction.right) {
                    SnakeRectangle newRect = new SnakeRectangle(lastRect.getX()-50, lastRect.getY());
                    rectPosList.add(newRect);

            } else if(MyGestureListener.getDirection() == Direction.left) {
                SnakeRectangle newRect = new SnakeRectangle(lastRect.getX()+50, lastRect.getY());
                rectPosList.add(newRect);
            } else if(MyGestureListener.getDirection() == Direction.down) {
                SnakeRectangle newRect = new SnakeRectangle(lastRect.getX(), lastRect.getY()-50);
                rectPosList.add(newRect);
            } else if(MyGestureListener.getDirection() == Direction.up) {
                SnakeRectangle newRect = new SnakeRectangle(lastRect.getX(), lastRect.getY()+50);
                rectPosList.add(newRect);
            }
        } else {
            SnakeRectangle secondLastRect = rectPosList.get(rectPosList.size()-2);
            if(lastRect.getX() == secondLastRect.getX()) {
                SnakeRectangle newRect = new SnakeRectangle(lastRect.getX(), lastRect.getY()-50);
                rectPosList.add(newRect);
            } else if(lastRect.getY() == secondLastRect.getY()) {
                SnakeRectangle newRect = new SnakeRectangle(lastRect.getX()-50, lastRect.getY());
                rectPosList.add(newRect);
            }
        }
    }

    public List<SnakeRectangle> getRectPosList() {
        return rectPosList;
    }

    public void setRectPosList(List<SnakeRectangle> rectPosList) {
        this.rectPosList = rectPosList;
    }

    public List<SnakeRectangle> duplicateList(List<SnakeRectangle> list) {
        List<SnakeRectangle> duplicatedList = new ArrayList<>();
        // I have to set each value for its own, to avoid a reference of the two lists.
        for(SnakeRectangle rect : list) {
            SnakeRectangle newRect = new SnakeRectangle(0, 0);
            newRect.setX(rect.getX());
            newRect.setY(rect.getY());
            duplicatedList.add(newRect);
        }
        return duplicatedList;
    }

}
