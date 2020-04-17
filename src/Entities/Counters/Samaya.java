package Entities.Counters;

import Entities.ui.UltimateBar;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Samaya extends Counter {
    //passive, nie zbija ale jej tez nie das sie zbic

    public Samaya(Handler handler, float x, float y, BufferedImage counterColor) {
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
