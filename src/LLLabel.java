import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class LLLabel extends JLabel {

    ArrayList<ImageIcon> msImages;

    public LLLabel() {
        getImages();
        setIcon(msImages.get(0));
        //setBorder(new LineBorder(Color.blue, 2, true));
    }

    public void setExplode(){
        setIcon(msImages.get(1));
    }

    public void updateLocation(Point p){
        setLocation(p);
    }
    private void getImages() {

        msImages = new ArrayList<>();
        msImages.add(new ImageIcon("resource/rocket-green.png"));
        msImages.add(new ImageIcon("resource/fail.png"));
    }

}
