package Entities.Counters;

import GFX.Assets;
import ludogame.Game;

import java.awt.*;

public class Funi extends Counter{
    //tworzy ogien na randomowych polach

    Game game;

    public Funi(Game game, int x, int y) {
        super(x, y,Counter.DEFAULT_WIDTH,Counter.DEFAULT_HEIGHT);
        this.game=game;
    }

    @Override
    public void tick() {
        y+=2;
        hitbox.y+=2;
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cloak_b,(int)(x-5),(int)(y-10),null);
        g.drawImage(Assets.counter_y,(int)x,(int) y,null);
        g.drawImage(Assets.cloak_f,(int)(x-5),(int)(y-10),null);
    }

    @Override
    public void move() {

    }
}
