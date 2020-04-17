package Entities.HUD;

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

    private boolean rolled;
    private int roll;


    //animation
    private int tickcount=-1;
    private static final int DICE_ANIM_TICKS=25;


    public Dice(Handler handler, int x, int y) {
        super(handler,x, y, DICE_WIDTH, DICE_HEIGHT);
        this.hitbox=new Rectangle(x,y,DICE_WIDTH,DICE_HEIGHT);
        this.setRolled(false);

    }

    public boolean isRolled() {
        return rolled;
    }

    public void setRolled(boolean rolled) {
        this.rolled = rolled;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    @Override
    public void tick() {

        if(tickcount<0&&!isRolled()&&this.hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY())){
            tickcount=0;
        }
        else if(tickcount>=0&&tickcount<DICE_ANIM_TICKS&&tickcount%4==0){
            handler.getGameState().setRoll((int)(Math.random()*6+1));
            tickcount++;
        }

        else if(tickcount>=0&&tickcount<DICE_ANIM_TICKS&&tickcount%4!=0){
            tickcount++;
        }
        else if(tickcount<0&&isRolled()&&this.hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY())){

        }
        else if(tickcount==DICE_ANIM_TICKS){
            handler.getGame().getMousemanager().reset();

            if(handler.getGameState().getPlayer(handler.getGameState().getTurnof()).isIsinbase()&&handler.getGameState().getRoll()==6){
                setRolled(true);
            }
            else if(handler.getGameState().getPlayer(handler.getGameState().getTurnof()).isIsinbase()&&handler.getGameState().getRoll()!=6){
                handler.getGameState().setTurnof();
                handler.getGameState().getTimer().resetTimer();
                setRolled(false);
            }
            else if(!handler.getGameState().getPlayer(handler.getGameState().getTurnof()).isIsinbase()){
                setRolled(true);
            }
            tickcount=-1;
            System.out.println("MOVING: "+handler.getGameState().getTurnof());

        }
    }

    public int getTickCount(){
        return this.tickcount;
    }

    public void setTickcount(){
        this.tickcount=0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rollimg[handler.getGameState().getRoll()-1],(int)x,(int)y,null);
    }
}
