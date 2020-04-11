package Entities.Counters;
import Entities.Entity;
import ludogame.Handler;

import java.awt.*;

public abstract class Counter extends Entity {

    public static final int DEFAULT_WIDTH=41,
                            DEFAULT_HEIGHT=78;

    //
    protected int posonmap;
    protected int direction;
    protected boolean isinbase;
    public Rectangle hitbox;
    //

    //animacja pionkow
    int tickcount=-1;
    int mx=-1,my=-1;

    public Counter(Handler handler, int x, int y, int width, int height) {
        super(handler,x, y,width,height);
        hitbox=new Rectangle((int)x,(int)y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    public void tick(){
        if(tickcount<0&&this.hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY())){
            //gamestate.setRoll((int)(Math.random()*6+1));
            tickcount=0;
        }





        move();
    }

    public abstract void move();

    public boolean isIsinbase() {
        return isinbase;
    }

    public void setIsinbase(boolean isinbase) {
        this.isinbase = isinbase;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getPosonmap() {
        return posonmap;
    }

    public void setPosonmap(int posonmap) {
        this.posonmap =this.posonmap+posonmap;
    }


}
