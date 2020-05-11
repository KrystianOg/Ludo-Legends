package Entities.Counters;

import Entities.HUD.Dice;
import Entities.HUD.Timer;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GFX.Assets.*;

public class Altair extends Counter{

    public static final int WINGSF_POSX=-7,WINGSF_POSY=-3;


/*
A mutant born with a pair of wings. Not long after birth, he was left near a river by his parents, as he would never be accepted by them.
Not long after that, an eagle flying by, saw little, featherless, yet bird-like being, and took him as his own child. Now 15 years later, Altair thinks of himself to be more
eagle than a human. Unfortunately, he still can't fully control his powers, often not able to control his flight, getting carried away by wind,
however no human is a match for his speed.

 */

    private final Dice dice;
    private final Timer timer;
    private boolean picked;

    public Altair(Handler handler, float x, float y, BufferedImage counterColor) {
        super(handler, x, y, counterColor);
        //
        ultBar=false;
        killable=true;
        canKill=true;
        vulnerable=true;
        ultimateAbility=true;
        //
        picked=false;

        dice=new Dice(handler,890,300,2,0);
        timer=new Timer(handler,890,300);
    }

    @Override
    protected void counterLogic() {
        if(moved==0) {
            picked = true;
            dice.botRoll();
            dice.tick();
            //handler.getDice().addSpecial(dice.getRoll());
            handler.getPlayer().rollsPlusOne();
        }
    }

    @Override
    public boolean ifStepped() {


        return true;
    }

    @Override
    public void render(Graphics g) {
        if(picked) {
            dice.tick();


            if(!dice.getClicked()) {
                handler.getDice().addSpecial(dice.getRoll());
                picked = false;
            }
        }

        if(isMoving())
            dice.render(g);

        //if(dice.)

        g.drawImage(wingsb,(int)x+(int)(WINGSF_POSX*SCALE),(int)y+(int)(WINGSF_POSY*SCALE),(int)(wingsb.getWidth()*SCALE),(int)(wingsb.getHeight()*SCALE),null);
        g.drawImage(counterColor,(int)x,(int)y,hitbox.width,hitbox.height,null);
        g.drawImage(wingsf,(int)x+(int)(WINGSF_POSX*SCALE),(int)y+(int)(WINGSF_POSY*SCALE),(int)(wingsf.getWidth()*SCALE),(int)(wingsf.getHeight()*SCALE),null);

    }
}
