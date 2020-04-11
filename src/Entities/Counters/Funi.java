package Entities.Counters;

import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;
import states.GameState;

import java.awt.*;

public class Funi extends Counter{
    //tworzy ogien na randomowych polach

    Handler handler;
    int c;

    public Funi(Handler handler, int x, int y,int c) {
        super(handler,x, y,Counter.DEFAULT_WIDTH,Counter.DEFAULT_HEIGHT);
        this.c=c;
    }

    @Override
    public void tick() {
        y+=2;
        hitbox.y+=2;
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cloak_b,(int)(x-5),(int)(y-10),null);
        g.drawImage(Assets.counter[c],(int)x,(int) y,null);
        g.drawImage(Assets.cloak_f,(int)(x-5),(int)(y-10),null);
    }

    @Override
    public void move() {

    }
}
