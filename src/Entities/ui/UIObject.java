package Entities.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected int x,y;
    protected int width,height;
    protected boolean hovering = false;
    protected Rectangle hitbox;

    public UIObject(int x,int y, int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        hitbox=new Rectangle(x,y,width,height);

    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        if(hitbox.contains(e.getX(),e.getY()))
            hovering=true;
        else
            hovering=false;
    }

    public void onMouseRelease(MouseEvent e){
        if(hovering){
            onClick();
        }

    }





    //getters and setters
}
