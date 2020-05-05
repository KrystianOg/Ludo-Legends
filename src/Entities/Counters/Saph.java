package Entities.Counters;

import Entities.HUD.UltimateBar;
import Entities.PositionOnMap;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.sword;
import static GFX.Assets.wand;

public class Saph extends Counter{
    //wojownik
    public static final int SWORD_POSY=11;
    private static final int ULT_LOAD=70;
    private final int TILES_AFFECTED=3;
    private int uses=TILES_AFFECTED;

    public Saph(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        //spec
        ultBar=true;
        canKill=true;
        killable=true;
        vulnerable=true;
        //
        ultimateBar=new UltimateBar(handler,this,ULT_LOAD,barPos);
        ultimateBar.loadCounterImage(sword,0,SWORD_POSY);
    }

    @Override
    protected void counterLogic() {
        if(uses>0&&ultimateAbility){
            handler.getTile(pos).killAll();
            uses--;
        }
        else{
            uses=TILES_AFFECTED;
            ultimateAbility=false;
        }
    }

    @Override
    public boolean ifStepped() {
        return true;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor, (int)x, (int)y,hitbox.width,hitbox.height,null);
        g.drawImage(sword, (int)x, (int)y +(int)(SWORD_POSY*SCALE),(int)(sword.getWidth()*SCALE),(int)(sword.getHeight()*SCALE),null);
    }

}

