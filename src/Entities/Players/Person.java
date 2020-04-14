package Entities.Players;
import ludogame.Handler;
import states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Person extends Player {

    private Handler handler;

    private int mx,my;
    private static final int ANIM_TICKS=25;
    private int tickcount=-1;

    private boolean clicked=false;

    public Person(Handler handler, int startingPos,int endingPos, BufferedImage ccolor) { //zmienic na getter
        super(startingPos,endingPos,ccolor);
        this.handler = handler;
        mx=-1;
        my=-1;
    }




    @Override
    public void tick() {

        if(!clicked) {
            counterNr = getInput();

            if(counterNr!=-1)
                clicked=true;
        }


        if(counterNr>0) {
            counter[counterNr].tick();
            System.out.println("CLICKED: "+counterNr);
        }

    }

    @Override
    public void render(Graphics g) {

        counter[0].render(g);
        counter[1].render(g);
        counter[2].render(g);
        counter[3].render(g);

    }

    @Override
    public void move() {

    }

    private void resetTick(){
        this.tickcount=-1;
        this.counterNr=-1;
        this.mx=-1;
        this.my=-1;
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

    private void setMx(int mx){
        this.mx=mx;
    }
    private void setMy(int my){
        this.my=my;
    }
}
