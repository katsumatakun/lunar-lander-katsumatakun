import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// This code is simply a stub for you to use for GitHub
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LLView v = new LLView();
        LLModel m  = new LLModel();
        LLController c = new LLController(v, m);
        frame.getContentPane().add(v);
        frame.pack();
        frame.setVisible(true);
        c.start();
    }
}
