package Entities;

import java.awt.*;

public abstract class Entity {

    protected int x,y; //

    protected int width,height;


    public Entity(int x, int y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;

    }

    public abstract void tick();

    public abstract void render(Graphics g);


    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
