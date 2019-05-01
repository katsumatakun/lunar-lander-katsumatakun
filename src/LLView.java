import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LLView extends JPanel{

    private LLLabel llLabel;
    private ArrayList<FinishListener> finishListeners;
    private ArrayList<Mountain> ms;
    public LLView(){
        setLayout(null);
        setFocusable(true);
        //setPreferredSize(new Dimension(500,500));
        llLabel = new LLLabel();
        add(llLabel);
        llLabel.setBounds(250, 30, 25, 30);
        finishListeners = new ArrayList<>();
    }


    public void setMs(ArrayList<Mountain> ms){this.ms = ms;}

    public void AddFinishListener( FinishListener fl ) {
         finishListeners.add(fl);
    }

    public void gameOver(){
        llLabel.setExplode();
        JOptionPane.showMessageDialog(null, "Sad Sad.");
    }

    public void gameWin(){
        JOptionPane.showMessageDialog(null, "Nice job!!!.");
    }
    public void update(MovingDot md){
        llLabel.setLocation(new Point(md.left(), md.top()));
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Mountain m: ms) {
            m.paint(g);
        }
    }
}
