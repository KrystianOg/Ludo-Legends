package Entities.ui;

import Entities.Counters.Counter;

public class Tile {

    public static final int TILE_WIDTH=50,
                            TILE_HEIGHT=50;

    public boolean capturable;
    public float x,y;

    Counter[] counter=new Counter[4];

    public Tile(int x,int y,boolean capturable){
        this.x=x;
        this.y=y;
        this.capturable=capturable;
    }

    public void setCounter(Counter counter){
        if(this.counter[0]==null)
            this.counter[0]=counter;
        else if(this.counter[1]==null)
            this.counter[1]=counter;
        else if(this.counter[2]==null)
            this.counter[2]=counter;
        else if(this.counter[3]==null)
            this.counter[3]=counter;
        else{

            counter.setIsinbase(true);
        }


    }


    public boolean getCapturable(){
        return this.capturable;
    }
}
