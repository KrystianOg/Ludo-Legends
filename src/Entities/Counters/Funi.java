package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Funi extends Counter{
    //tworzy ogien na randomowych polach

    public static final int WAND_POSX=24,WAND_POSY=-3;
    private final int WAND_WIDTH=30;
    private final int WAND_HEIGHT=80;

    private final int ULT_LOAD=25; //zmienic na zdefiniowane w klasie abstrakcyjnej

    public Funi(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        this.ultBar=true;
        ultimateBar=new UltimateBar(handler,ULT_LOAD);
    }

    @Override
    protected void counterLogic() {
        System.out.println("ADHFKJAD");
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(counterColor, (int)x, (int)y,null);
        g.drawImage(Assets.wand,(int)x+WAND_POSX,(int)y-WAND_POSY,WAND_WIDTH,WAND_HEIGHT,null);
        ultimateBar.render(g);
    }

    public int getUltLoad(){
        return this.ULT_LOAD;
    }

}

