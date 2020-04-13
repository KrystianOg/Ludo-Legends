package Entities.Counters;

import ludogame.Handler;

import java.awt.*;

public class Polaris extends Counter {
    //przesuwa pionek nastepnego o wyrzucona liczbe oczek

    public Polaris(Handler handler, float x, float y, int width, int height,int counterNr) {
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
