package entities.ui;

import entities.counters.*;
import entities.Entity;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import static entities.ui.CounterTile.COUNTER_TILE_HEIGHT;
import static entities.ui.CounterTile.COUNTER_TILE_WIDTH;
import static GFXandEffects.Assets.*;

public class LegendPick extends Entity {

    public static final int LEGEND_WINDOW_W=390,
                            LEGEND_WINDOW_H=190,
                            LEGEND_WINDOW_Y=280;

    public static final double SCALING=0.75;
    private final int SPACING=10;

    private final List<CounterTile> counterTile=new LinkedList<>();

    private int choosen;


    public LegendPick(Handler handler,BufferedImage counter) {
        super(handler,(handler.getFrameWidth()-LEGEND_WINDOW_W)/2,LEGEND_WINDOW_Y,LEGEND_WINDOW_W,LEGEND_WINDOW_H);

        this.choosen=0;

        counterTile.add(new CounterTile(handler,x+0,y+0,Albali.CLOAK_POSX,Albali.CLOAK_POSY,counter,cloak_f,cloak_b));
        counterTile.add(new CounterTile(handler,x+ COUNTER_TILE_WIDTH+ SPACING,y+0, Funi.WAND_POSX,Funi.WAND_POSY, counter,wand));
        counterTile.add(new CounterTile(handler,x+(COUNTER_TILE_WIDTH+ SPACING)*2,y+0, Intan.SHIELD_POSX,Intan.SHIELD_POSY,counter,shield));
        counterTile.add(new CounterTile(handler,x+(COUNTER_TILE_WIDTH+ SPACING)*3,y+0,Mira.MEDKIT_POSX,Mira.MEDKIT_POSY,counter,medkit));
        counterTile.add(new CounterTile(handler,x+0,y+ COUNTER_TILE_HEIGHT+ SPACING, Polaris.ARMORF_X,Polaris.ARMORF_Y,counter,armor_f[0]));
        counterTile.add(new CounterTile(handler,x+ COUNTER_TILE_WIDTH+ SPACING,y+ COUNTER_TILE_HEIGHT+ SPACING,Samaya.SWAN_X,Samaya.SWAN_Y,counter,swan));
        counterTile.add(new CounterTile(handler,x+(COUNTER_TILE_WIDTH+ SPACING)*2,y+COUNTER_TILE_HEIGHT+ SPACING,0, Saph.SWORD_POSY,counter,sword));
        //counterTile.add(new CounterTile(handler,x+(COUNTER_TILE_WIDTH+ SPACING)*3,y+ COUNTER_TILE_HEIGHT+ SPACING,Aggitarius.BOWR_FX,Aggitarius.BOWR_FY,counter,bow_rf));
        counterTile.add(new CounterTile(handler,x+(COUNTER_TILE_WIDTH+ SPACING)*3,y+ COUNTER_TILE_HEIGHT+ SPACING,Altair.WINGSF_POSX,Altair.WINGSF_POSY,counter,wingsf,wingsb));
        //counterTile.add(new CounterTile(handler,x+(COUNTER_TILE_WIDTH+SPACING),y+(COUNTER_TILE_HEIGHT+SPACING)*2,Altair.WINGSF_POSX,Altair.WINGSF_POSY,counter,wingsf,wingsb));

    }

    @Override
    public void tick() {
        choosen=0;
        for (CounterTile tile : counterTile) {
            tile.tick();
            if (tile.isChoosen())
                choosen++;
        }
    }

    @Override
    public void render(Graphics g) {

        for(int i=0;i<counterTile.size();i++)
            counterTile.get(i).render(g);

    }

    public CounterTile getCounterTile(int i){
        return this.counterTile.get(i);
    }

    public int getchoosen(){
        return choosen;
    }

    public int getCounterTileSize(){
        return this.counterTile.size();
    }
}
