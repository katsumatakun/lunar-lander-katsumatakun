import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class LLController extends JPanel {

    private LLView view;
    private LLModel model;
    private int keyCode;
    private ArrayList<Mountain> ms;
    private MovingDot mdot;

    public LLController(){
        view = new LLView();
        model = new LLModel();
        setPreferredSize(new Dimension(500,500));
        int x = (int)(Math.random() * 450) + 400;
        int y = (int)(Math.random() * 300) + 100;
        mdot = new MovingDot(new Point(250,30), new Point(x, y), 6 );
        MyKeyAdapter myKeyAdapter = new MyKeyAdapter();
        addKeyListener(myKeyAdapter);
        ms = new ArrayList<>();
        ms.add(new Mountain(new Point(0,500), new Point(100, 250), new Point(200, 500)));
        Thread t = new Thread(new ballThread(mdot));
        t.start();
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
                        System.out.println("Crush");
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
        for(Mountain m: ms)
            m.paint(g);
        mdot.paint(g);
    }
    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            keyCode = e.getKeyCode();
            repaint();
        }

    }




}
