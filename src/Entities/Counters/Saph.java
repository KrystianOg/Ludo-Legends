package Entities.Counters;

import GFX.Assets;
import ludogame.Game;

import java.awt.*;

public class Saph extends Counter{
    //wojownik

    Game game;

    public Saph(Game game, int x, int y) {
        super(x, y,Counter.DEFAULT_WIDTH,Counter.DEFAULT_HEIGHT);
        this.game=game;
    }

    @Override
    public void tick() {
        y-=2;
        hitbox.y-=2;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.counter_g,(int)x,(int) y,null);
        g.drawImage(Assets.sword,(int)x,(int)y+11,null);
    }

    @Override
    public void move() {

    }
}
