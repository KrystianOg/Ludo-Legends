package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Funi extends Counter{
    //tworzy ogien na randomowych polach

    private static final int ULT_LOAD=25; //zmienic na zdefiniowane w klasie abstrakcyjnej

    public Funi(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        ultimateBar=new UltimateBar(handler,ULT_LOAD);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cloak_b, (int)x-5, (int)y-10,null);
        //g.drawImage(Assets.counter[c], (int)x, (int)y,null);
        g.drawImage(Assets.cloak_f, (int)x-5, (int)y-10,null);
        ultimateBar.render(g);
    }
}

