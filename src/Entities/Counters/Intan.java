package Entities.Counters;

import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.shield;

public class Intan extends Counter {
    //2 razy trzeba go zbic zeby przesunal sie do "bazy"

    public static final int SHIELD_POSX=-3,SHIELD_POSY=21;

    private boolean hasShield;

    //passive bez paska umiejetnosci

    public Intan(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        //spec
        ultBar=false;
        hasShield=true;
        canKill=true;
        killable=true;
        vulnerable=true;
        //
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public boolean ifStepped() {
        if(hasShield){
            hasShield=false;
            return false;
        }
        else{
            hasShield=true;
            return true;
        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor,(int)x,(int)y,hitbox.width,hitbox.height,null);

        if(hasShield)
            g.drawImage(shield,(int)x-(int)(3*SCALE),(int)y+(int)(21*SCALE),(int)(shield.getWidth()*SCALE),(int)(shield.getHeight()*SCALE),null);
    }
}