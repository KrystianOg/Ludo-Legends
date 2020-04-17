package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Saph extends Counter{
    //wojownik

    private static final int ULT_LOAD=30;

    Handler handler;
    protected int counterNr;
    protected int c;

    public Saph(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        ultimateBar=new UltimateBar(handler,ULT_LOAD);
        this.handler=handler;
        this.c=2;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.counter[c], (int)x, (int)y,null);
        g.drawImage(Assets.sword, (int)x, (int)y +11,null);
        ultimateBar.render(g);
    }


}

