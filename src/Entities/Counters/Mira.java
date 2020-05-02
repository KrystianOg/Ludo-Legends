package Entities.Counters;

import Entities.ui.UltimateBar;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.medkit;

public class Mira extends Counter {
    // moze wskrzesic pionek po naladowaniu ult

    public static final int MEDKIT_POSX=4,MEDKIT_POSY=38;
    private final int ULT_LOAD=30;

    public Mira(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        ultBar=true;

        killable=true;
        canKill=true;

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
        g.drawImage(medkit, (int)x+(int)(MEDKIT_POSX*SCALE), (int)y+(int)(MEDKIT_POSY*SCALE),(int)(medkit.getWidth()*SCALE),(int)(medkit.getHeight()*SCALE),null);
    }
}
