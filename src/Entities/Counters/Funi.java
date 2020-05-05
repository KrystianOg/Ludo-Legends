package Entities.Counters;

import Entities.PositionOnMap;
import Entities.ui.Tile;
import Entities.HUD.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static GFX.Assets.wand;

public class Funi extends Counter{
    //tworzy ogien na randomowych polach
    //zmienia boolean na tiles pobiera tiles i tworzy grafike

    public static final int FIRE_ROUNDS=1;

    private final int WAND_WIDTH = 30;
    private final int WAND_HEIGHT = 80;

    public static final int WAND_POSX=24,WAND_POSY=-3;
    private final int ULT_LOAD=46; //zmienic na zdefiniowane w klasie abstrakcyjnej
    private final Tile[] fireTile=new Tile[2];

    public Funi(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        //spec
        ultBar=true;
        killable=true;
        canKill=true;
        vulnerable=false;
        //
        ultimateBar=new UltimateBar(handler,this,ULT_LOAD,barPos);

        ultimateBar.loadCounterImage(wand,WAND_POSX,WAND_POSY);
    }

    @Override
    protected void counterLogic() {

        List<Integer> arr=new LinkedList<>();
        for(int i=0;i<52;i++){
            if(handler.getTile(new PositionOnMap(i)).isCapturable()&&handler.getTile(new PositionOnMap(i)).getCounterListLength()==0&&!handler.getTile(new PositionOnMap(i)).getInstantKill()){
                arr.add(i);
            }
        }

        Collections.shuffle(arr);

        for(int i=0;i<fireTile.length;i++){
            fireTile[i]=handler.getTile(new PositionOnMap(arr.get(i)));
            fireTile[i].setInstantKill(true);
        }
        handler.getPlayer().setFireTiles(fireTile);
        ultimateAbility=false;
    }

    @Override
    public boolean ifStepped() {
        return true;
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(counterColor, (int)x, (int)y,hitbox.width,hitbox.height,null);

        g.drawImage(wand,(int)x+(int)(WAND_POSX*SCALE),(int)y-(int)(WAND_POSY*SCALE), (int)(WAND_WIDTH*SCALE), (int)(WAND_HEIGHT*SCALE),null);

    }
}

