package entities.counters;

import entities.HUD.UltimateBar;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFXandEffects.Assets.cloak_b;
import static GFXandEffects.Assets.cloak_f;

public class Albali extends Counter {
    //zbija wszystkie pionki na kolejnych polach (ilosc pól TILES AFFECTED)

    public static final int CLOAK_POSX=-5,CLOAK_POSY=-10;
    private final int ULT_LOAD=25;
    private final int ROUNDS_AFFECTED=3;
    private boolean first=true;
    private int resetOn;

    public Albali(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        //spec
        ultBar=true;
        killable=true;
        canKill=true;
        vulnerable=true;
        //
        ultimateBar=new UltimateBar(handler,this,ULT_LOAD,barPos);
        ultimateBar.loadCounterImages(cloak_f,cloak_b,CLOAK_POSX,CLOAK_POSY,CLOAK_POSX,CLOAK_POSY);
    }

    @Override
    protected void counterLogic() {

        if(resetOn<=handler.getGameState().getRound()&&!first){
            vulnerable=true;
            killable=true;
            first=true;

            ultimateAbility=false;
        }
        else if(ultimateAbility){
            vulnerable=false;
            killable=false;

            if(first){
                resetOn=handler.getGameState().getRound()+ROUNDS_AFFECTED;
                first=false;
            }
        }
    }

    @Override
    public boolean ifStepped() {
        return killable;
    }

    @Override
    public void render(Graphics g) {

            g.drawImage(cloak_b, (int) x + (int) (CLOAK_POSX * SCALE), (int) y + (int) (CLOAK_POSY * SCALE), (int) (cloak_b.getWidth() * SCALE), (int) (cloak_b.getHeight() * SCALE), null);
            if(!ultimateAbility)
            g.drawImage(counterColor, (int) x, (int) y, hitbox.width, hitbox.height, null);
            g.drawImage(cloak_f, (int) x + (int) (CLOAK_POSX * SCALE), (int) y + (int) (CLOAK_POSY * SCALE), (int) (cloak_f.getWidth() * SCALE), (int) (cloak_f.getHeight() * SCALE), null);

    }
}
