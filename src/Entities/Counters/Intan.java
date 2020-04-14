package Entities.Counters;

import GFX.Assets;
import ludogame.Handler;
import states.GameState;

import java.awt.*;

public class Intan extends Counter {
    //2 razy trzeba go zbic zeby przesunal sie do "bazy"
    private static final boolean ULT=false;

    int c;      //przenieść do counter

    public Intan(Handler handler, float x, float y, int c,int counterNr) {
        super(handler,x, y, counterNr);
        this.c=c;
    }

    @Override
    public int getUltLoad() {
        return 100;
    }

    @Override
    public void renderPick(Graphics g) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.counter[c],(int)x,(int)y,null);
        g.drawImage(Assets.shield,(int)x-3,(int)y+21,null);
    }
}
