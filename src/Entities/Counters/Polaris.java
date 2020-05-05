package Entities.Counters;

import Entities.HUD.UltimateBar;
import ludogame.Handler;
import states.SettingState;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.*;

public class Polaris extends Counter {
    //TANK - powolny ale 3 "życia"
    //ruch 1-1, 2-1, 3-2, 4-2, 5-3, 6-3

    public static final int ARMORF_X=-10,ARMORF_Y=25;
    private static final int ARMORB_X=-10,ARMORB_Y=25;


    private int armorLvl=1;
    private boolean hasArmor=true;
    private boolean first;
    //private final int ULT_LOAD=60;

    public Polaris(Handler handler, float x, float y,BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        ANIM_TICKS=(int)(0.57* SettingState.FPS);

        //spec
        ultBar=false;
        canKill=true;
        killable=true;
        vulnerable=true;
        first=true;
        //
        ultimateAbility=true;
    }

    @Override
    protected void counterLogic() {
        if(first) {
            int i = (int) (Math.ceil((double) handler.getRoll() / 2));
            handler.getDice().setRoll(i);
            if(i>1) first=false;
        }
        else if(moved==handler.getRoll()-1)
            first=true;

        if(pos.arr>0)
            ultimateAbility=false;
    }

    @Override
    public boolean ifStepped() {
        if(hasArmor) {

            if(armorLvl==1)
            armorLvl--;
            else if(armorLvl==0)
                hasArmor=false;

            return false;
        }
        else {
            armorLvl=1;
            hasArmor=true;
            return true;
        }
    }

    @Override
    public void render(Graphics g) {

        if(hasArmor)
        g.drawImage(armor_b[1-armorLvl],(int)x+(int)(ARMORB_X*SCALE),(int)y+(int)(ARMORB_Y*SCALE),(int)(armor_b[1-armorLvl].getWidth()*SCALE),(int)(armor_b[1-armorLvl].getHeight()*SCALE),null);

        g.drawImage(counterColor, (int)x, (int)y,hitbox.width,hitbox.height,null);

        if(hasArmor)
        g.drawImage(armor_f[1-armorLvl],(int)x+(int)(ARMORF_X*SCALE),(int)y+(int)(ARMORF_Y*SCALE),(int)(armor_f[1-armorLvl].getWidth()*SCALE),(int)(armor_f[1-armorLvl].getHeight()*SCALE),null);
    }
}
