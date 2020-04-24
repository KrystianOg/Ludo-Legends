package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Venator extends Counter {
    //łowca -?

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
        g.drawImage(counterColor, (int)x, (int)y,null);
        ultimateBar.render(g);
    }
}
