package Entities.ui;

import Entities.Entity;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LegendPick extends Entity {

    public static final int LEGEND_WINDOW_W=390,
                            LEGEND_WINDOW_H=190,
                            LEGEND_WINDOW_Y=280;

    private static final int SPACING=10;


    //przenieść to ---->
    public static final int[] COUNTER_POS_X={166,166,92,92},
                              COUNTER_POS_Y={88,162,162,88};
    // <----


    private final CounterTile[] counterTile;

    private final BufferedImage counter;
    private int choosen;


    public LegendPick(Handler handler,BufferedImage counter) {
        super(handler,(handler.getFrameWidth()-LEGEND_WINDOW_W)/2,LEGEND_WINDOW_Y,LEGEND_WINDOW_W,LEGEND_WINDOW_H);

        this.choosen=0;
        this.counter=counter;
        counterTile=new CounterTile[8];

        counterTile[0]=new CounterTile(handler,x+0,y+0,counter);
        counterTile[1]=new CounterTile(handler,x+CounterTile.COUNTER_TILE_WIDTH+SPACING,y+0,counter);
        counterTile[2]=new CounterTile(handler,x+(CounterTile.COUNTER_TILE_WIDTH+SPACING)*2,y+0,counter);
        counterTile[3]=new CounterTile(handler,x+(CounterTile.COUNTER_TILE_WIDTH+SPACING)*3,y+0,counter);
        counterTile[4]=new CounterTile(handler,x+0,y+CounterTile.COUNTER_TILE_HEIGHT+SPACING,counter);
        counterTile[5]=new CounterTile(handler,x+CounterTile.COUNTER_TILE_WIDTH+SPACING,y+CounterTile.COUNTER_TILE_HEIGHT+SPACING,counter);
        counterTile[6]=new CounterTile(handler,x+(CounterTile.COUNTER_TILE_WIDTH+SPACING)*2,y+CounterTile.COUNTER_TILE_HEIGHT+SPACING,counter);
        counterTile[7]=new CounterTile(handler,x+(CounterTile.COUNTER_TILE_WIDTH+SPACING)*3,y+CounterTile.COUNTER_TILE_HEIGHT+SPACING,counter);

    }

    @Override
    public void tick() {
        choosen=0;
        for(int i=0;i<counterTile.length;i++){
            counterTile[i].tick();
            if(counterTile[i].isChoosen())
                choosen++;
        }
    }

    @Override
    public void render(Graphics g) {


        counterTile[0].render(g);
        counterTile[1].render(g);
        counterTile[2].render(g);
        counterTile[3].render(g);
        counterTile[4].render(g);
        counterTile[5].render(g);
        counterTile[6].render(g);
        counterTile[7].render(g);

    }

    public CounterTile getCounterTile(int i){
        return this.counterTile[i];
    }

    public int getchoosen(){
        return choosen;
    }
}
