package Entities.Counters;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Altair extends Counter{
    public Altair(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler, x, y, counterColor);
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public boolean ifStepped() {
        return false;
    }

    @Override
    public void render(Graphics g) {

    }
}
