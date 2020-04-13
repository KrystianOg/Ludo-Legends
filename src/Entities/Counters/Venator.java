package Entities.Counters;

import ludogame.Handler;

import java.awt.*;

public class Venator extends Counter {
    //łowca -?

    public Venator(Handler handler, float x, float y, int width, int height,int counterNr) {
        super(handler,x, y, width, height,counterNr);
    }

    @Override
    public void move() {

    }

    @Override
    public int getUltLoad() {
        return 0;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
