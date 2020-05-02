package Entities.Counters;

import Entities.ui.UltimateBar;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lich extends Counter {
    //wskrzesza na 3 rundy, jak dojdzie do kona to zalicza jak nie to dead / zbijable?

    protected final int ULT_LOAD=52;

    public Lich(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y, counterColor);

        ultimateBar=new UltimateBar(handler,ULT_LOAD,barPos);
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
        g.drawImage(counterColor, (int)x, (int)y,null);
    }

}
