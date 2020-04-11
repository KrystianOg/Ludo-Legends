package Entities;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;
import states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dice extends Entity {

    public static final int DICE_WIDTH=75,
                            DICE_HEIGHT=75;


    protected Rectangle hitbox;
    protected GameState gamestate;

    //animation
    private int tickcount=-1;
    private static final int DICE_ANIM_TICKS=25;


    public Dice(Handler handler, int x, int y, GameState gamestate) {
        super(handler,x, y, DICE_WIDTH, DICE_HEIGHT);
        this.hitbox=new Rectangle(x,y,DICE_WIDTH,DICE_HEIGHT);
        this.gamestate=gamestate;
    }

    @Override
    public void tick() {

        if(tickcount<0&&this.hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY())){
            //gamestate.setRoll((int)(Math.random()*6+1));
            tickcount=0;
        }
        else if(tickcount>=0&&tickcount<DICE_ANIM_TICKS&&tickcount%4==0){
            gamestate.setRoll((int)(Math.random()*6+1));
            tickcount++;
        }
        else if(tickcount==DICE_ANIM_TICKS){
            handler.getGame().getMousemanager().reset();
            tickcount=-1;
        }
        else if(tickcount>=0&&tickcount<DICE_ANIM_TICKS&&tickcount%4!=0){
            tickcount++;
        }



    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rollimg[gamestate.getRoll()-1],x,y,null);
    }
}
