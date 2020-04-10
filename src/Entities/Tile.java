package Entities;

public class Tile {

    public static final int TILE_WIDTH=50,
                            TILE_HEIGHT=50;


    public boolean capturable;

    public int x,y;

    public int direction;

    Tile(int x,int y,int direction,boolean capturable){
        this.x=x;
        this.y=y;
        this.direction=direction;
        this.capturable=capturable;
    }

    Tile(int x,int y,boolean capturable){
        this.x=x;
        this.y=y;
        this.direction=-1;
        this.capturable=capturable;
    }


    public int getDirection(){
        return this.direction;
    }

    public boolean getCapturable(){
        return this.capturable;
    }


}
