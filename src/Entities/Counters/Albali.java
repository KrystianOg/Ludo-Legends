package Entities.Counters;

import Entities.ui.UltimateBar;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.cloak_b;
import static GFX.Assets.cloak_f;

public class Albali extends Counter {
    //zbija wszystkie ktore minie podczas trwania umiejetnosci;
    //co ruch sprawdza czy sa pionki

    public static final int CLOAK_POSX=-5,CLOAK_POSY=-10;
    private static final int ULT_LOAD=40;

    public Albali(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        this.ultBar=true;

        killable=true;
        canKill=true;

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
        g.drawImage(cloak_b, (int)x+(int)(CLOAK_POSX*SCALE), (int)y+(int)(CLOAK_POSY*SCALE),(int)(cloak_b.getWidth()*SCALE),(int)(cloak_b.getHeight()*SCALE),null);
        g.drawImage(counterColor, (int)x, (int)y,hitbox.width,hitbox.height,null);
        g.drawImage(cloak_f, (int)x+(int)(CLOAK_POSX*SCALE), (int)y+(int)(CLOAK_POSY*SCALE),(int)(cloak_f.getWidth()*SCALE),(int)(cloak_f.getHeight()*SCALE),null);
    }
}
