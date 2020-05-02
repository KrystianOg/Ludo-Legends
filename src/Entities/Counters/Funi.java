package Entities.Counters;

import Entities.ui.Tile;
import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.wand;

public class Funi extends Counter{
    //tworzy ogien na randomowych polach
    //zmienia boolean na tiles pobiera tiles i tworzy grafike

    private final int WAND_WIDTH = 30;
    private final int WAND_HEIGHT = 80;

    public static final int WAND_POSX=24,WAND_POSY=-3;
    private final int ULT_LOAD=46; //zmienic na zdefiniowane w klasie abstrakcyjnej
    private final Tile[] fireTile=new Tile[4];

    public Funi(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
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

        g.drawImage(wand,(int)x+(int)(WAND_POSX*SCALE),(int)y-(int)(WAND_POSY*SCALE), (int)(WAND_WIDTH*SCALE), (int)(WAND_HEIGHT*SCALE),null);
    }
}

