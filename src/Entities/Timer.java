package Entities;

import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;
import states.GameState;

import java.awt.*;

public class Timer extends Entity{

    private int time=10*Game.FPS,
            currentTime=0;
    private int TICK= 1;
    private static int TIMER_WIDTH=87,TIMER_HEIGHT=87;


    public Timer(Handler handler, float x, float y) {
        super(handler, x-6, y-6, TIMER_WIDTH,TIMER_HEIGHT);
    }

    public void setcurrentTime(){
        if(handler.getDice().getTickCopunt()<0)
        this.currentTime+=TICK;

        if(currentTime==time){
            handler.getDice().setTickcount();
            currentTime=0;
        }
    }


    @Override
    public void tick() {
        setcurrentTime();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(GameState.color[handler.getGameState().getTurnof()]);
        g.fillArc((int)(x-8),(int)(y-8),103,103,90,-(currentTime*360/this.time));
        g.drawImage(Assets.timerFrame,(int)x-8,(int)y-8,null);
    }

    public void resetTimer(){
        this.currentTime=0;
    }
}
