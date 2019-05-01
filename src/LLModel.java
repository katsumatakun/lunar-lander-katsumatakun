import java.awt.*;
import java.util.ArrayList;

public class LLModel {

    private MovingDot md;
    private int safeZone;
    private ArrayList<Mountain> mountains;
    private int energy;
    private ArrayList<MoveListener> moveListeners;
    private ArrayList<FinishListener> finishListeners;
    private boolean state;
    private Dimension display;

    public LLModel() {

        int x = (int) (Math.random() * 20) + 10;
        int y = (int) (Math.random() * 30) + 100;
        md = new MovingDot(new Point(250, 30), new Point(x, y), 3);
        safeZone = (int) (Math.random() * 400);
        mountains = new ArrayList<>();
        generateMountains();
        energy = 15;
        state = true;
        moveListeners = new ArrayList<>();
        finishListeners = new ArrayList<>();
        display = new Dimension(500, 500);
    }

    public ArrayList<Mountain> getMountains() {
        return mountains;
    }

    public void generateMountains() {
        int numLeft = safeZone / 50;
        int numRight = (500 - (safeZone + 100)) / 50;
        int leftCorner = 0;
        int rightCorner = 100;
        for (int i = 0; i < numLeft + 1; i++) {
            int height = (int) (Math.random() * 100) + 250;
            if (rightCorner <= safeZone) {
                mountains.add(new Mountain(new Point(leftCorner, 500), new Point((leftCorner + rightCorner) / 2, height), new Point(rightCorner, 500)));
                leftCorner += 50;
                rightCorner += 50;
            } else {
                rightCorner = safeZone;
                leftCorner = rightCorner - 100;
            }

        }
        leftCorner = safeZone + 100;
        rightCorner = leftCorner + 100;
        for (int i = 0; i <= numRight; i++) {
            int height = (int) (Math.random() * 100) + 250;
            if (rightCorner <= 500) {
                mountains.add(new Mountain(new Point(leftCorner, 500), new Point((leftCorner + rightCorner) / 2, height), new Point(rightCorner, 500)));
                leftCorner += 50;
                rightCorner += 50;
            } else {
                rightCorner = 500;
                leftCorner = rightCorner - 100;
            }

        }
    }

    public void AddFinishListener(FinishListener fl) {
        finishListeners.add(fl);
    }

    public void AddMoveListener(MoveListener ml) {
        moveListeners.add(ml);
    }

    public void goUp() {
        if (energy > 0) {
            md.moveUp();
            energy--;
        }
    }

    public void goDown() {
        if (energy > 0) {
            md.moveDown();
            energy--;
        }
    }

    public void goRight() {
        if (energy > 0) {
            md.moveRight();
            energy--;
        }
    }

    public void goLeft() {
        if (energy > 0) {
            md.moveLeft();
            energy--;
        }
    }

    public void Move() {
        boolean end = false;

        while(!end) {

            if (md.top() < 0) {
                state = false;
                notifyForFinish();
            }
            if (display.height!= 0 && md.bottom() > display.height) {
                notifyForFinish();
            }
            if (display.width != 0 && ((md.left() < 0) || md.right() > display.width)) {
                state=false;
                notifyForFinish();
            }
            for (Mountain m : mountains) {
                if (m.intersect(md.getRegion())){
                    end = true;
                    state = false;
                    notifyForFinish();
                }
            }
            if(end)
                System.exit(0);
            md.move();
            notifyForMove();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyForFinish(){
        FinishEvent fe = new FinishEvent(state);
        for(FinishListener f: finishListeners)
            f.finish(fe);
    }

    public void notifyForMove() {
        MoveEvent e = new MoveEvent(md);
        for(MoveListener ml : moveListeners)
            ml.makeMove(e);
    }

    public Dimension getDisplay(){return display;}

}
