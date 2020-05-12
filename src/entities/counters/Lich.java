package entities.counters;

import entities.HUD.UltimateBar;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lich extends Counter {

    /*
    +SPELL: On Ally: pushes 5 squares forward
	On foe:  pulls 5 squares back
     */

    protected final int ULT_LOAD=52;

    public Lich(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y, counterColor);

        ultimateBar=new UltimateBar(handler,this,ULT_LOAD,barPos);
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
