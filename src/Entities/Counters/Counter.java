package Entities.Counters;
import Entities.Entity;
import Players.PositionOnMap;
import Entities.ui.Tile;
import Entities.HUD.UltimateBar;
import ludogame.Handler;
import states.SettingState;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Counter extends Entity {

    public static final int DEFAULT_WIDTH=41,
                            DEFAULT_HEIGHT=78;

    protected double SCALE;
    //
    private final float basex;
    private final float basey;
    //
    protected PositionOnMap pos;

    protected double directionx,directiony;
    protected boolean cisinbase, cfinished;
    protected final Rectangle hitbox;

    private boolean moving;
    private boolean reseting;
    private boolean won;
    private PositionOnMap bufferedPosition;

    //cos do umiejetnosci
    protected boolean killable;     //czy może być zbity
    protected boolean canKill;      //czy może zbijąć
    private boolean beaten;         //czy był zbity - do odrodzenia
    protected boolean vulnerable;   //czy ogień go zbija
    //Ultimate bar
    protected boolean ultBar;
    protected UltimateBar ultimateBar=null;
    protected boolean ultimateAbility=false;
    //

    //
    protected BufferedImage counterColor;

    //animacja
    protected int ANIM_TICKS=(int)(0.37* SettingState.FPS);
    //protected int ANIM_TICKS=4;
    private int tickcount=0;
    protected int moved=0;
    protected int tilesMoved=0;

    public Counter(Handler handler, float x, float y,BufferedImage counterColor) {
        super(handler,x, y,DEFAULT_WIDTH,DEFAULT_HEIGHT);

        this.counterColor=counterColor;
        basex=x;
        basey=y;
        hitbox=new Rectangle((int)x, (int)y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        beaten=false;
        cisinbase=true;
        cfinished=false;
        SCALE=1;
        moving =false;
        won=false;
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

        if(!won) {
            if (cisinbase) {

                if (tickcount == 0) {
                    Tile tempTile = handler.getTile(handler.getPlayer().getStartingPos());

                    handler.getLoadingScreen().setPlay("move");
                    directionx = (tempTile.getX() + 4 - x) / ANIM_TICKS;
                    directiony = (tempTile.getY() - 48 - y) / ANIM_TICKS;
                    tickcount++;
                } else if (tickcount > 0 && tickcount < ANIM_TICKS) {
                    x += directionx;
                    y += directiony;
                    tickcount++;
                } else if (tickcount == ANIM_TICKS) {

                    Tile tempTile = handler.getTile(handler.getPlayer().getStartingPos());

                    x = tempTile.getX() + 4;
                    y = tempTile.getY() - 48;
                    hitbox.x = (int) x;
                    hitbox.y = (int) y;

                    cisinbase = false;
                    tickcount = 0;
                    moving = false;
                    tilesMoved=0;

                    pos = new PositionOnMap(handler.getPlayer().getStartingPos());

                    bufferedPosition = getNextPosition();
                    handler.setCounterOnTile(pos, this);
                    handler.getTimer().resetTimer();
                    handler.getDice().setRolled(false);

                    if(handler.getPlayer().getRollsLeft()==0)
                        handler.getPlayer().setRollsLeft(1);
                    handler.getPlayer().setIsinbase(false);
                    handler.getGameState().setRenderOrder();
                }
            } else {
                if (tickcount == 0) {
                    handler.removeCounterFromTile(pos, this);

                    if (ultimateAbility)
                        counterLogic();

                    handler.getLoadingScreen().setPlay("move");
                    renderBig();

                    directionx = (handler.getTile(bufferedPosition).getX() + 4 - x) / ANIM_TICKS;
                    directiony = (handler.getTile(bufferedPosition).getY() - 48 - y) / ANIM_TICKS;
                    tickcount++;
                } else if (tickcount > 0 && tickcount < ANIM_TICKS) {
                    x += directionx;
                    y += directiony;
                    tickcount++;
                } else if (tickcount == ANIM_TICKS) {

                    x = handler.getTile(bufferedPosition).getX() + 4;
                    y = handler.getTile(bufferedPosition).getY() - 48;

                    tickcount = 0;

                    handler.removeCounterFromTile(pos, this);

                    pos = bufferedPosition;

                    handler.getPlayer().addPoint();

                    moved++;
                    tilesMoved++;

                    bufferedPosition = getNextPosition();

                    if ((moved != handler.getRoll())&&!won)
                        handler.setCounterOnTile(pos, this);

                    handler.getGameState().setRenderOrder();

                    if ((moved == handler.getRoll())||won) {
                        moving = false;

                        hitbox.x = (int) x;
                        hitbox.y = (int) y;

                        handler.setCounterOnTile(pos, this);

                        handler.getDice().setRolled(false);
                        handler.getGameState().setRenderOrder();
                        moved = 0;
                        if (handler.getPlayer().getRollsLeft() == 0)
                            handler.setTurnof();

                    }
                }
            }
        }
        else {

            handler.setTurnof();
            handler.getPlayer().setRollsLeft(1);
            moving=false;
            handler.getDice().setRolled(false);
            handler.getTimer().resetTimer();
            if(handler.getPlayer().getClass().getName()=="Players.Bot")
                handler.getPlayer().setBotClicked();
        }
    }

    private void resetLogic(){
        if (tickcount == 0) {
            directionx = (basex - x) / (ANIM_TICKS*2);
            directiony = (basey - y) / (ANIM_TICKS*2);
            tickcount++;
            renderBig();

            handler.getGameState().getPlayerByColor(counterColor).addDeath();
            cisinbase = true;
            handler.getGameState().setRenderOrder();
        } else if (tickcount > 0 && tickcount < ANIM_TICKS*2) {
            x += directionx;
            y += directiony;
            tickcount++;
        } else if (tickcount == ANIM_TICKS*2) {
            x = basex;
            y = basey;
            hitbox.x=(int)x;
            hitbox.y=(int)y;


            beaten=true;
            cfinished=false;

            tickcount = 0;
            reseting=false;
            pos = handler.getPlayer().getStartingPos();
            handler.getTimer().resetTimer();
            handler.getDice().setRolled(false);
        }
    }

    protected PositionOnMap getNextPosition(){
        if(handler.getDice().getRoll()>0) {

            if (pos.arr == handler.getPlayer().getEndingPos().arr && pos.tile == handler.getPlayer().getEndingPos().tile&&tilesMoved>49) {
                if (ultBar)
                    this.ultimateBar.setCanBeLoaded(false);
                return new PositionOnMap(handler.getTurnOf() + 1, 0);
            } else if (pos.tile == 51)
                return new PositionOnMap(0);
            else if (pos.arr > 0 && pos.tile == 5) {
                won = true;
                ultimateAbility = false;
                System.out.println("WON");
                return new PositionOnMap(pos.arr, pos.tile);
            } else
                return new PositionOnMap(pos.arr, pos.tile + 1);
        }

        //do "cofanie"
        else if(handler.getDice().getRoll()==0)
            return new PositionOnMap(pos.arr, pos.tile);
        else{
            if(pos.tile==0)
                return new PositionOnMap(51);
            else
                return new PositionOnMap(pos.arr, pos.tile - 1);
        }
    }

    public boolean isInbase() {
        return cisinbase;
    }

    protected void renderWasKilled(Graphics g){
        //g.drawImage(Assets.);
    }
    
    protected abstract void counterLogic();
  
    //true jeśli wraca do bazy, false jeśli nie
    public abstract boolean ifStepped();

    public boolean hasUltBar(){
        return this.ultBar;
    }

    public boolean isVulnerable(){
        return this.vulnerable;
    }

    public void setMoving(boolean moving){
        this.moving=moving;
    }

    public boolean isMoving(){
        return this.moving;
    }

    public boolean isKillable(){
        return this.killable;
    }

    public void renderUltBar(Graphics g){
        if(ultimateBar!=null)
            ultimateBar.render(g);
    }

    public boolean isClicked(){
        return this.hitbox.contains(handler.getMouseClickX(),handler.getMouseClickY());
    }

    public void resetToBase(){
        reseting=true;
        moving=false;
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

    public int getBaseX(){
        return (int)this.basex;
    }

    public int getBaseY(){
        return (int)this.basey;
    }

    public boolean canKill(){
        return this.canKill;
    }

    public void useUltimateAbility(){
        this.ultimateAbility=true;
    }

    public void setUltimateAbility(boolean ult){
        this.ultimateAbility=ult;
    }

    public boolean isBeaten(){
        return this.beaten;
    }

    public void renderSmall(float shiftX,float shiftY){
        if(SCALE==1) {
            SCALE = 0.65;
            hitbox.setSize((int)(hitbox.width*SCALE),(int)(hitbox.height*SCALE));
        }
        x=shiftX+4;
        y=shiftY-48;

        hitbox.setLocation((int)x,(int)y);
    }

    public void renderBig(float x,float y){
        SCALE=1;
        hitbox.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        this.x=x;
        this.y=y;

        hitbox.setLocation((int)x,(int)y);
    }

    public void renderBig(){
        SCALE=1;
        hitbox.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    public UltimateBar getUltimateBar(){
        return this.ultimateBar;
    }

    public boolean getWon(){
        return this.won;
    }

}