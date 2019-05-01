import java.awt.event.*;

public class LLController implements MoveListener, FinishListener, KeyListener{

    private LLView view;
    private LLModel model;
    public LLController(LLView view, LLModel model){
          this.view = view;
          this.view.AddFinishListener(this );
          this.view.addKeyListener(this);
          this.model = model;
          this.model.AddMoveListener(this);
          this.model.AddFinishListener(this);
          view.setMs(model.getMountains());
          view.setPreferredSize(model.getDisplay());
    }

    public void start(){
        model.Move();
    }

    public void onKey(int key){
            switch (key){
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

    @Override
    public void makeMove(MoveEvent e){
        MovingDot md = (MovingDot) e.getSource();
        view.update(md);
    }

    @Override
    public void finish(FinishEvent e){
        if((boolean)e.getSource()){
            view.gameWin();
        }
        else {
            view.gameOver();
    }
        System.exit(0);

}
    @Override
    public void keyPressed(KeyEvent e) {
        onKey(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}

}
