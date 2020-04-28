package Entities.ui;

import Entities.Counters.Counter;
import ludogame.Handler;

import java.util.LinkedList;
import java.util.List;

public class Tile {

    private final boolean capturable;
    private boolean instantKill;
    private final float x,y;

    private final Handler handler;
    private final List<Counter> counter=new LinkedList<>();

    public Tile(Handler handler,int x,int y,boolean capturable){
        this.handler=handler;
        this.x=x;
        this.y=y;
        this.capturable=capturable;
    }

    public void setCounterOnTile(Counter counter){
        if(this.counter.isEmpty())
            this.counter.add(counter);
        else if(this.counter.size()==1){
            if(capturable &&this.counter.get(0).getCounterColor()!=counter.getCounterColor()){
                this.counter.get(0).resetToBase();

                this.counter.clear();
                this.counter.add(counter);
                handler.getPlayer().rollsPlusOne();
            }
            else{
                this.counter.add(counter);
            }
        }
        else if(this.counter.size()>1&&this.counter.size()<4){
            this.counter.add(counter);
        } else if (this.counter.size() == 4) {
            counter.resetToBase();
        }
    }

    public void removeCounterFromTile(Counter counter){
            this.counter.remove(counter);
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void setInstantKill(boolean instantKill){
        this.instantKill=instantKill;
    }

    public boolean getInstantKill(){
        return this.instantKill;
    }

}
