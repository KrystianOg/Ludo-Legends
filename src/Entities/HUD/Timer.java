package Entities.HUD;

import Entities.Entity;
import GFX.Assets;
import Players.Person;
import ludogame.Handler;
import states.GameState;

import java.awt.*;

import static states.SettingState.DICE_ANIM_TIME;
import static states.SettingState.FPS;

public class Timer extends Entity {

    private final int time;      //ilość klatek, pierwsza liczba to czas w sekundach
    private int currentTime=0;
    private static final int TIMER_WIDTH=87;
    private static final int TIMER_HEIGHT=87;

    public Timer(Handler handler, float x, float y) {
        super(handler, x-6, y-6, TIMER_WIDTH,TIMER_HEIGHT);
        this.time=FPS*DICE_ANIM_TIME;
    }

    @Override
    public void tick() {
        if (!handler.getDice().isClicked())
            currentTime++;

        if (currentTime == time) {

            if(!handler.getDice().isRolled())
                handler.getDice().botRoll();

            if (handler.getDice().isRolled()) {
                handler.getPlayer().autoPick();
                handler.getDice().setRolled(false);
            }
            currentTime = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(GameState.color[handler.getTurnOf()]);
        g.fillArc((int)(x-8),(int)(y-8),103,103,90,-360+(currentTime*360/this.time));
        g.drawImage(Assets.timerFrame,(int)x-8,(int)y-8,null);
    }

    public void resetTimer(){
        this.currentTime=0;
    }
}
