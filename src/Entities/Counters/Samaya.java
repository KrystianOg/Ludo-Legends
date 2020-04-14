package Entities.Counters;

import ludogame.Handler;

import java.awt.*;

public class Samaya extends Counter {
    //passive, nie zbija ale jej tez nie das sie zbic

    public Samaya(Handler handler, float x, float y,int counterNr) {
        super(handler,x, y,counterNr);
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
