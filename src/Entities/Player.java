package Entities;
import Entities.Counters.Counter;

import java.awt.*;

public abstract class Player {

    protected Counter[] counter;
    protected int currentlyinbase;
    protected int starting_pos;

    public Player(int starting_pos){
        counter=new Counter[4];


        this.starting_pos=starting_pos;

        currentlyinbase=4;

    }

    public void setCounters(Counter c1,Counter c2,Counter c3,Counter c4){
        this.counter[0]=c1;
        this.counter[1]=c2;
        this.counter[2]=c3;
        this.counter[3]=c4;



    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void move();

}
