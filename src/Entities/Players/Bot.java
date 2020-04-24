package Entities.Players;

import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;


public class Bot extends Player {

    private int input;
    private boolean clicked=false;

    public Bot(Handler handler, int startingPos, int endingPos, BufferedImage counterColor) {
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

    private void moveLogic() {

        if(!clicked){
            handler.getDice().botRoll();
            clicked=true;
        }


        if(!handler.getDice().isRolled()) {
            handler.getDice().tick();
            handler.getTimer().tick();
        }

        else{
            if (isinbase && handler.getRoll() != 6) {
                handler.setTurnof();
                handler.getDice().setRolled(false);
                handler.getTimer().resetTimer();
            } else if (handler.getRoll() == 6) {
                input = (int) (Math.random() * 4);

                if (input >= 0) {
                    if(counter[input].isInbase())
                        currentlyinbase--;


                    counter[input].setMoving(true);
                }
            } else if (!isinbase && handler.getRoll() < 6) {
                input = getOutsideBaseInput();

                if (input >= 0) {
                    if (!counter[input].isInbase()) {
                        counter[input].setMoving(true);
                    }
                }
            }

            clicked=false;
        }
    }


    private int getInBaseInput() {

        Vector<Integer> tab = new Vector<>();

        for(int i=0;i<4;i++){
            if(counter[i].isInbase())
                tab.add(i);
        }

        return tab.elementAt((int)Math.random()*tab.size());
    }

    private int getOutsideBaseInput(){
        Vector<Integer> tab = new Vector<>();

        for(int i=0;i<4;i++){
            if(!counter[i].isInbase())
                tab.add(i);
        }

        return tab.elementAt((int)Math.random()*tab.size());
    }



}
