package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Saph extends Counter{
    //wojownik
    public static final int SWORD_POSY=11;

    private static final int ULT_LOAD=70;


    public Saph(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        this.ultBar=true;
        ultimateBar=new UltimateBar(handler,ULT_LOAD,barPos);
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor, (int)x, (int)y,null);
        g.drawImage(Assets.sword, (int)x, (int)y +SWORD_POSY,null);
    }

}

