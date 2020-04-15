package Entities.ui;

import Entities.Counters.Counter;
import Entities.Entity;
import ludogame.Handler;

import java.awt.*;

public class CounterTile extends Entity {

    private  static final int COUNTER_TILE_WIDTH=90,
                              COUNTER_TILE_HEIGHT=90;

    CounterTile(Handler handler, float x, float y, Counter counter){
        super(handler,x,y,COUNTER_TILE_WIDTH,COUNTER_TILE_HEIGHT);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
