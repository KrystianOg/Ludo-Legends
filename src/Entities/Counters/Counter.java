package Entities.Counters;
import Entities.Entity;
import Entities.Tile;
import Entities.UltimateBar;
import ludogame.Handler;

import java.awt.*;

public abstract class Counter extends Entity {

    public static final int DEFAULT_WIDTH=41,
                            DEFAULT_HEIGHT=78;

    //
    protected float basex,basey;

    protected int posonmap;
    protected float directionx,directiony;
    protected boolean isinbase;
    public Rectangle hitbox;
    //

    //Ultimate bar
    protected UltimateBar ultimateBar;
    protected int ultLoad;

    //animacja pionkow
    private static final int COUNTER_ANIM_TICKS=25;
    private int tickcount=-1;
    private int moves=-1;
    private boolean changeDirection;

    public Counter(Handler handler, float x, float y, int width, int height,int nr) {
        super(handler,x, y,width,height);

        this.basex=x;
        this.basey=y;
        hitbox=new Rectangle((int)x, (int)y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        isinbase=true;
        ultimateBar=new UltimateBar(handler,nr);
    }

    public abstract int getUltLoad();

    public void tick(){

        moveLogic();
        setUltLoad();
        ultimateBar.tick();
    }

    private void moveLogic(){

        if(handler.getPlayer().isIsinbase()&&handler.getGameState().getRoll()!=6&&!handler.getDice().isRolled()){

        }
        else if(tickcount<0&&handler.getDice().isRolled()&&this.hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY())){
            if(this.isinbase&&handler.getRoll()==6) {
                //
                int i = handler.getPlayer().getStarting_pos();

                Tile tile = handler.getGameState().getTile(i);

                directionx = (tile.x+5 - this.x) / (COUNTER_ANIM_TICKS);
                directiony = (tile.y-51 - this.y) / (COUNTER_ANIM_TICKS);

                System.out.println("DX: " + directionx  + " DY: " + directiony );

                tickcount = 0;

            }
            else if(!this.isinbase){

                Tile tile = handler.getGameState().getTile(getNextTile());

                directionx = (tile.x+5 - this.x) / (COUNTER_ANIM_TICKS);
                directiony = (tile.y-51 - this.y) / (COUNTER_ANIM_TICKS);

                System.out.println("DX: " + directionx  + "DY: " + directiony );

                tickcount = 0;
            }

        }
        else if(tickcount>=0&&tickcount<COUNTER_ANIM_TICKS){

            if(changeDirection){

                Tile tile = handler.getGameState().getTile(getNextTile());

                directionx = (tile.x+5 - this.x) / (COUNTER_ANIM_TICKS);
                directiony = (tile.y-51 - this.y) / (COUNTER_ANIM_TICKS);

                changeDirection=false;
                System.out.println("DX: " + directionx  + "DY: " + directiony );
            }
            y+=directiony;
            x+=directionx;

            hitbox.y+=directiony;
            hitbox.x+=directionx;

            tickcount++;

        }
        else if(tickcount==COUNTER_ANIM_TICKS){
            handler.getGame().getMousemanager().reset();

            moves++;

            // pozycja ++
            posonmap++;
            if(posonmap==52)
                posonmap=0;

            if(moves==handler.getRoll()) {
                handler.getDice().setRolled(false);
                handler.getPlayer().setClicked(false);
                handler.getPlayer().setPoints(moves);
                handler.setTurnof();
                this.
                moves=0;                //reset void
                tickcount=-1;           //reset void
            }
            else if(isinbase) {
                handler.getDice().setRolled(false);
                handler.getPlayer().setIsinbase(false);
                setIsinbase(false);
                handler.getPlayer().setClicked(false);

                moves=0;
                tickcount=-1;
            }
            else{
                tickcount=0;
                changeDirection=true;
            }


            setPosonmap();
            handler.getGameState().boardLogic(posonmap);
        }
    }

    private void setUltLoad(){
        this.ultLoad=(int)(Math.ceil(100*handler.getPlayer().getPoints()/getUltLoad()));
    }

    public abstract void move();

    private int getNextTile(){
        if(posonmap==51)
            return 0;
        else
            return posonmap+1;

    }

    public boolean isIsinbase() {
        return isinbase;
    }

    public void setIsinbase(boolean isinbase) {
        if(isinbase){
            posonmap=-1;
            this.x=basex;
            this.y=basey;
            this.hitbox.x=(int)basex;
            this.hitbox.y=(int)basey;

        }
        else{

            posonmap=handler.getPlayer().getStarting_pos();
        }


        this.isinbase = isinbase;
    }


    public int getPosonmap() {
        return posonmap;
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
