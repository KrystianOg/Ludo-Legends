package Entities;

import java.awt.*;

import GFX.Assets;
import ludogame.Handler;
import states.GameState;

public class UltimateBar extends Entity{

    public static final int ULTIMATEBAR_WIDTH=200, ULTIMATEBAR_HEIGHT=60;

    public UltimateBar(Handler handler,int counterNr) {

        super(handler,(float)765, (float)55+counterNr*60, ULTIMATEBAR_WIDTH, ULTIMATEBAR_HEIGHT);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.progressbar_b,(int)x,(int)y,null);
        g.setColor(GameState.color[handler.getGameState().getTurnof()]);
        g.fillRect((int)x+7,(int)y+7,60,26);
        g.drawImage(Assets.progressbar_f,(int)x,(int)y,null);

    }
}