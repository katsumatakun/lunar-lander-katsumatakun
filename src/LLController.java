import java.awt.event.*;

public class LLController implements MoveListener, FinishListener{

    private LLView view;
    private LLModel model;
    public LLController(LLView view, LLModel model){
          this.view = view;
          this.view.AddListener(this );
          this.model = model;
          this.model.AddListener(this);
          view.setMs(model.getMountains());
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

}
