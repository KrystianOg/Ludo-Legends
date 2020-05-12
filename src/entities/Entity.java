package entities;

import ludogame.Handler;

import java.awt.*;

public abstract class Entity {

    protected float x,y; //float bo obliczenia

    protected int width,height;
    protected Handler handler;

    public Entity(Handler handler,float x, float y, int width, int height){
        this.handler=handler;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public Entity(Handler handler,int x, int y, int width, int height){
        this.handler=handler;
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
