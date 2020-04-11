package Entities.Players;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Person extends Player {

    private Handler handler;

    private int mx,my;
    private static final int ANIM_TICKS=25;
    private int tickcount=-1;

    int i=-1;

    public Person(Handler handler, int starting_pos, BufferedImage ccolor) {
        super(starting_pos,ccolor);
        this.handler = handler;
        mx=-1;
        my=-1;

    }

    @Override
    public void tick() {

        counter[0].tick();
        counter[1].tick();
        counter[2].tick();
        counter[3].tick();




    /*

        if(handler.getGame().getMousemanager().getX()!=mx&&handler.getGame().getMousemanager().getY()!=my&&tickcount<0){
            setMx(handler.getGame().getMousemanager().getX());
            setMy(handler.getGame().getMousemanager().getY());
            tickcount=0;
        }
        else if(tickcount>=0&&i>=0&&tickcount<=ANIM_TICKS){
            counter[i].tick();
            tickcount++;
        }
        else if(tickcount>ANIM_TICKS){
            resetTick();
            //counter[i].setPosonmap(1);
            handler.getGame().getMousemanager().reset();
        }
*/

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
        this.i=-1;
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
