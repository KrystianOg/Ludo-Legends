package Entities.Counters;
import Entities.Entity;

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

    public Counter(int x, int y, int width, int height) {
        super(x, y,width,height);
        hitbox=new Rectangle((int)x,(int)y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
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
