package Entities.Counters;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Polaris extends Counter {
    //przesuwa pionek nastepnego o wyrzucona liczbe oczek

    public Polaris(Handler handler, float x, float y,BufferedImage counterColor) {
        super(handler,x, y,1,counterColor);
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
