package Entities.Counters;

import Entities.ui.UltimateBar;
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

    public Venator(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        this.ultBar=true;
        beatable=true;
        killable=true;
        ultimateBar=new UltimateBar(handler,ULT_LOAD,barPos);
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public void render(Graphics g) {
        if(pos==null||pos.tile>37||pos.tile<12||cisinbase)
            g.drawImage(bow_rb,(int)x+BOWR_BX,(int)y+BOWR_BY,null);
        else
            g.drawImage(bow_lb,(int)x+BOWL_BX,(int)y+BOWL_BY,null);

        g.drawImage(counterColor, (int)x, (int)y,null);

        if(pos==null||pos.tile>37||pos.tile<12||cisinbase)
            g.drawImage(bow_rf,(int)x+BOWR_FX,(int)y+BOWR_FY,null);
        else
            g.drawImage(bow_lf,(int)x+BOWL_FX,(int)y+BOWL_FY,null);

    }
}
