package Entities.ui;

import java.awt.*;

import Entities.Entity;
import GFX.Assets;
import ludogame.Handler;
import states.GameState;

public class UltimateBar extends Entity {

    public static final int ULTIMATEBAR_WIDTH=200, ULTIMATEBAR_HEIGHT=60;

    private boolean ready=false;
    private static int ULT_LOAD;

    private float loadPercentage;

    public UltimateBar(Handler handler,int ULT_LOAD) {

        super(handler,(float)765, (float)55+3*60, ULTIMATEBAR_WIDTH, ULTIMATEBAR_HEIGHT);
        this.ULT_LOAD=ULT_LOAD;
    }

    @Override
    public void tick() {
        if(handler.getPlayer().getPoints()<=ULT_LOAD) {
            this.loadPercentage = (handler.getPlayer().getPoints() /  (float)ULT_LOAD*100);
        }
        else{
            this.loadPercentage=(float)100;
            this.ready=true;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.progressbar_b,(int)x,(int)y,null);
        g.setColor(GameState.color[handler.getGameState().getTurnof()]);
        g.fillRect((int)x+7,(int)y+7,(int)(186*loadPercentage/100),26);
        g.drawImage(Assets.progressbar_f,(int)x,(int)y,null);

    }
}