package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Funi extends Counter{
    //tworzy ogien na randomowych polach

    private final int WAND_WIDTH = 30;
    private final int WAND_HEIGHT = 80;

    public static final int WAND_POSX=24,WAND_POSY=-3;

    private final int ULT_LOAD=46; //zmienic na zdefiniowane w klasie abstrakcyjnej

    public Funi(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        ultBar=true;
        killable=true;
        beatable=true;
        ultimateBar=new UltimateBar(handler,ULT_LOAD,barPos);
    }

    @Override
    protected void counterLogic() {
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(counterColor, (int)x, (int)y,null);
        g.drawImage(Assets.wand,(int)x+WAND_POSX,(int)y-WAND_POSY, WAND_WIDTH, WAND_HEIGHT,null);
    }
}

