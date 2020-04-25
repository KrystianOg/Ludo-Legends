package Players;
import Entities.Counters.Counter;
import Entities.PositionOnMap;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public abstract class Player {

    protected Counter[] counter;      //zmienic na private / protected

    protected int currentlyinbase;

    protected int points=0;
    protected int rollsLeft=1;

    protected List<Integer> lastRolls=new LinkedList<>();

    protected boolean isinbase;
    protected PositionOnMap startingPos;
    protected PositionOnMap endingPos;

    protected Handler handler;
    protected BufferedImage counterColor;

    public Player(Handler handler,PositionOnMap startingPos,PositionOnMap endingPos,BufferedImage counterColor){

        this.startingPos=startingPos;
        this.endingPos=endingPos;

        this.handler=handler;

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

    public void renderUltBar(Graphics g){
        if(counter!=null) {
            for (Counter value : counter) {
                if (value.hasUltBar())
                    value.renderUltBar(g);
            }
        }
    }

    public void resetList(){
        this.lastRolls.clear();
    }

    protected boolean counterIsMoving(){
        return counter[0].isMoving()||counter[1].isMoving()||counter[2].isMoving()||counter[3].isMoving();
    }

    protected void setInBase(){
        if(counter[0].isInbase()&&counter[1].isInbase()&&counter[2].isInbase()&&counter[3].isInbase())
            isinbase=true;
    }

    public void setIsinbase(boolean isinbase) {
        this.isinbase = isinbase;
    }

    public PositionOnMap getStartingPos() {
        return this.startingPos;
    }

    public PositionOnMap getEndingPos(){
        return this.endingPos;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void setPoints(int points){
        this.points+=points;
    }

    public int getPoints(){
        return this.points;
    }

    public Counter getCounter(int i){
        return this.counter[i];
    }
//

    public int getRollsLeft(){
        return this.rollsLeft;
    }

    public void rollsPlusOne(){
        this.rollsLeft++;
    }

    public void rollsMinusOne(){
        this.rollsLeft--;
    }

    public void resetRolls(){
        this.rollsLeft=1;
    }
//
}
