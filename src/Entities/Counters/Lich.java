package Entities.Counters;

import GFX.Assets;
import ludogame.Handler;

import java.awt.*;

public class Lich extends Counter {
    //wskrzesza na 3 rundy, jak dojdzie do kona to zalicza jak nie to dead / zbijable?

    public Lich(Handler handler, float x, float y,int counterNr) {
        super(handler,x, y,counterNr, Assets.counter[0]);
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
