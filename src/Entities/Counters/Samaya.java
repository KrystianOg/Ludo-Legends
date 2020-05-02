package Entities.Counters;

import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.swan;

public class Samaya extends Counter {
    //passive, nie zbija ale jej tez nie das sie zbic

    public static final int SWAN_X=5,SWAN_Y=26;


    public Samaya(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        this.ultBar=false;
        killable=false;
        canKill=false;
        ultimateBar=null;
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public boolean ifStepped() {


        return false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor, (int)x, (int)y,hitbox.width,hitbox.height,null);
        g.drawImage(swan,(int)x+(int)(SWAN_X*SCALE),(int)y+(int)(SWAN_Y*SCALE),(int)(swan.getWidth()*SCALE),(int)(swan.getHeight()*SCALE),null);
    }
}
