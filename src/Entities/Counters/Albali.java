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

        ultimateBar=new UltimateBar(handler,ULT_LOAD);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
