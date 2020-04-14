package states;

import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public abstract class State {

    private static State currentstate=null;

    protected Handler handler;

    public State(Handler handler){
        this.handler=handler;
    }

    public static void setState(State state){
        currentstate=state;

    }

    public static State getState(){
        return currentstate;

    }

    //
    public abstract  void tick();

    public abstract void render(Graphics g);

}
