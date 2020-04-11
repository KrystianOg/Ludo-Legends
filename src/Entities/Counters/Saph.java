package Entities.Counters;

import GFX.Assets;
import ludogame.Game;
import ludogame.Handler;

import java.awt.*;

public class Saph extends Counter{
    //wojownik

    Handler handler;
    int  c;

    public Saph(Handler handler, int x, int y,int c) {
        super(handler,x, y,Counter.DEFAULT_WIDTH,Counter.DEFAULT_HEIGHT);
        this.handler=handler;
        this.c=c;
    }

    @Override
    public void tick() {
        y-=2;
        hitbox.y-=2;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.counter[c],(int)x,(int) y,null);
        g.drawImage(Assets.sword,(int)x,(int)y+11,null);
    }

    @Override
    public void move() {

    }
}
