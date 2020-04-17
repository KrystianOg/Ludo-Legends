package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lich extends Counter {
    //wskrzesza na 3 rundy, jak dojdzie do kona to zalicza jak nie to dead / zbijable?

    public Lich(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler,x, y, counterColor);
        ultimateBar=new UltimateBar(handler,20);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

}
