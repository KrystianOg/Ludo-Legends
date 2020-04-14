package Entities.Counters;

import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class Saph extends Counter{
    //wojownik

    private static final int ULT_LOAD=30;

    Handler handler;
    protected int counterNr;
    protected int c;

    public Saph(Handler handler, float x, float y,int c,int counterNr) {
        super(handler,x, y,counterNr);
        this.handler=handler;
        this.c=c;
    }

    @Override
    public int getUltLoad() {
        return ULT_LOAD;
    }

    @Override
    public void renderPick(Graphics g) {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.counter[c], (int)x, (int)y,null);
        g.drawImage(Assets.sword, (int)x, (int)y +11,null);
        ultimateBar.render(g);
    }

}
