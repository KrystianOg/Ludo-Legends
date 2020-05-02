package Entities.ui;

import Entities.Counters.Counter;
import ludogame.Handler;

import java.util.LinkedList;
import java.util.List;

public class Tile {

    private final boolean capturable;
    private boolean instantKill;
    private final float x,y;

    private final int[] shiftX={-2,50-(int)(Counter.DEFAULT_WIDTH*0.65),-2,50-(int)(Counter.DEFAULT_WIDTH*0.65)};
    private final int[] shiftY={8,8,21,21};

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

            if(counter.isMoving()){
                this.counter.add(counter);
            }
            else if(capturable &&this.counter.get(0).getCounterColor()!=counter.getCounterColor()&&this.counter.get(0).ifStepped()&&counter.canKill()){
                this.counter.get(0).resetToBase();
                this.counter.clear();
                this.counter.add(counter);
                handler.getPlayer().addBeat();
                handler.getPlayer().rollsPlusOne();
                handler.getPlayer().removeLastMove();
                System.out.println("Player: "+handler.getPlayer()+" Beats= "+handler.getPlayer().getBeats());
            }
            else{
                this.counter.add(counter);
                for(int i=0;i<this.counter.size();i++)
                    this.counter.get(i).renderSmall((int)x+shiftX[i],(int)x+shiftY[i]);
            }
        }
        else if(this.counter.size()>1&&this.counter.size()<4){
            this.counter.add(counter);
            for(int i=0;i<this.counter.size();i++)
                this.counter.get(i).renderSmall((int)x+shiftX[i],(int)y+shiftY[i]);
        } else if (this.counter.size() == 4) {
            counter.resetToBase();
        }
    }

    public void removeCounterFromTile(Counter counter){
            this.counter.remove(counter);
            if(this.counter.size()==0)
                return;

            if(this.counter.size()==1) {
                this.counter.get(0).renderBig(this.x + 4, this.y - 48);
            }else{
                for(int i=0;i<this.counter.size();i++){
                    this.counter.get(i).renderSmall(shiftX[i],shiftY[i]);
                }

            }
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

    public int getCounterListLength(){
        return this.counter.size();
    }

    public Counter getCounter(int i){
        return this.counter.get(i);
    }


}
