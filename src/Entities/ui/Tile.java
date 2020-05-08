package Entities.ui;

import Entities.Counters.Counter;
import GFX.Assets;
import ludogame.Handler;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Tile {

    private final boolean capturable;
    private boolean instantKill;
    private final float x,y;

    private final int[] shiftX={-6,44-(int)(Counter.DEFAULT_WIDTH*0.65),-6,44-(int)(Counter.DEFAULT_WIDTH*0.65)};
    private final int[] shiftY={8,8,36,36};

    private final Handler handler;
    private final List<Counter> counter=new LinkedList<>();

    private int ticks=0,i=0;

    public Tile(Handler handler,int x,int y,boolean capturable){
        this.handler=handler;
        this.x=x;
        this.y=y;
        this.capturable=capturable;
        instantKill=false;
    }

    public void setCounterOnTile(Counter counter){

        if(instantKill&&counter.isVulnerable()){
            counter.resetToBase();
        }
        else if(this.counter.isEmpty())
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
                    this.counter.get(i).renderSmall(x+shiftX[i],y+shiftY[i]);
            }
        }
        else if(this.counter.size()>1&&this.counter.size()<4){
            this.counter.add(counter);
            for(int i=0;i<this.counter.size();i++)
                this.counter.get(i).renderSmall(x+shiftX[i],y+shiftY[i]);
        } else if (this.counter.size() == 4) {
            counter.resetToBase();
        }
    }

    public void removeCounterFromTile(Counter counter){
            this.counter.remove(counter);
            if(this.counter.size()==0)
                return;

            if(this.counter.size()==1) {
                this.counter.get(0).renderBig(x + 4, y - 48);
            }else{
                for(int i=0;i<this.counter.size();i++){
                    this.counter.get(i).renderSmall(x+shiftX[i],y+shiftY[i]);
                }

            }
    }

    public void renderFire(Graphics g){
        ticks++;
        if(ticks==10){
            i++;
            if(i==Assets.fire.length)
                i=0;
            ticks=0;
        }

        g.drawImage(Assets.fire[i],(int)x,(int)y-20,50,60,null);
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

    public boolean isCapturable(){
        return this.capturable;
    }

    public void killAll(){
        for(int i=0;i<counter.size();i++){
            counter.get(i).resetToBase();
        }
        counter.clear();
    }


}
