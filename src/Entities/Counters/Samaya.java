package Entities.Counters;

import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Samaya extends Counter {
    //passive, nie zbija ale jej tez nie das sie zbic

    public static final int SWAN_X=5,SWAN_Y=26;

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
        g.drawImage(Assets.swan,(int)x+SWAN_X,(int)y+SWAN_Y,null);
    }
}
