package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lich extends Counter {
    //wskrzesza na 3 rundy, jak dojdzie do kona to zalicza jak nie to dead / zbijable?

    protected final int ULT_LOAD=50;

    public Lich(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y, counterColor);

        ultimateBar=new UltimateBar(handler,ULT_LOAD);
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
