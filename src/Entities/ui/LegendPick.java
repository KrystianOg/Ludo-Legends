package Entities.ui;

import Entities.Entity;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LegendPick extends Entity {

    public static final int LEGEND_WINDOW_W=190,
                            LEGEND_WINDOW_H=190,
                            LEGEND_WINDOW_X=300,
                            LEGEND_WINDOW_Y=200;

    private static final int SPACING=10;

    private final CounterTile[] counterTile;

    private final BufferedImage counter;

    public LegendPick(Handler handler,BufferedImage counter) {
        super(handler,0,0,LEGEND_WINDOW_W,LEGEND_WINDOW_H);
        //super(handler,(handler.getFrameWidth()-LEGEND_WINDOW_W)/2,(handler.getFrameHeight()-LEGEND_WINDOW_H)/2, LEGEND_WINDOW_W,LEGEND_WINDOW_H);

        this.counter=counter;

        counterTile=new CounterTile[8];

        counterTile[0]=new CounterTile(handler,LEGEND_WINDOW_W+0,LEGEND_WINDOW_H+0,counter);
        counterTile[1]=new CounterTile(handler,LEGEND_WINDOW_W+CounterTile.COUNTER_TILE_WIDTH+SPACING,LEGEND_WINDOW_H+0,counter);
        counterTile[2]=new CounterTile(handler,LEGEND_WINDOW_W+(CounterTile.COUNTER_TILE_WIDTH+SPACING)*2,LEGEND_WINDOW_H+0,counter);
        counterTile[3]=new CounterTile(handler,LEGEND_WINDOW_W+(CounterTile.COUNTER_TILE_WIDTH+SPACING)*3,LEGEND_WINDOW_H+0,counter);
        counterTile[4]=new CounterTile(handler,LEGEND_WINDOW_W+0,LEGEND_WINDOW_H+CounterTile.COUNTER_TILE_HEIGHT+SPACING,counter);
        counterTile[5]=new CounterTile(handler,LEGEND_WINDOW_W+CounterTile.COUNTER_TILE_WIDTH+SPACING,LEGEND_WINDOW_H+CounterTile.COUNTER_TILE_HEIGHT+SPACING,counter);
        counterTile[6]=new CounterTile(handler,LEGEND_WINDOW_W+(CounterTile.COUNTER_TILE_WIDTH+SPACING)*2,LEGEND_WINDOW_H+CounterTile.COUNTER_TILE_HEIGHT+SPACING,counter);
        counterTile[7]=new CounterTile(handler,LEGEND_WINDOW_W+(CounterTile.COUNTER_TILE_WIDTH+SPACING)*3,LEGEND_WINDOW_H+CounterTile.COUNTER_TILE_HEIGHT+SPACING,counter);

    }

    @Override
    public void tick() {

        counterTile[0].tick();
        counterTile[1].tick();
        counterTile[2].tick();
        counterTile[3].tick();
        counterTile[4].tick();
        counterTile[5].tick();
        counterTile[6].tick();
        counterTile[7].tick();

    }

    @Override
    public void render(Graphics g) {

        g.fillRect((int)x,(int)y,LEGEND_WINDOW_W,LEGEND_WINDOW_H);

        counterTile[0].render(g);
        counterTile[1].render(g);
        counterTile[2].render(g);
        counterTile[3].render(g);
        counterTile[4].render(g);
        counterTile[5].render(g);
        counterTile[6].render(g);
        counterTile[7].render(g);

    }
}
