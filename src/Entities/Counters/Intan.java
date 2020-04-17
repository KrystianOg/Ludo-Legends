package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;
import states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Intan extends Counter {
    //2 razy trzeba go zbic zeby przesunal sie do "bazy"
    private static final boolean ULT=false;

    //passive bez paska umiejetnosci

    public Intan(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        this.ultBar=false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor,(int)x,(int)y,null);
        g.drawImage(Assets.shield,(int)x-3,(int)y+21,null);
    }
}