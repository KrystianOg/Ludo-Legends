package Entities.Players;
import Entities.Counters.Counter;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Player {

    public  Counter[] counter;      //zmienic na private / protected

    protected int currentlyinbase;

    protected int points=0;

    protected boolean isinbase;
    protected int starting_pos;
    protected int ending_pos;

    protected Handler handler;
    protected BufferedImage counterColor;

    protected boolean clicked;
    protected int counterNr=-1;

    public Player(Handler handler,int starting_pos,int ending_pos,BufferedImage counterColor){

        this.starting_pos=starting_pos;
        this.ending_pos=ending_pos;

        counter=new Counter[4];
        counter[0]=null;
        counter[1]=null;
        counter[2]=null;
        counter[3]=null;

        this.isinbase=true;
        this.counterColor=counterColor;

        currentlyinbase=4;

    }

    public void setCounters(Counter[] counters){
        this.counter=counters;
    }

    public void setCounter(Counter counter){
        for(int i=0;i<4;i++){
            if(this.counter[i]==null) {
                this.counter[i] = counter;
                break;
            }
        }
    }

    public boolean isIsinbase() {
        return isinbase;
    }

    public void setIsinbase(boolean isinbase) {
        this.isinbase = isinbase;
    }


    public int getStarting_pos() {
        return starting_pos;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void setClicked(boolean clicked){
        this.clicked=clicked;
    }

    public void resetCounterNr(){
        this.counterNr=-1;
    }

    public void setPoints(int points){
        this.points+=points;
    }

    public int getPoints(){
        return this.points;
    }
}
