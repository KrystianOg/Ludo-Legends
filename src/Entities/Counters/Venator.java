package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Venator extends Counter {
    //łowca -?
    public static final int BOWR_FX=-3,BOWR_FY=-12;
    private static final int BOWR_BX=-2,BOWR_BY=-8,
                             BOWL_FX=-7,BOWL_FY=-12,
                             BOWL_BX=22,BOWL_BY=-8;




    public Venator(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        this.ultBar=true;
        ultimateBar=new UltimateBar(handler,20);
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public void render(Graphics g) {
        if(posonmap>38||posonmap<13||cisinbase)
            g.drawImage(Assets.bow_rb,(int)x+BOWR_BX,(int)y+BOWR_BY,null);
        else
            g.drawImage(Assets.bow_lb,(int)x+BOWL_BX,(int)y+BOWL_BY,null);

        g.drawImage(counterColor, (int)x, (int)y,null);

        if(posonmap>38||posonmap<13||cisinbase)
            g.drawImage(Assets.bow_rf,(int)x+BOWR_FX,(int)y+BOWR_FY,null);
        else
            g.drawImage(Assets.bow_lf,(int)x+BOWL_FX,(int)y+BOWL_FY,null);

        ultimateBar.render(g);
    }
}
