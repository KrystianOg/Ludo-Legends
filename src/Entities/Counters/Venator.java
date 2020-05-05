package Entities.Counters;

import Entities.HUD.UltimateBar;
import Entities.PositionOnMap;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.*;

public class Venator extends Counter {
    //łowca - zbija pionki na kilka kratek przed sobą podczas ulta
    public static final int BOWR_FX=-3,BOWR_FY=-12;
    private static final int BOWR_BX=-2,BOWR_BY=-8,
                             BOWL_FX=-7,BOWL_FY=-12,
                             BOWL_BX=22,BOWL_BY=-8;

    private final int ULT_LOAD=80;

    private final double velocity=1;

        //

    public Venator(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        //spec
        ultBar=true;
        canKill=true;
        killable=true;
        vulnerable=true;
        //
        ultimateBar=new UltimateBar(handler,this,ULT_LOAD,barPos);
        ultimateBar.loadCounterImages(bow_rf,bow_rb,BOWR_FX,BOWR_FY,BOWR_BX,BOWR_BY);
    }

    @Override
    protected void counterLogic() {





    }

    @Override
    public boolean ifStepped() {
        return true;
    }

    @Override
    public void render(Graphics g) {

        if(pos==null||pos.tile>37||pos.tile<12||cisinbase)
            g.drawImage(bow_rb,(int)x+(int)(BOWR_BX*SCALE),(int)y+(int)(BOWR_BY*SCALE),(int)(bow_rb.getWidth()*SCALE),(int)(bow_rb.getHeight()*SCALE),null);
        else
            g.drawImage(bow_lb,(int)x+(int)(BOWL_BX*SCALE),(int)y+(int)(BOWL_BY*SCALE),(int)(bow_lb.getWidth()*SCALE),(int)(bow_lb.getHeight()*SCALE),null);

        g.drawImage(counterColor, (int)x, (int)y,hitbox.width,hitbox.height,null);

        if(pos==null||pos.tile>37||pos.tile<12||cisinbase)
            g.drawImage(bow_rf,(int)x+(int)(BOWR_FX*SCALE),(int)y+(int)(BOWR_FY*SCALE),(int)(bow_rf.getWidth()*SCALE),(int)(bow_rf.getHeight()*SCALE),null);
        else
            g.drawImage(bow_lf,(int)x+(int)(BOWL_FX*SCALE),(int)y+(int)(BOWL_FY*SCALE),(int)(bow_lf.getWidth()*SCALE),(int)(bow_lf.getHeight()*SCALE),null);
    }

    private void arrowTick(){



    }
}
