package Entities.Players;
import Entities.Player;
import ludogame.Game;

import java.awt.*;


public class Person extends Player {

    private Game game;

    private int mx=-1,my=-1;
    private static final int ANIM_TICKS=25;
    private int tickcount=-1;

    int i=-1;

    public Person( Game game,int starting_pos) {
        super(starting_pos);
        this.game = game;
    }

    @Override
    public void tick() {

        if(i<0) {
            i = getInput();

        }

        if(game.getMousemanager().getX()!=mx&&game.getMousemanager().getY()!=my&&tickcount<0){
            setMx(game.getMousemanager().getX());
            setMy(game.getMousemanager().getY());
            tickcount=0;
        }
        else if(tickcount>=0&&i>=0&&tickcount<=ANIM_TICKS){
            counter[i].tick();
            tickcount++;
        }
        else if(tickcount>ANIM_TICKS){
            resetTick();
            //counter[i].setPosonmap(1);
            game.getMousemanager().reset();
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
        this.i=-1;
        this.mx=-1;
        this.my=-1;
    }

    private int getInput() {

        if(counter[0].hitbox.contains(game.getMousemanager().getX(),game.getMousemanager().getY()))
           return 0;

        else if(counter[1].hitbox.contains(game.getMousemanager().getX(),game.getMousemanager().getY()))
            return 1;
        else if(counter[2].hitbox.contains(game.getMousemanager().getX(),game.getMousemanager().getY()))
            return 2;
        else if(counter[3].hitbox.contains(game.getMousemanager().getX(),game.getMousemanager().getY()))
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
