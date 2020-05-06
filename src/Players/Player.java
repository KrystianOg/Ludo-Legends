package Players;
import Entities.Counters.Counter;
import Entities.Counters.Funi;
import Entities.PositionOnMap;
import Entities.ui.Tile;
import ludogame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Player {

    protected Counter[] counter;      //zmienic na private / protected

    protected final String nickname;

    protected int beats=0;
    protected int points=0;
    protected int ultLoad=0;
    protected int deaths=0;

    protected int rollsLeft=1;
    protected boolean clicked=false;

    protected List<Integer> lastRolls=new LinkedList<>();
    protected List<Boolean> notSix=new LinkedList<>();
    protected List<Integer> chance=new LinkedList<>();
    protected List<Tile> fireTile =new LinkedList<>();
    protected int resetFireWhileTurn=0;


    protected boolean isinbase;
    protected boolean won;

    protected final PositionOnMap startingPos;
    protected final PositionOnMap endingPos;

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

        isinbase=true;
        won=false;
        this.counterColor=counterColor;
        nickname ="";
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

    public void clearUltBarLoad(){
        for(int i=0;i<4;i++){
            if(counter[i].getUltimateBar()!=null)
            counter[i].getUltimateBar().setUltUsed();
        }
    }

    public void resetList(){
        this.lastRolls.clear();
    }

    public void removeLastMove(){
        this.lastRolls.remove(lastRolls.size()-1);
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

    public void resetUltLoad(){
        this.ultLoad=0;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void setBotClicked(){
        this.clicked=false;
    }

    public void renderInBaseCounters(Graphics g){
        render(g);

        if(counter!=null){
            if(counter[3].isInbase()||counter[3].isMoving())
                counter[3].render(g);
            for(int i=0;i<counter.length-1;i++){
                if(counter[i].isInbase()||counter[i].isMoving())
                    counter[i].render(g);
            }


        }
    }

    public int getPoints(){
        return this.points;
    }

    public int getUltLoad(){
        return this.ultLoad;
    }

    public Counter getCounter(int i){
        return this.counter[i];
    }

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

    public List<Integer> getChance(){
        return this.chance;
    }

    protected void notSixLogic(){
        notSix.add(true);
        if(notSix.size()>4){
            chance.add(6);
        }
    }

    public void addBeat(){
        this.beats++;
    }

    public int getBeats(){
        return this.beats;
    }

    public void addPoint(){
        this.points++;
        this.ultLoad++;
    }

    public boolean isThird(){
        return lastRolls.size() == 2 && lastRolls.get(0) == 6 && lastRolls.get(1) == 6;
    }

    public void addDeath(){
        this.deaths++;
    }

    public int getDeaths(){
        return this.deaths;
    }


    public PlayerData getPlayerData() {
    	PlayerData dane = new PlayerData(nickname, points, beats);
    	return dane;
    }


    public void setFireTiles(Tile[] fireTile){
        this.fireTile.addAll(Arrays.asList(fireTile));

        resetFireWhileTurn=handler.getGameState().getRound()+ Funi.FIRE_ROUNDS;
    }

    public void renderFire(Graphics g){
        if(fireTile.size()>0)
            for (Tile tile : fireTile) {
                tile.renderFire(g);
            }
    }

    protected void resetFire(){
        for (Tile tile : fireTile) {
            tile.setInstantKill(false);
        }
        fireTile.clear();
    }

    public void setRollsLeft(int i){
        this.rollsLeft=i;
    }

    public boolean getWon(){
        return this.won;
    }
}
