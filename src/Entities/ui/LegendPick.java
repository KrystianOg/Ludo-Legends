package Entities.ui;

import Entities.Counters.*;
import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LegendPick extends Entity {

    public static final int LEGEND_WINDOW_W=390,
                            LEGEND_WINDOW_H=190,
                            LEGEND_WINDOW_Y=280;

    public static final double SCALING=0.75;

    private final CounterTile[] counterTile;

    private int choosen;


    public LegendPick(Handler handler,BufferedImage counter) {
        super(handler,(handler.getFrameWidth()-LEGEND_WINDOW_W)/2,LEGEND_WINDOW_Y,LEGEND_WINDOW_W,LEGEND_WINDOW_H);

        this.choosen=0;
        counterTile=new CounterTile[8];

        int SPACING = 10;
        counterTile[0]=new CounterTile(handler,x+0,y+0,Albali.CLOAK_POSX,Albali.CLOAK_POSY,counter,Assets.cloak_f,Assets.cloak_b);
        counterTile[1]=new CounterTile(handler,x+CounterTile.COUNTER_TILE_WIDTH+ SPACING,y+0, Funi.WAND_POSX,Funi.WAND_POSY, counter,Assets.wand);
        counterTile[2]=new CounterTile(handler,x+(CounterTile.COUNTER_TILE_WIDTH+ SPACING)*2,y+0, Intan.SHIELD_POSX,Intan.SHIELD_POSY,counter,Assets.shield);
        counterTile[3]=new CounterTile(handler,x+(CounterTile.COUNTER_TILE_WIDTH+ SPACING)*3,y+0,Mira.MEDKIT_POSX,Mira.MEDKIT_POSY,counter,Assets.medkit);
        counterTile[4]=new CounterTile(handler,x+0,y+CounterTile.COUNTER_TILE_HEIGHT+ SPACING, Polaris.ARMORF_X,Polaris.ARMORF_Y,counter,Assets.armor_f[0]);
        counterTile[5]=new CounterTile(handler,x+CounterTile.COUNTER_TILE_WIDTH+ SPACING,y+CounterTile.COUNTER_TILE_HEIGHT+ SPACING,Samaya.SWAN_X,Samaya.SWAN_Y,counter,Assets.swan);
        counterTile[6]=new CounterTile(handler,x+(CounterTile.COUNTER_TILE_WIDTH+ SPACING)*2,y+CounterTile.COUNTER_TILE_HEIGHT+ SPACING,0, Saph.SWORD_POSY,counter,Assets.sword);
        counterTile[7]=new CounterTile(handler,x+(CounterTile.COUNTER_TILE_WIDTH+ SPACING)*3,y+CounterTile.COUNTER_TILE_HEIGHT+ SPACING,Venator.BOWR_FX,Venator.BOWR_FY,counter,Assets.bow_rf);

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
