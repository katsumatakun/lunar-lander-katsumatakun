import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class LLLabel extends JLabel {

    ArrayList<ImageIcon> llImages;

    public LLLabel() {
        getImages();
        setIcon(llImages.get(0));
        //setBorder(new LineBorder(Color.blue, 2, true));
    }

    public void setExplode(){
        setIcon(llImages.get(1));
    }

    private void getImages() {

        llImages = new ArrayList<>();
        llImages.add(new ImageIcon("resource/rocket-green.png"));
        llImages.add(new ImageIcon("resource/fail.png"));
    }

}
