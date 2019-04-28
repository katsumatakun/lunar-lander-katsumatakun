import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LLController extends JPanel{

    private LLView view;
    private LLModel model;
    private int keyCode;
    public LLController(){
        setPreferredSize(new Dimension(500,500));
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        view = new LLView();
        add(view.getLlLabel());
        model = new LLModel();
        Thread t = new Thread(new ballThread(model.getMd()));
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
                    view.gameOver();
                    finish = true;
                }
                if (getHeight() != 0 && md.bottom() > getHeight()) {
                    view.gameWin();
                    finish = true;
                }
                if (getWidth() != 0 && ((md.left() < 0) || md.right() > getWidth())) {
                    view.gameOver();
                    finish = true;
                }
                for (Mountain m:model.getMountains()){
                    if(m.intersect(md.getRegion())){
                        view.gameOver();
                        finish = true;
                    }
                }
                if(finish)
                    System.exit(0);
                md.move();
                repaint();

                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        view.paint(g, model.getMountains(), model.getMd());
    }
    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_UP :
                    model.goUp();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.goRight();
                    break;
                case KeyEvent.VK_LEFT:
                    model.goLeft();
                    break;
                case KeyEvent.VK_DOWN:
                    model.goDown();
                    break;
            }
        }

    }




}
