package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Albali extends Counter {
    //zbija wszystkie ktore minie podczas trwania umiejetnosci;

    private static final int ULT_LOAD=40;


    public Albali(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        this.ultBar=true;

        ultimateBar=new UltimateBar(handler,ULT_LOAD);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor, (int)x, (int)y,null);
        ultimateBar.render(g);
    }
}
