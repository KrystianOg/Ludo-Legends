package entities.counters;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFXandEffects.Assets.swan;

public class Samaya extends Counter {
    /*

     */




    public static final int SWAN_X=5,SWAN_Y=26;


    public Samaya(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        //spec
        ultBar=false;
        killable=false;
        canKill=false;
        vulnerable=true;
        //
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
