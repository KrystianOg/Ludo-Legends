package states;

import ludogame.Game;

import java.awt.*;

public abstract class State {

    private static State currentstate=null;

    protected Game game;

    public State(Game game){
        this.game=game;
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
