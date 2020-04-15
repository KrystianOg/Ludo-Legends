package Entities.Counters;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Samaya extends Counter {
    //passive, nie zbija ale jej tez nie das sie zbic

    public Samaya(Handler handler, float x, float y, int counterNr, BufferedImage counterColor) {
        super(handler,x, y,counterNr,counterColor);
    }
    @Override
    public int getUltLoad() {
        return 0;
    }

    @Override
    public void renderPick(Graphics g) {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
