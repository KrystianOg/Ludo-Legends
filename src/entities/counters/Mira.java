package entities.counters;

import entities.HUD.UltimateBar;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import static GFXandEffects.Assets.medkit;

public class Mira extends Counter {
    // moze wskrzesic pionek po naladowaniu ult

    public static final int MEDKIT_POSX=4,MEDKIT_POSY=38;
    private final int ULT_LOAD=30;

    public Mira(Handler handler, float x, float y, BufferedImage counterColor,int barPos) {
        super(handler,x, y,counterColor);
        //
        ultBar=true;
        killable=true;
        canKill=true;
        vulnerable=true;
        //
        ultimateBar=new UltimateBar(handler,this,ULT_LOAD,barPos);

        ultimateBar.loadCounterImage(medkit,MEDKIT_POSX,MEDKIT_POSY);
    }

    @Override
    protected void counterLogic() {

            List<Counter> beatenCounters=new LinkedList<>();
            for(int i=0;i<4;i++){
                if(handler.getPlayer().getCounter(i).isBeaten()&&handler.getPlayer().getCounter(i).isInbase())
                    beatenCounters.add(handler.getPlayer().getCounter(i));
            }

            int i=(int)(Math.random()*beatenCounters.size());

            if(beatenCounters.size()>0)
                beatenCounters.get(i).setMoving(true);

            ultimateAbility=false;
    }

    @Override
    public boolean ifStepped() {
        return true;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(counterColor, (int)x, (int)y,hitbox.width,hitbox.height,null);
        g.drawImage(medkit, (int)x+(int)(MEDKIT_POSX*SCALE), (int)y+(int)(MEDKIT_POSY*SCALE),(int)(medkit.getWidth()*SCALE),(int)(medkit.getHeight()*SCALE),null);


    }
}
