package Entities.Players;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Bot extends Player {
    public Bot(Handler handler, int startingPos, int endingPos, BufferedImage counterColor) {
        super(handler,startingPos,endingPos,counterColor);
    }

    @Override
    public void tick() {
        if(!handler.getDice().isRolled()) {

            handler.getGame().getMousemanager().setBot((int)handler.getDice().getX(),(int)handler.getDice().getY());

            handler.getDice().tick();
            handler.getTimer().tick();
        }

        else{
            if (!clicked) {
                counterNr = getInput();

                if (counterNr != -1)
                    clicked = true;
            }

            if (counterNr > 0) {
                counter[counterNr].tick();
                System.out.println("CLICKED: " + counterNr);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        counter[0].render(g);
        counter[1].render(g);
        counter[2].render(g);
        counter[3].render(g);
    }

    private int getInput(){
        return (int)(Math.random()*4+1);
    }
}
