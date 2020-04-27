package Entities.Counters;
import Entities.Entity;
import Entities.PositionOnMap;
import Entities.ui.Tile;
import Entities.ui.UltimateBar;
import ludogame.Handler;
import states.SettingState;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Counter extends Entity {

    public static final int DEFAULT_WIDTH=41,
                            DEFAULT_HEIGHT=78;

    //
    protected float basex,basey;
    //
    protected PositionOnMap pos;
    protected double directionx,directiony;
    protected boolean cisinbase;
    private final Rectangle hitbox;
    private boolean moving;
    private boolean reseting;


    //cos do umiejetnosci
    protected boolean killable;
    protected boolean beatable;
    private final boolean wasBeaten;
    //Ultimate bar
    protected boolean ultBar;
    protected UltimateBar ultimateBar=null;
    //

    //
    protected BufferedImage counterColor;

    //animacja
    private final int ANIM_TICKS=(int)(0.42* SettingState.FPS);
    private int tickcount=0;
    private int moved=0;

    //problem - nienaturalne nakładanie tekstur przy ruchu

    public Counter(Handler handler, float x, float y,BufferedImage counterColor) {
        super(handler,x, y,DEFAULT_WIDTH,DEFAULT_HEIGHT);

        this.counterColor=counterColor;
        basex=x;
        basey=y;
        hitbox=new Rectangle((int)x, (int)y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        wasBeaten=false;
        cisinbase=true;
        moving =false;
    }

    public void tick(){
        if(ultimateBar!=null)
            ultimateBar.tick();

        if(moving) {
            moveLogic();
        }

        if(reseting){
            resetLogic();
        }

    }

    private void moveLogic(){

        if(cisinbase) {

            if (tickcount == 0) {
                Tile tempTile = handler.getTile(handler.getPlayer().getStartingPos());

                directionx = (tempTile.getX() + 4 - this.x) / ANIM_TICKS;
                directiony = (tempTile.getY() - 48 - this.y) / ANIM_TICKS;
                tickcount++;
            } else if (tickcount > 0 && tickcount < ANIM_TICKS) {
                x += directionx;
                y += directiony;
                tickcount++;
            } else if (tickcount == ANIM_TICKS) {

                Tile tempTile = handler.getTile(handler.getPlayer().getStartingPos());

                x = tempTile.getX() + 4;
                y = tempTile.getY() - 48;
                hitbox.x=(int)x;
                hitbox.y=(int)y;

                cisinbase = false;
                tickcount = 0;
                moving = false;

                pos=new PositionOnMap(handler.getPlayer().getStartingPos());
                handler.setCounterOnTile(pos,this);

                handler.getTimer().resetTimer();
                handler.getDice().setRolled(false);
                handler.getPlayer().setIsinbase(false);
            }
        }
        else{
            if(tickcount==0) {
                Tile tempTile = handler.getTile(getNextTile());

                handler.removeCounterFromTile(pos,this);

                directionx = (tempTile.getX() + 4 - this.x) / ANIM_TICKS;
                directiony = (tempTile.getY() - 48 - this.y) / ANIM_TICKS;
                tickcount++;
            }
            else if(tickcount>0&&tickcount<ANIM_TICKS){
                x += directionx;
                y += directiony;
                tickcount++;
            }
            else if(tickcount==ANIM_TICKS){

                Tile tempTile = handler.getTile(getNextTile());
                x = tempTile.getX() + 4;
                y = tempTile.getY() - 48;

                tickcount=0;

                pos.setTile(pos.tile+1);
                if(pos.tile==52)
                    pos.setTile(0);

                handler.getPlayer().setPoints(1);
                moved++;

                if(moved==handler.getRoll()){
                    moving=false;
                    hitbox.x=(int)x;
                    hitbox.y=(int)y;

                    handler.setCounterOnTile(pos,this);

                    if(handler.getPlayer().getRollsLeft()==0)
                        handler.setTurnof();

                    handler.getDice().setRolled(false);
                    handler.getTimer().resetTimer();
                    moved=0;
                }
            }
        }

        //umiejetności specjalne tutaj
        counterLogic();
        //
    }

    private void resetLogic(){
        if (tickcount == 0) {
            directionx = (basex - this.x) / (ANIM_TICKS*2);
            directiony = (basey - this.y) / (ANIM_TICKS*2);
            tickcount++;
        } else if (tickcount > 0 && tickcount < ANIM_TICKS*2) {
            x += directionx;
            y += directiony;
            tickcount++;
        } else if (tickcount == ANIM_TICKS*2) {
            x = basex;
            y = basey;
            hitbox.x=(int)x;
            hitbox.y=(int)y;

            cisinbase = true;
            tickcount = 0;
            reseting=false;
            pos = handler.getPlayer().getStartingPos();
            handler.getTimer().resetTimer();
            handler.getDice().setRolled(false);
        }
    }

    private PositionOnMap getNextTile(){
        if(pos.tile==51)
            return new PositionOnMap(0);
        else if(pos.tile==handler.getPlayer().getEndingPos().tile&&pos.arr==handler.getPlayer().getEndingPos().arr) {
            pos.arr=handler.getTurnOf()+1;
            pos.tile=0;

            return new PositionOnMap(handler.getTurnOf() + 1, 0);
        }
        else
            return new PositionOnMap(pos.arr,pos.tile+1);
    }

    public boolean isInbase() {
        return cisinbase;
    }

    protected abstract void counterLogic();

    public boolean hasUltBar(){
        return this.ultBar;
    }

    public void setMoving(){
        this.moving=true;
    }

    public boolean isMoving(){
        return this.moving;
    }

    public boolean iskillable(){
        return this.killable;
    }

    public void renderUltBar(Graphics g){
        if(this.ultimateBar!=null)
            ultimateBar.render(g);
    }

    public boolean isClicked(){
        return this.hitbox.contains(handler.getMouseClickX(),handler.getMouseClickY());
    }

    public void resetToBase(){
        this.reseting=true;
        this.moving=false;
        cisinbase=true;
        handler.getGameState().addToReset(this);
    }

    public boolean getMoving(){
        return this.moving;
    }

    public boolean getReseting(){
        return this.reseting;
    }

    public BufferedImage getCounterColor(){
        return this.counterColor;
    }
}
