import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LLView extends JPanel{

    private LLLabel llLabel;
    private LLController controller;
    private ArrayList<Mountain> ms;
    public LLView(){
        setLayout(null);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        setPreferredSize(new Dimension(500,500));
        llLabel = new LLLabel();
        add(llLabel);
        llLabel.setBounds(250, 30, 25, 30);
    }

    public void setMs(ArrayList<Mountain> ms){this.ms = ms;}

    public void AddListener( LLController controller ) {
        this.controller = controller;
    }

    public void gameOver(){
        llLabel.setExplode();
        JOptionPane.showMessageDialog(null, "Sad Sad.");
    }

    public void gameWin(){
        JOptionPane.showMessageDialog(null, "Nice job!!!.");
    }
    public void update(MovingDot md){
        llLabel.updateLocation(new Point(md.left(), md.top()));
        if (md.top() < 0) {
            notifyForFinish(false);
        }
        if (getHeight() != 0 && md.bottom() > getHeight()) {
            notifyForFinish(true);
        }
        if (getWidth() != 0 && ((md.left() < 0) || md.right() > getWidth())) {
            notifyForFinish(false);
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Mountain m: ms) {
            m.paint(g);
        }
    }

    public void notifyForFinish(boolean state){
        FinishEvent fe = new FinishEvent(state);
        controller.finish(fe);
    }

    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            controller.onKey(e.getKeyCode());
        }
    }

}
