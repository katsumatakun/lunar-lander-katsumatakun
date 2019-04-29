import java.util.EventObject;

public class MoveEvent extends EventObject {

        public MoveEvent(MovingDot source){
            super(source);
        }
    }
