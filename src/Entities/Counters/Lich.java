package Entities.Counters;

import ludogame.Handler;

import java.awt.*;

public class Lich extends Counter {
    //wskrzesza na 3 rundy, jak dojdzie do kona to zalicza jak nie to dead / zbijable?

    public Lich(Handler handler, float x, float y, int width, int height,int counterNr) {
        super(handler,x, y, width, height,counterNr);
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
