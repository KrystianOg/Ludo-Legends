package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Albali extends Counter {
    //zbija wszystkie ktore minie podczas trwania umiejetnosci;
    //co ruch sprawdza czy sa pionki

    public static final int CLOAK_POSX=-5,CLOAK_POSY=-10;
    private static final int ULT_LOAD=40;


    public Albali(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        this.ultBar=true;

        ultimateBar=new UltimateBar(handler,ULT_LOAD,barPos);
    }

    @Override
    protected void counterLogic() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cloak_b, (int)x+CLOAK_POSX, (int)y+CLOAK_POSY,null);
        g.drawImage(counterColor, (int)x, (int)y,null);
        g.drawImage(Assets.cloak_f, (int)x+CLOAK_POSX, (int)y+CLOAK_POSY,null);
    }
}
