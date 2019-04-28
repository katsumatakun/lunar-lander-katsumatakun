import javax.swing.*;
import javax.swing.plaf.metal.MetalTheme;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class LLController extends JPanel{

    private LLView view;
    private LLModel model;
    private int keyCode;
    private ArrayList<Mountain> ms;
    private MovingDot mdot;
    private int safeZone;

    public LLController(){
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        view = new LLView();
        model = new LLModel();
        setPreferredSize(new Dimension(500,500));
        safeZone = (int)(Math.random() * 400);
        int x = (int)(Math.random() * 450) + 400;
        int y = (int)(Math.random() * 300) + 100;
        mdot = new MovingDot(new Point(250,30), new Point(x, y), 3 );
        ms = new ArrayList<>();
        System.out.println("working");
        generateMountains();
        System.out.println("working2");
        Thread t = new Thread(new ballThread(mdot));
        t.start();
    }

    public void generateMountains(){
        int numLeft = safeZone/50+1;
        System.out.println(numLeft);
        int numRight = (safeZone+100)/50+1;
        System.out.println(numRight);
        int leftCorner = 0;
        int rightCorner = 100;
        for (int i=0; i<numLeft+1; i++){
            //System.out.println(i);
            int height = (int) (Math.random()*100) + 250;
            if(rightCorner <= safeZone )
            {
                ms.add(new Mountain(new Point(leftCorner,500), new Point((leftCorner+rightCorner)/2, height), new Point(rightCorner, 500)));
                leftCorner+=50;
                rightCorner+=50;
            }
            else {
                rightCorner = safeZone;
                leftCorner = rightCorner - 100;
            }

        }
        leftCorner = safeZone+100;
        rightCorner = leftCorner+100;
        for (int i=0; i<=numRight+1; i++){
            //System.out.println("Working");
            int height = (int) (Math.random()*100) + 250;
            if(rightCorner <= 500 )
            {
                ms.add(new Mountain(new Point(leftCorner,500), new Point((leftCorner+rightCorner)/2, height), new Point(rightCorner, 500)));
                leftCorner+=50;
                rightCorner+=50;
            }
            else {
                rightCorner = 500;
                leftCorner = rightCorner - 100;
            }

        }
    }

    private class ballThread implements Runnable {

        private MovingDot md;
        private boolean finish;

        public ballThread(MovingDot md) {
            this.md = md;
            finish = false;
        }

        @Override
        public void run() {

            while (!finish) {
                if (md.top() < 0) {
                    md.reflectY();
                }
                if (getHeight() != 0 && md.bottom() > getHeight()) {
                    finish = true;
                }
                if ((md.left() < 0) || md.right() > getWidth()) {
                    md.reflectX();
                }
                for (Mountain m:ms){
                    if(m.intersect(md.getRegion())){
                        //System.out.println("Crush");
                    }
                }
                //System.out.println("working");
                md.move();
                repaint();
                //System.out.println("working2");
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.exit(0);
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Mountain m: ms) {
            m.paint(g);
        }
        mdot.paint(g);
    }
    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            keyCode = e.getKeyCode();
            System.out.println("ssss");
        }

    }




}
