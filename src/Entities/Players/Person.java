package Entities.Players;
import ludogame.Handler;
import states.SettingState;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Person extends Player {

    private int input;

    private final int ANIM_TICKS=(int)(0.42* SettingState.FPS);

    public Person(Handler handler, int startingPos,int endingPos, BufferedImage counterColor) { //zmienic na getter
        super(handler,startingPos,endingPos,counterColor);
        input=-1;
    }

    @Override
    public void tick() {

        if(!counterIsMoving())
            moveLogic();

        for(int i=0;i<counter.length;i++)
            counter[i].tick();

    }

    @Override
    public void render(Graphics g) {

        for(int i=0;i<counter.length;i++)
            counter[i].render(g);

    }

    private void moveLogic(){
        if(!handler.getDice().isRolled()) {
            handler.getDice().tick();
            handler.getTimer().tick();
        }

        else{
            if(isinbase&&handler.getRoll()!=6) {
                handler.setTurnof();
                handler .getDice().setRolled(false);
                handler.getTimer().resetTimer();
            }
            else if(handler.getRoll()==6) {
                input=getInput();

                if(input>=0)
                    counter[input].setMoving(true);

            }
            else if(!isinbase&&handler.getRoll()<6){
                input=getInput();

                if(input>=0) {
                    if(!counter[input].isInbase())
                        counter[input].setMoving(true);

                }
            }

        }

    }

    private int getInput() {

        if(counter[0].hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY()))
           return 0;
        else if(counter[1].hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY()))
            return 1;
        else if(counter[2].hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY()))
            return 2;
        else if(counter[3].hitbox.contains(handler.getGame().getMousemanager().getX(),handler.getGame().getMousemanager().getY()))
            return 3;
        else
            return -1;
    }

}
