package Entities.Counters;

import Entities.UltimateBar;
import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;
import states.GameState;

import java.awt.*;

public class Funi extends Counter{
    //tworzy ogien na randomowych polach

    private static final int ULT_LOAD=25; //zmienic na zdefiniowane w klasie abstrakcyjnej

    Handler handler;
    int c;

    public Funi(Handler handler, float x, float y,int c,int counterNr) {
        super(handler,x, y,Counter.DEFAULT_WIDTH,Counter.DEFAULT_HEIGHT,counterNr);
        this.c=c;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cloak_b, (int)x-5, (int)y-10,null);
        g.drawImage(Assets.counter[c], (int)x, (int)y,null);
        g.drawImage(Assets.cloak_f, (int)x-5, (int)y-10,null);
        ultimateBar.render(g);
    }

    @Override
    public int getUltLoad() {
        return ULT_LOAD;
    }

    @Override
    public void move() {

    }
}
