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

    public Funi(Handler handler, float x, float y) {
        super(handler,x, y,ULT_LOAD);
        this.c=1;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cloak_b, (int)x-5, (int)y-10,null);
        g.drawImage(Assets.counter[c], (int)x, (int)y,null);
        g.drawImage(Assets.cloak_f, (int)x-5, (int)y-10,null);
        ultimateBar.render(g);
    }

    @Override
    public void renderPick(Graphics g) {
        g.drawImage(Assets.cloak_b, (int)x-5, (int)y-10,(int)0.8*Assets.cloak_b.getWidth(),(int)0.9*Assets.cloak_b.getHeight(),null);
        g.drawImage(Assets.counter[c], (int)x, (int)y,(int)0.8*Assets.counter[c].getWidth(),(int)0.9*Assets.counter[c].getHeight(),null);
        g.drawImage(Assets.cloak_f, (int)x-5, (int)y-10,(int)0.8*Assets.cloak_f.getWidth(),(int)0.9*Assets.cloak_f.getHeight(),null);
    }

    @Override
    public int getUltLoad() {
        return ULT_LOAD;
    }

}