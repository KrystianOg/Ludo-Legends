package Entities.HUD;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;
import states.GameState;
import states.SettingState;

import java.awt.*;

public class Timer extends Entity {

    private final int time;      //ilość klatek, pierwsza liczba to czas w sekundach
            private int currentTime=0;
    private static final int TIMER_WIDTH=87;
    private static final int TIMER_HEIGHT=87;


    public Timer(Handler handler, float x, float y) {
        super(handler, x-6, y-6, TIMER_WIDTH,TIMER_HEIGHT);
        this.time=SettingState.FPS*SettingState.DICE_ANIM_TIME;
    }

    private void setcurrentTime(){
        if(handler.getDice().getTickCount()<0)
        this.currentTime++;

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
        g.fillArc((int)(x-8),(int)(y-8),103,103,90,-360+(currentTime*360/this.time));
        g.drawImage(Assets.timerFrame,(int)x-8,(int)y-8,null);
    }

    public void resetTimer(){
        this.currentTime=0;
    }
}
