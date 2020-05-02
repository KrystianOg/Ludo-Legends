package Entities.Counters;

import Entities.ui.UltimateBar;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.sword;

public class Saph extends Counter{
    //wojownik
    public static final int SWORD_POSY=11;

    private static final int ULT_LOAD=70;

    public Saph(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        ultBar=true;
        canKill=true;
        killable=true;
        ultimateBar=new UltimateBar(handler,ULT_LOAD,barPos);
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public boolean ifStepped() {
        return true;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor, (int)x, (int)y,hitbox.width,hitbox.height,null);
        g.drawImage(sword, (int)x, (int)y +(int)(SWORD_POSY*SCALE),(int)(sword.getWidth()*SCALE),(int)(sword.getHeight()*SCALE),null);
    }

}

