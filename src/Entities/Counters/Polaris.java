package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.icicle_f;

public class Polaris extends Counter {
    // zamraza losowego przeciwnika co runde, (czas trwania 2-3tury)

    public static final int ICICLE_POSX=-3,ICICLE_POSY=-2;

    private final int ULT_LOAD=60;

    public Polaris(Handler handler, float x, float y,BufferedImage counterColor,int barPos) {
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
        g.drawImage(icicle_f,(int)x+(int)(ICICLE_POSX*SCALE),(int)y+(int)(ICICLE_POSY*SCALE),(int)(icicle_f.getWidth()*SCALE),(int)(icicle_f.getHeight()*SCALE),null);
    }
}
