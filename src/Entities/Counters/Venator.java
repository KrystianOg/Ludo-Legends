package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Venator extends Counter {
    //łowca -?

    public Venator(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        ultimateBar=new UltimateBar(handler,20);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
