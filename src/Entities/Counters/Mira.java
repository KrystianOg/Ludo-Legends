package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mira extends Counter {
    // moze wskrzesic pionek po naladowaniu ult

    public static final int MEDKIT_POSX=4,MEDKIT_POSY=38;
    private static final int ULT_LOAD=40;


    public Mira(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        this.ultBar=true;

        ultimateBar=new UltimateBar(handler,ULT_LOAD);
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor, (int)x, (int)y,null);
        g.drawImage(Assets.medkit, (int)x+MEDKIT_POSX, (int)y+MEDKIT_POSY,null);
        ultimateBar.render(g);
    }
}
