package Entities.Counters;

import GFX.Assets;
import ludogame.Handler;

import java.awt.*;

public class Venator extends Counter {
    //łowca -?

    public Venator(Handler handler, float x, float y,int counterNr) {
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
