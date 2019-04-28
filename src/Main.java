import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// This code is simply a stub for you to use for GitHub
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LLController c = new LLController();
        c.setLayout(null);
        frame.getContentPane().add(c);

        frame.pack();
        frame.setVisible(true);
    }
}
