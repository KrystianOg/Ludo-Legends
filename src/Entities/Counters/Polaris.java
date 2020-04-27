package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Polaris extends Counter {
    // zamraza losowego przeciwnika co runde, (czas trwania 2-3tury)

    public static final int ICICLE_POSX=-3,ICICLE_POSY=-2;

    private final int ULT_LOAD=60;

    public Polaris(Handler handler, float x, float y,BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        ultBar=true;
        beatable=true;
        killable=true;
        ultimateBar=new UltimateBar(handler,ULT_LOAD,barPos);
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.icicle_b,(int)x+ICICLE_POSX,(int)y+ICICLE_POSY,null);
        g.drawImage(counterColor, (int)x, (int)y,null);
        g.drawImage(Assets.icicle_f,(int)x+ICICLE_POSX,(int)y+ICICLE_POSY,null);
    }
}
