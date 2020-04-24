package Entities.Counters;
import Entities.Entity;
import Entities.ui.Tile;
import Entities.ui.UltimateBar;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Counter extends Entity {

    public static final int DEFAULT_WIDTH=41,
                            DEFAULT_HEIGHT=78;

    //
    protected float basex,basey;
    //
    protected int posonmap;
    protected double directionx,directiony;
    protected boolean cisinbase;
    public Rectangle hitbox;
    protected boolean moving;
    //

    //Ultimate bar
    protected boolean ultBar;
    protected UltimateBar ultimateBar=null;        //zmienic na protected/private

    //
    protected boolean isUltBar;

    BufferedImage counterColor;

    //animacja
    private static final int COUNTER_ANIM_TICKS=25;
    private int tickcount=0;
    private int moved=0;

    //problem - nienaturalne nakładanie tekstur przy ruchu



    public Counter(Handler handler, float x, float y,BufferedImage counterColor) {
        super(handler,x, y,DEFAULT_WIDTH,DEFAULT_HEIGHT);

        this.counterColor=counterColor;
        this.basex=x;
        this.basey=y;
        hitbox=new Rectangle((int)x, (int)y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        cisinbase=true;
        moving =false;
    }

    public void tick(){
        if(ultimateBar!=null)
            ultimateBar.tick();

        if(moving) {

            moveLogic();



        }
    }

    private void moveLogic(){
        if(cisinbase) {

            if (tickcount == 0) {
                Tile tempTile = handler.getTile(handler.getPlayer(handler.getTurnOf()).getStarting_pos());

                directionx = (tempTile.x + 4 - this.x) / COUNTER_ANIM_TICKS;
                directiony = (tempTile.y - 48 - this.y) / COUNTER_ANIM_TICKS;
                tickcount++;
            } else if (tickcount > 0 && tickcount < COUNTER_ANIM_TICKS) {
                x += directionx;
                y += directiony;


                tickcount++;
            } else if (tickcount == COUNTER_ANIM_TICKS) {
                Tile tempTile = handler.getTile(handler.getPlayer(handler.getTurnOf()).getStarting_pos());
                x = tempTile.x + 4;
                y = tempTile.y - 48;
                hitbox.x=(int)x;
                hitbox.y=(int)y;



                cisinbase = false;
                tickcount = 0;
                moving = false;
                posonmap = handler.getPlayer(handler.getTurnOf()).getStarting_pos();
                handler.getTimer().resetTimer();
                handler.getDice().setRolled(false);
                handler.getPlayer(handler.getTurnOf()).setIsinbase(false);
            }
        }
        else{
            if(tickcount==0) {
                Tile tempTile = handler.getTile(getNextTile());

                directionx = (tempTile.x + 4 - this.x) / COUNTER_ANIM_TICKS;
                directiony = (tempTile.y - 48 - this.y) / COUNTER_ANIM_TICKS;
                tickcount++;
            }
            else if(tickcount>0&&tickcount<COUNTER_ANIM_TICKS){
                x += directionx;
                y += directiony;
                tickcount++;
            }
            else if(tickcount==COUNTER_ANIM_TICKS){

                Tile tempTile = handler.getTile(getNextTile());
                x = tempTile.x + 4;
                y = tempTile.y - 48;

                tickcount=0;

                posonmap++;
                if(posonmap==52)
                    posonmap=0;

                moved++;

                System.out.println(posonmap);

                if(moved==handler.getRoll()){
                    moving=false;
                    hitbox.x=(int)x;
                    hitbox.y=(int)y;

                    if(handler.getRoll()!=6)
                    handler.setTurnof();

                    handler.getDice().setRolled(false);
                    handler.getTimer().resetTimer();
                    moved=0;

                }
            }
        }

        //umiejetności specjalne turaj
        
        //
    }

    private int getNextTile(){
        if(posonmap==51)
            return 0;
        else
            return posonmap+1;
    }

    public boolean isInbase() {
        return cisinbase;
    }

    protected abstract void counterLogic();

    public boolean getHasUltBar(){
        return this.isUltBar;
    }

    public int getPosonmap() {
        return posonmap;
    }

    public void setMoving(boolean moving){
        this.moving=moving;
    }

    public boolean isMoving(){
        return this.moving;
    }

    public void setPosonmap() {
        this.x=handler.getGameState().getTile(posonmap).x+5;
        this.y=handler.getGameState().getTile(posonmap).y-51;
        hitbox.x=(int)handler.getGameState().getTile(posonmap).x+5;
        hitbox.y=(int)handler.getGameState().getTile(posonmap).y-51;
        System.out.println("POS: "+posonmap);

        handler.getPlayer().resetCounterNr();
        handler.getGameState().setCounter(this,posonmap);
    }

}
