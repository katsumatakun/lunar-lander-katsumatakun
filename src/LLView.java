import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LLView{

    LLLabel llLabel;
    public LLView(){
        llLabel = new LLLabel();
        llLabel.setBounds(250, 30, 25, 30);
    }

    public LLLabel getLlLabel(){return llLabel;}

    public void gameOver(){
        llLabel.setExplode();
        JOptionPane.showMessageDialog(null, "Sad Sad.");
    }

    public void gameWin(){
        JOptionPane.showMessageDialog(null, "Nice job!!!.");
    }
    public void paint(Graphics g, ArrayList<Mountain> ms, MovingDot mdot){
        for(Mountain m: ms) {
            m.paint(g);
        }
        llLabel.updateLocation(new Point(mdot.left(), mdot.top()));
        //mdot.paint(g);
    }
}
