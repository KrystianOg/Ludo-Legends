package Entities.Counters;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Samaya extends Counter {
    //passive, nie zbija ale jej tez nie das sie zbic

    public Samaya(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y,counterColor);
        this.ultBar=false;
        ultimateBar=null;
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor, (int)x, (int)y,null);
    }
}
