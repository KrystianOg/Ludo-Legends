package Entities.ui;

import java.awt.*;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;
import states.GameState;
import states.SettingState;

public class UltimateBar extends Entity {

    public static final int ULTIMATEBAR_WIDTH=200, ULTIMATEBAR_HEIGHT=60;

    private boolean loaded=false;
    private final int ULT_LOAD;

    private float loadPercentage;

    public UltimateBar(Handler handler,int ULT_LOAD,int barPos) {
        super(handler,(float)765, (float)55+barPos*60, ULTIMATEBAR_WIDTH, ULTIMATEBAR_HEIGHT);
        if(SettingState.ULT_LOAD==0){
            loaded=true;
            loadPercentage=100;
            this.ULT_LOAD=0;
        }
        else {
            this.ULT_LOAD = ULT_LOAD * 100 / SettingState.ULT_LOAD;
        }
    }

    @Override
    public void tick() {
        if(handler.getPlayer().getPoints()<ULT_LOAD) {
            this.loadPercentage = (handler.getPlayer().getPoints() /  (float)ULT_LOAD*100);
        }
        else{
            this.loadPercentage=(float)100;
            this.loaded=true;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.progressbar_b,(int)x,(int)y,null);
        g.setColor(GameState.color[handler.getTurnOf()]);
        g.fillRect((int)x+7,(int)y+7,(int)(186*loadPercentage/100),26);

        //animacja naladownia
        //if(ready)

        g.drawImage(Assets.progressbar_f,(int)x,(int)y,null);

    }

    public boolean isLoaded(){
        return this.loaded;
    }

    public void setUltUsed(){
        loaded=false;
    }
}