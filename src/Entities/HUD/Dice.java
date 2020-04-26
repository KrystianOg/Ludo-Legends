package Entities.HUD;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;

public class Dice extends Entity {

    public static final int DICE_WIDTH=75,
                            DICE_HEIGHT=75;

    private final Rectangle hitbox;

    private boolean rolled;
    private boolean clicked;
    private int roll;

    //animation
    private int tickcount;
    private static final int DICE_ANIM_TICKS=27;


    public Dice(Handler handler, int x, int y) {
        super(handler,x, y, DICE_WIDTH, DICE_HEIGHT);
        hitbox=new Rectangle(x,y,DICE_WIDTH,DICE_HEIGHT);
        rolled=false;
        clicked=false;
        tickcount=-1;
        roll=6;
    }

    public boolean isRolled() {
        return rolled;
    }

    public int getRoll(){
        return roll;
    }

    @Override
    public void tick() {

        if(this.hitbox.contains(handler.getMouseClickX(),handler.getMouseClickY())&&!rolled&&!clicked){
            handler.resetMousePOS();
            clicked=true;
            tickcount=0;
            handler.getPlayer().rollsMinusOne();
        }
        else if(tickcount>=0&&tickcount<DICE_ANIM_TICKS){
            tickcount++;
            if(tickcount%4==0)
                roll=(int)(Math.random()*6+1);        //  1-6
        }
        else if(tickcount==DICE_ANIM_TICKS){
            rolled=true;
            clicked=false;
            tickcount=-1;

            if(roll==6)
                handler.getPlayer().rollsPlusOne();


            System.out.println(roll);
        }

    }

    public void setTickcount(){
        clicked=true;
        tickcount=0;
        handler.getPlayer().rollsMinusOne();
    }

    public int getTickCount(){
        return this.tickcount;
    }

    public void setRolled(boolean rolled){
        this.rolled=rolled;
    }

    public void botRoll(){
        clicked=true;
        tickcount=0;
        handler.getPlayer().rollsMinusOne();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rollimg[roll-1],(int)x,(int)y,null);
    }
}
